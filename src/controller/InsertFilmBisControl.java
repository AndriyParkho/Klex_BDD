package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
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
	private boolean artisteExist = false;
	private boolean allArtisteExist = true;
	
	public InsertFilmBisControl(InsertFilmBis view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		if(artisteExist) {
			new FluxNbChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierFilm() ,null);
		} else {
			String resume = view.getResumeField().getText();
			String image[] = (view.getAfficheField().getText()).split(";");
			String categories[] = (view.getCategField().getText()).split(";");
			String artistes[] = (view.getArtisteField().getText()).split(";");
			String affiche = view.getAfficheField().getText();
			int ageMin = (int)view.getAgeField().getValue();
			
			view.getFichierFilm().getFilm().setResume(resume);
			view.getFichierFilm().getFilm().setUrlAffiche(affiche);
			view.getFichierFilm().getFilm().setAgeMin(ageMin);
			
			Connection connection = ConnectionOracle.getInstance();
			
			for(String cat : categories) {
				CategorieFilm categorieFilm = new CategorieFilm(cat.replaceAll(" ", ""));
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
				String[] artspe = art.split(",");
				String nomArtiste = artspe[0].replaceAll("\\(", "");
				String role = artspe[1].replaceAll("\\)| ", "");
				
				try(ResultSet resArt = artisteDAO.find(nomArtiste)){
					if(resArt.next()) {
						artiste.setId(resArt.getLong("idArtiste"));
						artiste.setNom(nomArtiste);
						artiste.setBiographie(resArt.getString("biographie"));
						artiste.setDateNaissance(resArt.getDate("dateNaissance"));
						artiste.setSpecialite(resArt.getString("specialite"));
						artiste.setUrlPhoto(resArt.getString("urlPhoto"));
						view.getFichierFilm().getArtistes().put(artiste, role);

						connection.commit();
					} else {
						allArtisteExist = false;
						new CreateArtiste(nomArtiste, role, view.getFichierFilm(), null);
						artisteExist = true;
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
			
			if(allArtisteExist) {
				new FluxNbChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierFilm() ,null);
			}
		}
	}
}
