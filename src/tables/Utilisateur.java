package src.tables;

import java.util.ArrayList;

public class Utilisateur {
    private String email = "";
    private String nom = "";
    private String prenom = "";
    private String age = "";
    private String langueDiffusion = "";
    private int code = 0;
    private ArrayList<Fichier> fichiers = new ArrayList<Fichier>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLangueDiffusion() {
        return langueDiffusion;
    }

    public void setLangueDiffusion(String langueDiffusion) {
        this.langueDiffusion = langueDiffusion;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<Fichier> getFichiers() {
        return fichiers;
    }

    public void setFichiers(ArrayList<Fichier> fichiers) {
        this.fichiers = fichiers;
    }

    public void addFichier(Fichier fichier) {
        this.fichiers.add(fichier);
    }

    public Fichier getFichier(int index) {
        return this.fichiers.get(index);
    }
}
