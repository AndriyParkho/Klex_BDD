package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.APourInstrument;

public class DAOAPourInstrument extends DAO<APourInstrument> {

    @Override
    public void create(APourInstrument aPourInstrument) throws SQLException {
        final String query = "INSERT INTO APourInstrument VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, aPourInstrument.getInstrument());
            statement.setLong(2, aPourInstrument.getIdArtiste());
            statement.setLong(3, aPourInstrument.getIdAlbum());
            statement.setInt(4, aPourInstrument.getNumPiste());
            statement.executeUpdate();
        }
    }

    public void createOrUpdate(APourInstrument aPourInstrument) throws SQLException {
        try {
            this.create(aPourInstrument);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(aPourInstrument + " est déjà dans la BDD.");
            }
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
