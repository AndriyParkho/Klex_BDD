package views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AcceuilControl;

public class Acceuil extends View {
	private AcceuilControl controler = new AcceuilControl();
	private JPanel container = new JPanel(new GridBagLayout());
	private JButton connectButton = new JButton("Se connecter");
	private JButton signUpButton = new JButton("S'inscrire");
	
	
	public Acceuil(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Acceuil"));
		container.setBackground(Color.WHITE);
		
		connectButton.addActionListener(new ConnectListener());
		signUpButton.addActionListener(new SignUpListener());
		
		container.add(connectButton);
		container.add(signUpButton);
		
		super.getContainerView().add(container, "Acceuil");
		super.getSwitcherView().show(super.getContainerView() , "Acceuil");
	}
	
	class ConnectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			controler.clicConnect();
		}
	}
	
	class SignUpListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			controler.clicSignUp();
		}
	}
}
