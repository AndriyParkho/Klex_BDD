package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connections.JDBCUtilities;
import tables.Album;
import tables.CategorieMusique;

public class DAOAlbum extends DAO<Album> {

    @Override
    public Album create(Album album) throws SQLException {
        String insertAlbumQuery = "INSERT INTO Album (titreAlbum, nomGroupe, dateSortieAlbum, urlImagePochette) VALUES (?, ?, TO_DATE(?, 'dd/mm/yyyy'), ?)";

        try (PreparedStatement statementAlbum = this.connection.prepareStatement(insertAlbumQuery,
                new String[] { "idAlbum" })) {
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

                DAO<CategorieMusique> categorieMusiqueDAO = new DAOCategorieMusique();
                try {
                    categorieMusique = categorieMusiqueDAO.create(categorieMusique);
                } catch (SQLException e) {
                    // si la catégorie existe déjà alors ne rien faire
                    if (!e.getSQLState().equalsIgnoreCase("23000")) {
                        JDBCUtilities.printSQLException(e);
                    }
                }

                // insertion dans la table intermédiaire
                String insertAlbumAPourCategorieQuery = "INSERT INTO AlbumAPourCategorie VALUES (?, ?)";
                try (PreparedStatement statementAlbumAPourCategorie = this.connection
                        .prepareStatement(insertAlbumAPourCategorieQuery)) {
                    statementAlbumAPourCategorie.setLong(1, createdId);
                    statementAlbumAPourCategorie.setString(2, categorieMusique.getCategorie());
                    statementAlbumAPourCategorie.executeUpdate();
                    connection.commit();
                }
            }
            album = this.find(createdId);
        }

        connection.commit();

        return album;
    }

    public Album find(long id) throws SQLException {
        Album album = null;
        String query = "SELECT * FROM Album LEFT JOIN AlbumAPourCategorie ON Album.idAlbum = AlbumAPourCategorie.idAlbum AND Album.idAlbum = "
                + id
                + " INNER JOIN CategorieMusique ON CategorieMusique.typeCategorieMusique = AlbumAPourCategorie.typeCategorieMusique";
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                ArrayList<CategorieMusique> categoriesMusique = new ArrayList<CategorieMusique>();
                DAOCategorieMusique categorieMusiqueDAO = new DAOCategorieMusique();

                rs.beforeFirst();
                while (rs.next() && rs.getString("typeCategorieMusique") != null) {
                    categoriesMusique.add(categorieMusiqueDAO.find(rs.getString("typeCategorieMusique")));
                }
                // on se replace
                rs.first();

                album = new Album(id, rs.getString("titreAlbum"), rs.getString("nomGroupe"),
                        rs.getString("dateSortieAlbum"), rs.getString("urlImagePochette"), categoriesMusique);
            }
        }
        connection.commit();

        return album;
    }

    @Override
    public Album update(Album album) throws SQLException {
        String query = "UPDATE Album SET titreAlbum = '" + album.getTitre() + "', nomGroupe = '" + album.getGroupe()
                + "', dateSortieAlbum = TO_DATE('" + album.getDateSortie()
                + "', 'YYYY-MM-DD HH24:MI:SS'), urlImagePochette = '" + album.getUrlImagePochette()
                + "' WHERE idAlbum = " + album.getId();
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.executeUpdate();
            connection.commit();

            // on crè les liens pour les catégories de musiques
            // si elle(s) n'existe(nt) pas on le(s) crèe(s)
            for (CategorieMusique categorieMusique : album.getCategoriesMusique()) {
                DAOCategorieMusique categorieMusiqueDAO = new DAOCategorieMusique();
                // Si la catégorie n'existe pas, on la crèe
                try {
                    categorieMusique = categorieMusiqueDAO.create(categorieMusique);
                } catch (SQLException e) {
                    // si la catégorie existe déjà alors ne rien faire
                    if (!e.getSQLState().equalsIgnoreCase("23000")) {
                        JDBCUtilities.printSQLException(e);
                    }
                }
                // si l'album n'est pas relié à la catégorie, on le relie
                try {
                    String insertAlbumAPourCategorieQuery = "INSERT INTO AlbumAPourCategorie VALUES (?, ?)";
                    try (PreparedStatement statementAlbumAPourCategorie = this.connection
                            .prepareStatement(insertAlbumAPourCategorieQuery)) {
                        statementAlbumAPourCategorie.setLong(1, album.getId());
                        statementAlbumAPourCategorie.setString(2, categorieMusique.getCategorie());
                        statementAlbumAPourCategorie.executeUpdate();
                        connection.commit();
                    }
                } catch (SQLException e) {
                    // si le couple existe déjà dans AlbumAPourCategorie alors ne rien faire
                    if (!e.getSQLState().equalsIgnoreCase("23000")) {
                        JDBCUtilities.printSQLException(e);
                    }
                }
            }
            album = this.find(album.getId());
        }

        connection.commit();

        return album;
    }

    @Override
    public void delete(Album album) throws SQLException {
        String queryAlbum = "DELETE FROM Album WHERE idAlbum = " + album.getId();
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(queryAlbum);

        connection.commit();
    }

}
