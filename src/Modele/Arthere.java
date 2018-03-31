package Modele;

import java.util.Vector;

public class Arthere {

	private boolean presenceAccident;
	private int vitesseLimite; //Toujours etre divisible par 4
	private int vitesseActuel; //Vitesse est egal a vitesse limite, diviser par 2 si accident, diviser par 2 si congestion
	private int longueur; //Pareille pour tous
	private String identifiant;
		
	private Intersection intersectionA;
	private Intersection intersectionB;
	private Vector<Vehicule> vehicules = new Vector<Vehicule>(); //Changer pour une structure elastique (Vector)
	
	public Arthere(int v, Intersection a, Intersection b){
		this.presenceAccident = false;
		this.vitesseLimite = v;
		this.vitesseActuel = this.vitesseLimite;
		this.longueur = 100;
		this.identifiant = a.getIdentifiant() + "-" + b.getIdentifiant();
		
		this.intersectionA = a;
		a.connecterArthere(this);
		this.intersectionB = b;
		b.connecterArthere(this);
	}
	
	/**
	 * Cette méthode permet de connaître l'intersection a de l'arthère
	 * 
	 * @return l'intersection a de l'arthère
	 */
	public Intersection getA() {
		return this.intersectionA;
	}
	
	/**
	 * Cette méthode permet de connaître l'intersection b de l'arthère
	 * 
	 * @return l'intersection b de l'arthère
	 */
	public Intersection getB() {
		return this.intersectionB;
	}
	
	
	/**
	 * Cette méthode permet de connaître la durée pour parcourir l'arthère
	 * 
	 * @return la durée pour parcourir l'arthère
	 */
	public int dureeTraverse(){
		return longueur/vitesseActuel;
	}
	
	public boolean creerAccident(){
		//Si pas au moins deux vehicule, annuler l'accident
		if (vehicules.size() < 2){
			return false;
		}
		
		presenceAccident = true;
		//Accidenter deux voitures au hasard
		int aleatoire = (int)((Math.random() * (vehicules.size()-1))+1); //Choisis un vehicule autre que le premier
		vehicules.get(aleatoire).SetAccidente(true);
		vehicules.get(aleatoire -1).SetAccidente(true);
		return true;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}
}
