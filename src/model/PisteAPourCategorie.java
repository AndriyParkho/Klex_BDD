package model;

public class PisteAPourCategorie {
    private int numPiste;
    private long idAlbum;
    private String typeCategorieMusique;

    public PisteAPourCategorie(int numPiste, long idAlbum, String typeCategorieMusique) {
        this.numPiste = numPiste;
        this.idAlbum = idAlbum;
        this.typeCategorieMusique = typeCategorieMusique;
    }
    
    public int getNumPiste() {
        return numPiste;
    }

    public void setNumPiste(int numPiste) {
        this.numPiste = numPiste;
    }

    public long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(long idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTypeCategorieMusique() {
        return typeCategorieMusique;
    }

    public void setTypeCategorieMusique(String typeCategorieMusique) {
        this.typeCategorieMusique = typeCategorieMusique;
    }

    @Override
    public String toString() {
        return "PisteAPourCategorie [idAlbum=" + idAlbum + ", numPiste=" + numPiste + ", typeCategorieMusique="
                + typeCategorieMusique + "]";
    }
}
