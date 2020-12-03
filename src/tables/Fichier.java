package tables;

public class Fichier {
    private long id = 0;
    private long taille = 0;
    private String dateDepot = "";
    private String email = "";
    private Contenu contenu = null; // film ou piste

    public Fichier(final long id, final long taille, final String dateDepot, final String email,
            final Contenu contenu) {
        this.id = id;
        this.taille = taille;
        this.dateDepot = dateDepot;
        this.email = email;
        this.contenu = contenu;
    }

    public Fichier() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getTaille() {
        return taille;
    }

    public void setTaille(final long taille) {
        this.taille = taille;
    }

    public String getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(final String dateDepot) {
        this.dateDepot = dateDepot;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Contenu getContenu() {
        return contenu;
    }

    public void setContenu(final Contenu contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Fichier [contenu=" + contenu + ", dateDepot=" + dateDepot + ", email=" + email + ", id=" + id
                + ", taille=" + taille + "]";
    }
}
