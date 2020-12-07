package tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Statement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import org.apache.ibatis.jdbc.ScriptRunner;

import connections.ConnectionOracle;
import connections.JDBCUtilities;

public class TestTransaction {

    public static void main(String args[]) {
        Connection connection = ConnectionOracle.getInstance();

        try {
            PreparedStatement ps1 = connection.prepareStatement("INSERT INTO Album VALUES (idAlbum_seq.nextval, ?, ?, ?, ?)");
            PreparedStatement ps2 = connection.prepareStatement("INSERT INTO CategorieMusique VALUES (?)");
            PreparedStatement ps3 = connection.prepareStatement("INSERT INTO AlbumAPourCategorie VALUES (idAlbum_seq.currval, ?)");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            boolean fini = false;

            while(!fini) {
              try {
              System.out.println("Entrez un titre d'album");
              try {
                  String s = br.readLine();
                  ps1.setString(1, s);
              } catch (IOException e) {System.out.println("Erreur s1");}

              System.out.println("Entrez un nom de groupe");
              try {
                  String s2 = br.readLine();
                  ps1.setString(2, s2);
              } catch (IOException e) {System.out.println("Erreur s2");}

              System.out.println("Entrez une date de sortie d'un album");
              try {
                  String s3 = br.readLine();
                  ps1.setDate(3, Date.valueOf(s3));
              } catch (IOException e) {System.out.println("Erreur s3");}

              System.out.println("Entrez l'url de l'image de la pochette");
              try {
                  String s4 = br.readLine();
                  ps1.setString(4, s4);
              } catch (IOException e) {System.out.println("Erreur s4");}

              System.out.println("Entrez la catégorie de la musique");
              try {
                  String s5 = br.readLine();
                  ps2.setString(1, s5);
                  ps3.setString(1, s5);
              } catch (IOException e) {System.out.println("Erreur s5");}

              ps1.executeUpdate();
              ps2.executeUpdate();
              ps3.executeUpdate();

              } catch(SQLException e) {}
                
              System.out.println("Voulez-vous encore entrer des données?");
              try {
                  String continuer = br.readLine();
                  if (!continuer.equals("Yes")) fini = true;
              } catch(IOException e) {System.out.println("Erreur fini");}
            }
            System.out.println("Commit");
            try {
                String answer = br.readLine();
                if (answer.equals("Yes")) connection.commit();
                System.out.println("Succès de tous les ajouts !");
            } catch(IOException e) {System.out.println("Erreur d'ajouts");}

        } catch (SQLException e) {e.printStackTrace();}
          finally {ConnectionOracle.closeInstance();}
    }
}
