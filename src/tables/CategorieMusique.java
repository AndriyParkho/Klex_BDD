package tables;

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
}
