package controller;

import views.InsertFichier;
import views.InsertFlux;

public class InsertFluxControl {
	private InsertFlux view;
	
	public InsertFluxControl(InsertFlux view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO
		// Récupérer les infos du flux et les ajouter à la liste des flux de FichierPiste ou FichierFilm 
		if(view.getNumeroFlux() < view.getNbMaxFlux()) {
			// On réouvre une fenetre de flux car nous n'avons pas encore atteint le nombre de flux que l'util veut ajouter
			new InsertFlux(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getNumeroFlux() + 1, view.getNbMaxFlux(), view.getFichierFilm(), view.getFichierPiste());
		} else {
			// On passe à la fenetre d'après
			new InsertFichier(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierFilm(), view.getFichierPiste());
		}
	}
}
