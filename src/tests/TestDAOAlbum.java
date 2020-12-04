package tests;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOAlbum;
import dao.DAOCategorieMusique;
import dao.DAOFactory;
import model.Album;
import model.CategorieMusique;

public class TestDAOAlbum {
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

            DAOCategorieMusique categorieMusiqueDAO = DAOFactory.getCategorieMusiqueDAO();

            System.out.println("\nAvant création d'une catégorie :");
            System.out.println(categorieMusiqueDAO.find("pop"));
            System.out.println(categorieMusiqueDAO.find("EDM"));

            CategorieMusique categorieMusique = new CategorieMusique("pop");
            categorieMusiqueDAO.create(categorieMusique);
            connection.commit();

            System.out.println("\nAprès création d'une catégorie :");
            System.out.println(categorieMusiqueDAO.find("pop"));
            System.out.println(categorieMusiqueDAO.find("EDM"));

            categorieMusique = new CategorieMusique("EDM");
            categorieMusiqueDAO.create(categorieMusique);
            connection.commit();

            System.out.println("\nAprès création d'une catégorie :");
            System.out.println(categorieMusiqueDAO.find("pop"));
            System.out.println(categorieMusiqueDAO.find("EDM"));

            categorieMusique.setCategorie("electro");
            categorieMusiqueDAO.update(categorieMusique);
            connection.commit();

            System.out.println("\nAprès update d'une catégorie :");
            System.out.println(categorieMusiqueDAO.find("pop"));
            System.out.println(categorieMusiqueDAO.find("EDM"));
            System.out.println(categorieMusiqueDAO.find("electro"));

            categorieMusique.setCategorie("pop");
            categorieMusiqueDAO.delete(categorieMusique);
            connection.commit();

            System.out.println("\nAprès delete d'une catégorie :");
            System.out.println(categorieMusiqueDAO.find("pop"));
            System.out.println(categorieMusiqueDAO.find("EDM"));
            System.out.println(categorieMusiqueDAO.find("electro"));

            JDBCUtilities.selectAll(connection, "CategorieMusique");

            DAOAlbum albumDAO = new DAOAlbum();

            Album album = new Album();
            album.setTitre("Motion");
            album.setGroupe("Calvin Harris");
            album.setDateSortie(Date.valueOf("2014-11-03"));
            album.setUrlImagePochette("https://fr.wikipedia.org/wiki/Motion_(album_de_Calvin_Harris)");
            album.addCategorieMusique(new CategorieMusique("pop"));
            album.addCategorieMusique(new CategorieMusique("electro"));

            albumDAO.create(album);
            connection.commit();

            System.out.println("\nAprès create d'un album :");
            System.out.println(albumDAO.find(album.getId()));

            JDBCUtilities.selectAll(connection, "CategorieMusique");
            JDBCUtilities.selectAll(connection, "AlbumAPourCategorie");

            album.setGroupe("Théo le Guitariste");
            album.setUrlImagePochette("none");
            albumDAO.update(album);
            connection.commit();

            System.out.println("\nAprès update d'un album :");
            System.out.println(albumDAO.find(album.getId()));

            album.setTitre("18 Months");
            album.setGroupe("Calvin Harris");
            album.setDateSortie(Date.valueOf("2012-10-26"));
            album.setUrlImagePochette("https://fr.wikipedia.org/wiki/18_Months");
            album.setCategoriesMusique(new HashSet<CategorieMusique>());
            album.addCategorieMusique(new CategorieMusique("pop"));
            album.addCategorieMusique(new CategorieMusique("electro-dance"));
            album.addCategorieMusique(new CategorieMusique("EDM"));

            albumDAO.create(album);
            connection.commit();

            System.out.println("\nAprès create d'un album :");
            JDBCUtilities.selectAll(connection, "Album");
            JDBCUtilities.selectAll(connection, "CategorieMusique");
            JDBCUtilities.selectAll(connection, "AlbumAPourCategorie");

            albumDAO.delete(albumDAO.find(1));
            connection.commit();

            System.out.println("\nAprès delete d'un album :");
            JDBCUtilities.selectAll(connection, "Album");
            JDBCUtilities.selectAll(connection, "CategorieMusique");
            JDBCUtilities.selectAll(connection, "AlbumAPourCategorie");

            album = albumDAO.find(2);
            album.setCategoriesMusique(new HashSet<CategorieMusique>());
            album.addCategorieMusique(new CategorieMusique("classique"));
            albumDAO.update(album);
            connection.commit();

            System.out.println("\nAprès update d'une catégorie d'un album :");
            System.out.println(albumDAO.find(album));
            JDBCUtilities.selectAll(connection, "Album");
            JDBCUtilities.selectAll(connection, "CategorieMusique");
            JDBCUtilities.selectAll(connection, "AlbumAPourCategorie");

        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);

            try {
                JDBCUtilities.selectAll(connection, "Album");
                JDBCUtilities.selectAll(connection, "CategorieMusique");
                JDBCUtilities.selectAll(connection, "AlbumAPourCategorie");
            } catch (SQLException ex) {
            }
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
