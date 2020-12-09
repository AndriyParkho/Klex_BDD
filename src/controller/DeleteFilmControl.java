package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dao.DAOFilm;
import model.Film;
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
		Film film = new Film();
		film.setAnneeSortie(anneeSortie);
		film.setTitreFilm(titreFilm);
		DAOFilm filmDAO = new DAOFilm();
		ResultSet resFilm;
		try{
			resFilm = filmDAO.find(titreFilm,anneeSortie);
		
			try {
				if(resFilm.next()) {
					film.setTitreFilm(resFilm.getString("titreFilm"));
					film.setAgeMin(resFilm.getInt("ageMin"));
					film.setAnneeSortie(resFilm.getDate("anneeSortie"));
					film.setResume(resFilm.getString("resume"));
					film.setUrlAffiche(resFilm.getString("urlAffiche"));
					
					//transaction pour supprimer le film
				}else {
					System.out.println("film non trouv\\u00E9");
					// email non trouvé dans la BDD
					JDialog erreur = new JDialog(view.getFenetre(),"Erreur");
					JLabel label = new JLabel("Film non trouv\\u00E9", SwingConstants.CENTER);
					erreur.add(label);
					erreur.setSize(250, 100);
					erreur.setLocationRelativeTo(null);
					erreur.setVisible(true);
					
					clicBack();
				}
			}catch(SQLException e) {
			}
		}catch(SQLException e) {
		}

	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix supprimer");
		view.getFenetre().setTitle("Choix de suppression");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
