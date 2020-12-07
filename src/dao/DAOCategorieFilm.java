package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CategorieFilm;

public class DAOCategorieFilm extends DAO<CategorieFilm> {

    @Override
    public void create(CategorieFilm categorieFilm) throws SQLException {
        final String query = "INSERT INTO CategorieFilm VALUES (?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, categorieFilm.getCategorie());
            statement.executeUpdate();
        }
    }

    @Override
    public ResultSet find(CategorieFilm categorieFilm) throws SQLException {
        return this.find(categorieFilm.getCategorie());
    }

    public ResultSet find(String typeCategorieFilm) throws SQLException {
        final String query = String.format("SELECT * FROM CategorieFilm WHERE typeCategorieFilm = '%s'",
                typeCategorieFilm);
        return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeQuery(query);
    }

    public void delete(String typeCategorieFilm) throws SQLException {
        final String query = String.format("DELETE FROM CategorieFilm WHERE typeCategorieFilm = '%s'",
                typeCategorieFilm);
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
                .executeUpdate(query);
    }
}
