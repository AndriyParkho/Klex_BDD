package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
import dao.DAOFactory;
import dao.DAOFilm;
import model.Film;
import views.FluxNbChoice;
import views.InsertFilm;
import views.InsertFilmBis;

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
		
		Connection connection = ConnectionOracle.getInstance();
		
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
