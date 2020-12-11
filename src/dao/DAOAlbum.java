package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.Album;

public class DAOAlbum extends DAO<Album> {

    @Override
    public void create(Album album) throws SQLException {
        // on check si l'album existe déjà
        boolean albumAlreadyExist = false;
        int nextID_from_seq = 0;
        try (ResultSet rs = this.find(album.getTitre(), album.getGroupe(), album.getDateSortie())) {
            if (rs.next()) {
                albumAlreadyExist = true;
                nextID_from_seq = rs.getInt(1);
                System.out.println("IdAlbum: " + nextID_from_seq);
                album.setId(nextID_from_seq);
            }
        }

        // si l'album n'existe pas on le crè
        if (!albumAlreadyExist) {
            String queryId = "SELECT idAlbum_seq.nextval from DUAL";
            try (ResultSet rs = this.connection.prepareStatement(queryId).executeQuery()) {
                if (rs.next()) {
                    nextID_from_seq = rs.getInt(1);
                    System.out.println("IdAlbum: " + nextID_from_seq);
                    album.setId(nextID_from_seq);
                }
            }

            final String insertAlbumQuery = "INSERT INTO Album VALUES (idAlbum_seq.currval, ?, ?, ?, ?)";
            try (PreparedStatement statementAlbum = this.connection.prepareStatement(insertAlbumQuery)) {
                statementAlbum.setString(1, album.getTitre());
                statementAlbum.setString(2, album.getGroupe());
                statementAlbum.setDate(3, album.getDateSortie());
                statementAlbum.setString(4, album.getUrlImagePochette());
                statementAlbum.executeUpdate();
            }
        }
    }

    public void createOrUpdate(Album album) throws SQLException {
        try {
            this.create(album);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(album + " est déjà dans la BDD.");
            }
        }
    }

    @Override
    public ResultSet find(Album album) throws SQLException {
        return this.find(album.getId());
    }

    public ResultSet find(long idAlbum) throws SQLException {
        final String query = String.format("SELECT * FROM Album WHERE idAlbum = %d", idAlbum);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public ResultSet find(String titreAlbum) throws SQLException {
        final String query = String.format("SELECT * FROM Album WHERE titreAlbum = '%s'", titreAlbum);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public ResultSet find(String titreAlbum, String groupe, Date dateSortie) throws SQLException {
        final String query = String.format(
                "SELECT * FROM Album WHERE titreAlbum = '%s' AND nomGroupe = '%s' AND dateSortieAlbum = TO_DATE('%s', 'YYYY-MM-DD')", titreAlbum,
                groupe, dateSortie);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(long idAlbum) throws SQLException {
        final String query = String.format("DELETE FROM Album WHERE idAlbum = %d", idAlbum);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

}
