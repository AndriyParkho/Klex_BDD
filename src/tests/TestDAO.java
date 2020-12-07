package tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import connections.ConnectionOracle;
import connections.JDBCUtilities;

import model.Utilisateur;
import dao.DAOUtilisateur;

public class TestDAO {

    public static void main(String args []) {
        boolean connecte = true;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("1) S'inscrire (Tapez 1)");
            System.out.println("2) S'authentifier (Tapez 2)");

            String answer = br.readLine();

            DAOUtilisateur fonctionsUtilisateur = new DAOUtilisateur();

            if (answer.equals("1")) {
                System.out.println("Entrez un email");
                String email = br.readLine();
                System.out.println("Entrez un nom");
                String nom = br.readLine();
                System.out.println("Entrez un prenom");
                String prenom = br.readLine();
                System.out.println("Entrez un age");
                String age = br.readLine();
                System.out.println("Entrez une langue de diffusion");
                String langueUtilisateur = br.readLine();
                System.out.println("Entrez un code"); //Comment garder les 0 devant?
                String code = br.readLine();

                Utilisateur utilisateur = new Utilisateur(email, nom, prenom, Integer.parseInt(age), langueUtilisateur, Integer.parseInt(code));

                fonctionsUtilisateur.create(utilisateur);

                System.out.println("Inscription terminée.");
                fonctionsUtilisateur.getConnection().commit();
            }
            else {
                System.out.println("Donnez votre email");
                String votreEmail = br.readLine();
                System.out.println("Donnez votre code");
                String votreCode = br.readLine();

                ResultSet rs = fonctionsUtilisateur.find(votreEmail);

                int resCode = -1;

                while (rs.next()) {resCode = rs.getInt(6);}

                if (rs != null && Integer.parseInt(votreCode) == resCode) {
                    System.out.println("Ok le code est bon, je vous fais entrer");
                }
                else {
                    System.out.println("Code incorrect...");
                    connecte = false;
                }
            }

            if (connecte) {
                //TODO AJOUT INFORMATIONS
            }

        } catch (SQLException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        finally {ConnectionOracle.closeInstance();}
    }

}