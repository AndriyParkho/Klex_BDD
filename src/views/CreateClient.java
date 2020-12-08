package views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretListener;

import controller.CreateClientControl;
import model.aggregates.ClientCodecs;

public class CreateClient extends JFrame {
	private JPanel contentPane;
	private JTextField marqueField;
	private JTextField modeleField;
	private ArrayList<InsertCodecPanel> codecPanels = new ArrayList<InsertCodecPanel>();
	private JSpinner largeurField = new JSpinner();
	private JSpinner hauteurField = new JSpinner();
	private String marqueInconnu;
	private String modeleInconnu;
	private CreateClientControl controller = new CreateClientControl(this);

	/**
	 * Create the frame.
	 */
	public CreateClient(String marqueInconnu, String modeleInconnu) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClientInconnu = new JLabel("CLIENT INCONNU !");
		lblClientInconnu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblClientInconnu.setBounds(123, 13, 205, 34);
		contentPane.add(lblClientInconnu);
		
		JLabel lblAjoutezLeClient = new JLabel("Ajoutez le client :");
		lblAjoutezLeClient.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAjoutezLeClient.setBounds(161, 44, 140, 22);
		contentPane.add(lblAjoutezLeClient);
		
		JLabel lblNewLabel_1 = new JLabel("Marque :");
		lblNewLabel_1.setBounds(12, 92, 60, 16);
		contentPane.add(lblNewLabel_1);
		
		marqueField = new JTextField();
		marqueField.setText(marqueInconnu);
		marqueField.setColumns(10);
		marqueField.setBounds(77, 89, 116, 22);
		contentPane.add(marqueField);
		
		JLabel lblNewLabel_1_2 = new JLabel("Mod\u00E8le :");
		lblNewLabel_1_2.setBounds(229, 92, 55, 16);
		contentPane.add(lblNewLabel_1_2);
		
		modeleField = new JTextField();
		modeleField.setText(modeleInconnu);
		modeleField.setColumns(10);
		modeleField.setBounds(296, 89, 116, 22);
		contentPane.add(modeleField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Largeur max :");
		lblNewLabel_1_1.setBounds(12, 127, 89, 16);
		contentPane.add(lblNewLabel_1_1);
		
		
		largeurField.setBounds(101, 124, 60, 22);
		contentPane.add(largeurField);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Hauteur max :");
		lblNewLabel_1_1_1.setBounds(229, 127, 89, 16);
		contentPane.add(lblNewLabel_1_1_1);
		
		
		hauteurField.setBounds(318, 124, 60, 22);
		contentPane.add(hauteurField);
		
		CardLayout codecSwitch = new CardLayout();
		JPanel panel = new JPanel(codecSwitch);
		panel.setBounds(0, 170, 432, 60);
		contentPane.add(panel);
		
		InsertCodecPanel codecInit = new InsertCodecPanel(1);
		panel.add(codecInit, "codec1");
		codecPanels.add(codecInit);
		codecSwitch.show(panel, "codec1");
		
		JButton ajoutCodecBtn = new JButton("Ajouter Codec");
		ajoutCodecBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertCodecPanel lastCodec = codecPanels.get(codecPanels.size() - 1);
				Border invalidBrd = BorderFactory.createLineBorder(Color.RED);
				if("".equals(lastCodec.getNomField().getText()))
					lastCodec.getNomField().setBorder(invalidBrd);
				else {
					int count = lastCodec.getCount() + 1;
					InsertCodecPanel newCodecP = new InsertCodecPanel(count);
					panel.add(newCodecP, "codec" + count);
					codecPanels.add(newCodecP);
					codecSwitch.show(panel, "codec" + count);
				}
			}
		});
		ajoutCodecBtn.setBounds(282, 235, 133, 25);
		contentPane.add(ajoutCodecBtn);
		
		JButton valideButton = new JButton("Valider");
		valideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.clicValid();
			}
		});
		valideButton.setBounds(177, 285, 97, 25);
		contentPane.add(valideButton);
		codecPanels.add(codecInit);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	class InsertCodecPanel extends JPanel {
		private JTextField nomField;
		private JComboBox typeField = new JComboBox();
		private int count;

		/**
		 * Create the panel.
		 */
		public InsertCodecPanel(int count) {
			this.count = count;
			setBorder(new TitledBorder(null, "Ajout du codec n\u00B0" + count, TitledBorder.LEADING, TitledBorder.TOP, null, null));
			setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Nom :");
			lblNewLabel_1.setBounds(22, 28, 60, 16);
			add(lblNewLabel_1);
			
			nomField = new JTextField();
			nomField.setColumns(10);
			nomField.setBounds(75, 25, 116, 22);
			nomField.addCaretListener(new CaretListener() {
	            public void caretUpdate(javax.swing.event.CaretEvent e) {
	            	nomField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				}
			});
			add(nomField);
			
			JLabel lblNewLabel_1_2 = new JLabel("Type :");
			lblNewLabel_1_2.setBounds(229, 28, 55, 16);
			add(lblNewLabel_1_2);
			
			
			typeField.setModel(new DefaultComboBoxModel(new String[] {"Texte", "Audio", "Video"}));
			typeField.setBounds(286, 25, 116, 22);
			add(typeField);

		}

		public JTextField getNomField() {
			return nomField;
		}

		public void setNomField(JTextField nomField) {
			this.nomField = nomField;
		}

		public JComboBox getTypeField() {
			return typeField;
		}

		public void setTypeField(JComboBox typeField) {
			this.typeField = typeField;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}	
	}

	public JTextField getMarqueField() {
		return marqueField;
	}

	public void setMarqueField(JTextField marqueField) {
		this.marqueField = marqueField;
	}

	public JTextField getModeleField() {
		return modeleField;
	}

	public void setModeleField(JTextField modeleField) {
		this.modeleField = modeleField;
	}

	public ArrayList<InsertCodecPanel> getCodecPanels() {
		return codecPanels;
	}

	public void setCodecPanels(ArrayList<InsertCodecPanel> codecPanels) {
		this.codecPanels = codecPanels;
	}

	public JSpinner getLargeurField() {
		return largeurField;
	}

	public void setLargeurField(JSpinner largeurField) {
		this.largeurField = largeurField;
	}

	public JSpinner getHauteurField() {
		return hauteurField;
	}

	public void setHauteurField(JSpinner hauteurField) {
		this.hauteurField = hauteurField;
	}

	public String getMarqueInconnu() {
		return marqueInconnu;
	}

	public void setMarqueInconnu(String marqueInconnu) {
		this.marqueInconnu = marqueInconnu;
	}

	public String getModeleInconnu() {
		return modeleInconnu;
	}

	public void setModeleInconnu(String modeleInconnu) {
		this.modeleInconnu = modeleInconnu;
	}
	

}
