package tables;

public class Codec {
	private String nom = null;
	private String type = null;
	
	public Codec(String nom, String type) {
		this.nom = nom;
		this.type = type;
	}
	
	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Codec [nom=" + nom + ", type=" + type + "]";
    }
}
