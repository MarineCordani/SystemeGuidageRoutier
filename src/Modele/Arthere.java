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
	
	/**
	 * Cette m�thode permet de conna�tre l'intersection a de l'arth�re
	 * 
	 * @return l'intersection a de l'arth�re
	 */
	public Intersection getA() {
		return this.a;
	}
	
	/**
	 * Cette m�thode permet de conna�tre l'intersection b de l'arth�re
	 * 
	 * @return l'intersection b de l'arth�re
	 */
	public Intersection getB() {
		return this.b;
	}
	
	
	/**
	 * Cette m�thode permet de conna�tre la dur�e pour parcourir l'arth�re
	 * 
	 * @return la dur�e pour parcourir l'arth�re
	 */
	public int dureeTraverse(){
		return longueur/vitesseActuel;
	}
	
	
}
