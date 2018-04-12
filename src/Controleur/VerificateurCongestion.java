package Controleur;

import java.util.Vector;

import Modele.Artere;

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
