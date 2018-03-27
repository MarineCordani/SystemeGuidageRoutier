package Modele;

import java.util.Vector;

public class Intersection {

	int positionX; //Position X de l'intersection
	int positionY; //Position Y de l'intersection
	Vector<Arthere> connection = new Vector<Arthere>();
	String identifiant; //Identifiant
	Vector<Vehicule> vehicule = new Vector<Vehicule>(); //Vehicule sur l'arthere
	
	
	public void ajouterVehicule(Vehicule v){
		vehicule.add(v);
		
	}
	
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
	public String getIdentifiant() {
		return identifiant;
	}
	
	public void connecterArthere(Arthere a){
		connection.add(a);
	}
	
}
