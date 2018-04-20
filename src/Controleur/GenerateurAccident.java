package Controleur;

import java.util.Vector;

import Modele.Artere;

/**
 * Classe du contrôleur pour décider quand un accident survient sur un artère
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class GenerateurAccident {

	private double probabilite;

	/**
	 * Constructeur
	 */
	public GenerateurAccident() {
		probabilite = 0.05;
	}

	/**
	 * Constructeur
	 * 
	 * @param p
	 *            probabilité d'accidents
	 */
	public GenerateurAccident(double p) {
		probabilite = p;
	}

	/**
	 * Méthode pour générer les accidents
	 * 
	 * @return true si un accident a bien eu lieu
	 */
	public boolean genererAccident() {
		if (Math.random() > probabilite) {
			return false;
		}

		Vector<Artere> artere = MoteurTraitement.getReseauRoutier().getArteres();
		Artere artereAleatoire;
		int indexAleatoire = (int) (Math.random() * artere.size());
		artereAleatoire = artere.get(indexAleatoire);

		do {
			if (artereAleatoire.creerAccident()) {
				MoteurTraitement.getEcran()
						.ajouterTexteAuJournal("L'arthère " + artereAleatoire.getIdentifiant() + " est accidentée");
				return true;
			} else {
				indexAleatoire++; // Essayer de creer accident sur prochain artere
			}
		} while (indexAleatoire != artere.size());

		return false;
	}
}
