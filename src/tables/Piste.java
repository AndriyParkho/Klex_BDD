package tables;

import java.util.ArrayList;
import java.util.HashMap;

public class Piste extends Contenu {
    private long id = 0;
    private int num = 0;
    private String titre = "";
    private String duree = "";
    private ArrayList<CategorieMusique> categoriesMusique = new ArrayList<CategorieMusique>();
    private Album album = null;

    public Piste(HashMap<Artiste, String> artistes, long id, int num, String titre, String duree,
            ArrayList<CategorieMusique> categoriesMusique, Album album) {
        super(artistes);
        this.id = id;
        this.num = num;
        this.titre = titre;
        this.duree = duree;
        this.categoriesMusique = categoriesMusique;
        this.album = album;
    }

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

    public void addArtiste(Artiste artiste, String instrument) {
        this.artistes.put(artiste, instrument);
    }

    public String getInstrument(Artiste artiste) {
        return this.artistes.get(artiste);
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
