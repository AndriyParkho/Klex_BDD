package views;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.SearchControl;
import model.Client;
import model.aggregates.FichierPiste;

public class Search extends View{
	private JTextField categField;
	private JLabel terminalLbl = new JLabel("La suite dans le terminal...");
	private JRadioButton filmChoice = new JRadioButton("Un film");
	private JRadioButton pisteChoice = new JRadioButton("Une piste");
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

		super.getFenetre().setSize(320, 320);
		super.getFenetre().setLocationRelativeTo(null);
	}


	class SearchPanel extends JPanel {

		/**
		 * Create the panel.
		 */
		public SearchPanel() {
			setLayout(null);
			
			ButtonGroup choixInsert = new ButtonGroup();
			
			filmChoice.setSelected(true);
			filmChoice.setBounds(100, 55, 127, 25);
			choixInsert.add(filmChoice);
			add(filmChoice);
			
			
			pisteChoice.setBounds(100, 85, 127, 25);
			choixInsert.add(pisteChoice);
			add(pisteChoice);
			
			JLabel lblRecherche = new JLabel("Recherche :");
			lblRecherche.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblRecherche.setBounds(101, 18, 116, 16);
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
			lblCatgorie.setBounds(59, 135, 67, 16);
			add(lblCatgorie);
			
			categField = new JTextField();
			categField.setColumns(10);
			categField.setBounds(138, 132, 116, 22);
			add(categField);
			
			JButton btnRechercher = new JButton("Rechercher");
			btnRechercher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicSearch();
				}
			});
			btnRechercher.setBounds(109, 196, 97, 25);
			add(btnRechercher);
			
			terminalLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			terminalLbl.setBounds(54, 167, 215, 16);
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


	public JRadioButton getFilmChoice() {
		return filmChoice;
	}


	public void setFilmChoice(JRadioButton filmChoice) {
		this.filmChoice = filmChoice;
	}


	public JRadioButton getPisteChoice() {
		return pisteChoice;
	}


	public void setPisteChoice(JRadioButton pisteChoice) {
		this.pisteChoice = pisteChoice;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
	
	

}
