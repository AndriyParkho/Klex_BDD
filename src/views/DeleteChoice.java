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

import controller.DeleteChoiceControl;

public class DeleteChoice extends View{
	private JLabel lblNewLabel = new JLabel("Supprimer :");
	private JRadioButton filmChoice = new JRadioButton("Un film");
	private JRadioButton pisteChoice = new JRadioButton("Une piste");
	private JButton valideButton = new JButton("Valider");
	private JPanel container = new DeleteChxPanel();
	private DeleteChoiceControl controller = new DeleteChoiceControl(this);

	public DeleteChoice(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Choix de suppression"));

		super.getContainerView().add(container, "Choix supprimer");
		super.getPanels().add("Choix supprimer");
		super.getSwitcherView().show(super.getContainerView() , "Choix supprimer");

		super.getFenetre().setSize(282, 210);
		super.getFenetre().setLocationRelativeTo(null);
	}

	class DeleteChxPanel extends JPanel {
		/**
		 * Create the panel.
		 */
		public DeleteChxPanel() {
			setLayout(null);
			
			ButtonGroup choixInsert = new ButtonGroup();
			
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setBounds(80, 23, 117, 22);
			add(lblNewLabel);
			
			
			filmChoice.setSelected(true);
			filmChoice.setBounds(76, 59, 127, 25);
			choixInsert.add(filmChoice);
			add(filmChoice);
			
			
			pisteChoice.setBounds(76, 89, 127, 25);
			choixInsert.add(pisteChoice);
			add(pisteChoice);
			valideButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			
			
			valideButton.setBounds(86, 133, 97, 25);
			add(valideButton);
			
			JButton backButton = new JButton("<");
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			backButton.setBounds(12, 16, 47, 34);
			add(backButton);
		}

	}


}