package views;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.FonctionsControl;
import controller.InsertChoiceControl;

public class InsertChoice extends View{
	private JLabel lblNewLabel = new JLabel("Insérer :");
	private JRadioButton filmChoice = new JRadioButton("Un film");
	private JRadioButton pisteChoice = new JRadioButton("Une piste");
	private JButton valideButton = new JButton("Valider");
	private JPanel container = new InsertChxPanel();
	private InsertChoiceControl controller = new InsertChoiceControl(this);

	public InsertChoice(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Choix d'insertion"));

		super.getContainerView().add(container, "Choix insertion");
		super.getPanels().add("Choix insertion");
		super.getSwitcherView().show(super.getContainerView() , "Choix insertion");

		super.getFenetre().setSize(282, 210);
		super.getFenetre().setLocationRelativeTo(null);
	}

	class InsertChxPanel extends JPanel {
		
		/**
		 * Create the panel.
		 */
		public InsertChxPanel() {
			setLayout(null);
			
			
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setBounds(94, 23, 89, 16);
			add(lblNewLabel);
			
			
			filmChoice.setSelected(true);
			filmChoice.setBounds(76, 59, 127, 25);
			add(filmChoice);
			
			
			pisteChoice.setBounds(76, 89, 127, 25);
			add(pisteChoice);
			valideButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicValide();
				}
			});
			
			
			valideButton.setBounds(86, 133, 97, 25);
			add(valideButton);
			
			JButton backButton = new JButton("<");
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicBack();
				}
			});
			backButton.setBounds(12, 16, 47, 34);
			add(backButton);

		}
	}

}
