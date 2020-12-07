package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.APourInstrument;

public class DAOAPourInstrument extends DAO<APourInstrument> {

    @Override
    public void create(APourInstrument aPourInstrument) throws SQLException {
        final String query = "INSERT INTO APourInstrument VALUES (?, idArtiste_seq.currval, idAlbum_seq.currval, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, aPourInstrument.getInstrument());
            statement.setInt(2, aPourInstrument.getNumPiste());

            statement.executeUpdate();
        }
    }

    @Override
    public ResultSet find(APourInstrument aPourInstrument) throws SQLException {
        return this.find(aPourInstrument.getIdArtiste(), aPourInstrument.getNumPiste());
    }

    public ResultSet find(long idArtiste, int numPiste) throws SQLException {
        final String query = String.format("SELECT * FROM APourInstrument WHERE idArtiste = %ld AND numPiste = %ld",
                idArtiste, numPiste);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(long idArtiste, int numPiste) throws SQLException {
        final String query = String.format("DELETE FROM APourInstrument WHERE idArtiste = %ld AND numPiste = %ld",
                idArtiste, numPiste);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
