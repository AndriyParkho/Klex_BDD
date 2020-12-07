package controller;

import views.DeleteFilm;

public class DeleteFilmControl {
	private DeleteFilm view;
	
	public DeleteFilmControl(DeleteFilm view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// Récupère les id du film et lance la fonction de suppression du film 
		
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix supprimer");
		view.getFenetre().setTitle("Choix de suppression");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
