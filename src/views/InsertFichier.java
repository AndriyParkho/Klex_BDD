package views;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import controller.InsertFichierControl;
import model.FichierFilm;
import model.FichierPiste;

public class InsertFichier extends View{
	private JTextField dateField;
	private JButton suivButton = new JButton("Suivant");
	private JSpinner tailleField = new JSpinner();
	private JPanel container = new InsertFichierPanel();
	private FichierFilm fichierFilm;
	private FichierPiste fichierPiste;
	private InsertFichierControl controller = new InsertFichierControl(this);
	
	public InsertFichier(JFrame fenetre, CardLayout switcherView, JPanel containerView, FichierFilm fichierFilm, FichierPiste fichierPiste) {
		super(fenetre, switcherView, containerView, new String("Ajout des infos du fichier"));
		this.fichierFilm = fichierFilm;
		this.fichierPiste = fichierPiste;
		
		super.getContainerView().add(container, "Insertion fichier");
		super.getPanels().add("Insertion fichier");
		super.getSwitcherView().show(super.getContainerView() , "Insertion fichier");

		super.getFenetre().setSize(282, 210);
		super.getFenetre().setLocationRelativeTo(null);
	}


	class InsertFichierPanel extends JPanel {
		
		
		/**
		 * Create the panel.
		 */
		public InsertFichierPanel() {
			setLayout(null);
			
			JLabel lblFilmInfos = new JLabel("Fichier infos :");
			lblFilmInfos.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblFilmInfos.setBounds(75, 13, 130, 16);
			add(lblFilmInfos);
			
			JLabel lblNewLabel = new JLabel("Taille :");
			lblNewLabel.setBounds(50, 64, 47, 16);
			add(lblNewLabel);
			
			dateField = new JTextField();
			dateField.setEditable(false);
			dateField.setEnabled(false);
			dateField.setColumns(10);
			dateField.setBounds(109, 96, 116, 22);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			LocalDateTime now = LocalDateTime.now();  
			dateField.setText(dtf.format(now));
			add(dateField);
			
			JLabel lblAnnenDe = new JLabel("Date d\u00E9pot :");
			lblAnnenDe.setBounds(26, 99, 80, 16);
			add(lblAnnenDe);
			suivButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			
			
			suivButton.setBounds(93, 131, 97, 25);
			add(suivButton);
			
			
			tailleField.setBounds(109, 61, 47, 22);
			add(tailleField);
			
			JLabel lblNewLabel_1 = new JLabel("Ko");
			lblNewLabel_1.setBounds(169, 64, 56, 16);
			add(lblNewLabel_1);
		}
	}


	public JTextField getDateField() {
		return dateField;
	}


	public void setDateField(JTextField dateField) {
		this.dateField = dateField;
	}


	public JButton getSuivButton() {
		return suivButton;
	}


	public void setSuivButton(JButton suivButton) {
		this.suivButton = suivButton;
	}


	public JSpinner getTailleField() {
		return tailleField;
	}


	public void setTailleField(JSpinner tailleField) {
		this.tailleField = tailleField;
	}


	public FichierFilm getFichierFilm() {
		return fichierFilm;
	}


	public void setFichierFilm(FichierFilm fichierFilm) {
		this.fichierFilm = fichierFilm;
	}


	public FichierPiste getFichierPiste() {
		return fichierPiste;
	}


	public void setFichierPiste(FichierPiste fichierPiste) {
		this.fichierPiste = fichierPiste;
	}


}
