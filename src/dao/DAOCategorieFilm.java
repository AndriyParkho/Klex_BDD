package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tables.CategorieFilm;

public class DAOCategorieFilm extends DAO<CategorieFilm> {

    @Override
    public CategorieFilm create(CategorieFilm categorieFilm) throws SQLException {
        String query = "INSERT INTO CategorieFilm VALUES (?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            String categorie = categorieFilm.getCategorie();

            statement.setString(1, categorie);

            int nbRowsAffected = statement.executeUpdate();
            if (nbRowsAffected != 1) {
                throw new SQLException("no rows affected");
            }

            categorieFilm = this.find(categorie);
        }

        connection.commit();

        return categorieFilm;
    }

    public CategorieFilm find(String categorie) throws SQLException {
        CategorieFilm categorieFilm = null;
        String query = "SELECT * FROM CategorieFilm WHERE typeCategorieFilm = '" + categorie + "'";
        try (ResultSet rs = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les attributs de la ligne
            if (rs.first()) {
                categorieFilm = new CategorieFilm(categorie);
            }
        }
        connection.commit();

        return categorieFilm;
    }

    @Override
    public CategorieFilm update(CategorieFilm categorieFilm) throws SQLException {
        return this.create(categorieFilm);
    }

    @Override
    public void delete(CategorieFilm categorieFilm) throws SQLException {
        String query = "DELETE FROM CategorieFilm WHERE typeCategorieFilm = '" + categorieFilm.getCategorie() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(query);
        connection.commit();
    }
    
}
