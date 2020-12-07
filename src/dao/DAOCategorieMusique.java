package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import model.CategorieMusique;

public class DAOCategorieMusique {

    public void create(CategorieMusique categorieMusique) throws SQLException {
        final String query = "INSERT INTO CategorieMusique VALUES (?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, categorieMusique.getCategorie());

            statement.executeUpdate();
        }
    }

    public void find(String typeCategorieMusique) throws SQLException {
        final String query = String.format("SELECT * FROM CategorieMusique WHERE typeCategorieMusique = '%s'", typeCategorieMusique);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);    
    }

    public void delete(String typeCategorieMusique) throws SQLException {
        final String query = String.format("DELETE FROM CategorieMusique WHERE typeCategorieMusique = '%s'", typeCategorieMusique);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query); 
    }    
}
