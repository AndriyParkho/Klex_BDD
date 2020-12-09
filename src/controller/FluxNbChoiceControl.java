package controller;

import views.FluxNbChoice;
import views.InsertFlux;

public class FluxNbChoiceControl {
	private FluxNbChoice view;
	
	public FluxNbChoiceControl(FluxNbChoice view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		int nbFluxMax = (int) view.getNombreField().getValue();
		new InsertFlux(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), 1, nbFluxMax, view.getFichierFilm(), view.getFichierPiste());
	}
}
