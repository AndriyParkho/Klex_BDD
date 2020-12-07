package controller;

import java.awt.Toolkit;

import views.DeleteChoice;
import views.Fonctions;
import views.InsertChoice;
import views.SelectClient;

public class FonctionsControl {
	private Fonctions view;
	
	public FonctionsControl(Fonctions view) {
		this.view = view;
	}
	
	public void clicInsert() {
		new InsertChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}
	
	public void clicSelect() {
		new SelectClient(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}
	
	public void clicDelete() {
		new DeleteChoice(view.getFenetre(), view.getSwitcherView(), view.getContainerView());
	}
	
	public void clicDisconnect() {
		view.getSwitcherView().show(view.getContainerView(), "Acceuil");
		view.getFenetre().setTitle("Acceuil");
		view.getFenetre().setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.2));
        view.getFenetre().setLocationRelativeTo(null);
	}
}
