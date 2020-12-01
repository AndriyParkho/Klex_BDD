package src.tables;

import java.util.ArrayList;

public class Flux {
    private long id = 0;
    private int debit = 0;
    private ArrayList<Codec> codecs = new ArrayList<Codec>();
    
    public Flux(long id, int debit) {
    	this.id = id;
    	this.debit = debit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }
    
    public ArrayList<Codec> getCodec() {
        return codecs;
    }

    public void setCodec(ArrayList<Codec> codecs) {
        this.codecs = codecs;
    }
    
    public void addCodec(Codec codec) {
    	this.codecs.add(codec);
    }

    @Override
    public String toString() {
        return "id=" + id + ", debit=" + debit;
    }
}
