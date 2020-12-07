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
		// TODO 3 cas à distinguer :
		// Soit l'album et la piste existe donc les récup dans la bdd + ajouter dans le FichierPiste et envoyer écran flux
		// Soit la piste n'existe pas mais l'album si donc récup album et afficher écran créer piste
		// Soit aucun des deux n'existe donc écran album (puis y aura écran piste) mais garder le peu d'info de la piste et album
		
		
		new InsertAlbum(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierPiste());
//		new InsertPisteBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getPiste(), view.getFichierPiste());
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix insertion");
	}
}
