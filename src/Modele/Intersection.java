package Modele;

import java.util.Vector;

/**
 * Classe du mod�le qui repr�sente un point de jointure entre plusieurs art�res
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class Intersection {

	private int positionX; // Position X de l'intersection
	private int positionY; // Position Y de l'intersection
	private Vector<Artere> connection = new Vector<Artere>();
	private String identifiant; // Identifiant
	private Vector<Vehicule> vehicules = new Vector<Vehicule>(); // Vehicule sur l'artere

	public Intersection(String i, int x, int y) {
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

	/**
	 * M�thode pour connecter art�res
	 * 
	 * @param a
	 */
	public void connecterArtere(Artere a) {
		connection.add(a);
	}

	/**
	 * M�thode pour obtenir les v�hicules
	 * 
	 * @return
	 */
	public Vector<Vehicule> getVehicules() {
		return this.vehicules;
	}

	/**
	 * Op�rateur d'�galit�
	 */
	public boolean equals(Object object) {
		Intersection a = (Intersection) object;
		return object instanceof Intersection && (this.positionX == a.positionX) && (this.positionY == a.positionY);
	}

	/**
	 * M�thode pour ajouter les v�hicules
	 * 
	 * @param v
	 */
	public void ajouterVehicule(Vehicule v) {
		vehicules.add(v);
	}

}
