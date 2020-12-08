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
		// R�cup�rer les infos du flux et les ajouter � la liste des flux de FichierPiste ou FichierFilm 
		if(view.getNumeroFlux() < view.getNbMaxFlux()) {
			// On r�ouvre une fenetre de flux car nous n'avons pas encore atteint le nombre de flux que l'util veut ajouter
			new InsertFlux(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getNumeroFlux() + 1, view.getNbMaxFlux(), view.getFichierFilm(), view.getFichierPiste());
		} else {
			// On passe � la fenetre d'apr�s
			new InsertFichier(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierFilm(), view.getFichierPiste());
		}
	}
}
