package views;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.SelectClientControl;
import model.Client;

public class SelectClient extends View{
	private JTextField marqueField;
	private JTextField modeleField;
	private JButton backButton = new JButton("<");
	private JButton suivButton = new JButton("Suivant");
	private JPanel container = new SelectClientPanel();
	private Client client;
	private SelectClientControl controller = new SelectClientControl(this);
	
	public SelectClient(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Choisir un client"));
		
		super.getContainerView().add(container, "Choisir client");
		super.getPanels().add("Choisir client");
		super.getSwitcherView().show(super.getContainerView() , "Choisir client");

		super.getFenetre().setSize(282, 210);
		super.getFenetre().setLocationRelativeTo(null);
	}


	class SelectClientPanel extends JPanel {

		/**
		 * Create the panel.
		 */
		public SelectClientPanel() {
			setLayout(null);
			
			JLabel lblFilmInfos = new JLabel("Client infos :");
			lblFilmInfos.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblFilmInfos.setBounds(81, 13, 122, 16);
			add(lblFilmInfos);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicBack();
				}
			});
			
			
			backButton.setBounds(12, 11, 47, 34);
			add(backButton);
			
			marqueField = new JTextField();
			marqueField.setBounds(109, 61, 116, 22);
			add(marqueField);
			marqueField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Marque :");
			lblNewLabel.setBounds(41, 64, 56, 16);
			add(lblNewLabel);
			
			modeleField = new JTextField();
			modeleField.setColumns(10);
			modeleField.setBounds(109, 96, 116, 22);
			add(modeleField);
			
			JLabel lblAnnenDe = new JLabel("Mod\u00E8le :");
			lblAnnenDe.setBounds(41, 99, 56, 16);
			add(lblAnnenDe);
			suivButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicSuiv();
				}
			});
			
			
			suivButton.setBounds(93, 131, 97, 25);
			add(suivButton);
		}

	}


	public JTextField getMarqueField() {
		return marqueField;
	}


	public void setMarqueField(JTextField marqueField) {
		this.marqueField = marqueField;
	}


	public JTextField getModeleField() {
		return modeleField;
	}


	public void setModeleField(JTextField modeleField) {
		this.modeleField = modeleField;
	}


	public JButton getBackButton() {
		return backButton;
	}


	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}


	public JButton getSuivButton() {
		return suivButton;
	}


	public void setSuivButton(JButton suivButton) {
		this.suivButton = suivButton;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
	
}
