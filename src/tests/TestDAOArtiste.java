package tests;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOArtiste;
import model.Artiste;

public class TestDAOArtiste {
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

            DAOArtiste artisteDAO = new DAOArtiste();

            Artiste artiste = new Artiste();
            artiste.setNom("Calvin Harris");
            artiste.setDateNaissance(Date.valueOf("2014-11-04"));
            artiste.setUrlPhoto("https://www.journaldugeek.com/content/uploads/2019/06/supermariorunta.jpg");
            artiste.setSpecialite("Chanteur");
            artiste.setBiographie("Plus besoin de présenter ce chanteur !!");

            artisteDAO.create(artiste);
            connection.commit();

            System.out.println("\nAprès création d'un artiste :");
            System.out.println(artisteDAO.find(artiste.getId()));

            artiste = new Artiste();
            artiste.setNom("Théo");
            artiste.setUrlPhoto("https://");
            artiste.setSpecialite("Programmeur");

            artisteDAO.create(artiste);
            connection.commit();

            System.out.println("\nAprès création d'un artiste :");
            System.out.println(artisteDAO.find(artiste.getId()));
            
            JDBCUtilities.selectAll(connection, "Artiste");

            artiste = artisteDAO.find(1);
            artiste.setDateNaissance(null);
            artisteDAO.update(artiste);
            connection.commit();

            System.out.println("\nAprès update d'un artiste :");
            System.out.println(artisteDAO.find(artiste.getId()));

            artiste.setBiographie(null);
            artisteDAO.update(artiste);
            connection.commit();

            System.out.println("\nAprès update d'un artiste :");
            System.out.println(artisteDAO.find(artiste.getId()));

            artisteDAO.delete(artiste);
            connection.commit();

            System.out.println("\nAprès delete d'un artiste :");
            System.out.println(artisteDAO.find(2));

            JDBCUtilities.selectAll(connection, "Artiste");
            JDBCUtilities.selectAll(connection, "APourRole");
            JDBCUtilities.selectAll(connection, "APourInstrument");

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
        } finally {
            ConnectionOracle.closeInstance();
        }
    }
}
