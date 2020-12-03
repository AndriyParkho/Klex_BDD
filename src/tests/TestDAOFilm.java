package tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOCategorieFilm;
import dao.DAOFactory;
import dao.DAOImgExtraiteFilm;
import tables.CategorieFilm;
import tables.ImgExtraiteFilm;

public class TestDAOFilm {
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

            DAOCategorieFilm categorieFilmDAO = DAOFactory.getCategorieFilmDAO();

            System.out.println("\nAvant création d'une catégorie :");
            System.out.println(categorieFilmDAO.find("horreur"));
            System.out.println(categorieFilmDAO.find("comédie"));

            CategorieFilm categorieFilm = new CategorieFilm("comédie");
            categorieFilm = categorieFilmDAO.create(categorieFilm);

            System.out.println("\nAprès création d'une catégorie :");
            System.out.println(categorieFilmDAO.find("horreur"));
            System.out.println(categorieFilmDAO.find("comédie"));

            categorieFilm = new CategorieFilm("horreur");
            categorieFilm = categorieFilmDAO.create(categorieFilm);

            System.out.println("\nAprès création d'une catégorie :");
            System.out.println(categorieFilmDAO.find("horreur"));
            System.out.println(categorieFilmDAO.find("comédie"));

            categorieFilm.setCategorie("action");
            categorieFilm = categorieFilmDAO.update(categorieFilm);

            System.out.println("\nAprès update d'une catégorie :");
            System.out.println(categorieFilmDAO.find("horreur"));
            System.out.println(categorieFilmDAO.find("comédie"));
            System.out.println(categorieFilmDAO.find("action"));

            categorieFilm.setCategorie("comédie");
            categorieFilmDAO.delete(categorieFilm);

            System.out.println("\nAprès delete d'une catégorie :");
            System.out.println(categorieFilmDAO.find("horreur"));
            System.out.println(categorieFilmDAO.find("comédie"));
            System.out.println(categorieFilmDAO.find("action"));

            JDBCUtilities.selectAll(connection, "categorieFilm");

            DAOImgExtraiteFilm imgExtraiteFilmDAO = DAOFactory.getImgExtraiteFilmDAO();
            ImgExtraiteFilm imgExtraiteFilm = new ImgExtraiteFilm();

        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        }
    }
}
