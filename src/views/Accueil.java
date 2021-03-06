package views;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AccueilControl;

public class Accueil extends View {
	private AccueilControl controler = new AccueilControl(this);
	private JPanel container = new JPanel(new GridBagLayout());
	private JButton connectButton = new JButton("Se connecter");
	private JButton signUpButton = new JButton("S'inscrire");
	
	
	public Accueil(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Acceuil"));
		
		connectButton.addActionListener(new ConnectListener());
		signUpButton.addActionListener(new SignUpListener());
		
		container.add(connectButton);
		container.add(signUpButton);
		
		super.getContainerView().add(container, "Acceuil");
		super.getPanels().add("Acceuil");
		super.getSwitcherView().show(super.getContainerView() , "Acceuil");
		

        super.getFenetre().setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2), (int) (Toolkit.getDefaultToolkit().getScreenSize().height*0.2));
        super.getFenetre().setLocationRelativeTo(null);
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

	public AccueilControl getControler() {
		return controler;
	}

	public void setControler(AccueilControl controler) {
		this.controler = controler;
	}

	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}

	public JButton getConnectButton() {
		return connectButton;
	}

	public void setConnectButton(JButton connectButton) {
		this.connectButton = connectButton;
	}

	public JButton getSignUpButton() {
		return signUpButton;
	}

	public void setSignUpButton(JButton signUpButton) {
		this.signUpButton = signUpButton;
	}
	
	
}
