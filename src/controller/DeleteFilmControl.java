package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOFilm;
import model.Film;
import transactions.TransactionDeletes;
import views.DeleteFilm;

public class DeleteFilmControl {
	private DeleteFilm view;
	
	public DeleteFilmControl(DeleteFilm view) {
		this.view = view;
	}

	public void clicSuiv() {
		String titreFilm = view.getTitreField().getText();
		String annee = view.getAnneeField().getText();
		if(annee.length()<= 4) {
			annee+="-01-01";
		}
		Date anneeSortie =  Date.valueOf(annee);
		DAOFilm filmDAO = new DAOFilm();
		ResultSet resFilm;
		
		Connection connection = ConnectionOracle.getInstance();
		
		try{
			resFilm = filmDAO.find(titreFilm,anneeSortie);
			if(resFilm.next()) {
				TransactionDeletes.deleteFilm(titreFilm, anneeSortie);
			}else {
				System.out.println("film non trouv\u00E9");
				// email non trouvé dans la BDD
				JDialog erreur = new JDialog(view.getFenetre(),"Erreur");
				JLabel label = new JLabel("Film non trouv\u00E9", SwingConstants.CENTER);
				erreur.add(label);
				erreur.setSize(250, 100);
				erreur.setLocationRelativeTo(null);
				erreur.setVisible(true);
				
				clicBack();
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
		view.getSwitcherView().show(view.getContainerView(), "Choix supprimer");
		view.getFenetre().setTitle("Choix de suppression");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
