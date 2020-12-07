package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
