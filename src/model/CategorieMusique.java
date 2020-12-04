package model;

import java.util.Objects;

public class CategorieMusique {
    private String categorie = null;

    public CategorieMusique(String categorie) {
        this.categorie = categorie;
    }

	public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "CategorieMusique [categorie=" + categorie + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(categorie);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CategorieMusique other = (CategorieMusique) obj;
        if (categorie == null) {
            if (other.categorie != null)
                return false;
        } else if (!categorie.equals(other.categorie))
            return false;
        return true;
    }
}
