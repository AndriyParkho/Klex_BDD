package model;

import java.sql.Date;

public class FilmAPourCategorie {
    private String titreFilm;
    private Date anneeSortie;
    private String typeCategorieFilm;

    public FilmAPourCategorie(String titreFilm, Date anneeSortie, String typeCategorieFilm) {
        this.titreFilm = titreFilm;
        this.anneeSortie = anneeSortie;
        this.typeCategorieFilm = typeCategorieFilm;
    }
    
    public String getTitreFilm() {
        return titreFilm;
    }

    public void setTitreFilm(String titreFilm) {
        this.titreFilm = titreFilm;
    }

    public Date getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(Date anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    public String getTypeCategorieFilm() {
        return typeCategorieFilm;
    }

    public void setTypeCategorieFilm(String typeCategorieFilm) {
        this.typeCategorieFilm = typeCategorieFilm;
    }

    @Override
    public String toString() {
        return "FilmAPourCategorie [anneeSortie=" + anneeSortie + ", titreFilm=" + titreFilm + ", typeCategorieFilm="
                + typeCategorieFilm + "]";
    }
}
