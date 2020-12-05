package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;

import connections.JDBCUtilities;
import model.Album;
import model.CategorieMusique;

public class DAOAlbum extends DAO<Album> {

    @Override
    public void create(Album album) throws SQLException {
        final String insertAlbumQuery = "INSERT INTO Album (idAlbum, titreAlbum, nomGroupe, dateSortieAlbum, urlImagePochette) VALUES (idAlbum_seq.nextval, ?, ?, ?, ?)";

        /* String queryId = "SELECT idAlbum_seq.nextval from DUAL";
        int nextID_from_seq = 0;
        try (ResultSet rs = this.connection.prepareStatement(queryId).executeQuery()) {
            if (rs.next()) {
                nextID_from_seq = rs.getInt(1);
                album.setId(nextID_from_seq);
            }
        }
        System.out.println(nextID_from_seq); */

        System.out.println("Statement Album\n");
        try (PreparedStatement statementAlbum = this.connection.prepareStatement(insertAlbumQuery)) {
            statementAlbum.setString(1, album.getTitre());
            statementAlbum.setString(2, album.getGroupe());
            statementAlbum.setDate(3, album.getDateSortie());
            statementAlbum.setString(4, album.getUrlImagePochette());

            statementAlbum.executeUpdate();
        }
        // on doit créer les catégories
        for (CategorieMusique categorieMusique : album.getCategoriesMusique()) {
            System.out.println("Statement CategorieMusique\n");
            DAOFactory.getCategorieMusiqueDAO().createOrUpdate(categorieMusique);
        }
        // connection.commit(); // NECESSARY because AlbumAPourCategorie references CategorieMusique.typeCategorieMusique and Album.idAlbum ?

        for (CategorieMusique categorieMusique : album.getCategoriesMusique()) {
            // insertion dans la table intermédiaire, c'est un nouvel album donc on est sur
            // que le couple n'existe pas
            createAlbumAPourCategorieQuery(album, categorieMusique);
        }
    }

    @Override
    public void createOrUpdate(Album album) throws SQLException {
        try {
            this.create(album);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(album + " est déjà dans la BDD. On l'update.");
                this.update(album);
            }
        }
    }

    @Override
    public Album find(final Album album) throws SQLException {
        return this.find(album.getId());
    }

    public Album find(final long id) throws SQLException {
        Album album = null;
        final String query = "SELECT * FROM Album LEFT JOIN AlbumAPourCategorie ON Album.idAlbum = AlbumAPourCategorie.idAlbum AND Album.idAlbum = "
                + id
                + " INNER JOIN CategorieMusique ON CategorieMusique.typeCategorieMusique = AlbumAPourCategorie.typeCategorieMusique";
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                final HashSet<CategorieMusique> categoriesMusique = new HashSet<CategorieMusique>();

                rs.beforeFirst();
                while (rs.next() && rs.getString("typeCategorieMusique") != null) {
                    categoriesMusique
                            .add(DAOFactory.getCategorieMusiqueDAO().find(rs.getString("typeCategorieMusique")));
                }
                // on se replace
                rs.first(); 

                album = new Album(id, rs.getString("titreAlbum"), rs.getString("nomGroupe"),
                        rs.getDate("dateSortieAlbum"), rs.getString("urlImagePochette"), categoriesMusique);
            }
        }
        connection.commit();

        return album;
    }

    @Override
    public void update(Album album) throws SQLException {
        final String query = "UPDATE Album SET titreAlbum = '" + album.getTitre() + "', nomGroupe = '"
                + album.getGroupe() + "', dateSortieAlbum = TO_DATE('" + album.getDateSortie()
                + "', 'YYYY-MM-DD'), urlImagePochette = '" + album.getUrlImagePochette()
                + "' WHERE idAlbum = " + album.getId();
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            final int nbRowsAffected = statement.executeUpdate();
            if (nbRowsAffected != 1) {
                throw new SQLException("not only one row affected");
            }
        }
        for (CategorieMusique categorieMusique : album.getCategoriesMusique()) {
            // Si la catégorie n'existe pas, on la crèe
            DAOFactory.getCategorieMusiqueDAO().createOrUpdate(categorieMusique);
        }

        final String deleteAlbumAPourCategorieQuery = "DELETE FROM AlbumAPourCategorie WHERE idAlbum = " + album.getId();
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(deleteAlbumAPourCategorieQuery);
        for (CategorieMusique categorieMusique : album.getCategoriesMusique()) {
            // si l'album n'est pas relié à la catégorie, on le relie
            createOrUpdateAlbumAPourCategorieQuery(album, categorieMusique);
        }
    }

    @Override
    public void delete(final Album album) throws SQLException {
        final String query = "DELETE FROM Album WHERE idAlbum = " + album.getId();
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    private void createAlbumAPourCategorieQuery(Album album, CategorieMusique categorieMusique) throws SQLException {
        final String insertAlbumAPourCategorieQuery = "INSERT INTO AlbumAPourCategorie VALUES (idAlbum_seq.currval, ?)";
        try (PreparedStatement statementAlbumAPourCategorie = this.connection
                .prepareStatement(insertAlbumAPourCategorieQuery)) {
            // statementAlbumAPourCategorie.setLong(1, album.getId());
            statementAlbumAPourCategorie.setString(1, categorieMusique.getCategorie());
            statementAlbumAPourCategorie.executeUpdate();
        }
    }

    private void createOrUpdateAlbumAPourCategorieQuery(Album album, CategorieMusique categorieMusique)
            throws SQLException {
        try {
            createAlbumAPourCategorieQuery(album, categorieMusique);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            }
        }
    }
}
