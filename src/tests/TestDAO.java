package tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.JDBCUtilities;
import connections.ConnectionOracle;
import dao.DAOAlbum;
import dao.DAOCategorieMusique;
import tables.Album;
import tables.CategorieMusique;

public class TestDAO {
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
                }
            }
            JDBCUtilities.loadFile(sr, "ressources/CreateTables.sql");

            DAOCategorieMusique categorieMusiqueDAO = new DAOCategorieMusique();

            System.out.println("\nAvant création d'une catégorie :");
            System.out.println(categorieMusiqueDAO.find("pop"));
            System.out.println(categorieMusiqueDAO.find("EDM"));

            CategorieMusique categorieMusique = new CategorieMusique("pop");
            categorieMusique = categorieMusiqueDAO.create(categorieMusique);

            System.out.println("\nAprès création d'une catégorie :");
            System.out.println(categorieMusiqueDAO.find("pop"));
            System.out.println(categorieMusiqueDAO.find("EDM"));

            categorieMusique = new CategorieMusique("EDM");
            categorieMusique = categorieMusiqueDAO.create(categorieMusique);

            System.out.println("\nAprès création d'une catégorie :");
            System.out.println(categorieMusiqueDAO.find("pop"));
            System.out.println(categorieMusiqueDAO.find("EDM"));

            categorieMusique.setCategorie("electro");
            categorieMusique = categorieMusiqueDAO.update(categorieMusique);

            System.out.println("\nAprès update d'une catégorie :");
            System.out.println(categorieMusiqueDAO.find("pop"));
            System.out.println(categorieMusiqueDAO.find("EDM"));
            System.out.println(categorieMusiqueDAO.find("electro"));

            categorieMusique.setCategorie("pop");
            categorieMusiqueDAO.delete(categorieMusique);

            System.out.println("\nAprès delete d'une catégorie :");
            System.out.println(categorieMusiqueDAO.find("pop"));
            System.out.println(categorieMusiqueDAO.find("EDM"));
            System.out.println(categorieMusiqueDAO.find("electro"));

            JDBCUtilities.selectAll(connection, "CategorieMusique");

            DAOAlbum albumDAO = new DAOAlbum();
            
            Album album = new Album();
            album.setTitre("Motion");
            album.setGroupe("Calvin Harris");
            album.setDateSortie("03/11/2014");
            album.setUrlImagePochette("https://fr.wikipedia.org/wiki/Motion_(album_de_Calvin_Harris)");
            album.addCategorieMusique(new CategorieMusique("pop"));
            album.addCategorieMusique(new CategorieMusique("electro"));

            album = albumDAO.create(album);

            System.out.println("\nAprès create d'un album :");
            System.out.println(albumDAO.find(album.getId()));

            JDBCUtilities.selectAll(connection, "CategorieMusique");
            JDBCUtilities.selectAll(connection, "AlbumAPourCategorie");

        } catch (SQLException e) {
            System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);
        }
    }
}
