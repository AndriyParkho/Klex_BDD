package model;

import java.sql.Date;

public class Fichier {
    private long id = 0;
    private long taille = 0;
    private Date dateDepot = null;
    private String email = "";

    public Fichier(final long id, final long taille, final Date dateDepot, final String email) {
        this.id = id;
        this.taille = taille;
        this.dateDepot = dateDepot;
        this.email = email;
    }

    public Fichier() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getTaille() {
        return taille;
    }

    public void setTaille(final long taille) {
        this.taille = taille;
    }

    public Date getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(final Date dateDepot) {
        this.dateDepot = dateDepot;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fichier [dateDepot=" + dateDepot + ", email=" + email + ", id=" + id + ", taille=" + taille + "]";
    }
}
