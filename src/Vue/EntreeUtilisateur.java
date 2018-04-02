package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe de la vue qui est un panneau o� mettre les contr�les d'entr�e
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class EntreeUtilisateur extends JPanel implements ActionListener{
	
	/**
	 * La cl� de hachage SHA qui identifie de mani�re unique la Classe
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getText() == "D�marrer") {
			button.setText("Arr�ter");
		}
		else {
			button.setText("D�marrer");
		}
	}
}
