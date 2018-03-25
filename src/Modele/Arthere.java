package Modele;

public class Arthere {

	boolean presenceAccident;
	int vitesseLimite; //Toujours etre divisible par 4
	int vitesseActuel; //Vitesse est egal a vitesse limite, diviser par 2 si accident, diviser par 2 si congestion
	int longueur; //Pareille pour tous
	
	
	Intersection a;
	Intersection b;
	Vehicule[] v; //Changer pour une structure elastique (Vector)
	
	public Arthere(int v){
		this.presenceAccident = false;
		this.vitesseLimite = v;
		this.vitesseActuel = this.vitesseLimite;
		this.longueur = 100;
	}
	
	public int dureeTraverse(){
		return longueur/vitesseActuel;
	}
	
	
}
