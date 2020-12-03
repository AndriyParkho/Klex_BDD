package tables;

import java.util.HashSet;

public class Album {
    private long id = 0;
    private String titre = "";
    private String groupe = "";
    private String dateSortie = "";
    private String urlImagePochette = "";
    private HashSet<CategorieMusique> categoriesMusique = new HashSet<CategorieMusique>();

    public Album(long id, String titre, String groupe, String dateSortie, String urlImagePochette,
            HashSet<CategorieMusique> categoriesMusique) {
        this.id = id;
        this.titre = titre;
        this.groupe = groupe;
        this.dateSortie = dateSortie;
        this.urlImagePochette = urlImagePochette;
        this.categoriesMusique = categoriesMusique;
    }

    public Album() {
	}

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

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getUrlImagePochette() {
        return urlImagePochette;
    }

    public void setUrlImagePochette(String urlImagePochette) {
        this.urlImagePochette = urlImagePochette;
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

    @Override
    public String toString() {
        return "Album [categoriesMusique=" + categoriesMusique + ", dateSortie=" + dateSortie + ", groupe=" + groupe
                + ", id=" + id + ", titre=" + titre + ", urlImagePochette=" + urlImagePochette + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoriesMusique == null) ? 0 : categoriesMusique.hashCode());
        result = prime * result + ((dateSortie == null) ? 0 : dateSortie.hashCode());
        result = prime * result + ((groupe == null) ? 0 : groupe.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((titre == null) ? 0 : titre.hashCode());
        result = prime * result + ((urlImagePochette == null) ? 0 : urlImagePochette.hashCode());
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
        Album other = (Album) obj;
        if (categoriesMusique == null) {
            if (other.categoriesMusique != null)
                return false;
        } else if (!categoriesMusique.equals(other.categoriesMusique))
            return false;
        if (dateSortie == null) {
            if (other.dateSortie != null)
                return false;
        } else if (!dateSortie.equals(other.dateSortie))
            return false;
        if (groupe == null) {
            if (other.groupe != null)
                return false;
        } else if (!groupe.equals(other.groupe))
            return false;
        if (id != other.id)
            return false;
        if (titre == null) {
            if (other.titre != null)
                return false;
        } else if (!titre.equals(other.titre))
            return false;
        if (urlImagePochette == null) {
            if (other.urlImagePochette != null)
                return false;
        } else if (!urlImagePochette.equals(other.urlImagePochette))
            return false;
        return true;
    }
}
