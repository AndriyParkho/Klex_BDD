package views;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.InsertPisteBisControl;
import model.aggregates.FichierPiste;

public class InsertPisteBis extends View{
	private JTextField titreField;
	private JTextField dureeField;
	private JTextField categField;
	private JSpinner numeroField = new JSpinner();
	private JTextArea artisteField = new JTextArea();
	private JPanel container = new InsertPisteBisPanel();
	private FichierPiste fichierPiste;
	private InsertPisteBisControl controller = new InsertPisteBisControl(this);

	public InsertPisteBis(JFrame fenetre, CardLayout switcherView, JPanel containerView, FichierPiste fichierPiste) {
		super(fenetre, switcherView, containerView, new String("Ajout d'une piste"));
		this.fichierPiste = fichierPiste;
		
		super.getContainerView().add(container, "Insertion piste 2");
		super.getPanels().add("Insertion piste 2");
		super.getSwitcherView().show(super.getContainerView() , "Insertion piste 2");

		super.getFenetre().setSize(405, 360);
		super.getFenetre().setLocationRelativeTo(null);
	}

	class InsertPisteBisPanel extends JPanel {

		/**
		 * Create the panel.
		 */
		public InsertPisteBisPanel() {
			setLayout(null);
			
			JLabel lblFilmInfos = new JLabel("Piste infos :");
			lblFilmInfos.setBounds(154, 13, 116, 22);
			lblFilmInfos.setFont(new Font("Tahoma", Font.BOLD, 18));
			add(lblFilmInfos);
			
			JLabel lblNewLabel_1 = new JLabel("Titre :");
			lblNewLabel_1.setBounds(24, 76, 45, 16);
			add(lblNewLabel_1);
			
			titreField = new JTextField();
			titreField.setColumns(10);
			titreField.setBounds(81, 73, 116, 22);
			add(titreField);
			
			JLabel lblNPiste = new JLabel("N\u00B0 piste :");
			lblNPiste.setBounds(258, 76, 60, 16);
			add(lblNPiste);
			numeroField.setModel(new SpinnerNumberModel(1, 1, null, 1));
			
			numeroField.setBounds(330, 73, 46, 22);
			add(numeroField);
			
			JLabel lblNewLabel_1_1 = new JLabel("Dur\u00E9e :");
			lblNewLabel_1_1.setBounds(24, 111, 45, 16);
			add(lblNewLabel_1_1);
			
			dureeField = new JTextField();
			dureeField.setText("HH:mm:ss");
			dureeField.addFocusListener(new FocusListener() {


				public void focusGained(FocusEvent e) {
				    dureeField.setText(""); 
				}


				public void focusLost(FocusEvent e) {

				}

			});
			dureeField.setColumns(10);
			dureeField.setBounds(81, 108, 116, 22);
			add(dureeField);
			
			JLabel lblNewLabel_2 = new JLabel("Cat\u00E9gorie(s) :");
			lblNewLabel_2.setBounds(24, 146, 88, 16);
			add(lblNewLabel_2);
			
			categField = new JTextField();
			categField.setText("Categ1 ; categ2 ; ...");
			categField.addFocusListener(new FocusListener() {


				public void focusGained(FocusEvent e) {
				    categField.setText(""); 
				}


				public void focusLost(FocusEvent e) {

				}

			});
			categField.setColumns(10);
			categField.setBounds(120, 143, 211, 22);
			add(categField);
			
			JLabel lblNewLabel_4 = new JLabel("Artiste(s) et instument:");
			lblNewLabel_4.setBounds(24, 175, 133, 16);
			add(lblNewLabel_4);
			
			artisteField.setWrapStyleWord(true);
			artisteField.setText("Sous la forme : (NomArtiste, instrument) ; ...");
			artisteField.addFocusListener(new FocusListener() {


				public void focusGained(FocusEvent e) {
				    artisteField.setText(""); 
				}


				public void focusLost(FocusEvent e) {

				}

			});
			artisteField.setLineWrap(true);
			artisteField.setBounds(24, 197, 352, 47);
			add(artisteField);
			
			JButton suivButton = new JButton("Suivant");
			suivButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicSuiv();
				}
			});
			suivButton.setBounds(154, 274, 97, 25);
			add(suivButton);

		}
	}

	public JTextField getTitreField() {
		return titreField;
	}

	public void setTitreField(JTextField titreField) {
		this.titreField = titreField;
	}

	public JTextField getDureeField() {
		return dureeField;
	}

	public void setDureeField(JTextField dureeField) {
		this.dureeField = dureeField;
	}

	public JTextField getCategField() {
		return categField;
	}

	public void setCategField(JTextField categField) {
		this.categField = categField;
	}

	public JSpinner getNumeroField() {
		return numeroField;
	}

	public void setNumeroField(JSpinner numeroField) {
		this.numeroField = numeroField;
	}

	public JTextArea getArtisteField() {
		return artisteField;
	}

	public void setArtisteField(JTextArea artisteField) {
		this.artisteField = artisteField;
	}

	public FichierPiste getFichierPiste() {
		return fichierPiste;
	}

	public void setFichierPiste(FichierPiste fichierPiste) {
		this.fichierPiste = fichierPiste;
	}

	
}
