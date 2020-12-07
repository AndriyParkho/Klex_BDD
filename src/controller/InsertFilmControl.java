package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dao.DAOFilm;
import model.Film;
import model.aggregates.FichierFilm;
import views.InsertFilm;
import views.InsertFilmBis;

public class InsertFilmControl {
	private InsertFilm view;
	private DAOFilm  DAOfilm = new DAOFilm();
	private FichierFilm fichierFilm;
	
	public InsertFilmControl(InsertFilm view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		// TODO
		String titreFilm = view.getTitreField().getText();
		String annee = view.getAnneeField().getText();
		if(annee.length()<= 4) {
			annee+="-01-01";
		}
		Date anneeSortie =  Date.valueOf(annee); 
		System.out.println(anneeSortie);
		fichierFilm = new FichierFilm();
		Film film = new Film();
		film.setAnneeSortie(anneeSortie);
		film.setTitreFilm(titreFilm);
		fichierFilm.setFilm(film);
		//System.out.println(fichierFilm.getFilm());
		// Si le film n'existe pas dans la bdd
		

		try (ResultSet res =  DAOfilm.find(titreFilm, anneeSortie)){
			if(res.next()) {
				new InsertFilmBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierFilm());
			}else {
				System.out.println("Film existe deja");
				JDialog erreur = new JDialog(view.getFenetre(),"erreur");
				JLabel label = new JLabel("ce film existe deja", SwingConstants.CENTER);
				erreur.add(label);
				erreur.setSize(250, 100);
				erreur.setLocationRelativeTo(null);
				erreur.setVisible(true);
			}
			
		}catch(SQLException e) {
			System.out.println("exception"+e);
		}
		
		
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Choix insertion");
	}
}
