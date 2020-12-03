package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import connections.JDBCUtilities;
import tables.CategorieMusique;

public class DAOCategorieMusique extends DAO<CategorieMusique> {

    @Override
    public CategorieMusique create(CategorieMusique categorieMusique) throws SQLException {
        final String query = "INSERT INTO CategorieMusique VALUES (?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, categorieMusique.getCategorie());
            statement.executeUpdate();
            categorieMusique = this.find(categorieMusique);
        }
        connection.commit();

        return categorieMusique;
    }

    @Override
    public CategorieMusique createOrUpdate(CategorieMusique categorieMusique) throws SQLException {
        try {
            categorieMusique = this.create(categorieMusique);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            }
        }
        return categorieMusique;
    }

    @Override
    public CategorieMusique find(final CategorieMusique categorieMusique) throws SQLException {
        return this.find(categorieMusique.getCategorie());
    }

    public CategorieMusique find(final String categorie) throws SQLException {
        CategorieMusique categorieMusique = null;
        final String query = "SELECT * FROM CategorieMusique WHERE typeCategorieMusique = '" + categorie + "'";
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                categorieMusique = new CategorieMusique(categorie);
            }
        }
        connection.commit();

        return categorieMusique;
    }

    @Override
    public CategorieMusique update(final CategorieMusique categorieMusique) throws SQLException {
        return this.createOrUpdate(categorieMusique);
    }

    @Override
    public void delete(final CategorieMusique categorieMusique) throws SQLException {
        final String query = "DELETE FROM CategorieMusique WHERE typeCategorieMusique = '"
                + categorieMusique.getCategorie() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(query);
        connection.commit();
    }
    
}
