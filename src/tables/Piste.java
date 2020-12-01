package tables;

import java.util.ArrayList;
import java.util.HashMap;

public class Piste {
    private long id = 0;
    private int num = 0;
    private String titre = "";
    private String duree = "";
    private Fichier fichier = new Fichier();
    private ArrayList<CategorieMusique> categoriesMusique = new ArrayList<CategorieMusique>();
    private HashMap<Artiste, String> artistes = new HashMap<Artiste, String>();
    private Album album = null;

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

    public HashMap<Artiste, String> getArtistes() {
        return artistes;
    }

    public void setArtistes(HashMap<Artiste, String> artistes) {
        this.artistes = artistes;
    }

    public void addArtiste(Artiste artiste, String role) {
        this.artistes.put(artiste, role);
    }

    public String getRole(Artiste artiste) {
        return this.artistes.get(artiste);
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
