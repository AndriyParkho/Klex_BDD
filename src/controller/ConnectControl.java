package controller;

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
//	private DAOUtilisateur bddUtil = new DAOUtilisateur();
	private Connexion view;
	
	public ConnectControl(Connexion view) {
		this.view = view;
	}

	public void clicToConnect() {
//		String email = view.getMailField().getText();
//		int code = Integer.parseInt(view.getCodeField().getText());
//		try {
//			util = bddUtil.find(email);
//			
//		}catch(SQLException sqlE) {
//			System.out.println("erreur");
//			//fenetre erreur
//		}
//		if(util == null) {
//			System.out.println("pas d'user");
//			JDialog erreur = new JDialog(view.getFenetre(),"erreur");
//			JLabel label = new JLabel("email ou mot de passe incorrect", SwingConstants.CENTER);
//			erreur.add(label);
//			erreur.setSize(250, 100);
//			erreur.setLocationRelativeTo(null);
//			erreur.setVisible(true);
//			System.out.println(code);
//			System.out.println(email);
//		}else {
//			int codeUser = util.getCode();
//			if(code == codeUser) {
//				System.out.println(codeUser);
//				View.setUtilConnected(util);
				new Fonctions(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
//			}else {
//				System.out.println(email);//fenetre erreur
//			}
//		}
		// Ce qui va se passer quand on appui sur se Connecter
	}
	
}
