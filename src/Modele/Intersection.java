package Modele;

import java.util.Vector;

public class Intersection {

	private int positionX; //Position X de l'intersection
	private int positionY; //Position Y de l'intersection
	private Vector<Arthere> connection = new Vector<Arthere>();
	private String identifiant; //Identifiant
	private Vector<Vehicule> vehicule = new Vector<Vehicule>(); //Vehicule sur l'arthere
	
	
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
	 * Cette m�thode permet de conna�tre l'identifiant de l'intersection
	 * 
	 * @return l'identifiant de l'intersection
	 */
	public String toString() {
		return this.identifiant;
	}
	
	public void connecterArthere(Arthere a){
		connection.add(a);
	}
	
}
