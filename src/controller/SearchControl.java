package controller;

import transactions.TransactionSelectFilm;
import transactions.TransactionSelectPiste;
import views.Search;
import views.View;

public class SearchControl {
	private Search view;
	
	public SearchControl(Search view) {
		this.view = view;
	}
	
	public void clicSearch() {
		view.getTerminalLbl().setVisible(true);

		if(view.getFilmChoice().isSelected()) {
			TransactionSelectFilm.execute(view.getCategField().getText(), view.getClient(), View.getUtilConnected().getEmail());
		} else if(view.getPisteChoice().isSelected()) {
			TransactionSelectPiste.execute(view.getCategField().getText(), view.getClient(), View.getUtilConnected().getEmail());
		}
		
		view.getSwitcherView().show(view.getContainerView(), "Fonctions");
		view.getFenetre().setTitle("Fonctions");
		view.getFenetre().setSize(453, 350);
		view.getFenetre().setLocationRelativeTo(null);
	}

	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choisir client");
		view.getFenetre().setTitle("Choisir un client");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
