package controller;

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
				// email non trouvé dans la BDD
				JDialog erreur = new JDialog(view.getFenetre(),"erreur");
				JLabel label = new JLabel("email incorrect", SwingConstants.CENTER);
				erreur.add(label);
				erreur.setSize(250, 100);
				erreur.setLocationRelativeTo(null);
				erreur.setVisible(true);
				
			}else {
				util = new Utilisateur(email, rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
				int codeUser = util.getCode();
				
				if (code == codeUser) {
					// code bon
					View.setUtilConnected(util);
					new Fonctions(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
				} else {
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
}
