package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tables.Codec;

public class DAOCodec extends DAO<Codec> {

    @Override
    public Codec create(Codec codec) throws SQLException {
        String query = "INSERT INTO Codec VALUES (?, ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            String nom = codec.getNom();
            String type = codec.getType();

            statement.setString(1, nom);
            statement.setString(2, type);

            int nbRowsAffected = statement.executeUpdate();
            if (nbRowsAffected != 1) {
                throw new SQLException("no rows affected");
            }

            codec = this.find(nom, type);
        }

        connection.commit();

        return codec;
    }

    public Codec find(String nom, String type) throws SQLException {
        Codec codec = null;
        String query = "SELECT * FROM Codec WHERE nomCodec = '" + nom + "' AND typeCodec = '" + type + "'";
        try (ResultSet rs = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les attributs de la ligne
            if (rs.first()) {
                codec = new Codec(nom, type);
            }
        }
        connection.commit();

        return codec;
    }
    
    @Override
    public Codec update(Codec codec) throws SQLException {
        return this.create(codec);
    }

    @Override
    public void delete(Codec codec) throws SQLException {
        String query = "DELETE FROM Codec WHERE nomCodec = '" + codec.getNom() + "' AND typeCodec = '" + codec.getType() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(query);
        connection.commit();
    }

    @Override
    public Codec createOrUpdate(Codec obj) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Codec find(Codec obj) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
}