package tables;

import java.util.HashMap;

public abstract class Contenu {
    protected HashMap<Artiste, String> artistes = new HashMap<Artiste, String>();

    public Contenu(HashMap<Artiste, String> artistes) {
        this.artistes = artistes;
    }

    public Contenu() {
    }

    public HashMap<Artiste, String> getArtistes() {
        return artistes;
    }

    public void setArtistes(HashMap<Artiste, String> artistes) {
        this.artistes = artistes;
    }

    @Override
    public String toString() {
        return "Contenu [artistes=" + artistes + "]";
    }
}
