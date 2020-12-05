package controller;

import views.InsertFilm;
import views.InsertFilmBis;

public class InsertFilmControl {
	private InsertFilm view;
	
	public InsertFilmControl(InsertFilm view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO
		
		// Si le film n'existe pas dans la bdd
		new InsertFilmBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFilm());
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix insertion");
	}
}
