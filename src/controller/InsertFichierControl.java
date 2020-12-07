package controller;

import views.InsertFichier;

public class InsertFichierControl {
	private InsertFichier view;
	
	public InsertFichierControl(InsertFichier view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// Vérifie le quel des deux FichierFilm ou FichierPiste est null et
		// Ajoute les infos du fichier en récupérant les infos dans les champs et lance la transaction insert selon le fichier 
		

		view.getSwitcherView().show(view.getContainerView(), "Fonctions");
		view.getFenetre().setTitle("Fonctions");
		view.getFenetre().setSize(453, 350);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
