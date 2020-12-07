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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((anneeSortie == null) ? 0 : anneeSortie.hashCode());
        result = prime * result + ((titreFilm == null) ? 0 : titreFilm.hashCode());
        result = prime * result + ((urlImg == null) ? 0 : urlImg.hashCode());
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
        ImgExtraiteFilm other = (ImgExtraiteFilm) obj;
        if (anneeSortie == null) {
            if (other.anneeSortie != null)
                return false;
        } else if (!anneeSortie.equals(other.anneeSortie))
            return false;
        if (titreFilm == null) {
            if (other.titreFilm != null)
                return false;
        } else if (!titreFilm.equals(other.titreFilm))
            return false;
        if (urlImg == null) {
            if (other.urlImg != null)
                return false;
        } else if (!urlImg.equals(other.urlImg))
            return false;
        return true;
    }
}
