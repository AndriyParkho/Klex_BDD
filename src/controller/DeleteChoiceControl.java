package controller;

import views.DeleteChoice;
import views.DeleteFilm;
import views.DeletePiste;

public class DeleteChoiceControl {
	private DeleteChoice view;
	
	public DeleteChoiceControl(DeleteChoice view) {
		this.view = view;
	}
	
	public void clicValide() {
		if(view.getFilmChoice().isSelected())
			new DeleteFilm(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
		if(view.getPisteChoice().isSelected())
			new DeletePiste(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Fonctions");
		view.getFenetre().setTitle("Fonctions");
		view.getFenetre().setSize(453, 350);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
