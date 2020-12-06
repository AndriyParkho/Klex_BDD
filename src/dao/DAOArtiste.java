package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.Artiste;

public class DAOArtiste {

    public void create(Artiste artiste) throws SQLException {
        final String query = "INSERT INTO Artiste VALUES (idArtiste_seq.nextval, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, artiste.getNom());
            statement.setDate(2, Date.valueOf(artiste.getDateNaissance()));
            statement.setString(3, artiste.getUrlPhoto());
            statement.setString(4, artiste.getSpecialite());
            statement.setString(5, artiste.getBiographie());

            statement.executeUpdate();
        }
    }

    public void find(long idArtiste) throws SQLException {
        final String query = String.format("SELECT * FROM Artiste WHERE idArtiste = %ld", idArtiste);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);       
    }

    public void delete(long idArtiste) throws SQLException {
        final String query = String.format("DELETE FROM Artiste WHERE idArtiste = %ld", idArtiste);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
