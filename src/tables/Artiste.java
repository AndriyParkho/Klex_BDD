package src.tables;

import java.util.ArrayList;

public abstract class Artiste {
    private long id = 0;
    private String nom = "";
    private String dateNaissance = "";
    private String urlPhoto = "";
    private String specialite = "";
    private String biographie = "";
    private String roleFilm = "";
    private String instrumentPiste = "";
    private ArrayList<Piste> pistes = new ArrayList<Piste>();
    private ArrayList<Film> films = new ArrayList<Film>();

    public String getInstrumentPiste() {
        return instrumentPiste;
    }

    public void setInstrumentPiste(String instrumentPiste) {
        this.instrumentPiste = instrumentPiste;
    }
    
    public String getRoleFilm() {
        return roleFilm;
    }

    public void setRoleFilm(String roleFilm) {
        this.roleFilm = roleFilm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public ArrayList<Piste> getPistes() {
        return pistes;
    }

    public void setPistes(ArrayList<Piste> pistes) {
        this.pistes = pistes;
    }

    public void addPiste(Piste piste) {
        this.pistes.add(piste);
    }

    public Piste getPiste(int index) {
        return this.pistes.get(index);
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film) {
        this.films.add(film);
    }

    public Film getFilm(int index) {
        return this.films.get(index);
    }
}
