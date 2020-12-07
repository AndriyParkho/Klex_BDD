package controller;

import views.DeletePiste;
import views.InsertAlbum;

public class DeletePisteControl {
	private DeletePiste view;
	
	public DeletePisteControl(DeletePiste view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// Récupère les infos de la piste et lance la fonction de suppression de la piste
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix supprimer");
		view.getFenetre().setTitle("Choix de suppression");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
