package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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

	public void clicToConnect() {
		
		String email = view.getMailField().getText();
		int code = Integer.parseInt(view.getCodeField().getText());
		
		DAOUtilisateur utilisateurDAO = new DAOUtilisateur();
		//util.setCode(code);
		try(ResultSet resUtil = utilisateurDAO.find(email)) {
			if(!resUtil.next()) {
				System.out.println("pas d'user");
				JDialog erreur = new JDialog(view.getFenetre(),"erreur");
				JLabel label = new JLabel("email incorrect", SwingConstants.CENTER);
				erreur.add(label);
				erreur.setSize(250, 100);
				erreur.setLocationRelativeTo(null);
				erreur.setVisible(true);
				
			}else {
				util = new Utilisateur(email);
				//int codeUser = util.getCode();
				//if(code = codeUtilisateur Trouvé){
					//util = utilisateurtrouv
					//View.setUtilConnecte;
					
					new Fonctions(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
//				}else {
//					System.out.println("pas d'user");
//					JDialog erreur = new JDialog(view.getFenetre(),"erreur");
//					JLabel label = new JLabel("email incorrect", SwingConstants.CENTER);
//					erreur.add(label);
//					erreur.setSize(250, 100);
//					erreur.setLocationRelativeTo(null);
//					erreur.setVisible(true);
//				}
			}
			
		}catch(SQLException sqlE) {
			System.out.println("erreur");
			//fenetre erreur
		}
		//Ce qui va se passer quand on appui sur se Connecter
	}
	
}
