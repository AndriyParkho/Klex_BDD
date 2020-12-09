package controller;

import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Artiste;
import views.CreateArtiste;

public class CreateArtisteControl {
	private CreateArtiste view;

	public CreateArtisteControl(CreateArtiste view) {
		this.view = view;
	}
	
	public void clicValid() {
		Artiste artiste = new Artiste();
		artiste.setNom(view.getNomField().getText());
		try {
			artiste.setDateNaissance(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(view.getDateField().getText()))));
		} catch (ParseException e) {
			JDialog erreur = new JDialog(view,"Erreur");
			JLabel label = new JLabel("Date au mauvais format", SwingConstants.CENTER);
			erreur.add(label);
			erreur.setSize(250, 100);
			erreur.setLocationRelativeTo(null);
			erreur.setVisible(true);
			e.printStackTrace();
		}
		artiste.setSpecialite(view.getSpecField().getText());
		artiste.setUrlPhoto(view.getPhotoField().getText());
		artiste.setBiographie(view.getBioField().getText());
		
		if(view.getFichierFilm() != null) {
			view.getFichierFilm().getArtistes().put(artiste, view.getRoleOuInstru());
		} else if(view.getFichierPiste() != null) {
			view.getFichierPiste().getArtistes().put(artiste, view.getRoleOuInstru());
		}
		
		view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING)); // La fenetre se ferme
	}
}
