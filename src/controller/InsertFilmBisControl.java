package controller;

import views.CreateArtiste;
import views.InsertFilmBis;

public class InsertFilmBisControl {
	private InsertFilmBis view;
	
	public InsertFilmBisControl(InsertFilmBis view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO
		
		// Si un artiste existe pas
		new CreateArtiste("Exemple");
	}
}
