package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import views.InsertAlbum;
import views.InsertPisteBis;

public class InsertAlbumControl {
	private InsertAlbum view;
	
	public InsertAlbumControl(InsertAlbum view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		try {
			view.getFichierPiste().getAlbum().setDateSortie(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(view.getDateField().getText()))));
		} catch (ParseException e) {
			JDialog erreur = new JDialog(view.getFenetre(),"Erreur");
			JLabel label = new JLabel("Date au mauvais format", SwingConstants.CENTER);
			erreur.add(label);
			erreur.setSize(250, 100);
			erreur.setLocationRelativeTo(null);
			erreur.setVisible(true);
			e.printStackTrace();
		}
		view.getFichierPiste().getAlbum().setGroupe(view.getGroupeField().getText());
		view.getFichierPiste().getAlbum().setUrlImagePochette(view.getPochetteField().getText());
		
		new InsertPisteBis(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), view.getFichierPiste());
	}
}
