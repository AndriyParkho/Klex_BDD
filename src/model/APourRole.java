package model;

import java.sql.Date;

public class APourRole {
    private String titreFilm;
    private Date anneeSortie;
    private long idArtiste;
    private String role;

    public APourRole(String titreFilm, Date anneeSortie, long idArtiste, String role) {
        this.titreFilm = titreFilm;
        this.anneeSortie = anneeSortie;
        this.idArtiste = idArtiste;
        this.role = role;
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

    public long getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(final long idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "APourRole [anneeSortie=" + anneeSortie + ", idArtiste=" + idArtiste + ", role=" + role + ", titreFilm="
                + titreFilm + "]";
    }
}
