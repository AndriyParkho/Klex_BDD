package model;

import java.sql.Date;

public class EstUnFilm {
    private long idFichier;
    private String titreFilm;
    private Date anneeSortie;

    public long getIdFichier() {
        return idFichier;
    }

    public void setIdFichier(long idFichier) {
        this.idFichier = idFichier;
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

    @Override
    public String toString() {
        return "EstUnFilm [anneeSortie=" + anneeSortie + ", idFichier=" + idFichier + ", titreFilm=" + titreFilm + "]";
    }
}
