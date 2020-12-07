package controller;

import java.awt.event.WindowEvent;

import views.CreateClient;

public class CreateClientControl {
	private CreateClient view;

	public CreateClientControl(CreateClient view) {
		this.view = view;
	}
	
	public void clicValid() {
		// Récupérer toutes les infos du client dans chaque case dont la liste des codecs 
		// et appeler la transaction d'ajout d'un client
		
		view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING)); // La fenetre se ferme
	}
}
