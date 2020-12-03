package views;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class View {
	private JFrame fenetre;
	private CardLayout switcherView;
	private JPanel containerView;
	
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
}
