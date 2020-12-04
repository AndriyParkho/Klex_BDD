package model;

import java.util.HashMap;
import java.util.Objects;

public abstract class Contenu {
    protected HashMap<Artiste, String> artistes = new HashMap<Artiste, String>();

    public Contenu(final HashMap<Artiste, String> artistes) {
        this.artistes = artistes;
    }

    public Contenu() {
    }

    public HashMap<Artiste, String> getArtistes() {
        return artistes;
    }

    public void setArtistes(final HashMap<Artiste, String> artistes) {
        this.artistes = artistes;
    }

    @Override
    public String toString() {
        return "Contenu [artistes=" + artistes + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contenu other = (Contenu) obj;
        if (artistes == null) {
            if (other.artistes != null)
                return false;
        } else if (!artistes.equals(other.artistes))
            return false;
        return true;
    }
}
