package tables;

public class FluxAudio extends Flux {
    private int echantillonnage = 0;
    private String langue = "";

    public FluxAudio(long id, int debit,int echantillonnage, String langue) {
    	super(id, debit);
    	this.echantillonnage = echantillonnage;
    	this.langue = langue;
    }
    
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
