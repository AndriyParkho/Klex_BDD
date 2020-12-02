package tests;

import java.sql.Connection;
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

            DAOFichier fichierDAO = new DAOFichier();
            DAOUtilisateur utilisateurDAO = new DAOUtilisateur();

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setEmail("theo.manfredi@grenoble-inp.fr");
            utilisateur.setAge(21);
            utilisateur.setNom("Manfredi");
            utilisateur.setPrenom("Théo");
            utilisateur.setLangueDiffusion("Français");
            utilisateur.setCode(1234);

            utilisateur = utilisateurDAO.create(utilisateur); // A COMMENTER pour tester la création d'un utilisateur par défaut

            Fichier fichier = new Fichier();
            fichier.setDateDepot("13/04/2019");
            fichier.setTaille(76000);
            fichier.setEmail("theo.manfredi@grenoble-inp.fr");

            fichier = fichierDAO.create(fichier);

            System.out.println("\nAprès création d'un fichier :");
            System.out.println(fichierDAO.find(fichier.getId()));
            System.out.println(utilisateurDAO.find(fichier.getEmail()));

            JDBCUtilities.selectAll(connection, "Fichier");
            JDBCUtilities.selectAll(connection, "Utilisateur");

            System.out.println(utilisateur);

        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        }
    }
}