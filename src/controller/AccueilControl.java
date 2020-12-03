package controller;

import views.Accueil;
import views.Connexion;
import views.SignUp;

public class AccueilControl {
	private Accueil view;
	
	public AccueilControl(Accueil view) {
		this.view= view;
	}
	
	public void clicConnect() {
		new Connexion(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}
	
	public void clicSignUp() {
		new SignUp(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}
}
