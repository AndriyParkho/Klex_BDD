package tables;

public class Codec {
	private String nom = null;
	private String type = null;
	
	public Codec(final String nom, final String type) {
        this.nom = nom;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Codec [nom=" + nom + ", type=" + type + "]";
    }
}
