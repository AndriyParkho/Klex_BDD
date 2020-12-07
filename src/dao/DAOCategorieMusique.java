package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.CategorieMusique;

public class DAOCategorieMusique extends DAO<CategorieMusique> {

    @Override
    public void create(CategorieMusique categorieMusique) throws SQLException {
        final String query = "INSERT INTO CategorieMusique VALUES (?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, categorieMusique.getCategorie());
            statement.executeUpdate();
        }
    }

    public void createOrUpdate(CategorieMusique categorieMusique) throws SQLException {
        try {
            this.create(categorieMusique);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(categorieMusique + " est déjà dans la BDD.");
            }
        }
    }

    @Override
    public ResultSet find(CategorieMusique categorieMusique) throws SQLException {
        return this.find(categorieMusique.getCategorie());
    }

    public ResultSet find(String typeCategorieMusique) throws SQLException {
        final String query = String.format("SELECT * FROM CategorieMusique WHERE typeCategorieMusique = '%s'",
                typeCategorieMusique);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(String typeCategorieMusique) throws SQLException {
        final String query = String.format("DELETE FROM CategorieMusique WHERE typeCategorieMusique = '%s'",
                typeCategorieMusique);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
