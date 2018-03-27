package Modele;

import java.util.Vector;

public class Arthere {

	boolean presenceAccident;
	int vitesseLimite; //Toujours etre divisible par 4
	int vitesseActuel; //Vitesse est egal a vitesse limite, diviser par 2 si accident, diviser par 2 si congestion
	int longueur; //Pareille pour tous
	String identifiant;
	
	
	Intersection intersectionA;
	Intersection intersectionB;
	Vector<Vehicule> vehicules = new Vector<Vehicule>(); //Changer pour une structure elastique (Vector)
	
	public Arthere(int v, Intersection a, Intersection b){
		this.presenceAccident = false;
		this.vitesseLimite = v;
		this.vitesseActuel = this.vitesseLimite;
		this.longueur = 100;
		this.identifiant = a.identifiant + "-" + b.identifiant;
		
		this.intersectionA = a;
		a.connecterArthere(this);
		this.intersectionB = b;
		b.connecterArthere(this);
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
	public int dureeTraverse(){
		return longueur/vitesseActuel;
	}
	
	public void creerAccident(){
		presenceAccident = true;
		
		//Accidenter deux voitures au hasard
		int aleatoire = (int)((Math.random() * vehicules.size()));
		vehicules.get(aleatoire).accidente = true;
		vehicules.get(aleatoire -1).accidente = true;
	}
}
