package tables;

public class Client {
	private String marque = "";
	private String modele = "";
	private int largeurMax = 0;
	private int hauteurMax = 0;
	
	public Client(String marque, String modele, int largeurMax, int hauteurMax) {
		this.marque = marque;
		this.modele = modele;
		this.largeurMax = largeurMax;
		this.hauteurMax = hauteurMax;
	}
	
	public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
    
	public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
    
    public int getLargeurMax() {
        return largeurMax;
    }

    public void setLargeurMax(int largeurMax) {
        this.largeurMax = largeurMax;
    }
    
    public int getHauteurMax() {
        return hauteurMax;
    }

    public void setHauteurMax(int hauteurMax) {
        this.hauteurMax = hauteurMax;
    }
}
