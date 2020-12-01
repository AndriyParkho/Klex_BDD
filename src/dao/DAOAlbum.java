package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tables.Album;
import tables.CategorieMusique;

public class DAOAlbum extends DAO<Album> {

    @Override
    public Album create(Album album) throws SQLException {
        this.connection.setAutoCommit(false);
        this.connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        String insertAlbumQuery = "INSERT INTO Album (titreAlbum, nomGroupe, dateSortieAlbum, urlImagePochette) VALUES (?, ?, to_date(?, 'dd/mm/yyyy'), ?)";

        try (PreparedStatement statementAlbum = this.connection.prepareStatement(insertAlbumQuery, Statement.RETURN_GENERATED_KEYS)) {
            statementAlbum.setString(1, album.getTitre());
            statementAlbum.setString(2, album.getGroupe());
            statementAlbum.setString(3, album.getDateSortie());
            statementAlbum.setString(4, album.getUrlImagePochette());

            int nbRowsAffected = statementAlbum.executeUpdate();
            Long createdId = null;
            if (nbRowsAffected == 1) {
                try (ResultSet rs = statementAlbum.getGeneratedKeys()) {
                    rs.next();
                    createdId = rs.getLong(1);
                    album.setId(createdId);
                }
            } else {
                throw new SQLException("no rows affected");
            }

            // on doit créer les liens entre albums et catégories
            for (CategorieMusique categorieMusique : album.getCategoriesMusique()) {
                // si pas de categorie
                if (categorieMusique.getCategorie() == null) {
                    DAO<CategorieMusique> categorieMusiqueDAO = new DAOCategorieMusique();
                    categorieMusique = categorieMusiqueDAO.create(categorieMusique);
                }

                String insertAlbumAPourCategorieQuery = "INSERT INTO AlbumAPourCategorie VALUES (?, ?)";
                try (PreparedStatement statementAlbumAPourCategorie = this.connection.prepareStatement(insertAlbumAPourCategorieQuery)) {
                    statementAlbumAPourCategorie.setLong(1, createdId);
                    statementAlbumAPourCategorie.setString(2, categorieMusique.getCategorie());
                    statementAlbumAPourCategorie.executeUpdate();
                }
            }

        }

        connection.commit();
        connection.setAutoCommit(true);

        return album;
    }

    public Album find(long id) throws SQLException {
        Album album = null;
        String query = "SELECT * FROM Album WHERE idAlbum = " + id;
        try (ResultSet rs = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les attributs de la ligne
            if (rs.first()) {
                ArrayList<CategorieMusique> categoriesMusique = new ArrayList<CategorieMusique>();
                DAOCategorieMusique categorieMusiqueDAO = new DAOCategorieMusique();

                rs.beforeFirst();
                while (rs.next() && rs.getString("typeCategorieMusique") != null) {
                    categoriesMusique.add(categorieMusiqueDAO.find(rs.getString("typeCategorieMusique")));
                }

                album = new Album(id, rs.getString("titreAlbum"), rs.getString("nomGroupe"), rs.getString("dateSortie"), rs.getString("urlImagePochette"), categoriesMusique);
            }
        }

        return album;
    }

    @Override
    public Album update(Album obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Album obj) {
        // TODO Auto-generated method stub

    }

}
