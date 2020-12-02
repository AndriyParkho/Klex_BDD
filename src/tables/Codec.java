package tables;

public class Codec {
	private String nom = "";
	private String type = "";
	
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Codec c = (Codec) o;
        return nom == c.getNom() && type == c.getType();
    }

    @Override
    public int hashCode() {
        return 31 + (nom == null ? 0 : nom.hashCode()) + (type == null ? 0 : type.hashCode());
    }
}
