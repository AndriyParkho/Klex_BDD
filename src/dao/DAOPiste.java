package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import model.Piste;

public class DAOPiste {

    public void create(Piste piste) throws SQLException {
        final String query = "INSERT INTO Piste VALUES (idPiste_seq.nextval, ?, ?, ?, ?, idFichier_seq.currval)";

        System.out.println("Statement Piste");
        try (PreparedStatement statementAlbum = this.connection.prepareStatement(query)) {
            statementAlbum.setInt(1, piste.getNumPiste());
            statementAlbum.setString(2, piste.getTitrePiste());
            statementAlbum.setInt(3, piste.getDureePiste());
            statementAlbum.setLong(4, piste.getIdAlbum());

            statementAlbum.executeUpdate();
        }
    }

    public void find(long idPiste, long idAlbum) throws SQLException {
        final String query = String.format("SELECT * FROM Piste WHERE idPiste = %ld AND idAlbum = %ld", idPiste, idAlbum);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(long idPiste, long idAlbum) throws SQLException {
        final String query = String.format("DELETE FROM Piste WHERE idPiste = %ld AND idAlbum = %ld", idPiste, idAlbum);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
