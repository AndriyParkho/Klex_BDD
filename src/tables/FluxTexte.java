package tables;

public class FluxTexte extends Flux {
    private String langue = "";

    public FluxTexte(long id, int debit, String langue) {
    	super(id, debit);
    	this.langue = langue;
    }
    
    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    @Override
    public String toString() {
        return "FluxTexte [" + super.toString() + ", langue=" + langue + "]";
    }
}
