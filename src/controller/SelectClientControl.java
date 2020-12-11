package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import connections.ConnectionOracle;
import connections.JDBCUtilities;
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
		
		Connection connection = ConnectionOracle.getInstance();
		
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
		view.getSwitcherView().show(view.getContainerView(), "Fonctions");
		view.getFenetre().setTitle("Fonctions");
		view.getFenetre().setSize(453, 350);
		view.getFenetre().setLocationRelativeTo(null);
	}
}
