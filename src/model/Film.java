package model;

import java.sql.Date;

public class Film {
    private String titreFilm = "";
    private Date anneeSortie = null;
    private String resume = "";
    private int ageMin = 0;
    private String urlAffiche = "";

    public Film(final String titreFilm, final Date anneeSortie, final String resume, final int ageMin,
            final String urlAffiche) {
        this.titreFilm = titreFilm;
        this.anneeSortie = anneeSortie;
        this.resume = resume;
        this.ageMin = ageMin;
        this.urlAffiche = urlAffiche;
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

    @Override
    public String toString() {
        return "Film [ageMin=" + ageMin + ", anneeSortie=" + anneeSortie + ", resume=" + resume + ", titreFilm="
                + titreFilm + ", urlAffiche=" + urlAffiche + "]";
    }
}