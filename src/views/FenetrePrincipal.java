package views;

import java.awt.CardLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetrePrincipal {
	
	public FenetrePrincipal() {
		JFrame frame = new JFrame();
		JPanel containerView = new JPanel();
		CardLayout switcherView = new CardLayout();
		containerView.setLayout(switcherView);
		
		new Accueil(frame, switcherView, containerView);
		
		frame.add(containerView);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
	}
}
