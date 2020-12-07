package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.ConnectionOracle;
import dao.DAOUtilisateur;
import model.Utilisateur;

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
                ConnectionOracle.getInstance().commit();
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
                System.out.println("Souhaitez-vous entrer un film (tapez film)/une piste(tapez piste)");
                answer = br.readLine();
                if (answer.equals("film")) {
                    //FLOW FILM
                    System.out.println("Ajout d'un film :");
                }
                else {
                    //FLOW PISTE
                    System.out.println("Ajout d'une piste :");
                }
            }

        } catch (SQLException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        finally {ConnectionOracle.closeInstance();}
    }

}