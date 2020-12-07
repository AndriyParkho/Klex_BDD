package controller;

import java.awt.event.WindowEvent;

import views.CreateArtiste;

public class CreateArtisteControl {
	private CreateArtiste view;

	public CreateArtisteControl(CreateArtiste view) {
		this.view = view;
	}
	
	public void clicValid() {
		// R�cup�rer toutes les infos de l'artiste dans chaque case 
		// et rajoute l'artiste � la liste d'artiste du mod�le fichierFilm ou fichierPiste (celui qui est non null)
		
		view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING)); // La fenetre se ferme
	}
}
