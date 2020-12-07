package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;

import connections.JDBCUtilities;
import model.Client;

public class DAOClient {

    public void create(Client client) throws SQLException {
        final String insertClientQuery = "INSERT INTO Client VALUES (?, ?, ?, ?)";

        try (PreparedStatement statementClient = this.connection.prepareStatement(insertClientQuery)) {
            statementClient.setString(1, client.getMarque());
            statementClient.setString(2, client.getModele());
            statementClient.setInt(3, client.getLargeurMax());
            statementClient.setInt(4, client.getHauteurMax());

            statementClient.executeUpdate();
        }
    }

    public void find(String marque, String modele) throws SQLException {
        final String query = String.format("SELECT * FROM Client WHERE marque = '%s' AND modele = '%s'", marque, modele);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

    public void delete(String marque, String modele) throws SQLException {
        final String query = String.format("DELETE FROM Client WHERE marque = '%s' AND modele = '%s'", marque, modele);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
