package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOFilm;
import model.Film;
import transactions.TransactionFichierFilm;
import views.DeleteFilm;

public class DeleteFilmControl {
	private DeleteFilm view;
	
	public DeleteFilmControl(DeleteFilm view) {
		this.view = view;
	}
	
	public void clicSuiv() throws SQLException {
		String titreFilm = view.getTitreField().getText();
		String anneeSortie = view.getAnneeField().getText()+"-01-01";
//		DAOFilm film = new DAOFilm();
//		ResultSet rs = film.find(titreFilm, Date.valueOf(anneeSortie));
//		if (!rs.next()) {
//			// le film cherché n'existe pas
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
