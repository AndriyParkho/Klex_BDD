package controller;

import views.FluxNbChoice;
import views.InsertChoice;
import views.InsertFilm;
import views.InsertFlux;

public class FluxNbChoiceControl {
	private FluxNbChoice view;
	
	public FluxNbChoiceControl(FluxNbChoice view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		int nbFluxMax = (int) view.getNombreField().getValue();
		// Créer un insert flux avec le model fichierFilm ou fichierPiste ça dépend le quel est non null
		// TODO
		new InsertFlux(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), 1, nbFluxMax);
	}
}
