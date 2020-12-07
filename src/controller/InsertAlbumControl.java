package controller;

import views.InsertAlbum;
import views.InsertPisteBis;

public class InsertAlbumControl {
	private InsertAlbum view;
	
	public InsertAlbumControl(InsertAlbum view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO
		new InsertPisteBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getPiste());
	}
}
