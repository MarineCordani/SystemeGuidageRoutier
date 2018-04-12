package Modele;

import java.util.Vector;

public class Artere {

	private final int NBR_VEHICULES_POUR_CONGESTION = 3;
	private final int DISTANCE_MINIMAL_POUR_ACCIDENT = 22;
	
	private boolean presenceAccident;
	private boolean presenceCongestion;
	private int vitesseLimite; //Toujours etre divisible par 4
	private int vitesseActuel; //Vitesse est egal a vitesse limite, diviser par 2 si accident, diviser par 2 si congestion
	private int longueur; //Pareille pour tous
	private String identifiant;
		
	private Intersection intersectionA;
	private Intersection intersectionB;
	private Vector<Vehicule> vehicules = new Vector<Vehicule>(); //Changer pour une structure elastique (Vector)
	
	public Artere(int v, Intersection a, Intersection b){
		this.presenceAccident = false;
		this.presenceCongestion = false;
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
		int temp = longueur/this.getVitesseActuelle();
		/*if (presenceAccident){
			temp*=5;
		}*/
		return temp;
	}
	
	public boolean creerAccident(){
		//Si pas au moins deux vehicule, annuler l'accident
		//Si pas congestion aussi
		if (vehicules.size() < 2 || presenceCongestion){
			return false;
		}
		
		//Accidenter deux voitures au hasard
		//int aleatoire = (int)((Math.random() * (vehicules.size()-1))+1); //Choisis un vehicule autre que le premier
		//vehicules.get(aleatoire).SetAccidente(true);
		//vehicules.get(aleatoire -1).SetAccidente(true);
		
		Vehicule a = null;
		Vehicule b = null;
		
		for(int i = 0;i < vehicules.size();i++) {
			for(int j = i+1;j < vehicules.size();j++) {
				a = vehicules.get(i);
				b = vehicules.get(j);
				
				if(a.getPositionX() == b.getPositionX() 
				   && Math.abs(a.getPositionY() - b.getPositionY()) <= DISTANCE_MINIMAL_POUR_ACCIDENT) {
					a.SetAccidente(true);
					b.SetAccidente(true);
					presenceAccident = true;
					return true;
				}
				else if(a.getPositionY() == b.getPositionY() 
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
	
	public boolean verifierCongestion(){
		//Si le nombre de véhicule pour avoir congestion est atteint
		//peut importe la direction des véhicules
		if (vehicules.size() > NBR_VEHICULES_POUR_CONGESTION){
			presenceCongestion = true;
		}
		else {
			presenceCongestion = false;
		}
		
		return presenceCongestion;
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

	public String toString() {
		return this.identifiant;
	}

	public String getIdentifiant() {
		return identifiant;
	}
	
	public boolean hasVehiculeUtilisateur() {
		for(Vehicule v: this.vehicules) {
			if(v.isVoitureUtilisateur()) {
				return true;
			}
		}

		return false;
	}
	
	public int getVitesseActuelle() {
		
		if(presenceCongestion) {
			return this.vitesseActuel / 4;
		}
		else if(presenceAccident) {
			return this.vitesseActuel / 2;
		}
		else {
			return this.vitesseActuel;
		}
	}
}
