package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOAlbum;
import dao.DAOFactory;
import dao.DAOPiste;
import model.Album;
import model.Piste;
import views.FluxNbChoice;
import views.InsertAlbum;
import views.InsertPiste;
import views.InsertPisteBis;

public class InsertPisteControl {
	private InsertPiste view;
	
	public InsertPisteControl(InsertPiste view) {
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
		
		Connection connection = ConnectionOracle.getInstance();
		
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
				
				view.getFichierPiste().setAlbum(album);
				view.getFichierPiste().setPiste(piste);
				
				new FluxNbChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), null, view.getFichierPiste());
			} else if(albumSearch.next()) {
				piste.setTitre(titrePiste);
				piste.setIdAlbum(albumSearch.getLong("idAlbum"));
				
				album.setId(albumSearch.getLong("idAlbum"));
				album.setDateSortie(albumSearch.getDate("dateSortieAlbum"));
				album.setGroupe(albumSearch.getString("nomGroupe"));
				album.setTitre(titreAlbum);
				album.setUrlImagePochette(albumSearch.getString("urlImagePochette"));
				
				view.getFichierPiste().setAlbum(album);
				view.getFichierPiste().setPiste(piste);
				
				new InsertPisteBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierPiste());
			} else {
				piste.setTitre(titrePiste);
				album.setTitre(titreAlbum);
				
				view.getFichierPiste().setAlbum(album);
				view.getFichierPiste().setPiste(piste);
				
				new InsertAlbum(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierPiste());
			}
			connection.commit();
		}catch(SQLException e) {
			System.err.println("sql error !");
            JDBCUtilities.printSQLException(e);

            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep) {
                    JDBCUtilities.printSQLException(excep);
                }
            }
        } finally {
            ConnectionOracle.closeInstance();
        }
	}

	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix insertion");
		view.getFenetre().setTitle("Choix d'insertion");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
