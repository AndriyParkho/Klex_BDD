package controller;

import views.Acceuil;
import views.Connexion;

public class AcceuilControl {
	private Acceuil view;
	
	public AcceuilControl(Acceuil view) {
		this.view= view;
	}
	
	public void clicConnect() {
		new Connexion(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}
	
	public void clicSignUp() {
		// On switch sur la view pour s'inscrire
	}
}
