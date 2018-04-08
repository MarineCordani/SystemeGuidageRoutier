package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Vue.Graphique.AfficheurDirection;
import Controleur.MoteurTraitement;

/**
 * Classe de la vue qui est un panneau où mettre les contrôles d'entrée
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class EntreeUtilisateur extends JPanel implements ActionListener{
	
	/**
	 * La clé de hachage SHA qui identifie de manière unique la Classe
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getText() == "Démarrer") {
			button.setText("Arrêter");
			String o = MoteurTraitement.getEcran().getOrigine();
			String d = MoteurTraitement.getEcran().getDestination();
			if (o.equals(d)){
				MoteurTraitement.getEcran().ajouterTexteAuJournal("L'origine et la destination doivent être différentes!");
				return;
			}
			MoteurTraitement.definirTrajet(o, d);
			MoteurTraitement.setLancer(true);
		}
		else {
			button.setText("Démarrer");
			MoteurTraitement.setLancer(false);
		}
	}
}
