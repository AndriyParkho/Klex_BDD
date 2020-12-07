package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import model.EstUnePiste;

public class DAOEstUnePiste {

    public void create(EstUnePiste estUnePiste) throws SQLException {
        final String query = "INSERT INTO EstUnePiste VALUES (idFichier_seq.currval, idPiste_seq.currval, idAlbum_seq.currval)";

        System.out.println("Statement EstUnePiste\n");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }

    public void find(long idFichier, long idPiste, long idAlbum) throws SQLException {
        final String query = String.format("SELECT * FROM EstUnePiste WHERE idFichier = %ld AND idPiste = %ld AND idAlbum = %ld", idFichier, idPiste, idAlbum);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(long idFichier, long idPiste, long idAlbum) throws SQLException {
        final String query = String.format("DELETE FROM EstUnePiste WHERE idFichier = %ld AND idPiste = %ld AND idAlbum = %ld", idFichier, idPiste, idAlbum);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
