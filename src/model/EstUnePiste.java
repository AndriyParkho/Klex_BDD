package model;

public class EstUnePiste {
    private long idFichier;
    private int numPiste;
    private long idAlbum;

    public long getIdFichier() {
        return idFichier;
    }

    public void setIdFichier(long idFichier) {
        this.idFichier = idFichier;
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

    @Override
    public String toString() {
        return "EstUnePiste [idAlbum=" + idAlbum + ", idFichier=" + idFichier + ", numPiste=" + numPiste + "]";
    }
}
