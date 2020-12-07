package controller;

import views.SelectClient;

public class SelectClientControl {
	private SelectClient view;
	
	public SelectClientControl(SelectClient view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO Vérifier si client existe si oui ouvrir un dialogue disant d'aller voir le terminal pour continuer
		// Sinon ouvrir fenetre CreateClient en recupérant les infos saisies
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Fonctions");
		view.getFenetre().setTitle("Fonctions");
		view.getFenetre().setSize(453, 350);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
