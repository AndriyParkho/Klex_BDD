package tables;

import java.util.ArrayList;

public class Album {
    private long id = 0;
    private String titre = "";
    private String groupe = "";
    private String dateSortie = "";
    private String urlImagePochette = "";
    private ArrayList<CategorieMusique> categoriesMusique = new ArrayList<CategorieMusique>();

    public Album(long id, String titre, String groupe, String dateSortie, String urlImagePochette,
            ArrayList<CategorieMusique> categoriesMusique) {
        this.id = id;
        this.titre = titre;
        this.groupe = groupe;
        this.dateSortie = dateSortie;
        this.urlImagePochette = urlImagePochette;
        this.categoriesMusique = categoriesMusique;
    }

    public Album() {
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getUrlImagePochette() {
        return urlImagePochette;
    }

    public void setUrlImagePochette(String urlImagePochette) {
        this.urlImagePochette = urlImagePochette;
    }

    public ArrayList<CategorieMusique> getCategoriesMusique() {
        return categoriesMusique;
    }

    public void setCategoriesMusique(ArrayList<CategorieMusique> categoriesMusique) {
        this.categoriesMusique = categoriesMusique;
    }

    public void addCategorieMusique(CategorieMusique categorieMusique) {
        this.categoriesMusique.add(categorieMusique);
    }

    public CategorieMusique getCategorieMusique(int index) {
        return this.categoriesMusique.get(index);
    }

    @Override
    public String toString() {
        return "Album [categoriesMusique=" + categoriesMusique + ", dateSortie=" + dateSortie + ", groupe=" + groupe
                + ", id=" + id + ", titre=" + titre + ", urlImagePochette=" + urlImagePochette + "]";
    }
}
