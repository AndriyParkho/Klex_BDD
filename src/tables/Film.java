package tables;

import java.util.ArrayList;
import java.util.HashMap;

public class Film {
    private String titreFilm = "";
    private String anneeSortie = "";
    private String resume = "";
    private int ageMin = 0;
    private String urlAffiche = "";
    private ArrayList<CategorieFilm> categoriesFilm = new ArrayList<CategorieFilm>();
    private ArrayList<ImgExtraiteFilm> imgExtraitesFilm = new ArrayList<ImgExtraiteFilm>();
    private HashMap<Artiste, String> artistes = new HashMap<Artiste, String>();

    public String getTitreFilm() {
        return titreFilm;
    }

    public void setTitreFilm(String titreFilm) {
        this.titreFilm = titreFilm;
    }

    public String getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public String getUrlAffiche() {
        return urlAffiche;
    }

    public void setUrlAffiche(String urlAffiche) {
        this.urlAffiche = urlAffiche;
    }

    public ArrayList<CategorieFilm> getCategoriesFilm() {
        return categoriesFilm;
    }

    public void setCategoriesFilm(ArrayList<CategorieFilm> categoriesFilm) {
        this.categoriesFilm = categoriesFilm;
    }

    public void addCategorie(CategorieFilm categorieFilm) {
        this.categoriesFilm.add(categorieFilm);
    }

    public CategorieFilm getCategorieFilm(int index) {
        return this.categoriesFilm.get(index);
    }

    public ArrayList<ImgExtraiteFilm> getImgExtraitesFilm() {
        return imgExtraitesFilm;
    }

    public void setImgExtraitesFilm(ArrayList<ImgExtraiteFilm> imgExtraitesFilm) {
        this.imgExtraitesFilm = imgExtraitesFilm;
    }

    public void addImgExtraiteFilm(ImgExtraiteFilm imgExtraiteFilm) {
        this.imgExtraitesFilm.add(imgExtraiteFilm);
    }

    public ImgExtraiteFilm getImgExtraiteFilm(int index) {
        return this.imgExtraitesFilm.get(index);
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
}
