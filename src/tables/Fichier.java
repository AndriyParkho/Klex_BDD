package tables;

public class Fichier {
    private long id;
    private long taille;
    private String dateDepot = "";
    private String email = "";

    public Fichier(long id, long taille, String dateDepot, String email) {
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

    public void setId(long id) {
        this.id = id;
    }

    public long getTaille() {
        return taille;
    }

    public void setTaille(long taille) {
        this.taille = taille;
    }

    public String getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(String dateDepot) {
        this.dateDepot = dateDepot;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fichier [dateDepot=" + dateDepot + ", email=" + email + ", id=" + id + ", taille=" + taille + "]";
    }
}
