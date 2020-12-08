package controller;

import java.awt.event.WindowEvent;

import model.aggregates.FichierFilm;
import views.CreateArtiste;

public class CreateArtisteControl {
	private CreateArtiste view;

	public CreateArtisteControl(CreateArtiste view) {
		this.view = view;
	}
	
	public void clicValid() {
		// R�cup�rer toutes les infos de l'artiste dans chaque case 
		// et rajoute l'artiste � la liste d'artiste du mod�le fichierFilm ou fichierPiste (celui qui est non null)
		
		String nomArtiste = view.getNomField().getText();
		String dateArtiste = view.getDateField().getText();
		String specArtiste = view.getSpecField().getText();
		String photoArtiste = view.getPhotoField().getText();
		String bioArtiste = view.getBioField().getText();
		FichierFilm fichier = view.getFichierFilm();
		
		view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING)); // La fenetre se ferme
	}
}
