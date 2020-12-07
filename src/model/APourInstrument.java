package model;

public class APourInstrument {
    private long idArtiste;
    private int numPiste;
    private long idAlbum;
    private String instrument;

    public APourInstrument(long idArtiste, int numPiste, long idAlbum, String instrument) {
        this.idArtiste = idArtiste;
        this.numPiste = numPiste;
        this.idAlbum = idAlbum;
        this.instrument = instrument;
    }

    public long getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(long idArtiste) {
        this.idArtiste = idArtiste;
    }

    public int getNumPiste() {
        return numPiste;
    }

    public void setNumPiste(int numPiste) {
        this.numPiste = numPiste;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(long idAlbum) {
        this.idAlbum = idAlbum;
    }
    
    @Override
    public String toString() {
        return "APourInstrument [idAlbum=" + idAlbum + ", idArtiste=" + idArtiste + ", instrument=" + instrument
                + ", numPiste=" + numPiste + "]";
    }
}
