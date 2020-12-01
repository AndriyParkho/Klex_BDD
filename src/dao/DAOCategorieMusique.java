package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import tables.CategorieMusique;

public class DAOCategorieMusique extends DAO<CategorieMusique> {

    @Override
    public CategorieMusique create(CategorieMusique obj) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public CategorieMusique find(String categorie) throws SQLException {
        CategorieMusique categorieMusique = null;
        String query = "SELECT * FROM CategorieMusique WHERE typeCategorieMusique = " + categorie;
        try (ResultSet rs = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les attributs de la ligne
            if (rs.first()) {
                categorieMusique = new CategorieMusique(categorie);
            }
        }

        return categorieMusique;
    }

    @Override
    public CategorieMusique update(CategorieMusique obj) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(CategorieMusique obj) {
        // TODO Auto-generated method stub

    }
    
}
