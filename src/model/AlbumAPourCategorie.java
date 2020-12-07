package model;

public class AlbumAPourCategorie {
    private long idAlbum;
    private String typeCategorieMusique;

    public long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(long idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTypeCategorieMusique() {
        return typeCategorieMusique;
    }

    public void setTypeCategorieMusique(String typeCategorieMusique) {
        this.typeCategorieMusique = typeCategorieMusique;
    }

    @Override
    public String toString() {
        return "AlbumAPourCategorie [idAlbum=" + idAlbum + ", typeCategorieMusique=" + typeCategorieMusique + "]";
    }
}
