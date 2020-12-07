package model;

import java.sql.Date;

public class ImgExtraiteFilm {
    private String urlImg = "";
    private String titreFilm = "";
    private Date anneeSortie = null;

    public ImgExtraiteFilm(final String urlImg, final String titreFilm, final Date anneeSortie) {
        this.urlImg = urlImg;
        this.titreFilm = titreFilm;
        this.anneeSortie = anneeSortie;
    }

    public ImgExtraiteFilm() {
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(final String urlImg) {
        this.urlImg = urlImg;
    }

    public String getTitreFilm() {
        return titreFilm;
    }

    public void setTitreFilm(final String titreFilm) {
        this.titreFilm = titreFilm;
    }

    public Date getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(final Date anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    @Override
    public String toString() {
        return "ImgExtraiteFilm [anneeSortie=" + anneeSortie + ", titreFilm=" + titreFilm + ", urlImg=" + urlImg + "]";
    }
}
