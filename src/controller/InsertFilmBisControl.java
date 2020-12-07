package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOArtiste;
import dao.DAOCategorieFilm;
import model.Artiste;
import model.CategorieFilm;
import model.ImgExtraiteFilm;
import views.CreateArtiste;
import views.InsertFilmBis;

public class InsertFilmBisControl {
	private InsertFilmBis view;
	//private DAOFichierFilm fichierFilmDAO= new DAOFichierFilm();
	
	public InsertFilmBisControl(InsertFilmBis view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		String resume = view.getResumeField().getText();
		String image[] = (view.getAfficheField().getText()).split(";");
		String categories[] = (view.getCategField().getText()).split(";");
		String artistes[] = (view.getArtisteField().getText()).split(";");
		String affiche = view.getAfficheField().getText();
		int ageMin = (int)view.getAgeField().getValue();
		
		view.getFichierFilm().getFilm().setResume(resume);
		view.getFichierFilm().getFilm().setUrlAffiche(affiche);
		view.getFichierFilm().getFilm().setAgeMin(ageMin);
		
		for(String cat : categories) {
			CategorieFilm categorieFilm = new CategorieFilm(cat);
			DAOCategorieFilm catDAO= new DAOCategorieFilm();
			try (ResultSet resCat =  catDAO.find(categorieFilm)){
				if( !resCat.next()) {
					catDAO.create(categorieFilm);
				}
			//faire une view pour cr�er une cat�gorie
		
			}catch(SQLException e) {
				System.out.println(e);
			}
			view.getFichierFilm().getCategories().add(categorieFilm);

		}
			
		for(String img : image) {
			ImgExtraiteFilm imgExtraiteFilm = new ImgExtraiteFilm(img, view.getFichierFilm().getFilm().getTitreFilm(), view.getFichierFilm().getFilm().getAnneeSortie());
			view.getFichierFilm().getImgExtraiteFilms().add(imgExtraiteFilm);
		}
		
		
		for(String art : artistes) {
			Artiste artiste = new Artiste();
			DAOArtiste artisteDAO = new DAOArtiste();
			String[] artspe = art.split("(//,//)");
			artiste.setNom(artspe[0]);
			String role = artspe[1];
			artiste.setSpecialite(role);
			
			try(ResultSet resArt = artisteDAO.find(artiste)){
				if(!resArt.next()) {
					CreateArtiste viewArtiste = new CreateArtiste(artiste.getNom(), view.getFichierFilm(), null);
				}
			}catch(SQLException e) {
				System.out.println(e);
			}
			view.getFichierFilm().getArtistes().put(artiste, role);
		}
		
		
		
		
		// fichierFilmDAO.create(fichierFilm);
	}
}
