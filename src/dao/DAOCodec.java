package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import tables.Codec;

public class DAOCodec extends DAO<Codec> {

    @Override
    public Codec create(Codec codec) throws SQLException {
        final String query = "INSERT INTO Codec VALUES (?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, codec.getNom());
            statement.setString(2, codec.getType());
            statement.executeUpdate();
            codec = this.find(codec);
        }
        connection.commit();

        return codec;
    }

    @Override
    public Codec createOrUpdate(Codec codec) throws SQLException {
        try {
            codec = this.create(codec);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            }
        }
        return codec;
    }

    @Override
    public Codec find(final Codec codec) throws SQLException {
        return this.find(codec.getNom(), codec.getType());
    }

    public Codec find(final String nom, final String type) throws SQLException {
        Codec codec = null;
        final String query = "SELECT * FROM Codec WHERE nomCodec = '" + nom + "' AND typeCodec = '" + type + "'";
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                codec = new Codec(nom, type);
            }
        }
        connection.commit();

        return codec;
    }

    @Override
    public Codec update(final Codec codec) throws SQLException {
        return this.createOrUpdate(codec);
    }

    @Override
    public void delete(final Codec codec) throws SQLException {
        final String query = "DELETE FROM Codec WHERE nomCodec = '" + codec.getNom() + "' AND typeCodec = '"
                + codec.getType() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(query);
        connection.commit();
    }
}