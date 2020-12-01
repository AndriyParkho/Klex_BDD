package src.tables;

import java.util.ArrayList;

public class Album {
    private long id = 0;
    private String titre = "";
    private String groupe = "";
    private String dateSortie = "";
    private String urlImagePochette = "";
    private ArrayList<CategorieMusique> categoriesMusique = new ArrayList<CategorieMusique>();
    private ArrayList<Piste> pistes = new ArrayList<Piste>();

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

    public ArrayList<Piste> getPistes() {
        return pistes;
    }

    public void setPistes(ArrayList<Piste> pistes) {
        this.pistes = pistes;
    }

    public void addPiste(Piste piste) {
        this.pistes.add(piste);
    }

    public Piste getPiste(int index) {
        return pistes.get(index);
    }

    @Override
    public String toString() {
        return "Album [categoriesMusique=" + categoriesMusique + ", dateSortie=" + dateSortie + ", groupe=" + groupe
                + ", id=" + id + ", titre=" + titre + ", urlImagePochette=" + urlImagePochette + "]";
    }
}
