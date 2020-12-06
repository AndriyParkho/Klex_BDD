package controller;

import views.InsertChoice;
import views.InsertFilm;
import views.InsertPiste;

public class InsertChoiceControl {
	private InsertChoice view;
	
	public InsertChoiceControl(InsertChoice view) {
		this.view = view;
	}
	
	public void clicValide() {
		if(view.getFilmChoice().isSelected())
			new InsertFilm(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
		if(view.getPisteChoice().isSelected())
			new InsertPiste(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Fonctions");
	}
}
