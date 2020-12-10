package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOArtiste;
import dao.DAOFactory;
import model.Artiste;
import model.CategorieMusique;
import views.CreateArtiste;
import views.FluxNbChoice;
import views.InsertPisteBis;

public class InsertPisteBisControl {
	private InsertPisteBis view;
	private boolean artisteExist = false;
	private boolean allArtisteExist = true;
	
	public InsertPisteBisControl(InsertPisteBis view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		if(artisteExist) {
			new FluxNbChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), null, view.getFichierPiste());
		} else {
			view.getFichierPiste().getPiste().setDuree(view.getDureeField().getText());
			view.getFichierPiste().getPiste().setNum((int) view.getNumeroField().getValue());
			String categories[] = (view.getCategField().getText()).split(";");
			String artistes[] = (view.getArtisteField().getText()).split(";");
			
			for(String cat : categories) {
				CategorieMusique catMusique = new CategorieMusique(cat.replaceAll(" ", ""));
				view.getFichierPiste().getCategories().add(catMusique);
			}
			
			for(String art : artistes) {
				Artiste artiste = new Artiste();
				DAOArtiste artisteDAO = DAOFactory.getArtisteDAO();
				String[] artspe = art.split(",");
				String nomArtiste = artspe[0].replaceAll("\\(", "");
				String instrument = artspe[1].replaceAll("\\)| ", "");
				System.out.println(art);
				System.out.println(nomArtiste);
				
				try(ResultSet resArt = artisteDAO.find(nomArtiste)){
					if(resArt.next()) {
						artiste.setId(resArt.getLong("idArtiste"));
						artiste.setNom(nomArtiste);
						artiste.setBiographie(resArt.getString("biographie"));
						artiste.setDateNaissance(resArt.getDate("dateNaissance"));
						artiste.setSpecialite(resArt.getString("specialite"));
						artiste.setUrlPhoto(resArt.getString("urlPhoto"));
						view.getFichierPiste().getArtistes().put(artiste, instrument);
					} else {
						allArtisteExist = false;
						new CreateArtiste(nomArtiste, instrument, null, view.getFichierPiste());
						artisteExist = true;
					}
				}catch(SQLException e) {
					System.out.println(e);
				}
			}
			if(allArtisteExist) {
				new FluxNbChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), null, view.getFichierPiste());
			}
		}
	}
}
