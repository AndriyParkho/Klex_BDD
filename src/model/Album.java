package model;

import java.sql.Date;

public class Album {
    private long id = 0;
    private String titre = "";
    private String groupe = "";
    private Date dateSortie = null;
    private String urlImagePochette = "";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getUrlImagePochette() {
        return urlImagePochette;
    }

    public void setUrlImagePochette(String urlImagePochette) {
        this.urlImagePochette = urlImagePochette;
    }

    @Override
    public String toString() {
        return "Album [dateSortie=" + dateSortie + ", groupe=" + groupe + ", id=" + id + ", titre=" + titre
                + ", urlImagePochette=" + urlImagePochette + "]";
    }
}
