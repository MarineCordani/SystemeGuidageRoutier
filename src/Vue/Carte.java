package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JPanel;

import Controlleur.MoteurTraitement;
import Modele.Arthere;
import Modele.Intersection;
import Modele.ReseauRoutier;
import Modele.Vehicule;
import Vue.Graphique.AfficheurCarte;
import Vue.Graphique.AfficheurDirection;
import Vue.Graphique.AfficheurVehicule;

public class Carte extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Color COULEUR_CARTE = new Color(227, 224, 217);
	private final int TAILLE_MARGE = 0;

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

		AfficheurCarte afficheurCarte = new AfficheurCarte();
		afficheurCarte.dessiner(g, x, y, intersections, artheres, rapportModeleCarte);

		AfficheurVehicule afficheurVehicule = new AfficheurVehicule();

		for (Arthere arthere : artheres) {
			Intersection a = arthere.getA();
			Intersection b = arthere.getB();
			Vector<Vehicule> vehicules = arthere.getVehicules();

			for (Vehicule vehicule : vehicules) {
				afficheurVehicule.dessiner(g, x, y, vehicule.getPositionX(), vehicule.getPositionY(), a.getPositionY() == b.getPositionY(), rapportModeleCarte);
			}
		}
		
		for (Intersection intersection : intersections) {
			Vector<Vehicule> vehicules = intersection.getVehicules();

			for (Vehicule vehicule : vehicules) {
				afficheurVehicule.dessiner(g, x, y, vehicule.getPositionX(), vehicule.getPositionY(), true, rapportModeleCarte);
			}
		}
		
		//AfficheurDirection afficheurDirection = new AfficheurDirection();
		//afficheurDirection.dessiner(g, x, y, artheres.get(12), artheres.get(0), rapportModeleCarte);
	}
}
