package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JPanel;

import Controleur.MoteurTraitement;
import Modele.Artere;
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

	private AfficheurCarte afficheurCarte;
	private AfficheurVehicule afficheurVehicule;

	/**
	 * Le constructeur de la classe
	 */
	public Carte() {
		this.setBackground(COULEUR_CARTE);

		afficheurCarte = new AfficheurCarte();
		afficheurVehicule = new AfficheurVehicule();
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
		Vector<Artere> arteres = reseau.getArteres();

		g.setColor(COULEUR_CARTE);

		// Calculer le rapport entre le modèle et la Carte
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

		// Afficher la carte
		afficheurCarte.dessiner(g, x, y, (float) panelSize.width / 2,
				y + (ReseauRoutier.DISTANCE_MAX_ARTHERE / rapportModeleCarte) / 2.0f, intersections, arteres,
				rapportModeleCarte);

		// Afficher les véhicules
		for (Artere artere : arteres) {
			Intersection a = artere.getA();
			Intersection b = artere.getB();
			Vector<Vehicule> vehicules = artere.getVehicules();

			for (Vehicule vehicule : vehicules) {
				int sens = 0;
				Intersection p = vehicule.getProchaineIntersection();
				if (p != null) {
					if (vehicule.getPositionY() == p.getPositionY()) {
						if (vehicule.getPositionX() <= p.getPositionX()) {
							sens = 0;// est
						} else {
							sens = 2;// ouest
						}
					} else {
						if (vehicule.getPositionY() <= p.getPositionY()) {
							sens = 3;// sud
						} else {
							sens = 1;// nord
						}
					}

					afficheurVehicule.dessiner(g, x, y, vehicule.getPositionX(), vehicule.getPositionY(),
							vehicule.isVoitureUtilisateur(), sens, rapportModeleCarte);
				}
			}
		}
	}
}
