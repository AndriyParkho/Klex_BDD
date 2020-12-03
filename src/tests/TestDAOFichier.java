package tests;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOFichier;
import dao.DAOUtilisateur;
import tables.Fichier;
import tables.Utilisateur;

public class TestDAOFichier {
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

            DAOFichier fichierDAO = new DAOFichier();
            DAOUtilisateur utilisateurDAO = new DAOUtilisateur();

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setEmail("theo.manfredi@grenoble-inp.fr");
            utilisateur.setAge(21);
            utilisateur.setNom("Manfredi");
            utilisateur.setPrenom("Théo");
            utilisateur.setLangueDiffusion("Français");
            utilisateur.setCode(1234);

            utilisateurDAO.create(utilisateur); // A COMMENTER pour tester la création d'un utilisateur par défaut
            connection.commit();

            Fichier fichier = new Fichier();
            fichier.setDateDepot(Date.valueOf("2019-04-13"));
            fichier.setTaille(76000);
            fichier.setEmail("theo.manfredi@grenoble-inp.fr");

            fichierDAO.create(fichier);
            connection.commit();

            System.out.println("\nAprès création d'un fichier :");
            System.out.println(fichierDAO.find(fichier.getId()));
            System.out.println(utilisateurDAO.find(fichier.getEmail()));
            JDBCUtilities.selectAll(connection, "Fichier");
            JDBCUtilities.selectAll(connection, "Utilisateur");

            fichier.setDateDepot(Date.valueOf("2017-01-05"));
            fichier.setTaille(35000);
            fichier.setEmail("me@gre.fr");

            fichierDAO.create(fichier);
            connection.commit();

            System.out.println("\nAprès création d'un fichier :");
            System.out.println(fichierDAO.find(fichier.getId()));
            System.out.println(utilisateurDAO.find(fichier.getEmail()));
            JDBCUtilities.selectAll(connection, "Fichier");
            JDBCUtilities.selectAll(connection, "Utilisateur");

            fichier.setEmail("theo.manfredi@grenoble-inp.fr"); 
            fichierDAO.update(fichier);
            connection.commit();

            System.out.println("\nAprès changement d'ownership (utilisateur existant) :");
            System.out.println(fichierDAO.find(fichier.getId()));
            System.out.println(utilisateurDAO.find(fichier.getEmail()));
            JDBCUtilities.selectAll(connection, "Fichier");
            JDBCUtilities.selectAll(connection, "Utilisateur");

            fichier.setEmail("new@gmail.com"); 
            fichierDAO.update(fichier);
            connection.commit();

            System.out.println("\nAprès changement d'ownership (utilisateur inexistant):");
            System.out.println(fichierDAO.find(fichier.getId()));
            System.out.println(utilisateurDAO.find(fichier.getEmail()));
            JDBCUtilities.selectAll(connection, "Fichier");
            JDBCUtilities.selectAll(connection, "Utilisateur");

            utilisateur.setEmail("new@gmail.com");
            utilisateurDAO.delete(utilisateur);
            connection.commit();

            System.out.println("\nAprès suppression de l'utilisateur (avec fichier(s)):");
            System.out.println(fichierDAO.find(fichier.getId()));
            System.out.println(utilisateurDAO.find(fichier.getEmail()));
            JDBCUtilities.selectAll(connection, "Fichier");
            JDBCUtilities.selectAll(connection, "Utilisateur");

            utilisateur.setEmail("me@gre.fr");
            utilisateurDAO.delete(utilisateur);
            connection.commit();

            System.out.println("\nAprès suppression de l'utilisateur (sans fichier(s)):");
            System.out.println(fichierDAO.find(fichier.getId()));
            System.out.println(utilisateurDAO.find(fichier.getEmail()));
            JDBCUtilities.selectAll(connection, "Fichier");
            JDBCUtilities.selectAll(connection, "Utilisateur");

            fichier = fichierDAO.find(1);
            fichierDAO.delete(fichier);
            connection.commit();

            System.out.println("\nAprès suppression du fichier :");
            System.out.println(fichierDAO.find(fichier.getId()));
            System.out.println(utilisateurDAO.find(fichier.getEmail()));
            JDBCUtilities.selectAll(connection, "Fichier");
            JDBCUtilities.selectAll(connection, "Utilisateur");

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
