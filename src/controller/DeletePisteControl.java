package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOAlbum;
import dao.DAOFactory;
import dao.DAOPiste;
import model.Album;
import model.Piste;
import transactions.TransactionDeletes;
import views.DeletePiste;

public class DeletePisteControl {
	private DeletePiste view;
	
	public DeletePisteControl(DeletePiste view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		String titrePiste = view.getTitrePisteField().getText();
		String titreAlbum = view.getTitreAlbumField().getText();
		DAOPiste bddPiste = DAOFactory.getPisteDAO();
		DAOAlbum bddAlbum = DAOFactory.getAlbumDAO();
		ResultSet pisteSearch;
		ResultSet albumSearch;
		
		Connection connection = ConnectionOracle.getInstance();
		
		try {
			pisteSearch = bddPiste.find(titrePiste, titreAlbum);
			albumSearch = bddAlbum.find(titreAlbum);
			connection.commit();
			
			if(pisteSearch.next()) {				
				TransactionDeletes.deletePiste(titrePiste, titreAlbum);
			
			}else {
				System.out.println("piste non trouv\u00E9e");
				// email non trouvé dans la BDD
				JDialog erreur = new JDialog(view.getFenetre(),"erreur");
				JLabel label = new JLabel("piste non trouv\u00E9e", SwingConstants.CENTER);
				erreur.add(label);
				erreur.setSize(250, 100);
				erreur.setLocationRelativeTo(null);
				erreur.setVisible(true);
				
				clicBack();
			}
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
		view.getSwitcherView().show(view.getContainerView(), "Choix supprimer");
		view.getFenetre().setTitle("Choix de suppression");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
