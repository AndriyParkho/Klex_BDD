package model;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Film extends Contenu {
    private String titreFilm = "";
    private Date anneeSortie = null;
    private String resume = "";
    private int ageMin = 0;
    private String urlAffiche = "";
    private HashSet<CategorieFilm> categoriesFilm = new HashSet<CategorieFilm>();
    private HashSet<ImgExtraiteFilm> imgExtraitesFilm = new HashSet<ImgExtraiteFilm>();

    public Film(final String titreFilm, final Date anneeSortie, final String resume, final int ageMin,
            final String urlAffiche, final HashSet<CategorieFilm> categoriesFilm,
            final HashSet<ImgExtraiteFilm> imgExtraitesFilm, final HashMap<Artiste, String> artistes) {
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

    public void setTitreFilm(final String titreFilm) {
        this.titreFilm = titreFilm;
    }

    public Date getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(final Date anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(final String resume) {
        this.resume = resume;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(final int ageMin) {
        this.ageMin = ageMin;
    }

    public String getUrlAffiche() {
        return urlAffiche;
    }

    public void setUrlAffiche(final String urlAffiche) {
        this.urlAffiche = urlAffiche;
    }

    public HashSet<CategorieFilm> getCategoriesFilm() {
        return categoriesFilm;
    }

    public void setCategoriesFilm(final HashSet<CategorieFilm> categoriesFilm) {
        this.categoriesFilm = categoriesFilm;
    }

    public void addCategorie(final CategorieFilm categorieFilm) {
        this.categoriesFilm.add(categorieFilm);
    }

    public HashSet<ImgExtraiteFilm> getImgExtraitesFilm() {
        return imgExtraitesFilm;
    }

    public void setImgExtraitesFilm(final HashSet<ImgExtraiteFilm> imgExtraitesFilm) {
        this.imgExtraitesFilm = imgExtraitesFilm;
    }

    public void addImgExtraiteFilm(final ImgExtraiteFilm imgExtraiteFilm) {
        this.imgExtraitesFilm.add(imgExtraiteFilm);
    }

    public void addArtiste(final Artiste artiste, final String role) {
        this.artistes.putIfAbsent(artiste, role);
    }

    public String getRole(final Artiste artiste) {
        return this.artistes.get(artiste);
    }

    @Override
    public String toString() {
        return super.toString() + "\nFilm [ageMin=" + ageMin + ", anneeSortie=" + anneeSortie + ", categoriesFilm="
                + categoriesFilm + ", imgExtraitesFilm=" + imgExtraitesFilm + ", resume=" + resume + ", titreFilm="
                + titreFilm + ", urlAffiche=" + urlAffiche + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(titreFilm, anneeSortie, ageMin, categoriesFilm, imgExtraitesFilm, resume, urlAffiche, super.getArtistes());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (!super.equals(obj)) return false;
        Film other = (Film) obj;
        if (ageMin != other.ageMin)
            return false;
        if (anneeSortie == null) {
            if (other.anneeSortie != null)
                return false;
        } else if (!anneeSortie.equals(other.anneeSortie))
            return false;
        if (categoriesFilm == null) {
            if (other.categoriesFilm != null)
                return false;
        } else if (!categoriesFilm.equals(other.categoriesFilm))
            return false;
        if (imgExtraitesFilm == null) {
            if (other.imgExtraitesFilm != null)
                return false;
        } else if (!imgExtraitesFilm.equals(other.imgExtraitesFilm))
            return false;
        if (resume == null) {
            if (other.resume != null)
                return false;
        } else if (!resume.equals(other.resume))
            return false;
        if (titreFilm == null) {
            if (other.titreFilm != null)
                return false;
        } else if (!titreFilm.equals(other.titreFilm))
            return false;
        if (urlAffiche == null) {
            if (other.urlAffiche != null)
                return false;
        } else if (!urlAffiche.equals(other.urlAffiche))
            return false;
        return true;
    }
}
