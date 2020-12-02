package tables;

public class CategorieFilm {
    private String categorie;

    public CategorieFilm(String categorie) {
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
        return "CategorieFilm [categorie=" + categorie + "]";
    }
}
