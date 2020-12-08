package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOClient;
import dao.DAOFactory;
import model.Client;
import views.CreateClient;
import views.Search;
import views.SelectClient;

public class SelectClientControl {
	private SelectClient view;
	
	public SelectClientControl(SelectClient view) {
		this.view = view;
	}
	
	public void clicSuiv() {
		Client client = new Client();
		String marque = view.getMarqueField().getText();
		String modele = view.getModeleField().getText();
		
		DAOClient bddClient = DAOFactory.getClientDAO();
		ResultSet searchClient;
		try {
			searchClient = bddClient.find(marque, modele);
			
			if(searchClient.next()) {
				client.setMarque(marque);
				client.setModele(modele);
				client.setHauteurMax(searchClient.getInt("hauteurMax"));
				client.setLargeurMax(searchClient.getInt("largeurMax"));
				
				new Search(view.getFenetre(), view.getSwitcherView(), view.getContainerView(), client);
			} else {
				new CreateClient(marque, modele);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clicBack() {
		view.getSwitcherView().show(view.getContainerView(), "Fonctions");
		view.getFenetre().setTitle("Fonctions");
		view.getFenetre().setSize(453, 350);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
