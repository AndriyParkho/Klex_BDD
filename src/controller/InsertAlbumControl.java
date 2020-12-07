package controller;

import views.InsertAlbum;
import views.InsertPisteBis;

public class InsertAlbumControl {
	private InsertAlbum view;
	
	public InsertAlbumControl(InsertAlbum view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// Récupérer les infos de l'album dans chaque case et créer l'objet Album pour l'insérer dans le model FichierPiste
		
		new InsertPisteBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierPiste());
	}
}
