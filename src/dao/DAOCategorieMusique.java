package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tables.CategorieMusique;

public class DAOCategorieMusique extends DAO<CategorieMusique> {

    @Override
    public CategorieMusique create(CategorieMusique categorieMusique) throws SQLException {
        String query = "INSERT INTO CategorieMusique SELECT ? FROM dual WHERE NOT EXISTS (SELECT NULL FROM CategorieMusique WHERE typeCategorieMusique = ?)";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            String categorie = categorieMusique.getCategorie();

            statement.setString(1, categorie);
            statement.setString(2, categorie);

            statement.executeUpdate();

            categorieMusique = this.find(categorie);
        }
        connection.commit();

        return categorieMusique;
    }

    public CategorieMusique find(String categorie) throws SQLException {
        CategorieMusique categorieMusique = null;
        String query = "SELECT * FROM CategorieMusique WHERE typeCategorieMusique = '" + categorie + "'";
        try (ResultSet rs = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les attributs de la ligne
            if (rs.first()) {
                categorieMusique = new CategorieMusique(categorie);
            }
        }
        connection.commit();

        return categorieMusique;
    }

    @Override
    public CategorieMusique update(CategorieMusique categorieMusique) throws SQLException {
        return this.create(categorieMusique);
    }

    @Override
    public void delete(CategorieMusique categorieMusique) throws SQLException {
        String query = "DELETE FROM CategorieMusique WHERE typeCategorieMusique = '" + categorieMusique.getCategorie() + "'";
        this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(query);
        connection.commit();
    }
    
}
