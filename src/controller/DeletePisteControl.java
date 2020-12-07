package controller;

import views.DeletePiste;
import views.InsertAlbum;

public class DeletePisteControl {
	private DeletePiste view;
	
	public DeletePisteControl(DeletePiste view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix supprimer");
	}
}
