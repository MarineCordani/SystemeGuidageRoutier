package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JPanel;

import Controleur.MoteurTraitement;
import Modele.Arthere;
import Modele.Intersection;
import Modele.ReseauRoutier;
import Modele.Vehicule;
import Vue.Graphique.AfficheurCarte;
import Vue.Graphique.AfficheurDirection;
import Vue.Graphique.AfficheurVehicule;

/**
 * Classe de la vue qui est un panneau où mettre la carte du réseau routier
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class Carte extends JPanel {

	/**
	 * La clé de hachage SHA qui identifie de manière unique la Classe
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * La couleur grise de la carte
	 */
	private final Color COULEUR_CARTE = new Color(227, 224, 217);
	
	/**
	 * La taille de la marge entre la fenêtre principale et la carte
	 */
	private final int TAILLE_MARGE = 0;

	/**
	 * Le constructeur de la classe
	 */
	public Carte() {
		this.setBackground(COULEUR_CARTE);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		ReseauRoutier reseau = MoteurTraitement.getReseauRoutier();

		Dimension panelSize = this.getSize();
		Intersection[] coins = reseau.getIntersectionsCoins();

		Rectangle carteSize = new Rectangle(coins[0].getPositionX() - ReseauRoutier.DISTANCE_MAX_ARTHERE,
				coins[0].getPositionY() - ReseauRoutier.DISTANCE_MAX_ARTHERE,
				coins[1].getPositionX() + ReseauRoutier.DISTANCE_MAX_ARTHERE,
				coins[1].getPositionY() + ReseauRoutier.DISTANCE_MAX_ARTHERE);

		Vector<Intersection> intersections = reseau.getIntersections();
		Vector<Arthere> artheres = reseau.getArtheres();

		g.setColor(COULEUR_CARTE);

		//Calculer le rapport entre le modèle et la Carte
		float rapportModeleCarte = 0.0f;
		if (carteSize.width > carteSize.height) {
			rapportModeleCarte = (float) carteSize.height / (panelSize.height - (2 * TAILLE_MARGE));
		} else {
			rapportModeleCarte = (float) carteSize.height / (panelSize.height - (2 * TAILLE_MARGE));
		}

		float width = carteSize.width / rapportModeleCarte;
		float height = carteSize.height / rapportModeleCarte;
		float x = (panelSize.width - width) / 2;
		float y = (panelSize.height - height) / 2;

		//Afficher la carte
		AfficheurCarte afficheurCarte = new AfficheurCarte();
		afficheurCarte.dessiner(g, x, y, intersections, artheres, rapportModeleCarte);

		//Afficher les véhicules
		AfficheurVehicule afficheurVehicule = new AfficheurVehicule();

		for (Arthere arthere : artheres) {
			Intersection a = arthere.getA();
			Intersection b = arthere.getB();
			Vector<Vehicule> vehicules = arthere.getVehicules();

			for (Vehicule vehicule : vehicules) {
				afficheurVehicule.dessiner(g, x, y, vehicule.getPositionX(), vehicule.getPositionY(), false, a.getPositionY() == b.getPositionY(), rapportModeleCarte);
			}
		}
		/*
		for (Intersection intersection : intersections) {
			Vector<Vehicule> vehicules = intersection.getVehicules();

			for (Vehicule vehicule : vehicules) {
				afficheurVehicule.dessiner(g, x, y, vehicule.getPositionX(), vehicule.getPositionY(), true, rapportModeleCarte);
			}
		}*/
		/*
		AfficheurDirection afficheurDirection = new AfficheurDirection();
		afficheurDirection.dessiner(g, x, y, artheres.get(12), artheres.get(0), rapportModeleCarte);
		afficheurDirection.dessiner(g, x, y, artheres.get(2), artheres.get(21), rapportModeleCarte);
		afficheurDirection.dessiner(g, x, y, artheres.get(9), artheres.get(14), rapportModeleCarte);
		afficheurDirection.dessiner(g, x, y, artheres.get(23), artheres.get(11), rapportModeleCarte);
		
		afficheurDirection.dessiner(g, x, y, artheres.get(0), artheres.get(12), rapportModeleCarte);
		afficheurDirection.dessiner(g, x, y, artheres.get(21), artheres.get(2), rapportModeleCarte);
		afficheurDirection.dessiner(g, x, y, artheres.get(14), artheres.get(9), rapportModeleCarte);
		afficheurDirection.dessiner(g, x, y, artheres.get(11), artheres.get(23), rapportModeleCarte);*/
	}
}
