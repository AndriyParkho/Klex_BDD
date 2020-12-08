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

import controller.SearchControl;
import model.Client;
import model.aggregates.FichierPiste;

public class Search extends View{
	private JTextField categField;
	private JLabel terminalLbl = new JLabel("La suite dans le terminal...");
	private JPanel container = new SearchPanel();
	private FichierPiste fichierPiste = new FichierPiste();
	private SearchControl controller = new SearchControl(this);
	private Client client;
	
	public Search(JFrame fenetre, CardLayout switcherView, JPanel containerView, Client client) {
		super(fenetre, switcherView, containerView, new String("Effectuer une recherche"));
		this.client = client;
		
		super.getContainerView().add(container, "Recherche");
		super.getPanels().add("Recherche");
		super.getSwitcherView().show(super.getContainerView() , "Recherche");

		super.getFenetre().setSize(282, 210);
		super.getFenetre().setLocationRelativeTo(null);
	}


	class SearchPanel extends JPanel {

		/**
		 * Create the panel.
		 */
		public SearchPanel() {
			setLayout(null);
			
			JLabel lblRecherche = new JLabel("Recherche :");
			lblRecherche.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblRecherche.setBounds(70, 13, 116, 16);
			add(lblRecherche);
			
			JButton backButton = new JButton("<");
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicBack();
				}
			});
			backButton.setBounds(11, 11, 47, 34);
			add(backButton);
			
			JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
			lblCatgorie.setBounds(32, 61, 67, 16);
			add(lblCatgorie);
			
			categField = new JTextField();
			categField.setColumns(10);
			categField.setBounds(111, 58, 116, 22);
			add(categField);
			
			JButton btnRechercher = new JButton("Rechercher");
			btnRechercher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicSearch();
				}
			});
			btnRechercher.setBounds(78, 123, 97, 25);
			add(btnRechercher);
			
			terminalLbl.setVisible(false);
			terminalLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			terminalLbl.setBounds(23, 94, 215, 16);
			add(terminalLbl);

		}
	}


	public JTextField getCategField() {
		return categField;
	}


	public void setCategField(JTextField categField) {
		this.categField = categField;
	}


	public JLabel getTerminalLbl() {
		return terminalLbl;
	}


	public void setTerminalLbl(JLabel terminalLbl) {
		this.terminalLbl = terminalLbl;
	}


	public FichierPiste getFichierPiste() {
		return fichierPiste;
	}


	public void setFichierPiste(FichierPiste fichierPiste) {
		this.fichierPiste = fichierPiste;
	}
	
	

}
