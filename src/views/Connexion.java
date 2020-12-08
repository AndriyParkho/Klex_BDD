package views;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ConnectControl;

public class Connexion extends View {
	private JTextField mailField;
	private JTextField codeField;
	private JButton connectButton = new JButton("Se connecter");
	private JPanel container = new ConnexPanel();
	private ConnectControl controller = new ConnectControl(this);
	
	public Connexion(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Se connecter"));

		super.getContainerView().add(container, "Se connecter");
		super.getPanels().add("Se connecter");
		super.getSwitcherView().show(super.getContainerView() , "Se connecter");

		super.getFenetre().setSize(453, 350);
		super.getFenetre().setLocationRelativeTo(null);
	}
	
	class ConnexPanel extends JPanel {

		/**
		 * Create the panel.
		 */
		public ConnexPanel() {
			setLayout(null);
			
			mailField = new JTextField();
			mailField.setBounds(169, 104, 135, 22);
			add(mailField);
			mailField.setColumns(10);
			
			JLabel mailLabel = new JLabel("Email :");
			mailLabel.setBounds(109, 107, 48, 16);
			add(mailLabel);
			
			codeField = new JTextField();
			codeField.setColumns(10);
			codeField.setBounds(169, 155, 135, 22);
			add(codeField);
			
			JLabel codeLabel = new JLabel("Code :");
			codeLabel.setBounds(109, 158, 48, 16);
			add(codeLabel);
			
			connectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						controller.clicToConnect();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			connectButton.setBounds(179, 248, 116, 25);
			add(connectButton);
			
			JButton backButton = new JButton("<");
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicBack();
				}
			});
			backButton.setIcon(null);
			backButton.setBounds(12, 13, 47, 34);
			add(backButton);

		}
	}
	
	public JTextField getMailField() {
		return mailField;
	}

	public void setMailField(JTextField mailField) {
		this.mailField = mailField;
	}

	public JTextField getCodeField() {
		return codeField;
	}

	public void setCodeField(JTextField codeField) {
		this.codeField = codeField;
	}

	public JButton getConnectButton() {
		return connectButton;
	}

	public void setConnectButton(JButton connectButton) {
		this.connectButton = connectButton;
	}

	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}

}
