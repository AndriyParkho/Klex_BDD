package views;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import connections.ConnectionOracle;

public class FenetrePrincipal {
	
	public FenetrePrincipal() {
		JFrame frame = new JFrame();
		JPanel containerView = new JPanel();
		CardLayout switcherView = new CardLayout();
		containerView.setLayout(switcherView);
		
		new Accueil(frame, switcherView, containerView);
		
		frame.add(containerView);
		frame.addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		        super.windowClosing(e);
		        ConnectionOracle.closeInstance();
		    }
		});
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
	}
}
