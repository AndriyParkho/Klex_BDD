package controller;

import views.Fonctions;
import views.InsertChoice;

public class FonctionsControl {
	private Fonctions view;
	
	public FonctionsControl(Fonctions view) {
		this.view = view;
	}
	
	public void clicInsert() {
		new InsertChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}
	
	public void clicSelect() {
		// Page selectionner
	}
	
	public void clicDelete() {
		// Page supprimmer
	}
	
	public void clicDisconnect() {
		view.getSwitcherView().show(view.getContainerView(), "Se connecter");
	}
}
