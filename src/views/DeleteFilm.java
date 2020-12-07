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

import controller.DeleteFilmControl;
import model.Film;

public class DeleteFilm extends View{
	private JTextField titreField;
	private JTextField anneeField;
	private JButton backButton = new JButton("<");
	private JButton suivButton = new JButton("Suivant");
	private JPanel container = new DeleteFilmPanel();
	private Film film;
	private DeleteFilmControl controller = new DeleteFilmControl(this);
	
	public DeleteFilm(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Supprimer un film"));
		
		super.getContainerView().add(container, "Suppression film");
		super.getPanels().add("Suppression film");
		super.getSwitcherView().show(super.getContainerView() , "Suppression film");

		super.getFenetre().setSize(282, 210);
		super.getFenetre().setLocationRelativeTo(null);
	}


	class DeleteFilmPanel extends JPanel {

		/**
		 * Create the panel.
		 */
		public DeleteFilmPanel() {
			setLayout(null);
			
			JLabel lblFilmInfos = new JLabel("Film infos :");
			lblFilmInfos.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblFilmInfos.setBounds(93, 13, 104, 16);
			add(lblFilmInfos);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicBack();
				}
			});
			
			
			backButton.setBounds(12, 11, 47, 34);
			add(backButton);
			
			titreField = new JTextField();
			titreField.setBounds(109, 61, 116, 22);
			add(titreField);
			titreField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Titre :");
			lblNewLabel.setBounds(50, 64, 47, 16);
			add(lblNewLabel);
			
			anneeField = new JTextField();
			anneeField.setColumns(10);
			anneeField.setBounds(109, 96, 116, 22);
			add(anneeField);
			
			JLabel lblAnnenDe = new JLabel("Année :");
			lblAnnenDe.setBounds(50, 99, 47, 16);
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


	public JTextField getTitreField() {
		return titreField;
	}


	public void setTitreField(JTextField titreField) {
		this.titreField = titreField;
	}


	public JTextField getAnneeField() {
		return anneeField;
	}


	public void setAnneeField(JTextField anneeField) {
		this.anneeField = anneeField;
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


	public JPanel getContainer() {
		return container;
	}


	public void setContainer(JPanel container) {
		this.container = container;
	}


	public Film getFilm() {
		return film;
	}


	public void setFilm(Film film) {
		this.film = film;
	}

}
