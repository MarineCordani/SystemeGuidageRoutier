package Modele;

public class Vehicule {

	private Trajet trajet;
	private boolean accidente; //Indique si le vehicule a ete impliqué dans un accident
	private int positionX;
	private int positionY;
	
	
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
		this.positionX = i.getPositionX();
		this.positionY = i.getPositionY();
	}

	public void SetAccidente(boolean etat) {
		this.accidente = etat;		
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}
}
