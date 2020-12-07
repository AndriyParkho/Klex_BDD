package model;

public class SupporteCodec {
    private String marque;
    private String modele;
    private String nomCodec;
    private String typeCodec;

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
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
        return "SupporteCodec [marque=" + marque + ", modele=" + modele + ", nomCodec=" + nomCodec + ", typeCode="
                + typeCodec + "]";
    }
}
