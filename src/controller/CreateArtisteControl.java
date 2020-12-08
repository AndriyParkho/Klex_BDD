package controller;

import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;

import connections.JDBCUtilities;
import dao.DAOArtiste;
import model.Artiste;
import model.aggregates.FichierFilm;
import views.CreateArtiste;

public class CreateArtisteControl {
	private CreateArtiste view;

	public CreateArtisteControl(CreateArtiste view) {
		this.view = view;
	}
	
	public void clicValid() {
		// R���cup���rer toutes les infos de l'artiste dans chaque case 
		// et rajoute l'artiste ��� la liste d'artiste du mod���le fichierFilm ou fichierPiste (celui qui est non null)
		
		String nomArtiste = view.getNomField().getText();
		String dateArtiste = view.getDateField().getText();
		Date dateNaissance =  Date.valueOf(dateArtiste);
		String specArtiste = view.getSpecField().getText();
		String photoArtiste = view.getPhotoField().getText();
		String bioArtiste = view.getBioField().getText();
		
		String roleArtiste = view.getRoleOuInstru();
		FichierFilm fichier = view.getFichierFilm();
		
		Artiste artiste = new Artiste();
		artiste.setNom(nomArtiste);
		artiste.setDateNaissance(dateNaissance);
		artiste.setSpecialite(specArtiste);
		artiste.setUrlPhoto(photoArtiste);
		artiste.setBiographie(bioArtiste);
		
		view.getFichierFilm().getArtistes().put(artiste, roleArtiste);
		
		view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING)); // La fenetre se ferme
	}
}
