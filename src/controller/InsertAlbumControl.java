package controller;

import java.sql.Date;

import views.InsertAlbum;
import views.InsertPisteBis;

public class InsertAlbumControl {
	private InsertAlbum view;
	
	public InsertAlbumControl(InsertAlbum view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		view.getFichierPiste().getAlbum().setDateSortie(Date.valueOf(view.getDateField().getText()));
		view.getFichierPiste().getAlbum().setGroupe(view.getGroupeField().getText());
		view.getFichierPiste().getAlbum().setUrlImagePochette(view.getPochetteField().getText());
		
		new InsertPisteBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierPiste());
	}
}
