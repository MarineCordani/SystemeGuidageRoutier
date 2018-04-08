package Controleur;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Modele.Intersection;
import Modele.ReseauRoutier;
import Modele.Trajet;
import Modele.Vehicule;
import Vue.EcranGPS;
import Vue.EcranGPS;

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
		//SwingUtilities.invokeLater(new Runnable() { 
	         //public void run() { 
	        	 creerEtAfficherInterface(); 
	       //  } 
	      //}); 
		/*Intersection a = reseau.getIntersection("A1");
		Intersection b = reseau.getIntersection("C4");

		Trajet trajet = GenerateurTrajet.genererTrajet(a, b);
		trajet.imprimerTrajet();*/
		
	      
		//Créer boucle d'execution
		GenerateurVehicule gv = new GenerateurVehicule();
		Vehicule vehiculeTemporaire = null;
		Intersection intersectionTemporaire = null;
		
		GenerateurAccident ga = new GenerateurAccident();
		
		do { //Boucle principale d'exécution
			reseau = new ReseauRoutier();
		do { //Boucle infini d'attente de démarrage de la simulation
			System.out.print("");
		} while(!lancer);
		
		//Ajouter voiture utilisateur
		Intersection a = reseau.getIntersection(depart);
		Intersection b = reseau.getIntersection(arrive);
		Trajet t1 = GenerateurTrajet.genererTrajet(a,b);
		utilisateur = new Vehicule(t1, a, true);
		reseau.ajouterVehicule(utilisateur);
		Trajet nouveauTrajet = null;
		
		do{
			//Generer voiture
			vehiculeTemporaire = gv.genererVehicule();
			if (vehiculeTemporaire != null){
				reseau.ajouterVehicule(vehiculeTemporaire);
			}
			
			//Generer accident
			if (ga.genererAccident()){
				//Si accident est generer, verifier si le trjaet doit etre recalculé
				ecran.ajouterTexteAuJournal("Accident survenue. Recalcul de l'itinéraire");
				nouveauTrajet = GenerateurTrajet.genererTrajet(utilisateur.getProchaineIntersection(), b);
				utilisateur.setTrajet(nouveauTrajet);
			}
			
			//Faire avancer vehicule
			//TODO: Faire deplacer les vehicules
			reseau.avancerVehicule();
			
			//Attente de quelques milisecondes pour que l'animation soit visible
			try {
				Thread.sleep(DUREE_CYCLE);
			} catch (InterruptedException e) {
				
			}
			//Rafraichir carte
			ecran.RafraichirInterface();
		} while (lancer);
	} while (true);
	}

	/**
	 * Méthode nous permet d'obtenir le réseau routier
	 * 
	 * @return le réseau routier
	 */
	public static ReseauRoutier getReseauRoutier() {
		return reseau;
	}

	public static EcranGPS getEcran() {
		return ecran;
	}

	
	/**
	 * Méthode pour créer et afficher la fenêtre principale
	 */
	private static void creerEtAfficherInterface() {
		ecran = new EcranGPS("Système de guidage dans un réseau routier");
		ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ecran.setLocation(0, 0);
		ecran.setSize(1024, 600);
		ecran.setVisible(true);
	}

	
	public static void definirTrajet(String d, String a){
		depart = d;
		arrive = a;
	}
	
	public static void setLancer(boolean lancer) {
		MoteurTraitement.lancer = lancer;
	}

	public static Vehicule getVehiculeUtilisateur(){
		return utilisateur;
	}
	
}
