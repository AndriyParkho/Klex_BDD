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
            statement.setString(1, codec.getNomCodec());
            statement.setString(2, codec.getTypeCodec());

            statement.executeUpdate();
        }
    }

    public void find(String nomCodec, String typeCodec) throws SQLException {
        final String query = String.format("SELECT * FROM Codec WHERE nomCodec = '%s' AND typeCodec = '%s'", nomCodec, typeCodec);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(String nomCodec, String typeCodec) throws SQLException {
        final String query = String.format("DELETE FROM Codec WHERE nomCodec = '%s' AND typeCodec = '%s'", nomCodec, typeCodec);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}