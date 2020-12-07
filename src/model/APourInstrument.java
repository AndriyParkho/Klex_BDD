package model;

public class APourInstrument {
    private long idArtiste;
    private int numPiste;
    private String instrument;

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

    @Override
    public String toString() {
        return "APourInstrument [idArtiste=" + idArtiste + ", instrument=" + instrument + ", numPiste=" + numPiste
                + "]";
    }
}
