package Modele;

import Controleur.MoteurTraitement;

public class Vehicule {
	private final float RAPPORT_VITESSES = 50.0f / 0.0002f;	private boolean presenceAccident;
	
	private Trajet trajet;
	private boolean accidente; //Indique si le vehicule a ete impliqué dans un accident
	private int positionX;
	private int positionY;
	private Intersection intersectionInitial;
	private Artere artereEnCours;
	
	private Intersection derniereIntersection;
	private Intersection prochaineIntersection;
	private double pourcentageCompletion;
	private boolean surIntersection;

	private boolean voitureUtilisateur;
	
	public Vehicule(Trajet t, Intersection i){
		this.trajet = t;
		this.accidente = false;
		this.definirPositionInitial(i);
		pourcentageCompletion = 0;
		this.intersectionInitial = i;
		surIntersection = false;
		voitureUtilisateur = false;
	}	
	
	public Vehicule(Trajet t, Intersection i, boolean vi){
		this.trajet = t;
		this.accidente = false;
		this.definirPositionInitial(i);
		pourcentageCompletion = 0;
		this.intersectionInitial = i;
		surIntersection = false;
		voitureUtilisateur = vi;
	}	
	
	
	public void changerSegment(Artere artere){
		pourcentageCompletion = 0;
		
		if (artere != null && MoteurTraitement.getEcran() != null && this.voitureUtilisateur == true){
			MoteurTraitement.getEcran().ajouterTexteAuJournal("La voiture prend l'artère " + artere.toString());
		}
		
		//Intersection de l'artere en cours
		Intersection a = artereEnCours.getA();
		Intersection b = artereEnCours.getB();
		
		//Doit trouver si a ou b est l'intersection la plus proche
		if (getDistance(a.getPositionX(),a.getPositionY(),this.positionX,this.positionY) < getDistance(b.getPositionX(),b.getPositionY(),this.positionX,this.positionY)){
			derniereIntersection = a;
			prochaineIntersection = b;
		}
		else
		{
			derniereIntersection = b;
			prochaineIntersection = a;
		}

	}
	
	
	private int getDistance(int x1, int y1, int x2, int y2){
		int x = x1 - x2;
		int y = y1 - y2;
		x = Math.abs(x);
		y = Math.abs(y);
		return (int)(Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 0.5));
	}
	
	//Si arrivé au bout de l'artere, retourne true
	public boolean avancer(int dureeCycle, int vitesse){
		
		//System.out.println(derniereIntersection.toString() + " - " + prochaineIntersection.toString());
		//this.getTrajet().imprimerTrajet();
		if (surIntersection){
			surIntersection = false;
			return true;
		}
		
		if (accidente){ //Ne pas avancé si accidenté
			return false;
		}
		//TODO: Doit trouver une facon de donner information a derniere et prochaine intersection
		//System.out.println("test");
		pourcentageCompletion = pourcentageCompletion + ((double)dureeCycle * (double)vitesse / RAPPORT_VITESSES); //0.01;
		//System.out.println(pourcentageCompletion);
		int deltaX = prochaineIntersection.getPositionX() - derniereIntersection.getPositionX();
		int deltaY = prochaineIntersection.getPositionY() - derniereIntersection.getPositionY();
		
		this.positionX = (int)(derniereIntersection.getPositionX() + (deltaX * pourcentageCompletion));
		this.positionY = (int)(derniereIntersection.getPositionY() + (deltaY * pourcentageCompletion));
		
		if (pourcentageCompletion > 0.95){
			pourcentageCompletion = 0;
			//System.out.println("arrive au bout"); //TODO: Erreur ici. N'est jamais executer
			return true;
		}
		return false;
	}
	

	public void definirPositionInitial(Intersection i){
		this.positionX = i.getPositionX();
		this.positionY = i.getPositionY();
	}

	public void SetAccidente(boolean etat) {
		if (this.voitureUtilisateur){
			return; //Ne jamais accidenter voiture de l'utilisateur
		}
		this.accidente = etat;		
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}

	public Trajet getTrajet() {
		return trajet;
	}

	public Intersection getIntersectionInitial() {
		return intersectionInitial;
	}
	
	public void setArthereEnCours(Artere a){
		artereEnCours = a;
	}

	public boolean isSurIntersection() {
		return surIntersection;
	}

	public void setSurIntersection(boolean surIntersection) {
		this.surIntersection = surIntersection;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public boolean isVoitureUtilisateur() {
		return voitureUtilisateur;
	}
	
	public Intersection getProchaineIntersection(){
		return prochaineIntersection;
	}
	
	public void setTrajet(Trajet t){
		this.trajet = t;
	}
}
