package model;

import java.sql.Date;

public class Artiste {
    private long id = 0;
    private String nom = "";
    private Date dateNaissance = null;
    private String urlPhoto = "";
    private String specialite = "";
    private String biographie = "";

    public Artiste(long id, String nom, Date dateNaissance, String urlPhoto, String specialite, String biographie) {
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.urlPhoto = urlPhoto;
        this.specialite = specialite;
        this.biographie = biographie;
    }

    public Artiste() {
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    @Override
    public String toString() {
        return "Artiste [biographie=" + biographie + ", dateNaissance=" + dateNaissance + ", id=" + id + ", nom=" + nom
                + ", specialite=" + specialite + ", urlPhoto=" + urlPhoto + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((biographie == null) ? 0 : biographie.hashCode());
        result = prime * result + ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((specialite == null) ? 0 : specialite.hashCode());
        result = prime * result + ((urlPhoto == null) ? 0 : urlPhoto.hashCode());
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
        Artiste other = (Artiste) obj;
        if (biographie == null) {
            if (other.biographie != null)
                return false;
        } else if (!biographie.equals(other.biographie))
            return false;
        if (dateNaissance == null) {
            if (other.dateNaissance != null)
                return false;
        } else if (!dateNaissance.equals(other.dateNaissance))
            return false;
        if (id != other.id)
            return false;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (specialite == null) {
            if (other.specialite != null)
                return false;
        } else if (!specialite.equals(other.specialite))
            return false;
        if (urlPhoto == null) {
            if (other.urlPhoto != null)
                return false;
        } else if (!urlPhoto.equals(other.urlPhoto))
            return false;
        return true;
    }
}
