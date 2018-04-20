package Modele;

import java.util.Vector;

/**
 * Classe du modèle qui constitue l'ensemble d'artères suivies par un véhicule
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class Trajet {

	private Vector<Artere> arteres = new Vector<Artere>();

	/**
	 * Méthode pour retirer la prochaine artère
	 * 
	 * @return
	 */
	public Artere retirerProchainArtere() { // Retire le prochain artere du trajet et l'envoi a la méthode appelante
		if (arteres.isEmpty()) {
			return null;
		}
		Artere temporaire = arteres.get(0);
		arteres.remove(0);
		return temporaire;
	}

	/**
	 * Méthode pour ajouter la prochaine artère
	 * 
	 * @param a
	 */
	public void ajouterProchainArtere(Artere a) {
		arteres.add(a);
	}

	/**
	 * Méthode pour vérifier s'il reste des éléments
	 * 
	 * @return
	 */
	public boolean estTermine() { // Indique (True/false) s'il reste des éléments
		return arteres.isEmpty();
	}

	/**
	 * Méthode pour imprimer le trajet Pour déboggage seulement
	 */
	public void imprimerTrajet() {
		for (Artere a : arteres) {
			System.out.print(a.toString() + ",");
		}
		System.out.println();
	}

	/**
	 * Méthode pour obtenir les artères
	 * 
	 * @return
	 */
	public Vector<Artere> getArteres() {
		return arteres;
	}
}
