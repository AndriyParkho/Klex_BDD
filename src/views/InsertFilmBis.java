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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import controller.InsertFilmBisControl;
import model.aggregates.FichierFilm;

public class InsertFilmBis extends View{
	private JTextField afficheField;
	private JTextField categField;
	private JTextField imagesField;
	private JTextArea resumeField = new JTextArea();
	private JSpinner ageField = new JSpinner();
	private JButton afficheButton = new JButton("Parcourir");
	private JButton imagesButton = new JButton("Parcourir");
	private JButton suivButton = new JButton("Suivant");
	private JTextArea artisteField = new JTextArea();
	private JPanel container = new InsertFilmBisPanel();
	private FichierFilm fichierFilm;
	private InsertFilmBisControl controller = new InsertFilmBisControl(this);

	public InsertFilmBis(JFrame fenetre, CardLayout switcherView, JPanel containerView, FichierFilm fichierFilm) {
		super(fenetre, switcherView, containerView, new String("Ins\u00E9rer un film"));
		this.fichierFilm = fichierFilm;
		
		super.getContainerView().add(container, "Insertion film 2");
		super.getPanels().add("Insertion film 2");
		super.getSwitcherView().show(super.getContainerView() , "Insertion film 2");

		super.getFenetre().setSize(470, 400);
		super.getFenetre().setLocationRelativeTo(null);
	}

	class InsertFilmBisPanel extends JPanel {

		/**
		 * Create the panel.
		 */
		public InsertFilmBisPanel() {
			setLayout(null);
			
			JLabel lblFilmInfos = new JLabel("Film infos :");
			lblFilmInfos.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblFilmInfos.setBounds(172, 13, 119, 16);
			add(lblFilmInfos);
			resumeField.setWrapStyleWord(true);
			resumeField.setLineWrap(true);
			
			
			resumeField.setBounds(29, 82, 392, 62);
			add(resumeField);
			
			JLabel lblNewLabel = new JLabel("R\u00E9sum\u00E9 :");
			lblNewLabel.setBounds(29, 60, 56, 16);
			add(lblNewLabel);
			
			
			ageField.setBounds(101, 157, 46, 22);
			add(ageField);
			
			JLabel lblAgeMin = new JLabel("Age min. :");
			lblAgeMin.setBounds(29, 160, 60, 16);
			add(lblAgeMin);
			
			afficheField = new JTextField();
			afficheField.setBounds(242, 157, 104, 22);
			add(afficheField);
			afficheField.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Affiche :");
			lblNewLabel_1.setBounds(184, 160, 56, 16);
			add(lblNewLabel_1);
			
			
			afficheButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					int returnValue = jfc.showOpenDialog(null);

			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			            File selectedFile = jfc.getSelectedFile();
			            afficheField.setText(selectedFile.getAbsolutePath());
			        }
				}
			});
			afficheButton.setBounds(353, 157, 85, 22);
			add(afficheButton);
			
			JLabel lblNewLabel_2 = new JLabel("Cat\u00E9gorie(s) :");
			lblNewLabel_2.setBounds(29, 189, 247, 16);
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
			categField.setBounds(29, 209, 211, 22);
			add(categField);
			categField.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Image(s) extraite(s) :");
			lblNewLabel_3.setBounds(29, 244, 130, 16);
			add(lblNewLabel_3);
			
			imagesField = new JTextField();
			imagesField.setToolTipText("Selection multiple possible");
			imagesField.setBounds(29, 269, 151, 22);
			add(imagesField);
			imagesField.setColumns(10);
			
			
			imagesButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			        jfc.setMultiSelectionEnabled(true);
			        int returnValue = jfc.showOpenDialog(null);

			        if (returnValue == JFileChooser.APPROVE_OPTION) {
			        	String urls = new String("");
			            File[] selectedFiles = jfc.getSelectedFiles();
			            for(int i = 0; i < selectedFiles.length - 1; ++i)
			            	urls += selectedFiles[i].getAbsolutePath() + " ; ";
			            urls += selectedFiles[selectedFiles.length - 1];
			            imagesField.setText(urls);
			        }
				}
			});
			imagesButton.setToolTipText("Selection multiple possible");
			imagesButton.setBounds(184, 269, 85, 22);
			add(imagesButton);
			
			
			suivButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicSuiv();
				}
			});
			suivButton.setBounds(179, 321, 97, 25);
			add(suivButton);
			
			JLabel lblNewLabel_4 = new JLabel("Artiste(s) et role:");
			lblNewLabel_4.setBounds(290, 189, 131, 16);
			add(lblNewLabel_4);
			
			
			artisteField.setWrapStyleWord(true);
			artisteField.setLineWrap(true);
			artisteField.setText("Sous la forme : (NomArtiste, role) ; ...");
			artisteField.addFocusListener(new FocusListener() {


				public void focusGained(FocusEvent e) {
				    artisteField.setText(""); 
				}


				public void focusLost(FocusEvent e) {

				}

			});
			artisteField.setBounds(290, 209, 148, 82);
			add(artisteField);

		}
	}

	public JTextField getAfficheField() {
		return afficheField;
	}

	public void setAfficheField(JTextField afficheField) {
		this.afficheField = afficheField;
	}

	public JTextField getCategField() {
		return categField;
	}

	public void setCategField(JTextField categField) {
		this.categField = categField;
	}

	public JTextField getImagesField() {
		return imagesField;
	}

	public void setImagesField(JTextField imagesField) {
		this.imagesField = imagesField;
	}

	public JTextArea getResumeField() {
		return resumeField;
	}

	public void setResumeField(JTextArea resumeField) {
		this.resumeField = resumeField;
	}

	public JSpinner getAgeField() {
		return ageField;
	}

	public void setAgeField(JSpinner ageField) {
		this.ageField = ageField;
	}

	public JButton getAfficheButton() {
		return afficheButton;
	}

	public void setAfficheButton(JButton afficheButton) {
		this.afficheButton = afficheButton;
	}

	public JButton getImagesButton() {
		return imagesButton;
	}

	public void setImagesButton(JButton imagesButton) {
		this.imagesButton = imagesButton;
	}

	public JButton getSuivButton() {
		return suivButton;
	}

	public void setSuivButton(JButton suivButton) {
		this.suivButton = suivButton;
	}

	public JTextArea getArtisteField() {
		return artisteField;
	}

	public void setArtisteField(JTextArea artisteField) {
		this.artisteField = artisteField;
	}

	public FichierFilm getFichierFilm() {
		return fichierFilm;
	}

	public void setFichierFilm(FichierFilm fichierFilm) {
		this.fichierFilm = fichierFilm;
	}
	
	
}
