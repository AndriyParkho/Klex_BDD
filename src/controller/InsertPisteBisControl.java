package controller;

import views.CreateArtiste;
import views.FluxNbChoice;
import views.InsertPisteBis;

public class InsertPisteBisControl {
	private InsertPisteBis view;
	
	public InsertPisteBisControl(InsertPisteBis view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO
		new FluxNbChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
		// Si un artiste existe pas
//		new CreateArtiste("Exemple");
	}
}
