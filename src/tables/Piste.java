package src.tables;

import java.util.ArrayList;

public class Piste {
    private long id = 0;
    private int num = 0;
    private String titre = "";
    private String duree = "";
    private Fichier fichier = new Fichier();
    private ArrayList<CategorieMusique> categoriesMusique = new ArrayList<CategorieMusique>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Fichier getFichier() {
        return fichier;
    }

    public void setFichier(Fichier fichier) {
        this.fichier = fichier;
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
        return "Piste [categoriesMusique=" + categoriesMusique + ", duree=" + duree + ", id=" + id + ", num=" + num
                + ", titre=" + titre + "]";
    }
}
