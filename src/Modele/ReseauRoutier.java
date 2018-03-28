package Modele;

import java.util.Vector;

public class ReseauRoutier {

	Vector<Intersection> intersections = new Vector<Intersection>();
	Vector<Arthere> artheres = new Vector<Arthere>();

	
	public ReseauRoutier(){
		//Creer les arthere et intersection
		//     A   B   C   D
		// 1   X   X   X   X
		// 2   X   X   X   X
		// 3   X   X   X   X
		// 4   X   X   X   X
		
		//Ajout des intersections
		intersections.add(new Intersection("A1", 10, 10));
		intersections.add(new Intersection("A2", 20, 10));
		intersections.add(new Intersection("A3", 30, 10));
		intersections.add(new Intersection("A4", 40, 10));
		intersections.add(new Intersection("B1", 10, 20));
		intersections.add(new Intersection("B2", 20, 20));
		intersections.add(new Intersection("B3", 30, 20));
		intersections.add(new Intersection("B4", 40, 20));
		intersections.add(new Intersection("C1", 10, 30));
		intersections.add(new Intersection("C2", 20, 30));
		intersections.add(new Intersection("C3", 30, 30));
		intersections.add(new Intersection("C4", 40, 30));
		intersections.add(new Intersection("D1", 10, 40));
		intersections.add(new Intersection("D2", 20, 40));
		intersections.add(new Intersection("D3", 30, 40));
		intersections.add(new Intersection("D4", 40, 40));

		//Ajout arthere horizontal
		artheres.add(new Arthere(10, getIntersection("A1"),getIntersection("A2"))); //Ajouter une arthere qui lie A1 et A2
		artheres.add(new Arthere(10, getIntersection("A2"),getIntersection("A3")));
		artheres.add(new Arthere(10, getIntersection("A3"),getIntersection("A4")));
		artheres.add(new Arthere(10, getIntersection("B1"),getIntersection("B2")));
		artheres.add(new Arthere(10, getIntersection("B2"),getIntersection("B3")));
		artheres.add(new Arthere(10, getIntersection("B3"),getIntersection("B4")));
		artheres.add(new Arthere(10, getIntersection("C1"),getIntersection("C2")));
		artheres.add(new Arthere(10, getIntersection("C2"),getIntersection("C3")));
		artheres.add(new Arthere(10, getIntersection("C3"),getIntersection("C4")));
		artheres.add(new Arthere(10, getIntersection("D1"),getIntersection("D2")));
		artheres.add(new Arthere(10, getIntersection("D2"),getIntersection("D3")));
		artheres.add(new Arthere(10, getIntersection("D3"),getIntersection("D4")));
		

		//Ajout arthere vertical
		artheres.add(new Arthere(10, getIntersection("A1"),getIntersection("B1")));
		artheres.add(new Arthere(10, getIntersection("B1"),getIntersection("C1")));
		artheres.add(new Arthere(10, getIntersection("C1"),getIntersection("D1")));
		artheres.add(new Arthere(10, getIntersection("A2"),getIntersection("B2")));
		artheres.add(new Arthere(10, getIntersection("B2"),getIntersection("C2")));
		artheres.add(new Arthere(10, getIntersection("C2"),getIntersection("D2")));
		artheres.add(new Arthere(10, getIntersection("A3"),getIntersection("B3")));
		artheres.add(new Arthere(10, getIntersection("B3"),getIntersection("C3")));
		artheres.add(new Arthere(10, getIntersection("C3"),getIntersection("D3")));
		artheres.add(new Arthere(10, getIntersection("A4"),getIntersection("B4")));
		artheres.add(new Arthere(10, getIntersection("B4"),getIntersection("C4")));
		artheres.add(new Arthere(10, getIntersection("C4"),getIntersection("D4")));
		
		//Ajouter voiture initial
		//TODO:
		
	}

	public Intersection getIntersection(String n){
		for (Intersection s: intersections){
			if (s.getIdentifiant().equals(n)){
				return s;
			}
		}
		return null; //Signifie qu'un mauvais nom a été demandé
	}
	
	public Vector<Arthere> getArtheres() {
		return this.artheres;
	}
	
	public void ajouterVehicule(Vehicule v, Intersection i){
		i.ajouterVehicule(v);
	}

	public Vector<Intersection> getIntersections() {
		return this.intersections;
	}	
}
