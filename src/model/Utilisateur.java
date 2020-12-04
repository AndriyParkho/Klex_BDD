package model;

import java.util.HashSet;

public class Utilisateur {
    private String email = "";
    // champs par défaut NOT null
    private String nom = "default_name";
    private String prenom = "default_surname";
    private int age = 30;
    private String langueDiffusion = "Français";
    private int code = 9999;
    private HashSet<Fichier> fichiers = new HashSet<Fichier>();

    public Utilisateur(String email, String nom, String prenom, int age, String langueDiffusion, int code,
            HashSet<Fichier> fichiers) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.langueDiffusion = langueDiffusion;
        this.code = code;
        this.fichiers = fichiers;
    }

    public Utilisateur(String email) {
        this.email = email;
    }

    public Utilisateur() {
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public HashSet<Fichier> getFichiers() {
        return fichiers;
    }

    public void setFichiers(HashSet<Fichier> fichiers) {
        this.fichiers = fichiers;
    }

    public void addFichier(Fichier fichier) {
        this.fichiers.add(fichier);
    }

    @Override
    public String toString() {
        return "Utilisateur [age=" + age + ", code=" + code + ", email=" + email + ", fichiers=" + fichiers
                + ", langueDiffusion=" + langueDiffusion + ", nom=" + nom + ", prenom=" + prenom + "]";
    }
}
