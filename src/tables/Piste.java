package tables;

import java.util.HashMap;
import java.util.HashSet;

public class Piste extends Contenu {
    private long id = 0;
    private int num = 0;
    private String titre = "";
    private String duree = "";
    private HashSet<CategorieMusique> categoriesMusique = new HashSet<CategorieMusique>();
    private Album album = null;

    public Piste(HashMap<Artiste, String> artistes, long id, int num, String titre, String duree,
            HashSet<CategorieMusique> categoriesMusique, Album album) {
        super(artistes);
        this.id = id;
        this.num = num;
        this.titre = titre;
        this.duree = duree;
        this.categoriesMusique = categoriesMusique;
        this.album = album;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public HashSet<CategorieMusique> getCategoriesMusique() {
        return categoriesMusique;
    }

    public void setCategoriesMusique(HashSet<CategorieMusique> categoriesMusique) {
        this.categoriesMusique = categoriesMusique;
    }

    public void addCategorieMusique(CategorieMusique categorieMusique) {
        this.categoriesMusique.add(categorieMusique);
    }

    public void addArtiste(Artiste artiste, String instrument) {
        this.artistes.put(artiste, instrument);
    }

    public String getInstrument(Artiste artiste) {
        return this.artistes.get(artiste);
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((album == null) ? 0 : album.hashCode());
        result = prime * result + ((categoriesMusique == null) ? 0 : categoriesMusique.hashCode());
        result = prime * result + ((duree == null) ? 0 : duree.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + num;
        result = prime * result + ((titre == null) ? 0 : titre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (!super.equals(obj)) return false;
        Piste other = (Piste) obj;
        if (album == null) {
            if (other.album != null)
                return false;
        } else if (!album.equals(other.album))
            return false;
        if (categoriesMusique == null) {
            if (other.categoriesMusique != null)
                return false;
        } else if (!categoriesMusique.equals(other.categoriesMusique))
            return false;
        if (duree == null) {
            if (other.duree != null)
                return false;
        } else if (!duree.equals(other.duree))
            return false;
        if (id != other.id)
            return false;
        if (num != other.num)
            return false;
        if (titre == null) {
            if (other.titre != null)
                return false;
        } else if (!titre.equals(other.titre))
            return false;
        return true;
    }
}
