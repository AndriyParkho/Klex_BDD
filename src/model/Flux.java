package model;

public abstract class Flux {
    private long id = 0;
    private int debit = 0;
    private long idFichier;
    private String nomCodec;
    private String typeCodec;

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

    public long getIdFichier() {
        return idFichier;
    }

    public void setIdFichier(long idFichier) {
        this.idFichier = idFichier;
    }

    public String getNomCodec() {
        return nomCodec;
    }

    public void setNomCodec(String nomCodec) {
        this.nomCodec = nomCodec;
    }

    public String getTypeCodec() {
        return typeCodec;
    }

    public void setTypeCodec(String typeCodec) {
        this.typeCodec = typeCodec;
    }

    @Override
    public String toString() {
        return "Flux [debit=" + debit + ", id=" + id + ", idFichier=" + idFichier + ", nomCodec=" + nomCodec
                + ", typeCodec=" + typeCodec + "]";
    }
}
