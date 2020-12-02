package tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.JDBCUtilities;
import connections.ConnectionOracle;
import dao.DAOCodec;
import tables.Codec;

public class TestDAOCodec {
    static List<String> tables = List.of("Flux", "FluxTexte", "FluxAudio", "FluxVideo", "Dept", "Emp",
            "PisteAPourCategorie", "Album", "AlbumAPourCategorie", "Codec", "Client", "SupporteCodec",
            "CategorieMusique", "Artiste", "APourRole", "Piste", "APourInstrument", "Utilisateur", "Fichier", "Film",
            "FilmAPourCategorie", "EstUnFilm", "EstUnePiste", "ImgExtraiteFilm", "CategorieFilm", "APourCategorie");

    public static void main(String[] args) {
        try {
            Connection connection = ConnectionOracle.getInstance();

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

            DAOCodec codecDAO = new DAOCodec();

            System.out.println("\nAvant création d'un codec :");
            System.out.println(codecDAO.find("MPEG2", "video"));

            Codec codec = new Codec("MPEG2", "video");
            Codec codec2 = new Codec("ACC", "audio");
            codec = codecDAO.create(codec);
            codec2 = codecDAO.create(codec2);

            System.out.println("\nAprès création d'un codec :");
            System.out.println(codecDAO.find("MPEG2", "video"));   
            System.out.println(codecDAO.find("ACC", "audio"));   

            JDBCUtilities.selectAll(connection, "Codec");

            codecDAO.delete(codec);

            System.out.println("\nAprès suppression d'un codec :");
            JDBCUtilities.selectAll(connection, "Codec");

            codec2.setNom("ACC3");
            codecDAO.update(codec2);

            System.out.println("\nAprès update d'un codec :");
            JDBCUtilities.selectAll(connection, "Codec");     

        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        }
    }
}
