package tables;

import java.util.HashSet;

public class Client {
	private String marque = "";
	private String modele = "";
	private int largeurMax = 0;
    private int hauteurMax = 0;
    private HashSet<Codec> codecs = new HashSet<Codec>();
	
    public Client(String marque, String modele, int largeurMax, int hauteurMax, HashSet<Codec> codecs) {
        this.marque = marque;
        this.modele = modele;
        this.largeurMax = largeurMax;
        this.hauteurMax = hauteurMax;
        this.codecs = codecs;
    }
	
	public Client() {
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

    public HashSet<Codec> getCodecs() {
        return codecs;
    }

    public void setCodecs(HashSet<Codec> codecs) {
        this.codecs = codecs;
    }

    public void addCodec(Codec codec) {
        this.codecs.add(codec);
    }

    @Override
    public String toString() {
        return "Client [codecs=" + codecs + ", hauteurMax=" + hauteurMax + ", largeurMax=" + largeurMax + ", marque="
                + marque + ", modele=" + modele + "]";
    }
}
