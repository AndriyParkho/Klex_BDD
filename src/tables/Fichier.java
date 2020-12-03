package tables;

import java.sql.Date;

public class Fichier {
    private long id = 0;
    private long taille = 0;
    private Date dateDepot = null;
    private String email = "";
    private Contenu contenu = null; // film ou piste

    public Fichier(final long id, final long taille, final Date dateDepot, final String email,
            final Contenu contenu) {
        this.id = id;
        this.taille = taille;
        this.dateDepot = dateDepot;
        this.email = email;
        this.contenu = contenu;
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

    public Contenu getContenu() {
        return contenu;
    }

    public void setContenu(final Contenu contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Fichier [contenu=" + contenu + ", dateDepot=" + dateDepot + ", email=" + email + ", id=" + id
                + ", taille=" + taille + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contenu == null) ? 0 : contenu.hashCode());
        result = prime * result + ((dateDepot == null) ? 0 : dateDepot.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (taille ^ (taille >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fichier other = (Fichier) obj;
        if (contenu == null) {
            if (other.contenu != null)
                return false;
        } else if (!contenu.equals(other.contenu))
            return false;
        if (dateDepot == null) {
            if (other.dateDepot != null)
                return false;
        } else if (!dateDepot.equals(other.dateDepot))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id != other.id)
            return false;
        if (taille != other.taille)
            return false;
        return true;
    }
}
