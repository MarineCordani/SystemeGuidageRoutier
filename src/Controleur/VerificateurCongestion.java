package Controleur;

import java.util.Vector;

import Modele.Artere;

/**
 * Classe du contr�leur pour v�rifier s'il y a congestion sur l'art�re
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
				MoteurTraitement.getEcran().ajouterTexteAuJournal("L'art�re " + a.getIdentifiant() + " est congestionn�e");
			}
		}
	}
	
}
