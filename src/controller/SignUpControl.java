package controller;

import dao.DAOUtilisateur;
import tables.Utilisateur;
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
		
	}

	public void clicValid() {
		// TODO Auto-generated method stub
		
	}

}
