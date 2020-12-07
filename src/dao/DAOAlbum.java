package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.Album;

public class DAOAlbum extends DAO<Album> {

    @Override
    public void create(Album album) throws SQLException {
        final String insertAlbumQuery = "INSERT INTO Album (idAlbum, titreAlbum, nomGroupe, dateSortieAlbum, urlImagePochette) VALUES (idAlbum_seq.nextval, ?, ?, ?, ?)";

        String queryId = "SELECT idAlbum_seq.nextval from DUAL";
        int nextID_from_seq = 0;
        try (ResultSet rs = this.connection.prepareStatement(queryId).executeQuery()) {
            if (rs.next()) {
                nextID_from_seq = rs.getInt(1);
                album.setId(nextID_from_seq);
            }
        }
        System.out.println(nextID_from_seq);

        try (PreparedStatement statementAlbum = this.connection.prepareStatement(insertAlbumQuery)) {
            statementAlbum.setString(1, album.getTitre());
            statementAlbum.setString(2, album.getGroupe());
            statementAlbum.setDate(3, album.getDateSortie());
            statementAlbum.setString(4, album.getUrlImagePochette());
            statementAlbum.executeUpdate();
        }
    }

    public void find(long idAlbum) throws SQLException {
        final String query = String.format("SELECT * FROM Album WHERE idAlbum = %ld", idAlbum);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(long idAlbum) throws SQLException {
        final String query = String.format("DELETE FROM Album WHERE idAlbum = %ld", idAlbum);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

}
