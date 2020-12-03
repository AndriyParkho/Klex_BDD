package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import connections.JDBCUtilities;
import tables.Client;
import tables.Codec;

public class DAOClient extends DAO<Client> {

    @Override
    public Client create(Client client) throws SQLException {
        final String insertClientQuery = "INSERT INTO Client VALUES (?, ?, ?, ?)";

        try (PreparedStatement statementClient = this.connection.prepareStatement(insertClientQuery)) {
            statementClient.setString(1, client.getMarque());
            statementClient.setString(2, client.getModele());
            statementClient.setInt(3, client.getLargeurMax());
            statementClient.setInt(4, client.getHauteurMax());
            statementClient.executeUpdate();

            // on doit créer les liens entre albums et catégories
            for (Codec codec : client.getCodecs()) {
                codec = DAOFactory.getCodecDAO().createOrUpdate(codec);

                // insertion dans la table intermédiaire, c'est un nouveau client donc on est
                // sur que le couple n'existe pas
                createSupporteCodec(client, codec);
            }
            client = this.find(client);
        }
        connection.commit();

        return client;
    }

    @Override
    public Client createOrUpdate(Client client) throws SQLException {
        try {
            client = this.create(client);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                client = this.update(client);
            }
        }
        return client;
    }

    @Override
    public Client find(Client client) throws SQLException {
        return this.find(client.getMarque(), client.getModele());
    }

    public Client find(String marque, String modele) throws SQLException {
        Client client = null;
        final String query = "SELECT * FROM Client LEFT JOIN SupporteCodec ON Client.marque = SupporteCodec.marque AND Client.modele = SupporteCodec.modele AND Client.marque = '"
                + marque + "' AND Client.modele = '" + modele
                + "' INNER JOIN Codec ON Codec.nomCodec = SupporteCodec.nomCodec AND Codec.typeCodec = SupporteCodec.typeCodec";
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                final ArrayList<Codec> codecs = new ArrayList<Codec>();

                rs.beforeFirst();
                while (rs.next() && rs.getString("nomCodec") != null && rs.getString("typeCodec") != null) {
                    codecs.add(DAOFactory.getCodecDAO().find(rs.getString("nomCodec"), rs.getString("typeCodec")));
                }
                // on se replace
                rs.first();

                client = new Client(rs.getString("marque"), rs.getString("modele"), rs.getInt("largeurMax"),
                        rs.getInt("hauteurMax"), codecs);
            }
        }
        connection.commit();

        return client;
    }

    @Override
    public Client update(Client client) throws SQLException {
        final String query = "UPDATE Client SET largeurMax = " + client.getLargeurMax() + ", hauteurMax = "
                + client.getHauteurMax() + " WHERE marque = '" + client.getMarque() + "' AND modele = '"
                + client.getModele() + "'";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            final int nbRowsAffected = statement.executeUpdate();
            if (nbRowsAffected != 1) {
                throw new SQLException("not only one row affected");
            }
            connection.commit();

            for (Codec codec : client.getCodecs()) {
                codec = DAOFactory.getCodecDAO().createOrUpdate(codec);

                // insertion dans la table intermédiaire
                createOrUpdateSupporteCodec(client, codec);
            }
            client = this.find(client);
        }
        return client;
    }

    @Override
    public void delete(Client client) throws SQLException {
        final String query = "DELETE FROM Client WHERE marque = '" + client.getMarque() + "' AND modele = '"
                + client.getModele() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
        connection.commit();
    }

    private void createSupporteCodec(Client client, Codec codec) throws SQLException {
        final String insertSupporteCodecQuery = "INSERT INTO SupporteCodec VALUES (?, ?, ?, ?)";
        try (PreparedStatement statementSupporteCodec = this.connection.prepareStatement(insertSupporteCodecQuery)) {
            statementSupporteCodec.setString(1, client.getMarque());
            statementSupporteCodec.setString(2, client.getModele());
            statementSupporteCodec.setString(3, codec.getNom());
            statementSupporteCodec.setString(4, codec.getType());
            statementSupporteCodec.executeUpdate();
            connection.commit();
        }
    }

    private void createOrUpdateSupporteCodec(Client client, Codec codec) throws SQLException {
        try {
            createSupporteCodec(client, codec);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            }
        }
    }
}
