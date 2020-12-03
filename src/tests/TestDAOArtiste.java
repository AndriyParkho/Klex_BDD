package tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOArtiste;
import tables.Artiste;

public class TestDAOArtiste {
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

            DAOArtiste artisteDAO = new DAOArtiste();

            Artiste artiste = new Artiste();
            artiste.setNom("Calvin Harris");
            artiste.setDateNaissance("04/11/2014");
            artiste.setUrlPhoto("https://www.journaldugeek.com/content/uploads/2019/06/supermariorunta.jpg");
            artiste.setSpecialite("Chanteur");
            artiste.setBiographie("Plus besoin de présenter ce chanteur !!");

            artiste = artisteDAO.create(artiste);

            System.out.println("\nAprès création d'un artiste :");
            System.out.println(artisteDAO.find(artiste.getId()));

            artiste = new Artiste();
            artiste.setNom("Théo");
            artiste.setUrlPhoto("https://");
            artiste.setSpecialite("Programmeur");

            artiste = artisteDAO.create(artiste);

            System.out.println("\nAprès création d'un artiste :");
            System.out.println(artisteDAO.find(artiste.getId()));
            
            JDBCUtilities.selectAll(connection, "Artiste");

            artiste = artisteDAO.find(1);
            artiste.setDateNaissance("");
            artiste = artisteDAO.update(artiste);

            System.out.println("\nAprès update d'un artiste :");
            System.out.println(artisteDAO.find(artiste.getId()));

            artiste.setBiographie(null);
            artiste = artisteDAO.update(artiste);

            System.out.println("\nAprès update d'un artiste :");
            System.out.println(artisteDAO.find(artiste.getId()));

            artisteDAO.delete(artiste);

            System.out.println("\nAprès delete d'un artiste :");
            System.out.println(artisteDAO.find(artiste.getId()));

            JDBCUtilities.selectAll(connection, "Artiste");
            JDBCUtilities.selectAll(connection, "APourRole");
            JDBCUtilities.selectAll(connection, "APourInstrument");

        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        }
    }
}
