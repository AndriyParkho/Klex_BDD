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

import controller.InsertPisteControl;
import model.aggregates.FichierPiste;

public class InsertPiste extends View{
	private JTextField titrePisteField;
	private JTextField titreAlbumField;
	private JButton backButton = new JButton("<");
	private JButton suivButton = new JButton("Suivant");
	private JPanel container = new InsertPistePanel();
	private FichierPiste fichierPiste = new FichierPiste();
	private InsertPisteControl controller = new InsertPisteControl(this);
	
	public InsertPiste(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Insérer une piste"));
		
		super.getContainerView().add(container, "Insertion piste 1");
		super.getPanels().add("Insertion piste 1");
		super.getSwitcherView().show(super.getContainerView() , "Insertion piste 1");

		super.getFenetre().setSize(282, 210);
		super.getFenetre().setLocationRelativeTo(null);
	}


	class InsertPistePanel extends JPanel {

		/**
		 * Create the panel.
		 */
		public InsertPistePanel() {
			setLayout(null);
			
			JLabel lblFilmInfos = new JLabel("Piste infos :");
			lblFilmInfos.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblFilmInfos.setBounds(85, 13, 116, 16);
			add(lblFilmInfos);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicBack();
				}
			});
			
			
			backButton.setBounds(12, 11, 47, 34);
			add(backButton);
			
			titrePisteField = new JTextField();
			titrePisteField.setBounds(109, 61, 116, 22);
			add(titrePisteField);
			titrePisteField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Titre piste :");
			lblNewLabel.setBounds(30, 64, 67, 16);
			add(lblNewLabel);
			
			titreAlbumField = new JTextField();
			titreAlbumField.setColumns(10);
			titreAlbumField.setBounds(109, 96, 116, 22);
			add(titreAlbumField);
			
			JLabel lblAnnenDe = new JLabel("Titre album :");
			lblAnnenDe.setBounds(22, 99, 75, 16);
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


	public JTextField getTitrePisteField() {
		return titrePisteField;
	}


	public void setTitrePisteField(JTextField titrePisteField) {
		this.titrePisteField = titrePisteField;
	}


	public JTextField getTitreAlbumField() {
		return titreAlbumField;
	}


	public void setTitreAlbumField(JTextField titreAlbumField) {
		this.titreAlbumField = titreAlbumField;
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


	public FichierPiste getFichierPiste() {
		return fichierPiste;
	}


	public void setFichierPiste(FichierPiste fichierPiste) {
		this.fichierPiste = fichierPiste;
	}


	


}
