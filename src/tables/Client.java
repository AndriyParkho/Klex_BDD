package tables;

import java.util.ArrayList;

public class Client {
	private String marque = "";
	private String modele = "";
	private int largeurMax = 0;
    private int hauteurMax = 0;
    private ArrayList<Codec> codecs = new ArrayList<Codec>();
	
    public Client(String marque, String modele, int largeurMax, int hauteurMax, ArrayList<Codec> codecs) {
        this.marque = marque;
        this.modele = modele;
        this.largeurMax = largeurMax;
        this.hauteurMax = hauteurMax;
        this.codecs = codecs;
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

    public ArrayList<Codec> getCodecs() {
        return codecs;
    }

    public void setCodecs(ArrayList<Codec> codecs) {
        this.codecs = codecs;
    }

    public void addCodec(Codec codec) {
        if (!this.codecs.contains(codec)) {
            this.codecs.add(codec);
        }
    }

    public Codec getCodec(int index) {
        return this.codecs.get(index);
    }
}
