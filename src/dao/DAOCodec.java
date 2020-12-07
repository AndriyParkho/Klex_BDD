package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.Codec;

public class DAOCodec extends DAO<Codec> {

    @Override
    public void create(Codec codec) throws SQLException {
        final String query = "INSERT INTO Codec VALUES (?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, codec.getNom());
            statement.setString(2, codec.getType());
            statement.executeUpdate();
        }
    }

    public void createOrUpdate(Codec codec) throws SQLException {
        try {
            this.create(codec);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(codec + " est déjà dans la BDD.");
            }
        }
    }

    @Override
    public ResultSet find(Codec codec) throws SQLException {
        return this.find(codec.getNom(), codec.getType());
    }

    public ResultSet find(String nomCodec, String typeCodec) throws SQLException {
        final String query = String.format("SELECT * FROM Codec WHERE nomCodec = '%s' AND typeCodec = '%s'", nomCodec,
                typeCodec);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(String nomCodec, String typeCodec) throws SQLException {
        final String query = String.format("DELETE FROM Codec WHERE nomCodec = '%s' AND typeCodec = '%s'", nomCodec,
                typeCodec);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}