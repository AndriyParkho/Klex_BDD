package controller;

import views.DeleteFilm;

public class DeleteFilmControl {
	private DeleteFilm view;
	
	public DeleteFilmControl(DeleteFilm view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO
		
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix supprimer");
	}
}
