package Modele;

import java.util.Vector;

public class Arthere {

	private boolean presenceAccident;
	private boolean presenceCongestion;
	private boolean appartenanceTrajetPrincipal;
	private int vitesseLimite; //Toujours etre divisible par 4
	private int vitesseActuel; //Vitesse est egal a vitesse limite, diviser par 2 si accident, diviser par 2 si congestion
	private int longueur; //Pareille pour tous
	private String identifiant;
		
	private Intersection intersectionA;
	private Intersection intersectionB;
	private Vector<Vehicule> vehicules = new Vector<Vehicule>(); //Changer pour une structure elastique (Vector)
	
	public Arthere(int v, Intersection a, Intersection b){
		this.presenceAccident = false;
		this.presenceCongestion = false;
		this.appartenanceTrajetPrincipal = false;
		this.vitesseLimite = v;
		this.vitesseActuel = this.vitesseLimite;
		this.longueur = 100;
		this.identifiant = a.toString() + "-" + b.toString();
		
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
	
	public void ajouterVehicule(Vehicule v){
		vehicules.add(v);
	}
	
	public void retirerVehicule(Vehicule v){
		vehicules.remove(v);
	}
	
	public Vector<Vehicule> getVehicules() {
		return this.vehicules;
	}
	
	public boolean getPresenceAccident() {
		return this.presenceAccident;
	}
	
	public boolean getPresenceCongestion() {
		return this.presenceCongestion;
	}
	
	public boolean getAppartenanceTrajetPrincipal() {
		return this.appartenanceTrajetPrincipal;
	}
	
	public void setAppartenanceTrajetPrincipal(boolean valeur) {
		this.appartenanceTrajetPrincipal = valeur;
	}

	public String toString() {
		return this.identifiant;
	}

	public String getIdentifiant() {
		return identifiant;
	}
}
