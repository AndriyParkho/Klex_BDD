package model;

public class FluxVideo extends Flux {
    private int largeur = 0;
    private int hauteur = 0;

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    @Override
    public String toString() {
        return "FluxTexte [" + super.toString() + ", largeurImage=" + largeur + ", hauteurImage=" + hauteur + "]";
    }
}
