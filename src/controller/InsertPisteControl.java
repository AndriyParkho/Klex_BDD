package controller;

import views.InsertAlbum;
import views.InsertPiste;
import views.InsertPisteBis;

public class InsertPisteControl {
	private InsertPiste view;
	
	public InsertPisteControl(InsertPiste view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO 3 cas � distinguer :
		// Soit l'album et la piste existe donc envoyer �cran flux
		// Soit la piste n'existe pas mais l'album si donc �cran cr�er piste
		// Soit aucun des deux n'existe donc �cran album (puis y aura �cran piste)
		
		// Si le film n'existe pas dans la bdd
		new InsertAlbum(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getPiste());
//		new InsertPisteBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getPiste());
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix insertion");
	}
}
