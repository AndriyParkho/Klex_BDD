package tables;

public class ImgExtraiteFilm {
    private String urlImg = "";
    private String titreFilm = "";
    private String anneeSortie = "";

    public ImgExtraiteFilm(String urlImg, String titreFilm, String anneeSortie) {
        this.urlImg = urlImg;
        this.titreFilm = titreFilm;
        this.anneeSortie = anneeSortie;
    }
    
    public ImgExtraiteFilm() {
	}

	public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getTitreFilm() {
        return titreFilm;
    }

    public void setTitreFilm(String titreFilm) {
        this.titreFilm = titreFilm;
    }

    public String getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    @Override
    public String toString() {
        return "ImgExtraiteFilm [anneeSortie=" + anneeSortie + ", titreFilm=" + titreFilm + ", urlImg=" + urlImg + "]";
    }
}
