package Controleur;

import java.util.Vector;

import Modele.Artere;

/**
 * Classe du contrôleur pour vérifier s'il y a congestion sur l'artère
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class VerificateurCongestion {	
	private boolean siChangementEtat = false;
	public void verifierCongestion(){
			
		Vector<Artere> arteres = MoteurTraitement.getReseauRoutier().getArteres();
		for(Artere a: arteres) {
			boolean presenceCongestion = a.getPresenceCongestion();
			boolean congestionDetecte = a.verifierCongestion();
			if(presenceCongestion == false && congestionDetecte) {
				MoteurTraitement.getEcran().ajouterTexteAuJournal("L'artère " + a.getIdentifiant() + " est congestionnée");
			}
		}
	}
	
}
