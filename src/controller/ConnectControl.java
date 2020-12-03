package controller;

import java.sql.SQLException;

import javax.swing.*;

import dao.DAOUtilisateur;
import tables.Utilisateur;
import views.Connexion;

public class ConnectControl {
	private Utilisateur util;
	private DAOUtilisateur bddUtil = new DAOUtilisateur();
	private Connexion viewCnx;
	
	public ConnectControl(Connexion view) {
		this.viewCnx = view;
	}
	
	public void clicToConnect() {
		String email = viewCnx.getMailField().getText();
		int code = Integer.parseInt(viewCnx.getCodeField().getText());
		try {
			util = bddUtil.find(email);
			
		}catch(SQLException sqlE) {
			System.out.println("erreur");
			//fenetre erreur
		}
		if(util == null) {
			System.out.println("pas d'user");
			JDialog erreur = new JDialog(viewCnx.getFenetre(),"erreur");
			JLabel label = new JLabel("email ou mot de passe incorrect", SwingConstants.CENTER);
			erreur.add(label);
			erreur.setSize(250, 100);
			erreur.setLocationRelativeTo(null);
			erreur.setVisible(true);
			System.out.println(code);
			System.out.println(email);
		}else {
			int codeUser = util.getCode();
			if(code == codeUser) {
				System.out.println(codeUser);
				//renvoie sur nouvel page
			}else {
				System.out.println(email);//fenetre erreur
			}
		}
		// Ce qui va se passer quand on appui sur se Connecter
	}
	
}
