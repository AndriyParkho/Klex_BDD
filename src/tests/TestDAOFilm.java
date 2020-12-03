package tests;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOCategorieFilm;
import dao.DAOFactory;
import dao.DAOFilm;
import tables.Artiste;
import tables.CategorieFilm;
import tables.Film;
import tables.ImgExtraiteFilm;

public class TestDAOFilm {
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

            DAOCategorieFilm categorieFilmDAO = DAOFactory.getCategorieFilmDAO();

            System.out.println("\nAvant création d'une catégorie :");
            System.out.println(categorieFilmDAO.find("horreur"));
            System.out.println(categorieFilmDAO.find("comédie"));

            CategorieFilm categorieFilm = new CategorieFilm("comédie");
            categorieFilmDAO.create(categorieFilm);
            connection.commit();

            System.out.println("\nAprès création d'une catégorie :");
            System.out.println(categorieFilmDAO.find("horreur"));
            System.out.println(categorieFilmDAO.find("comédie"));

            categorieFilm = new CategorieFilm("horreur");
            categorieFilmDAO.create(categorieFilm);
            connection.commit();

            System.out.println("\nAprès création d'une catégorie :");
            System.out.println(categorieFilmDAO.find("horreur"));
            System.out.println(categorieFilmDAO.find("comédie"));

            categorieFilm.setCategorie("action");
            categorieFilmDAO.update(categorieFilm);
            connection.commit();

            System.out.println("\nAprès update d'une catégorie :");
            System.out.println(categorieFilmDAO.find("horreur"));
            System.out.println(categorieFilmDAO.find("comédie"));
            System.out.println(categorieFilmDAO.find("action"));

            categorieFilm.setCategorie("comédie");
            categorieFilmDAO.delete(categorieFilm);
            connection.commit();

            System.out.println("\nAprès delete d'une catégorie :");
            System.out.println(categorieFilmDAO.find("horreur"));
            System.out.println(categorieFilmDAO.find("comédie"));
            System.out.println(categorieFilmDAO.find("action"));

            JDBCUtilities.selectAll(connection, "categorieFilm");

            Film film = new Film();
            film.setAgeMin(18);
            film.setAnneeSortie(Date.valueOf("2015-11-23"));
            film.setResume("BLABLABLA");
            film.setTitreFilm("Le Dernier Voeux");
            film.setUrlAffiche("http://urlaffiche");

            film.addCategorie(categorieFilm);
            film.addCategorie(new CategorieFilm("horreur"));

            Artiste artiste = new Artiste();
            artiste.setNom("Calvin Harris");
            artiste.setDateNaissance(Date.valueOf("2014-11-04"));
            artiste.setUrlPhoto("https://www.journaldugeek.com/content/uploads/2019/06/supermariorunta.jpg");
            artiste.setSpecialite("Chanteur");
            artiste.setBiographie("Plus besoin de présenter ce chanteur !!");
            film.addArtiste(artiste, "acteur");

            Artiste newArtiste = new Artiste();
            newArtiste.setDateNaissance(null);
            newArtiste.setBiographie(null);
            newArtiste.setNom("Théo Manfredi");
            newArtiste.setUrlPhoto("https://theo.jpg");
            newArtiste.setSpecialite("Manager");
            film.addArtiste(newArtiste, "rédacteur");

            ImgExtraiteFilm imgExtraiteFilm = new ImgExtraiteFilm();
            imgExtraiteFilm.setUrlImg("https://urlrandom");
            imgExtraiteFilm.setAnneeSortie(film.getAnneeSortie());
            imgExtraiteFilm.setTitreFilm(film.getTitreFilm());
            film.addImgExtraiteFilm(imgExtraiteFilm);

            ImgExtraiteFilm newImgExtraiteFilm = new ImgExtraiteFilm();
            newImgExtraiteFilm.setUrlImg("https://anotherurlrandom");
            newImgExtraiteFilm.setAnneeSortie(film.getAnneeSortie());
            newImgExtraiteFilm.setTitreFilm(film.getTitreFilm());
            film.addImgExtraiteFilm(newImgExtraiteFilm);
            System.out.println(film);

            JDBCUtilities.selectAll(connection, "CategorieFilm");
            JDBCUtilities.selectAll(connection, "FilmAPourCategorie");

            DAOFilm filmDAO = DAOFactory.getFilmDAO();
            filmDAO.create(film);
            connection.commit();

            System.out.println("\nAprès création d'un film :");
            System.out.println(filmDAO.find(film));
            JDBCUtilities.selectAll(connection, "ImgExtraiteFilm");
            JDBCUtilities.selectAll(connection, "Film");
            JDBCUtilities.selectAll(connection, "CategorieFilm");
            JDBCUtilities.selectAll(connection, "FilmAPourCategorie");

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
