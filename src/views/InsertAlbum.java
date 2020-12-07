package views;

import java.awt.CardLayout;
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
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import controller.InsertAlbumControl;
import model.aggregates.FichierPiste;

public class InsertAlbum extends View{
	private JTextField titreField;
	private JTextField groupeField;
	private JTextField dateField;
	private JTextField pochetteField;
	private JButton suivButton = new JButton("Suivant");
	private JPanel container = new InsertAlbumPanel();
	private FichierPiste fichierPiste;
	private InsertAlbumControl controller = new InsertAlbumControl(this);

	public InsertAlbum(JFrame fenetre, CardLayout switcherView, JPanel containerView, FichierPiste fichierPiste) {
		super(fenetre, switcherView, containerView, new String("Ajout d'un album"));
		this.fichierPiste = fichierPiste;
		
		super.getContainerView().add(container, "Insertion album");
		super.getPanels().add("Insertion album");
		super.getSwitcherView().show(super.getContainerView() , "Insertion album");

		super.getFenetre().setSize(470, 305);
		super.getFenetre().setLocationRelativeTo(null);
	}

	class InsertAlbumPanel extends JPanel {

		/**
		 * Create the panel.
		 */
		public InsertAlbumPanel() {
			setLayout(null);
			
			JLabel lblAlbumInconnu = new JLabel("ALBUM INCONNU !");
			lblAlbumInconnu.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblAlbumInconnu.setBounds(127, 13, 205, 34);
			add(lblAlbumInconnu);
			
			JLabel lblAjoutezLalbum = new JLabel("Ajoutez l'album :");
			lblAjoutezLalbum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			lblAjoutezLalbum.setBounds(150, 39, 140, 22);
			add(lblAjoutezLalbum);
			
			JLabel lblNewLabel_1 = new JLabel("Titre :");
			lblNewLabel_1.setBounds(12, 81, 45, 16);
			add(lblNewLabel_1);
			
			titreField = new JTextField();
			titreField.setColumns(10);
			titreField.setBounds(69, 78, 116, 22);
			add(titreField);
			
			JLabel lblNewLabel_1_1 = new JLabel("Groupe / Artiste :");
			lblNewLabel_1_1.setBounds(215, 81, 105, 16);
			add(lblNewLabel_1_1);
			
			groupeField = new JTextField();
			groupeField.setColumns(10);
			groupeField.setBounds(322, 78, 116, 22);
			add(groupeField);
			
			JLabel lblNewLabel_1_1_1 = new JLabel("Date de sortie :");
			lblNewLabel_1_1_1.setBounds(12, 123, 100, 16);
			add(lblNewLabel_1_1_1);
			
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
			dateField.setBounds(114, 120, 116, 22);
			add(dateField);
			
			JLabel lblNewLabel_1_3 = new JLabel("Pochette :");
			lblNewLabel_1_3.setBounds(12, 162, 65, 16);
			add(lblNewLabel_1_3);
			
			pochetteField = new JTextField();
			pochetteField.setColumns(10);
			pochetteField.setBounds(89, 159, 104, 22);
			add(pochetteField);
			
			JButton pochetteButton = new JButton("Parcourir");
			pochetteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					int returnValue = jfc.showOpenDialog(null);

			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			            File selectedFile = jfc.getSelectedFile();
			            pochetteField.setText(selectedFile.getAbsolutePath());
			        }
				}
			});
			pochetteButton.setBounds(205, 159, 85, 22);
			add(pochetteButton);
			
			
			suivButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicSuiv();
				}
			});
			suivButton.setBounds(181, 223, 97, 25);
			add(suivButton);

		}
	}

	public JTextField getTitreField() {
		return titreField;
	}

	public void setTitreField(JTextField titreField) {
		this.titreField = titreField;
	}

	public JTextField getGroupeField() {
		return groupeField;
	}

	public void setGroupeField(JTextField groupeField) {
		this.groupeField = groupeField;
	}

	public JTextField getDateField() {
		return dateField;
	}

	public void setDateField(JTextField dateField) {
		this.dateField = dateField;
	}

	public JTextField getPochetteField() {
		return pochetteField;
	}

	public void setPochetteField(JTextField pochetteField) {
		this.pochetteField = pochetteField;
	}

	public JButton getSuivButton() {
		return suivButton;
	}

	public void setSuivButton(JButton suivButton) {
		this.suivButton = suivButton;
	}

	public FichierPiste getFichierPiste() {
		return fichierPiste;
	}

	public void setFichierPiste(FichierPiste fichierPiste) {
		this.fichierPiste = fichierPiste;
	}

	
}
