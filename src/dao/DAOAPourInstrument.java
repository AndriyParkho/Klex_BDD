package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.APourInstrument;

public class DAOAPourInstrument {

    public void create(APourInstrument aPourInstrument) throws SQLException {
        final String query = "INSERT INTO APourInstrument VALUES (?, idArtiste_seq.currval, idAlbum_seq.currval, idPiste_seq.currval)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, aPourInstrument.getInstrument());
            
            statement.executeUpdate();
        }
    }

    public void find(long idArtiste, long idPiste) throws SQLException {
        final String query = String.format("SELECT * FROM APourInstrument WHERE idArtiste = %ld AND idPiste = %ld", idArtiste, idPiste);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(long idArtiste, long idPiste) throws SQLException {
        final String query = String.format("DELETE FROM APourInstrument WHERE idArtiste = %ld AND idPiste = %ld", idArtiste, idPiste);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
