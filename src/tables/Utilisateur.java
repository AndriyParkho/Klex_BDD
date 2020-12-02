package tables;

import java.util.ArrayList;

public class Utilisateur {
    private String email = "";
    private String nom = "";
    private String prenom = "";
    private int age = 0;
    private String langueDiffusion = "";
    private int code = 0;
    private ArrayList<Fichier> fichiers = new ArrayList<Fichier>();

    public Utilisateur(String email, String nom, String prenom, int age, String langueDiffusion, int code,
            ArrayList<Fichier> fichiers) {
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

    public ArrayList<Fichier> getFichiers() {
        return fichiers;
    }

    public void setFichiers(ArrayList<Fichier> fichiers) {
        this.fichiers = fichiers;
    }

    public void addFichier(Fichier fichier) {
        if (!this.fichiers.contains(fichier)) {
            this.fichiers.add(fichier);
        }
    }

    public Fichier getFichier(int index) {
        return this.fichiers.get(index);
    }

    @Override
    public String toString() {
        return "Utilisateur [age=" + age + ", code=" + code + ", email=" + email + ", fichiers=" + fichiers
                + ", langueDiffusion=" + langueDiffusion + ", nom=" + nom + ", prenom=" + prenom + "]";
    }
}
