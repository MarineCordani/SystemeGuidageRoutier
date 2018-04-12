package Modele;

import java.util.Iterator;
import java.util.Vector;

import Controleur.GenerateurTrajet;
import Controleur.MoteurTraitement;

public class ReseauRoutier {
	public static final int VITESSE_MAX = 50;
	public static final int DISTANCE_MAX_ARTHERE = 100;
	
	private Vector<Intersection> intersections = new Vector<Intersection>();
	private Vector<Arthere> artheres = new Vector<Arthere>();

	
	public ReseauRoutier(){
		//Creer les arthere et intersection
		//     A   B   C   D
		// 1   X   X   X   X
		// 2   X   X   X   X
		// 3   X   X   X   X
		// 4   X   X   X   X
		
		//Ajout des intersections
		intersections.add(new Intersection("A1", 100, 100));
		intersections.add(new Intersection("A2", 200, 100));
		intersections.add(new Intersection("A3", 300, 100));
		intersections.add(new Intersection("A4", 400, 100));
		intersections.add(new Intersection("B1", 100, 200));
		intersections.add(new Intersection("B2", 200, 200));
		intersections.add(new Intersection("B3", 300, 200));
		intersections.add(new Intersection("B4", 400, 200));
		intersections.add(new Intersection("C1", 100, 300));
		intersections.add(new Intersection("C2", 200, 300));
		intersections.add(new Intersection("C3", 300, 300));
		intersections.add(new Intersection("C4", 400, 300));
		intersections.add(new Intersection("D1", 100, 400));
		intersections.add(new Intersection("D2", 200, 400));
		intersections.add(new Intersection("D3", 300, 400));
		intersections.add(new Intersection("D4", 400, 400));

		//Ajout arthere horizontal
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("A1"),getIntersection("A2"))); //Ajouter une arthere qui lie A1 et A2
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("A2"),getIntersection("A3")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("A3"),getIntersection("A4")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("B1"),getIntersection("B2")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("B2"),getIntersection("B3")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("B3"),getIntersection("B4")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("C1"),getIntersection("C2")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("C2"),getIntersection("C3")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("C3"),getIntersection("C4")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("D1"),getIntersection("D2")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("D2"),getIntersection("D3")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("D3"),getIntersection("D4")));
		

		//Ajout arthere vertical
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("A1"),getIntersection("B1")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("B1"),getIntersection("C1")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("C1"),getIntersection("D1")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("A2"),getIntersection("B2")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("B2"),getIntersection("C2")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("C2"),getIntersection("D2")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("A3"),getIntersection("B3")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("B3"),getIntersection("C3")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("C3"),getIntersection("D3")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("A4"),getIntersection("B4")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("B4"),getIntersection("C4")));
		artheres.add(new Arthere(VITESSE_MAX, getIntersection("C4"),getIntersection("D4")));
	}

	
	public void creerVehiculeinitiaux(){
		//Ajouter voiture initial
		Intersection a = getIntersection("A1");
		Intersection b = getIntersection("D4");
		Trajet t1 = GenerateurTrajet.genererTrajet(a,b);
		Vehicule v1 = new Vehicule(t1, a);
		MoteurTraitement.getReseauRoutier().ajouterVehicule(v1);
		
		a = getIntersection("B1");
		b = getIntersection("C4");
		t1 = GenerateurTrajet.genererTrajet(a,b);
		v1 = new Vehicule(t1, a);
		MoteurTraitement.getReseauRoutier().ajouterVehicule(v1);
		
		a = getIntersection("D1");
		b = getIntersection("B4");
		t1 = GenerateurTrajet.genererTrajet(a,b);
		v1 = new Vehicule(t1, a);
		MoteurTraitement.getReseauRoutier().ajouterVehicule(v1);
			
	}
	
	public Intersection getIntersection(String n){
		for (Intersection s: intersections){
			if (s.toString().equals(n)){
				return s;
			}
		}
		return null; //Signifie qu'un mauvais nom a été demandé
	}
	
	public Vector<Arthere> getArtheres() {
		return this.artheres;
	}
	
	public void ajouterVehicule(Vehicule v){
		Arthere temporaire = v.getTrajet().retirerProchainArthere();
		temporaire.ajouterVehicule(v);
		Intersection i = v.getIntersectionInitial();
		v.setPositionX(i.getPositionX());
		v.setPositionY(i.getPositionY());
		v.setArthereEnCours(temporaire);
		v.changerSegment(temporaire);
	}

	public Vector<Intersection> getIntersections() {
		return this.intersections;
	}	
	
	public Intersection[] getIntersectionsCoins()
	{
		Intersection hautGauche = intersections.get(0);
		Intersection basDroite  = intersections.get(0);
		
		for (int i = 1; i < intersections.size();i++){
			Intersection s = intersections.get(i);
			if (s.getPositionX() <= hautGauche.getPositionX() && s.getPositionY() < hautGauche.getPositionY()){
				hautGauche = s;
			}
			
			if (s.getPositionX() >= basDroite.getPositionX() && s.getPositionY() >= basDroite.getPositionY()){
				basDroite = s;
			}
		}
		
		Intersection[] coins = new Intersection[] {hautGauche, basDroite};
		return coins;
	}
	
	public boolean avancerVehicule(int dureeCycle){
		Arthere temporaire;		
		
		for (Arthere a: artheres){
			Vector<Vehicule> vehicules = a.getVehicules();
			synchronized(vehicules)	{
			    Iterator vehiculeIterator = vehicules.iterator();
			    while (vehiculeIterator.hasNext()) {
			    	Vehicule v = (Vehicule)vehiculeIterator.next();
			    	if (v.avancer(dureeCycle, a.getVitesseActuelle())){
			    		//System.out.println("----------------------------");
						//Signifie que le vehicule est a la fin de son arthere
						//a.retirerVehicule(v);
			    		vehiculeIterator.remove();
			    		temporaire = v.getTrajet().retirerProchainArthere();
						if (temporaire == null){ //Indique que le vehicule est a la fin du trajet
							if(v.isVoitureUtilisateur()) {
								return false;
							}							
						}
						else {
							temporaire.ajouterVehicule(v);
							v.setArthereEnCours(temporaire);
							v.changerSegment(temporaire);
							
						}
			    		
					}
			    }
			}
		}
		
		return true;
	}
	
}
