package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import controller.CreateArtisteControl;
import model.aggregates.FichierFilm;
import model.aggregates.FichierPiste;

public class CreateArtiste extends JFrame {
	private String artisteInconnu;
	private JPanel contentPane;
	private JTextField nomField;
	private JTextField dateField;
	private JTextField specField;
	private JTextField photoField;
	private JButton valideButton = new JButton("Valider");
	private JTextArea bioField = new JTextArea();
	private String roleOuInstru;
	private FichierFilm fichierFilm;
	private FichierPiste fichierPiste;
	private CreateArtisteControl controller = new CreateArtisteControl(this);

	/**
	 * Create the frame.
	 */
	public CreateArtiste(String artisteInconnu, String roleOuInstru, FichierFilm fichierFilm, FichierPiste fichierPiste) {
		this.artisteInconnu = artisteInconnu;
		this.fichierFilm = fichierFilm;
		this.fichierPiste = fichierPiste;
		this.roleOuInstru = roleOuInstru;
		
		setTitle("Ajout artiste");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArtisteInconnu = new JLabel("ARTISTE INCONNU !");
		lblArtisteInconnu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblArtisteInconnu.setBounds(116, 13, 205, 34);
		contentPane.add(lblArtisteInconnu);
		
		JLabel lblNewLabel = new JLabel("Ajoutez l'artiste :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(154, 44, 140, 22);
		contentPane.add(lblNewLabel);
		
		nomField = new JTextField(artisteInconnu);
		nomField.setBounds(66, 89, 116, 22);
		contentPane.add(nomField);
		nomField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setBounds(9, 92, 45, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date de naissance :");
		lblNewLabel_1_1.setBounds(9, 127, 116, 16);
		contentPane.add(lblNewLabel_1_1);
		
		dateField = new JTextField();
		dateField.setText("jj/mm/yyyy");
		dateField.addFocusListener(new FocusListener() {


			public void focusGained(FocusEvent e) {
			    dateField.setText(""); 
			}


			public void focusLost(FocusEvent e) {

			}

		});
		dateField.setColumns(10);
		dateField.setBounds(137, 124, 116, 22);
		contentPane.add(dateField);
		
		JLabel lblNewLabel_1_2 = new JLabel("Sp�cialit� :");
		lblNewLabel_1_2.setBounds(217, 92, 64, 16);
		contentPane.add(lblNewLabel_1_2);
		
		specField = new JTextField();
		specField.setColumns(10);
		specField.setBounds(293, 89, 116, 22);
		contentPane.add(specField);
		
		JLabel lblNewLabel_1_3 = new JLabel("Photo :");
		lblNewLabel_1_3.setBounds(9, 162, 56, 16);
		contentPane.add(lblNewLabel_1_3);
		
		photoField = new JTextField();
		photoField.setColumns(10);
		photoField.setBounds(67, 159, 104, 22);
		contentPane.add(photoField);
		
		JButton photoButton = new JButton("Parcourir");
		photoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(null);

		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = jfc.getSelectedFile();
		            photoField.setText(selectedFile.getAbsolutePath());
		        }
			}
		});
		photoButton.setBounds(178, 159, 85, 22);
		contentPane.add(photoButton);
		
		JLabel lblNewLabel_2 = new JLabel("Biographie :");
		lblNewLabel_2.setBounds(9, 196, 75, 16);
		contentPane.add(lblNewLabel_2);
		
		
		bioField.setWrapStyleWord(true);
		bioField.setLineWrap(true);
		bioField.setBounds(88, 193, 321, 47);
		contentPane.add(bioField);
		valideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.clicValid();
			}
		});
		
		
		valideButton.setBounds(166, 259, 97, 25);
		contentPane.add(valideButton);
		
		this.setVisible(true);
	}

	public String getArtisteInconnu() {
		return artisteInconnu;
	}

	public void setArtisteInconnu(String artisteInconnu) {
		this.artisteInconnu = artisteInconnu;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getNomField() {
		return nomField;
	}

	public void setNomField(JTextField nomField) {
		this.nomField = nomField;
	}

	public JTextField getDateField() {
		return dateField;
	}

	public void setDateField(JTextField dateField) {
		this.dateField = dateField;
	}

	public JTextField getSpecField() {
		return specField;
	}

	public void setSpecField(JTextField specField) {
		this.specField = specField;
	}

	public JTextField getPhotoField() {
		return photoField;
	}

	public void setPhotoField(JTextField photoField) {
		this.photoField = photoField;
	}

	public JButton getValideButton() {
		return valideButton;
	}

	public void setValideButton(JButton valideButton) {
		this.valideButton = valideButton;
	}

	public JTextArea getBioField() {
		return bioField;
	}

	public void setBioField(JTextArea bioField) {
		this.bioField = bioField;
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

	public CreateArtisteControl getController() {
		return controller;
	}

	public void setController(CreateArtisteControl controller) {
		this.controller = controller;
	}
	
	
}
