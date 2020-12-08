package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOArtiste;
import dao.DAOCategorieFilm;
import dao.DAOFactory;
import model.Artiste;
import model.CategorieFilm;
import model.ImgExtraiteFilm;
import views.CreateArtiste;
import views.FluxNbChoice;
import views.InsertFilmBis;

public class InsertFilmBisControl {
	private InsertFilmBis view;
	
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
			view.getFichierFilm().getCategories().add(categorieFilm);

		}
			
		for(String img : image) {
			ImgExtraiteFilm imgExtraiteFilm = new ImgExtraiteFilm(img, view.getFichierFilm().getFilm().getTitreFilm(), view.getFichierFilm().getFilm().getAnneeSortie());
			view.getFichierFilm().getImgExtraiteFilms().add(imgExtraiteFilm);
		}
		
		
		for(String art : artistes) {
			Artiste artiste = new Artiste();
			DAOArtiste artisteDAO = DAOFactory.getArtisteDAO();
			String[] artspe = art.replaceAll("\\(|\\)| ", "").split(",");
			String nomArtiste = artspe[0];
			String role = artspe[1];
			
			try(ResultSet resArt = artisteDAO.find(nomArtiste)){
				if(resArt.next()) {
					artiste.setId(resArt.getLong("idArtiste"));
					artiste.setNom(nomArtiste);
					artiste.setBiographie(resArt.getString("biographie"));
					artiste.setDateNaissance(resArt.getDate("dateNaissance"));
					artiste.setSpecialite(resArt.getString("specialite"));
					artiste.setUrlPhoto(resArt.getString("urlPhoto"));
					view.getFichierFilm().getArtistes().put(artiste, role);
				} else {
					new CreateArtiste(artiste.getNom(), role, view.getFichierFilm(), null);
				}
			}catch(SQLException e) {
				System.out.println(e);
			}
		}
		
		new FluxNbChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierFilm() ,null);
	}
}
