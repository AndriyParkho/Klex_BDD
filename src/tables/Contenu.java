package tables;

import java.util.HashMap;

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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artistes == null) ? 0 : artistes.hashCode());
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
        Contenu other = (Contenu) obj;
        if (artistes == null) {
            if (other.artistes != null)
                return false;
        } else if (!artistes.equals(other.artistes))
            return false;
        return true;
    }
}
