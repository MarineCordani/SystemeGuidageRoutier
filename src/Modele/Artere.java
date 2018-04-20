package Modele;

import java.util.Vector;

/**
 * Classe du mod�le qui repr�sentation un tron�on sur lequel circulent les
 * v�hicules
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class Artere {

	private final int NBR_VEHICULES_POUR_CONGESTION = 4;
	private final int DISTANCE_MINIMAL_POUR_ACCIDENT = 22;

	private boolean presenceAccident;
	private boolean presenceCongestion;
	private int vitesseLimite; // Toujours etre divisible par 4
	private int vitesseActuel; // Vitesse est egal a vitesse limite, diviser par 2 si accident, diviser par 2
								// si congestion
	private int longueur; // Pareille pour tous
	private String identifiant;

	private Intersection intersectionA;
	private Intersection intersectionB;
	private Vector<Vehicule> vehicules = new Vector<Vehicule>(); // Changer pour une structure elastique (Vector)

	/**
	 * Constructeur
	 * 
	 * @param v
	 * @param a
	 * @param b
	 */
	public Artere(int v, Intersection a, Intersection b) {
		this.presenceAccident = false;
		this.presenceCongestion = false;
		this.vitesseLimite = v;
		this.vitesseActuel = this.vitesseLimite;
		this.longueur = 100;
		this.identifiant = a.toString() + "-" + b.toString();

		this.intersectionA = a;
		a.connecterArtere(this);
		this.intersectionB = b;
		b.connecterArtere(this);
	}

	/**
	 * Cette m�thode permet de conna�tre l'intersection a de l'arth�re
	 * 
	 * @return l'intersection a de l'arth�re
	 */
	public Intersection getA() {
		return this.intersectionA;
	}

	/**
	 * Cette m�thode permet de conna�tre l'intersection b de l'arth�re
	 * 
	 * @return l'intersection b de l'arth�re
	 */
	public Intersection getB() {
		return this.intersectionB;
	}

	/**
	 * Cette m�thode permet de conna�tre la dur�e pour parcourir l'arth�re
	 * 
	 * @return la dur�e pour parcourir l'arth�re
	 */
	public int dureeTraverse() {
		int temp = longueur / this.getVitesseActuelle();
		/*
		 * if (presenceAccident){ temp*=5; }
		 */
		return temp;
	}

	/**
	 * M�thode pour cr�er les accidents
	 * 
	 * @return
	 */
	public boolean creerAccident() {
		// Si pas au moins deux vehicule, annuler l'accident
		// Si pas congestion aussi
		if (vehicules.size() < 2 || presenceCongestion) {
			return false;
		}

		// Accidenter deux voitures
		Vehicule a = null;
		Vehicule b = null;

		for (int i = 0; i < vehicules.size(); i++) {
			for (int j = i + 1; j < vehicules.size(); j++) {
				a = vehicules.get(i);
				b = vehicules.get(j);

				if (a.getPositionX() == b.getPositionX()
						&& Math.abs(a.getPositionY() - b.getPositionY()) <= DISTANCE_MINIMAL_POUR_ACCIDENT) {
					a.SetAccidente(true);
					b.SetAccidente(true);
					presenceAccident = true;
					return true;
				} else if (a.getPositionY() == b.getPositionY()
						&& Math.abs(a.getPositionX() - b.getPositionX()) <= DISTANCE_MINIMAL_POUR_ACCIDENT) {
					a.SetAccidente(true);
					b.SetAccidente(true);
					presenceAccident = true;
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * V�rifier Congestion
	 * 
	 * @return
	 */
	public boolean verifierCongestion() {
		// Si le nombre de v�hicule pour avoir congestion est atteint
		// peut importe la direction des v�hicules
		if (vehicules.size() > NBR_VEHICULES_POUR_CONGESTION) {
			presenceCongestion = true;
		} else {
			presenceCongestion = false;
		}

		return presenceCongestion;
	}

	/**
	 * M�thode pour ajouter le v�hicule
	 * 
	 * @param v
	 *            v�hicule
	 */
	public void ajouterVehicule(Vehicule v) {
		vehicules.add(v);
	}

	/**
	 * M�thode pour obtenir les v�hicules
	 * 
	 * @return
	 */
	public Vector<Vehicule> getVehicules() {
		return this.vehicules;
	}

	/**
	 * M�thode pou obtenir la pr�sence d'accidents
	 * 
	 * @return
	 */
	public boolean getPresenceAccident() {
		return this.presenceAccident;
	}

	/**
	 * M�thode pour obtenir la pr�sence de congestion
	 * 
	 * @return
	 */
	public boolean getPresenceCongestion() {
		return this.presenceCongestion;
	}

	/**
	 * M�thode pour afficher l'art�re
	 */
	public String toString() {
		return this.identifiant;
	}

	/**
	 * M�thode pour obtenir l'identifiant
	 * 
	 * @return
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * M�thode pour v�rifier si l'art�re a le v�hicule utilisateur
	 * 
	 * @return
	 */
	public boolean hasVehiculeUtilisateur() {
		for (Vehicule v : this.vehicules) {
			if (v.isVoitureUtilisateur()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * M�thode pour obtenir la vitesse actuelle
	 * 
	 * @return
	 */
	public int getVitesseActuelle() {

		if (presenceCongestion) {
			return this.vitesseActuel / 4;
		} else if (presenceAccident) {
			return this.vitesseActuel / 2;
		} else {
			return this.vitesseActuel;
		}
	}
}
