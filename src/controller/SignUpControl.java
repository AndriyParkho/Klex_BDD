package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOUtilisateur;
import model.Utilisateur;
import transactions.TransactionUtilisateur;
import views.Accueil;
import views.Connexion;
import views.SignUp;

public class SignUpControl {
	private SignUp view;
	private DAOUtilisateur bddUtil = new DAOUtilisateur();
	private Utilisateur util = new Utilisateur();
	
	public SignUpControl(SignUp view) {
		this.view = view;
	}
	
	public void clicBack() {
		// TODO Auto-generated method stub
		new Accueil(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}

	public void clicValid() {
		String email = view.getMailField().getText();
		DAOUtilisateur utilisateurDAO = new DAOUtilisateur();
		util = new Utilisateur(email);
		
		Connection connection = ConnectionOracle.getInstance();
		
		try(ResultSet resUtil = utilisateurDAO.find(email)){
			if(resUtil.next()) {
				System.out.println("l'email existe deja");
				JDialog erreur = new JDialog(view.getFenetre(),"erreur");
				JLabel label = new JLabel("l'email existe deja", SwingConstants.CENTER);
				erreur.add(label);
				erreur.setSize(300, 100);
				erreur.setLocationRelativeTo(null);
				erreur.setVisible(true);
			}else {
				String nom = view.getNomField().getText();
				String prenom = view.getPrenomField().getText();
				int age = (int)(view.getAgeField().getValue());
				String langueDiffusion = view.getLangueField().getText();
				int code = Integer.parseInt(view.getCodeField().getText());
				util = new Utilisateur(email, nom, prenom, age, langueDiffusion, code);
				System.out.println(util);
				TransactionUtilisateur.execute(util);
				new Connexion(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
			}
			connection.commit();
		}catch(SQLException e) {
			System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);

            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep) {
                    JDBCUtilities.printSQLException(excep);
                }
            }
        } finally {
            ConnectionOracle.closeInstance();
        }
	}
}
