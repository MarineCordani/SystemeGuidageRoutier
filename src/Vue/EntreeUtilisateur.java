package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EntreeUtilisateur extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntreeUtilisateur(){		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getText() == "Démarrer") {
			button.setText("Arrêter");
		}
		else {
			button.setText("Démarrer");
		}
	}
}
