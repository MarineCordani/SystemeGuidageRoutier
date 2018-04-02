package Controlleur;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Modele.Intersection;
import Modele.ReseauRoutier;
import Modele.Vehicule;
import Vue.EcranGPS;

public class MoteurTraitement {

	static ReseauRoutier reseau = new ReseauRoutier();
	static EcranGPS ecran;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { 
	         public void run() { 
	        	 creerEtAfficherInterface(); 
	         } 
	      }); 
		/*Intersection a = reseau.getIntersection("A1");
		Intersection b = reseau.getIntersection("C4");

		Trajet trajet = GenerateurTrajet.genererTrajet(a, b);
		trajet.imprimerTrajet();*/
		
		
		//Créer boucle d'execution
		GenerateurVehicule gv = new GenerateurVehicule();
		Vehicule vehiculeTemporaire = null;
		Intersection intersectionTemporaire = null;
		
		GenerateurAccident ga = new GenerateurAccident();
		
		do{
			//Generer voiture
			vehiculeTemporaire = gv.genererVehicule();
			intersectionTemporaire = null;//TODO ERREUR, doit obtenir intersection de depart du vehiculeTemporaire
			//getReseauRoutier().ajouterVehicule(vehiculeTemporaire, intersectionTemporaire);
			
			//Generer accident
			ga.genererAccident();
			
			//Faire avancer vehicule
			//TODO: Faire deplacer les vehicules
			
			//Attente de quelques milisecondes pour que l'animation soit visible
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				
			}

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

}
