package tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOClient;
import dao.DAOCodec;
import dao.DAOFactory;
import tables.Client;
import tables.Codec;

public class TestDAOClient {
    static List<String> tables = List.of("Flux", "FluxTexte", "FluxAudio", "FluxVideo", "Dept", "Emp",
            "PisteAPourCategorie", "Album", "AlbumAPourCategorie", "Codec", "Client", "SupporteCodec",
            "CategorieMusique", "Artiste", "APourRole", "Piste", "APourInstrument", "Utilisateur", "Fichier", "Film",
            "FilmAPourCategorie", "EstUnFilm", "EstUnePiste", "ImgExtraiteFilm", "CategorieFilm", "APourCategorie");

    public static void main(String[] args) {
        Connection connection = ConnectionOracle.getInstance();
        try {
            // Initialize the script runner
            ScriptRunner sr = new ScriptRunner(connection);
            sr.setEscapeProcessing(false);

            // Drop existing tables
            try (Statement statement = connection.createStatement()) {
                for (String tableName : tables) {
                    statement.executeUpdate(JDBCUtilities.dropIfExist(tableName));
                    connection.commit();
                }
            }
            JDBCUtilities.loadFile(sr, "ressources/CreateTables.sql");

            DAOCodec codecDAO = DAOFactory.getCodecDAO();

            System.out.println("\nAvant création d'un codec :");
            System.out.println(codecDAO.find("MPEG2", "video"));

            Codec codec = new Codec("MPEG2", "video");
            Codec codec2 = new Codec("ACC", "audio");
            codecDAO.create(codec);
            codecDAO.create(codec2);
            connection.commit();

            System.out.println("\nAprès création d'un codec :");
            System.out.println(codecDAO.find("MPEG2", "video"));
            System.out.println(codecDAO.find("ACC", "audio"));

            JDBCUtilities.selectAll(connection, "Codec");

            codecDAO.delete(codec);
            connection.commit();

            System.out.println("\nAprès suppression d'un codec :");
            System.out.println(codecDAO.find("MPEG2", "video"));
            System.out.println(codecDAO.find("ACC", "audio"));
            JDBCUtilities.selectAll(connection, "Codec");

            codec2.setNom("ACC3");
            codecDAO.update(codec2);
            connection.commit();

            System.out.println("\nAprès update d'un codec :");
            System.out.println(codecDAO.find("MPEG2", "video"));
            System.out.println(codecDAO.find("ACC", "audio"));
            JDBCUtilities.selectAll(connection, "Codec");

            codec.setType("bad_type");

            System.out.println("\nEssaie d'insertion d'un codec n'ayant pas le bon type :");
            System.out.println(codec);

            try {
                codecDAO.create(codec);
                connection.commit();
            } catch (SQLIntegrityConstraintViolationException e) {
                JDBCUtilities.printSQLException(e);
            }

            JDBCUtilities.selectAll(connection, "Codec");

            DAOClient clientDAO = DAOFactory.getClientDAO();
            Client client = new Client();
            client.setMarque("Sony");
            client.setModele("M5");
            client.setLargeurMax(400);
            client.setHauteurMax(100);
            codec.setType("audio");
            client.addCodec(codec);

            clientDAO.create(client);
            connection.commit();

            System.out.println("\nAprès création d'un client :");
            System.out.println(clientDAO.find("Sony", "M5"));

            client.addCodec(new Codec("MP3", "audio"));
            clientDAO.update(client);
            connection.commit();

            System.out.println("\nAprès update d'un client :");
            System.out.println(clientDAO.find("Sony", "M5"));

            client.addCodec(new Codec("MP4", "video"));
            clientDAO.createOrUpdate(client);
            connection.commit();

            System.out.println("\nAprès update d'un client :");
            System.out.println(clientDAO.find("Sony", "M5"));
            JDBCUtilities.selectAll(connection, "Codec");

            client.setCodecs(new HashSet<Codec>());
            client.addCodec(new Codec("ACC3", "audio"));
            clientDAO.createOrUpdate(client);
            connection.commit();

            System.out.println("\nAprès update d'un client :");
            System.out.println(clientDAO.find("Sony", "M5"));
            JDBCUtilities.selectAll(connection, "Codec");
            JDBCUtilities.selectAll(connection, "SupporteCodec");

            codecDAO.delete(codec);
            connection.commit();

            System.out.println("\nAprès delete d'un codec :");
            JDBCUtilities.selectAll(connection, "Codec");
            JDBCUtilities.selectAll(connection, "Client");
            JDBCUtilities.selectAll(connection, "SupporteCodec");
            System.out.println(clientDAO.find("Sony", "M5"));

            clientDAO.delete(client);
            connection.commit();

            System.out.println("\nAprès delete d'un client :");
            JDBCUtilities.selectAll(connection, "Codec");
            JDBCUtilities.selectAll(connection, "Client");
            JDBCUtilities.selectAll(connection, "SupporteCodec");
            System.out.println(clientDAO.find("Sony", "M5"));

        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep) {
                    JDBCUtilities.printSQLException(excep);
                }
            }
        }
    }
}
