package Controleur;

import javax.swing.JFrame;

import Modele.Intersection;
import Modele.ReseauRoutier;
import Modele.Trajet;
import Modele.Vehicule;
import Vue.EcranGPS;

/**
 * Classe du contr�leur qui constitue le point centrale d'ex�cution du programme
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class MoteurTraitement {

	static private ReseauRoutier reseau;
	static private EcranGPS ecran;
	static private Vehicule utilisateur;
	static private boolean lancer;

	static private final int DUREE_CYCLE = 50;
	static private String depart = "A1";
	static private String arrive = "B2";

	public static void main(String[] args) {
		reseau = new ReseauRoutier();
		reseau.creerVehiculeinitiaux();

		creerEtAfficherInterface();

		// Cr�er boucle d'execution
		GenerateurVehicule gv = new GenerateurVehicule();
		Vehicule vehiculeTemporaire = null;
		//Intersection intersectionTemporaire = null;

		VerificateurCongestion vc = new VerificateurCongestion();

		GenerateurAccident ga = new GenerateurAccident();

		do { // Boucle principale d'ex�cution
			reseau = new ReseauRoutier();
			do { // Boucle infini d'attente de d�marrage de la simulation
				System.out.print("");
			} while (!lancer);

			// Ajouter voiture utilisateur
			Intersection a = reseau.getIntersection(depart);
			Intersection b = reseau.getIntersection(arrive);
			Trajet t1 = GenerateurTrajet.genererTrajet(a, b);
			utilisateur = new Vehicule(t1, a, true);
			reseau.ajouterVehicule(utilisateur);
			Trajet nouveauTrajet = null;

			do {
				// Generer voiture
				vehiculeTemporaire = gv.genererVehicule();
				if (vehiculeTemporaire != null) {
					reseau.ajouterVehicule(vehiculeTemporaire);
				}

				// V�rifier congestions
				vc.verifierCongestion();

				// Generer accident
				if (ga.genererAccident()) {
					// Si accident est generer, verifier si le trjaet doit etre recalcul�
					ecran.ajouterTexteAuJournal("Accident survenue. Recalcul de l'itin�raire");
					nouveauTrajet = GenerateurTrajet.genererTrajet(utilisateur.getProchaineIntersection(), b);
					utilisateur.setTrajet(nouveauTrajet);
				}

				// Faire avancer vehicule
				// TODO: Faire deplacer les vehicules
				lancer = reseau.avancerVehicule(DUREE_CYCLE);

				if (!lancer) {
					ecran.arreter();
				}

				// Attente de quelques milisecondes pour que l'animation soit visible
				try {
					Thread.sleep(DUREE_CYCLE);
				} catch (InterruptedException e) {

				}
				// Rafraichir carte
				ecran.RafraichirInterface();
			} while (lancer);
		} while (true);
	}

	/**
	 * M�thode nous permet d'obtenir le r�seau routier
	 * 
	 * @return le r�seau routier
	 */
	public static ReseauRoutier getReseauRoutier() {
		return reseau;
	}

	/**
	 * M�thode pour obtenir la fen�tre principale
	 * 
	 * @return
	 */
	public static EcranGPS getEcran() {
		return ecran;
	}

	/**
	 * M�thode pour cr�er et afficher la fen�tre principale
	 */
	private static void creerEtAfficherInterface() {
		ecran = new EcranGPS("Syst�me de guidage dans un r�seau routier");
		ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ecran.setLocation(0, 0);
		ecran.setSize(1024, 600);
		ecran.setVisible(true);
	}

	/**
	 * M�thode pour d�finir le trajet
	 * @param d d�part
	 * @param a arriv�e
	 */
	public static void definirTrajet(String d, String a) {
		depart = d;
		arrive = a;
	}

	/**
	 *  Modifier l'�tat de la simulation
	 *  
	 * @param lancer drapeau de l'�tat de la simulation
	 */
	public static void setLancer(boolean lancer) {
		MoteurTraitement.lancer = lancer;
	}

	/**
	 * M�thode pour obtenir le v�hicule utilisateur
	 * @return
	 */
	public static Vehicule getVehiculeUtilisateur() {
		return utilisateur;
	}

}
