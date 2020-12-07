package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOAlbum;
import dao.DAOFactory;
import dao.DAOPiste;
import model.Album;
import model.Piste;
import views.InsertAlbum;
import views.InsertPiste;

public class InsertPisteControl {
	private InsertPiste view;
	private DAOPiste bddPiste = DAOFactory.getPisteDAO();
	private DAOAlbum bddAlbum = DAOFactory.getAlbumDAO();
	
	public InsertPisteControl(InsertPiste view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		String titrePiste = view.getTitrePisteField().getText();
		String titreAlbum = view.getTitreAlbumField().getText();
		Piste piste = new Piste();
		Album album = new Album();
		ResultSet pisteSearch;
		ResultSet albumSearch;
		try {
			pisteSearch = bddPiste.find(titrePiste, titreAlbum);
			albumSearch = bddAlbum.find(titreAlbum);
			
			if(pisteSearch.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO 3 cas à distinguer :
		// Soit l'album et la piste existe donc les récup dans la bdd + ajouter dans le FichierPiste et envoyer écran flux
		// Soit la piste n'existe pas mais l'album si donc récup album et afficher écran créer piste
		// Soit aucun des deux n'existe donc écran album (puis y aura écran piste) mais garder le peu d'info de la piste et album
		
		
		new InsertAlbum(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierPiste());
//		new InsertPisteBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getPiste(), view.getFichierPiste());
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix insertion");
		view.getFenetre().setTitle("Choix d'insertion");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
