package model;

public class FluxAudio extends Flux {
    private int echantillonnage;
    private String langue;

    public int getEchantillonnage() {
        return echantillonnage;
    }

    public void setEchantillonnage(int echantillonnage) {
        this.echantillonnage = echantillonnage;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    @Override
    public String toString() {
        return "FluxTexte [" + super.toString() + ", langue=" + langue + ", echantillonnage=" + echantillonnage + "]";
    }
}
