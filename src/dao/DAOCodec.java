package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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