package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dao.DAOFactory;
import dao.DAOFilm;
import model.Film;
import model.aggregates.FichierFilm;
import views.FluxNbChoice;
import views.InsertFilm;
import views.InsertFilmBis;
import views.InsertFlux;

public class InsertFilmControl {
	private InsertFilm view;
	
	
	public InsertFilmControl(InsertFilm view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		String titreFilm = view.getTitreField().getText();
		String annee = view.getAnneeField().getText();
		if(annee.length()<= 4) {
			annee+="-01-01";
		}
		Date anneeSortie =  Date.valueOf(annee); 
		DAOFilm  DAOfilm = DAOFactory.getFilmDAO();
		ResultSet resFilm;
		Film film = new Film();
		film.setAnneeSortie(anneeSortie);
		film.setTitreFilm(titreFilm);
		view.getFichierFilm().setFilm(film);
		

		try{
			resFilm = DAOfilm.find(titreFilm, anneeSortie);
			
			if(resFilm.next()) {
				film.setTitreFilm(resFilm.getString("titreFilm"));
				film.setAgeMin(resFilm.getInt("ageMin"));
				film.setAnneeSortie(resFilm.getDate("anneeSortie"));
				film.setResume(resFilm.getString("resume"));
				film.setUrlAffiche(resFilm.getString("urlAffiche"));
				
				view.getFichierFilm().setFilm(film);
				
				new FluxNbChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView(),view.getFichierFilm() ,null);

			}else {
				film.setTitreFilm(titreFilm);
				film.setAnneeSortie(anneeSortie);
				
				view.getFichierFilm().setFilm(film);;
				new InsertFilmBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierFilm());

			}
			
		}catch(SQLException e) {
			e.printStackTrace();		
		}
		
		
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix insertion");
		view.getFenetre().setTitle("Choix d'insertion");
		view.getFenetre().setSize(282, 210);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
