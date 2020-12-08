package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.Utilisateur;

public class DAOUtilisateur extends DAO<Utilisateur> {

    @Override
    public void create(Utilisateur utilisateur) throws SQLException {
        final String query = "INSERT INTO Utilisateur VALUES (?, ?, ?, ?, ?, ?)";

        System.out.println("Statement Utilisateur");
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, utilisateur.getEmail());
            statement.setString(2, utilisateur.getNom());
            statement.setString(3, utilisateur.getPrenom());
            statement.setInt(4, utilisateur.getAge());
            statement.setString(5, utilisateur.getLangueDiffusion());
            statement.setInt(6, utilisateur.getCode());
            statement.executeUpdate();
        }
    }

    public void createOrUpdate(Utilisateur utilisateur) throws SQLException {
        try {
            this.create(utilisateur);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(utilisateur + " est déjà dans la BDD.");
            }
        }
    }

    @Override
    public ResultSet find(Utilisateur utilisateur) throws SQLException {
        return this.find(utilisateur.getEmail());
    }

    public ResultSet find(String email) throws SQLException {
        final String query = String.format("SELECT * FROM Utilisateur WHERE email = '%s'", email);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(String email) throws SQLException {
        final String query = String.format("DELETE FROM Utilisateur WHERE email = '%s'", email);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }

}
