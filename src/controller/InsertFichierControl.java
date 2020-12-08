package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Fichier;
import transactions.TransactionFichierFilm;
import transactions.TransactionFichierPiste;
import views.InsertFichier;
import views.View;

public class InsertFichierControl {
	private InsertFichier view;
	
	public InsertFichierControl(InsertFichier view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		Fichier fichier = new Fichier();
		fichier.setEmail(View.getUtilConnected().getEmail());
		try {
			fichier.setDateDepot(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(view.getDateField().getText()))));
		} catch (ParseException e) {
			JDialog erreur = new JDialog(view.getFenetre(),"Erreur");
			JLabel label = new JLabel("Date au mauvais format", SwingConstants.CENTER);
			erreur.add(label);
			erreur.setSize(250, 100);
			erreur.setLocationRelativeTo(null);
			erreur.setVisible(true);
			e.printStackTrace();
		}
		fichier.setTaille((long) view.getTailleField().getValue());
		
		if(view.getFichierFilm() != null) {
			view.getFichierFilm().setFichier(fichier);
			System.out.println(view.getFichierFilm());
			TransactionFichierFilm.execute(view.getFichierFilm());
		} else if(view.getFichierPiste() != null) {
			view.getFichierPiste().setFichier(fichier);
			System.out.println(view.getFichierPiste());
			TransactionFichierPiste.execute(view.getFichierPiste());
		}
		// Vérifie le quel des deux FichierFilm ou FichierPiste est null et
		// Ajoute les infos du fichier en récupérant les infos dans les champs et lance la transaction insert selon le fichier 
		

		view.getSwitcherView().show(view.getContainerView(), "Fonctions");
		view.getFenetre().setTitle("Fonctions");
		view.getFenetre().setSize(453, 350);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
