package Modele;

public class Vehicule {

	Trajet trajet;
	boolean accidente; //Indique si le vehicule a ete impliqué dans un accident
	int positionX;
	int positionY;
	
	
	public Vehicule(Trajet t){
		trajet = t;
		accidente = false;
	}
	
	public Vehicule(Trajet t, Intersection i){
		trajet = t;
		accidente = false;
		definirPositionInitial(i);
	}	
	
	public void definirPositionInitial(Intersection i){
		this.positionX = i.positionX;
		this.positionY = i.positionY;
	}
	
}
