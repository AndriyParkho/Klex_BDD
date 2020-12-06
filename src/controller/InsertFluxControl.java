package controller;

import views.InsertFlux;

public class InsertFluxControl {
	private InsertFlux view;
	
	public InsertFluxControl(InsertFlux view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		if(view.getNumeroFlux() <= view.getNbMaxFlux()) {
			// TODO
			new InsertFlux(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getNumeroFlux() + 1, view.getNbMaxFlux());
		} else {
			// TODO
			view.getSwitcherView().show(view.getContainerView(), "Fonctions");
		}
	}
}
