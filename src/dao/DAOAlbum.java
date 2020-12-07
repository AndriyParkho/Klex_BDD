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
        final String query = "INSERT INTO Album VALUES (idAlbum_seq.nextval, ?, ?, ?, ?)";

        System.out.println("Statement Album\n");
        try (PreparedStatement statementAlbum = this.connection.prepareStatement(query)) {
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
