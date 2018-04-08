package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Vue.Graphique.AfficheurDirection;
import Controleur.MoteurTraitement;

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
			String o = MoteurTraitement.getEcran().getOrigine();
			String d = MoteurTraitement.getEcran().getDestination();
			if (o.equals(d)){
				MoteurTraitement.getEcran().ajouterTexteAuJournal("L'origine et la destination doivent �tre diff�rentes!");
				return;
			}
			MoteurTraitement.definirTrajet(o, d);
			MoteurTraitement.setLancer(true);
		}
		else {
			button.setText("D�marrer");
			MoteurTraitement.setLancer(false);
		}
	}
}
