package controller;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOUtilisateur;
import model.Utilisateur;
import views.Connexion;
import views.Fonctions;
import views.View;

public class ConnectControl {
	private Utilisateur util;
	private Connexion view;
	
	public ConnectControl(Connexion view) {
		this.view = view;
	}

	public void clicToConnect() throws SQLException {
		
		String email = view.getMailField().getText();
		int code = Integer.parseInt(view.getCodeField().getText());
		
		DAOUtilisateur utilisateurDAO = new DAOUtilisateur();
//		System.out.println(JDBCUtilities.dumpResultSet(utilisateurDAO.find(email)));
		
		try(ResultSet rs = utilisateurDAO.find(email)) {
			if(!rs.next()) {
				System.out.println("Email non trouvé");
				// email non trouvé dans la BDD
				JDialog erreur = new JDialog(view.getFenetre(),"erreur");
				JLabel label = new JLabel("email incorrect", SwingConstants.CENTER);
				erreur.add(label);
				erreur.setSize(250, 100);
				erreur.setLocationRelativeTo(null);
				erreur.setVisible(true);
				
			}else {
				System.out.println("Email trouvé");
				util = new Utilisateur(email, rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6));
				int codeUser = util.getCode();

				
				if (code == codeUser) {
					System.out.println("Code bon");
					// code bon
					View.setUtilConnected(util);
					new Fonctions(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
				} else {
					System.out.println("code incorrect");
					// code incorrect
					JDialog erreur = new JDialog(view.getFenetre(),"erreur");
					JLabel label = new JLabel("code incorrect", SwingConstants.CENTER);
					erreur.add(label);
					erreur.setSize(250, 100);
					erreur.setLocationRelativeTo(null);
					erreur.setVisible(true);
				}
			}
			
		}catch(SQLException sqlE) {
			System.out.println("erreur");
			//fenetre erreur
		}
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Acceuil");
		view.getFenetre().setTitle("Acceuil");
		view.getFenetre().setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.2));
        view.getFenetre().setLocationRelativeTo(null);
	}
}
