package controller;

import views.Search;

public class SearchControl {
	private Search view;
	
	public SearchControl(Search view) {
		this.view = view;
	}
	
	public void clicSearch() {
		view.getTerminalLbl().setVisible(true);
		// Lance le programme de selection
	}

	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choisir client");
		view.getFenetre().setTitle("Choisir un client");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
