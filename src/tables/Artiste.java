package tables;

public class Artiste {
    private long id = 0;
    private String nom = "";
    private String dateNaissance = "";
    private String urlPhoto = "";
    private String specialite = "";
    private String biographie = "";

    public Artiste(long id, String nom, String dateNaissance, String urlPhoto, String specialite, String biographie) {
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

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
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
}
