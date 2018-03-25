package Modele;

public class Arthere {

	private boolean presenceAccident;
	private int vitesseLimite; //Toujours etre divisible par 4
	private int vitesseActuel; //Vitesse est egal a vitesse limite, diviser par 2 si accident, diviser par 2 si congestion
	private int longueur; //Pareille pour tous
	
	
	private Intersection a;
	private Intersection b;
	private Vehicule[] vehicules; //Changer pour une structure elastique (Vector)
	
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
