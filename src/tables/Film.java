package tables;

import java.util.HashMap;
import java.util.HashSet;

public class Film extends Contenu {
    private String titreFilm = "";
    private String anneeSortie = "";
    private String resume = "";
    private int ageMin = 0;
    private String urlAffiche = "";
    private HashSet<CategorieFilm> categoriesFilm = new HashSet<CategorieFilm>();
    private HashSet<ImgExtraiteFilm> imgExtraitesFilm = new HashSet<ImgExtraiteFilm>();

    public Film(String titreFilm, String anneeSortie, String resume, int ageMin, String urlAffiche,
            HashSet<CategorieFilm> categoriesFilm, HashSet<ImgExtraiteFilm> imgExtraitesFilm,
            HashMap<Artiste, String> artistes) {
        super(artistes);
        this.titreFilm = titreFilm;
        this.anneeSortie = anneeSortie;
        this.resume = resume;
        this.ageMin = ageMin;
        this.urlAffiche = urlAffiche;
        this.categoriesFilm = categoriesFilm;
        this.imgExtraitesFilm = imgExtraitesFilm;
    }

    public Film() {
    }

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

    public HashSet<CategorieFilm> getCategoriesFilm() {
        return categoriesFilm;
    }

    public void setCategoriesFilm(HashSet<CategorieFilm> categoriesFilm) {
        this.categoriesFilm = categoriesFilm;
    }

    public void addCategorie(CategorieFilm categorieFilm) {
        this.categoriesFilm.add(categorieFilm);
    }

    public HashSet<ImgExtraiteFilm> getImgExtraitesFilm() {
        return imgExtraitesFilm;
    }

    public void setImgExtraitesFilm(HashSet<ImgExtraiteFilm> imgExtraitesFilm) {
        this.imgExtraitesFilm = imgExtraitesFilm;
    }

    public void addImgExtraiteFilm(ImgExtraiteFilm imgExtraiteFilm) {
        this.imgExtraitesFilm.add(imgExtraiteFilm);
    }

    public void addArtiste(Artiste artiste, String role) {
        this.artistes.putIfAbsent(artiste, role);
    }

    public String getRole(Artiste artiste) {
        return this.artistes.get(artiste);
    }

    @Override
    public String toString() {
        return super.toString() + "\nFilm [ageMin=" + ageMin + ", anneeSortie=" + anneeSortie + ", categoriesFilm="
                + categoriesFilm + ", imgExtraitesFilm=" + imgExtraitesFilm + ", resume=" + resume + ", titreFilm="
                + titreFilm + ", urlAffiche=" + urlAffiche + "]";
    }
}
