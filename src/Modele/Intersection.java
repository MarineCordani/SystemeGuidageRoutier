package Modele;

import java.util.Vector;

public class Intersection {

	private int positionX; //Position X de l'intersection
	private int positionY; //Position Y de l'intersection
	private Vector<Artere> connection = new Vector<Artere>();
	private String identifiant; //Identifiant
	private Vector<Vehicule> vehicules = new Vector<Vehicule>(); //Vehicule sur l'artere
	

	public Intersection(String i, int x, int y){
		this.identifiant = i;
		this.positionX = x;
		this.positionY = y;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}

	/**
	 * Cette méthode permet de connaître l'identifiant de l'intersection
	 * 
	 * @return l'identifiant de l'intersection
	 */
	public String toString() {
		return this.identifiant;
	}
	
	public void connecterArthere(Artere a){
		connection.add(a);
	}
	
	public Vector<Vehicule> getVehicules() {
		return this.vehicules;
	}
	
	public boolean equals(Object object) {
		Intersection a = (Intersection)object;
	    return object instanceof Intersection && (this.positionX == a.positionX) && (this.positionY == a.positionY);
	}
	
	public void ajouterVehicule(Vehicule v){
		vehicules.add(v);
	}
	
	public void retirerVehicule(Vehicule v){
		vehicules.remove(v);
	}
	
}
