package Controleur;

import Modele.Intersection;
import Modele.Trajet;
import Modele.Vehicule;

public class GenerateurVehicule {

	private double probabilite;

	public GenerateurVehicule() {
		this.probabilite = 0.025;
	}

	public GenerateurVehicule(double p) {
		this.probabilite = p;
	}

	public Vehicule genererVehicule() {
		if (Math.random() < probabilite) {
			int aleatoireIntersectionA = (int) (Math.random() * 12);
			String intersectionDepart = "";
			switch (aleatoireIntersectionA) {
			case 0:
				intersectionDepart = "A1";
				break;
			case 1:
				intersectionDepart = "A2";
				break;
			case 2:
				intersectionDepart = "A3";
				break;
			case 3:
				intersectionDepart = "A4";
				break;
			case 4:
				intersectionDepart = "B1";
				break;
			case 5:
				intersectionDepart = "C1";
				break;
			case 6:
				intersectionDepart = "B4";
				break;
			case 7:
				intersectionDepart = "C4";
				break;
			case 8:
				intersectionDepart = "D1";
				break;
			case 9:
				intersectionDepart = "D2";
				break;
			case 10:
				intersectionDepart = "D3";
				break;
			case 11:
				intersectionDepart = "D4";
				break;
			}

			int aleatoireIntersectionB;

			do {
				aleatoireIntersectionB = (int) (Math.random() * 12);
			} while (aleatoireIntersectionA == aleatoireIntersectionB);

			String intersectionArrive = "";
			switch (aleatoireIntersectionB) {
			case 0:
				intersectionArrive = "A1";
				break;
			case 1:
				intersectionArrive = "A2";
				break;
			case 2:
				intersectionArrive = "A3";
				break;
			case 3:
				intersectionArrive = "A4";
				break;
			case 4:
				intersectionArrive = "B1";
				break;
			case 5:
				intersectionArrive = "C1";
				break;
			case 6:
				intersectionArrive = "B4";
				break;
			case 7:
				intersectionArrive = "C4";
				break;
			case 8:
				intersectionArrive = "D1";
				break;
			case 9:
				intersectionArrive = "D2";
				break;
			case 10:
				intersectionArrive = "D3";
				break;
			case 11:
				intersectionArrive = "D4";
				break;
			}

			Intersection a = MoteurTraitement.getReseauRoutier().getIntersection(intersectionDepart);
			Intersection b = MoteurTraitement.getReseauRoutier().getIntersection(intersectionArrive);

			// Créer trajet
			Trajet t = GenerateurTrajet.genererTrajet(a, b);
			// Retourner vehicule
			return new Vehicule(t, a);
		} else {
			return null;
		}
	}

}
