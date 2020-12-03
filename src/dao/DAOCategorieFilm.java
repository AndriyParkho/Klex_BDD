package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import tables.CategorieFilm;

public class DAOCategorieFilm extends DAO<CategorieFilm> {

    @Override
    public CategorieFilm create(CategorieFilm categorieFilm) throws SQLException {
        final String query = "INSERT INTO CategorieFilm VALUES (?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, categorieFilm.getCategorie());
            statement.executeUpdate();
            categorieFilm = this.find(categorieFilm);
        }

        connection.commit();

        return categorieFilm;
    }

    @Override
    public CategorieFilm createOrUpdate(CategorieFilm categorieFilm) throws SQLException {
        try {
            categorieFilm = this.create(categorieFilm);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                categorieFilm = this.find(categorieFilm);
            }
        }
        return categorieFilm;
    }

    @Override
    public CategorieFilm find(final CategorieFilm categorieFilm) throws SQLException {
        return this.find(categorieFilm.getCategorie());
    }

    public CategorieFilm find(final String categorie) throws SQLException {
        CategorieFilm categorieFilm = null;
        final String query = "SELECT * FROM CategorieFilm WHERE typeCategorieFilm = '" + categorie + "'";
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                categorieFilm = new CategorieFilm(categorie);
            }
        }
        connection.commit();

        return categorieFilm;
    }

    @Override
    public CategorieFilm update(final CategorieFilm categorieFilm) throws SQLException {
        return this.createOrUpdate(categorieFilm);
    }

    @Override
    public void delete(final CategorieFilm categorieFilm) throws SQLException {
        final String query = "DELETE FROM CategorieFilm WHERE typeCategorieFilm = '" + categorieFilm.getCategorie()
                + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(query);
        connection.commit();
    }    
}
