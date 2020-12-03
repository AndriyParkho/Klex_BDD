package controller;

import java.sql.SQLException;

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
		System.out.println(code);
		try {
			util = bddUtil.find(email);
			int codeUser = util.getCode();
			if(code == codeUser) {
				System.out.println(code);//renvoie sur nouvel page
			}else {
				System.out.println(email);//fenetre erreur
			}
			
		}catch(SQLException sqlE) {
			System.out.println("erreur");
			//fenetre erreur
		}
		// Ce qui va se passer quand on appui sur se Connecter
	}
	
}
