package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
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
    public void createOrUpdate(CategorieFilm categorieFilm) throws SQLException {
        try {
            this.create(categorieFilm);
        } catch (final SQLIntegrityConstraintViolationException e) {
            System.out.println(categorieFilm + " est déjà dans la BDD");
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            }
        }
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
    public void update(final CategorieFilm categorieFilm) throws SQLException {
        this.createOrUpdate(categorieFilm);
    }

    @Override
    public void delete(final CategorieFilm categorieFilm) throws SQLException {
        final String query = "DELETE FROM CategorieFilm WHERE typeCategorieFilm = '" + categorieFilm.getCategorie()
                + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(query);
    }    
}
