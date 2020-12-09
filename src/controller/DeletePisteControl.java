package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dao.DAOAlbum;
import dao.DAOFactory;
import dao.DAOPiste;
import model.Album;
import model.Piste;
import views.DeletePiste;

public class DeletePisteControl {
	private DeletePiste view;
	
	public DeletePisteControl(DeletePiste view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		String titrePiste = view.getTitrePisteField().getText();
		String titreAlbum = view.getTitreAlbumField().getText();
		Piste piste = new Piste();
		Album album = new Album();
		DAOPiste bddPiste = DAOFactory.getPisteDAO();
		DAOAlbum bddAlbum = DAOFactory.getAlbumDAO();
		ResultSet pisteSearch;
		ResultSet albumSearch;
		
		try {
			pisteSearch = bddPiste.find(titrePiste, titreAlbum);
			albumSearch = bddAlbum.find(titreAlbum);
			
			if(pisteSearch.next()) {
				piste.setNum(pisteSearch.getInt("numPiste"));
				piste.setDuree(pisteSearch.getString("dureePiste"));
				piste.setIdAlbum(pisteSearch.getInt("idAlbum"));
				piste.setTitre(titrePiste);
				
				albumSearch.next();
				album.setId(albumSearch.getLong("idAlbum"));
				album.setDateSortie(albumSearch.getDate("dateSortieAlbum"));
				album.setGroupe(albumSearch.getString("nomGroupe"));
				album.setTitre(titreAlbum);
				album.setUrlImagePochette(albumSearch.getString("urlImagePochette"));
				
				//transaction pour supprimer la piste
			
			}else {
				System.out.println("piste non trouvée");
				// email non trouvé dans la BDD
				JDialog erreur = new JDialog(view.getFenetre(),"erreur");
				JLabel label = new JLabel("piste non trouvée", SwingConstants.CENTER);
				erreur.add(label);
				erreur.setSize(250, 100);
				erreur.setLocationRelativeTo(null);
				erreur.setVisible(true);
				
				clicBack();
			}
			
		}catch(SQLException e ) {
				
			}
		// R�cup�re les infos de la piste et lance la fonction de suppression de la piste
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix supprimer");
		view.getFenetre().setTitle("Choix de suppression");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
