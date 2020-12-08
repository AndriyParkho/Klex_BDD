package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dao.DAOFilm;
import model.Film;
import transactions.TransactionFichierFilm;
import views.DeleteFilm;

public class DeleteFilmControl {
	private DeleteFilm view;
	
	public DeleteFilmControl(DeleteFilm view) {
		this.view = view;
	}

	public void clicSuiv() {
		String titreFilm = view.getTitreField().getText();
		String anneeSortie = view.getAnneeField().getText()+"-01-01";
		DAOFilm film = new DAOFilm();
		ResultSet rs = film.find(titreFilm, Date.valueOf(anneeSortie));
		try {
			if(rs.next()) {
				
			}else {
				System.out.println("film non trouvé");
				// email non trouvé dans la BDD
				JDialog erreur = new JDialog(view.getFenetre(),"erreur");
				JLabel label = new JLabel("film non trouvé", SwingConstants.CENTER);
				erreur.add(label);
				erreur.setSize(250, 100);
				erreur.setLocationRelativeTo(null);
				erreur.setVisible(true);
			}
		}catch(SQLException e) {
			
		}
		//		if (!rs.next()) {
//			// le film cherch�� n'existe pas
//			System.out.println("Le film cherche n'existe pas");
//		} else {
//			// le film cherche existe
//			//TODO
//		}
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix supprimer");
		view.getFenetre().setTitle("Choix de suppression");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
