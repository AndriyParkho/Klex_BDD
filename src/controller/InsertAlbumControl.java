package controller;

import views.InsertAlbum;
import views.InsertPisteBis;

public class InsertAlbumControl {
	private InsertAlbum view;

	public InsertAlbumControl(InsertAlbum view) {
		this.view = view;
	}

	public void clicSuiv() {
		// R�cup�rer les infos de l'album dans chaque case et cr�er l'objet Album pour l'ins�rer dans le model FichierPiste

		new InsertPisteBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierPiste());
	}
}
