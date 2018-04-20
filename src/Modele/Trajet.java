package Modele;

import java.util.Vector;

/**
 * Classe du mod�le qui constitue l'ensemble d'art�res suivies par un v�hicule
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class Trajet {

	private Vector<Artere> arteres = new Vector<Artere>();

	/**
	 * M�thode pour retirer la prochaine art�re
	 * 
	 * @return
	 */
	public Artere retirerProchainArtere() { // Retire le prochain artere du trajet et l'envoi a la m�thode appelante
		if (arteres.isEmpty()) {
			return null;
		}
		Artere temporaire = arteres.get(0);
		arteres.remove(0);
		return temporaire;
	}

	/**
	 * M�thode pour ajouter la prochaine art�re
	 * 
	 * @param a
	 */
	public void ajouterProchainArtere(Artere a) {
		arteres.add(a);
	}

	/**
	 * M�thode pour v�rifier s'il reste des �l�ments
	 * 
	 * @return
	 */
	public boolean estTermine() { // Indique (True/false) s'il reste des �l�ments
		return arteres.isEmpty();
	}

	/**
	 * M�thode pour imprimer le trajet Pour d�boggage seulement
	 */
	public void imprimerTrajet() {
		for (Artere a : arteres) {
			System.out.print(a.toString() + ",");
		}
		System.out.println();
	}

	/**
	 * M�thode pour obtenir les art�res
	 * 
	 * @return
	 */
	public Vector<Artere> getArteres() {
		return arteres;
	}
}
