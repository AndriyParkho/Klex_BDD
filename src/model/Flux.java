package model;

public abstract class Flux {
    private long id;
    private float debit;
    private long idFichier;
    private String nomCodec;
    private String typeCodec;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getDebit() {
        return debit;
    }

    public void setDebit(float debit) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(debit);
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (idFichier ^ (idFichier >>> 32));
        result = prime * result + ((nomCodec == null) ? 0 : nomCodec.hashCode());
        result = prime * result + ((typeCodec == null) ? 0 : typeCodec.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Flux other = (Flux) obj;
        if (Float.floatToIntBits(debit) != Float.floatToIntBits(other.debit))
            return false;
        if (id != other.id)
            return false;
        if (idFichier != other.idFichier)
            return false;
        if (nomCodec == null) {
            if (other.nomCodec != null)
                return false;
        } else if (!nomCodec.equals(other.nomCodec))
            return false;
        if (typeCodec == null) {
            if (other.typeCodec != null)
                return false;
        } else if (!typeCodec.equals(other.typeCodec))
            return false;
        return true;
    }
}
