package controller;

import views.InsertChoice;
import views.InsertFilm;

public class InsertChoiceControl {
	private InsertChoice view;
	
	public InsertChoiceControl(InsertChoice view) {
		this.view = view;
	}
	
	public void clicValide() {
		// TODO
		new InsertFilm(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Fonctions");
	}
}
