package views;

import java.awt.CardLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Utilisateur;

public abstract class View {
	private JFrame fenetre;
	private CardLayout switcherView;
	private JPanel containerView;
	private static Set<String> panels = new HashSet<String>();
	private static Utilisateur utilConnected;
	
	public View(JFrame fenetre, CardLayout switcherView, JPanel containerView, String name) {
		this.fenetre = fenetre;
		this.switcherView = switcherView;
		this.containerView = containerView;
		this.fenetre.setTitle(name);
	}
	
	public JFrame getFenetre() {
		return this.fenetre;
	}
	
	public CardLayout getSwitcherView() {
		return this.switcherView;
	}
	
	public JPanel getContainerView() {
		return this.containerView;
	}

	public static Set<String> getPanels() {
		return panels;
	}

	public static void setPanels(Set<String> panels) {
		View.panels = panels;
	}

	public static Utilisateur getUtilConnected() {
		return utilConnected;
	}

	public static void setUtilConnected(Utilisateur utilConnected) {
		View.utilConnected = utilConnected;
	}
	
	
}
