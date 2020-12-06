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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controller.FluxNbChoiceControl;
import controller.FonctionsControl;
import controller.InsertChoiceControl;

public class FluxNbChoice extends View{
	private JSpinner nombreField = new JSpinner();
	private JPanel container = new ChoixNbFluxPanel();
	private FluxNbChoiceControl controller = new FluxNbChoiceControl(this);

	public FluxNbChoice(JFrame fenetre, CardLayout switcherView, JPanel containerView) {
		super(fenetre, switcherView, containerView, new String("Nombre de flux"));

		super.getContainerView().add(container, "Nombre flux");
		super.getPanels().add("Nombre flux");
		super.getSwitcherView().show(super.getContainerView() , "Nombre flux");

		super.getFenetre().setSize(282, 210);
		super.getFenetre().setLocationRelativeTo(null);
	}

	class ChoixNbFluxPanel extends JPanel {
		/**
		 * Create the panel.
		 */
		public ChoixNbFluxPanel() {
			setLayout(null);
			
			JLabel lblFluxDuFichier = new JLabel("Flux du fichier :");
			lblFluxDuFichier.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblFluxDuFichier.setBounds(61, 23, 151, 16);
			add(lblFluxDuFichier);
			
			JLabel lblNombreDeFlux = new JLabel("Nombre de flux :");
			lblNombreDeFlux.setBounds(58, 81, 96, 16);
			add(lblNombreDeFlux);
			nombreField.setModel(new SpinnerNumberModel(1, 1, null, 1));
			
			nombreField.setBounds(166, 78, 46, 22);
			add(nombreField);
			
			JButton suivButton = new JButton("Suivant");
			suivButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.clicSuiv();
				}
			});
			suivButton.setBounds(84, 137, 97, 25);
			add(suivButton);

		}

	}

	public JSpinner getNombreField() {
		return nombreField;
	}

	public void setNombreField(JSpinner nombreField) {
		this.nombreField = nombreField;
	}

}
