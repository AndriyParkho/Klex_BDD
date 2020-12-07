package controller;

import views.SelectClient;

public class SelectClientControl {
	private SelectClient view;
	
	public SelectClientControl(SelectClient view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Fonctions");
	}
}
