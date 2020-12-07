package model;

import java.sql.Date;

public class APourRole {
    private String titreFilm;
    private Date anneeSortie;
    private long idArtiste;
    private String role;

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

    public long getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(long idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "APourRole [anneeSortie=" + anneeSortie + ", idArtiste=" + idArtiste + ", role=" + role + ", titreFilm="
                + titreFilm + "]";
    }
}
