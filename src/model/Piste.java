package model;

public class Piste {
    private int num = 0;
    private String titre = "";
    private String duree = "";
    private long idAlbum;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(long idAlbum) {
        this.idAlbum = idAlbum;
    }

    @Override
    public String toString() {
        return "Piste [duree=" + duree + ", idAlbum=" + idAlbum + ", num=" + num + ", titre=" + titre + "]";
    }
}
