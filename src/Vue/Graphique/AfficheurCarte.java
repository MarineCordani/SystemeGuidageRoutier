package Vue.Graphique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import Modele.Arthere;
import Modele.Intersection;

/**
 * Classe de la vue pour afficher la carte
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class AfficheurCarte {
	private final int EPAISSEUR_TRAIT = 5;

	/**
	 * Méthode pour dessiner la carte
	 * @param g objet qui contient des méthodes pour dessiner sur le panneau
	 * @param x début de la carte sur l'axe des x
	 * @param y debut de la carte sur l'axe des y
	 * @param intersections vecteur des intersections
	 * @param artheres vecteur des arthères
	 * @param rapportModeleCarte rapport entre le modèle et la carte
	 */
	public void dessiner(Graphics g, float x, float y, Vector<Intersection> intersections, Vector<Arthere> artheres,
			float rapportModeleCarte) {
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(new Color(191, 188, 183));
		g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT + 2));

		float x1 = 0.0f;
		float y1 = 0.0f;
		float x2 = 0.0f;
		float y2 = 0.0f;

		//afficher les arthères un peu large premièrement en gris
		for (Arthere arthere : artheres) {
			Intersection a = arthere.getA();
			Intersection b = arthere.getB();

			x1 = x + ((float) a.getPositionX() / rapportModeleCarte);
			y1 = y + ((float) a.getPositionY() / rapportModeleCarte);
			x2 = x + ((float) b.getPositionX() / rapportModeleCarte);
			y2 = y + ((float) b.getPositionY() / rapportModeleCarte);
			g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		}

		//afficher les arthères plus petites en blanc
		g.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT));

		for (Arthere arthere : artheres) {
			Intersection a = arthere.getA();
			Intersection b = arthere.getB();

			x1 = x + ((float) a.getPositionX() / rapportModeleCarte);
			y1 = y + ((float) a.getPositionY() / rapportModeleCarte);
			x2 = x + ((float) b.getPositionX() / rapportModeleCarte);
			y2 = y + ((float) b.getPositionY() / rapportModeleCarte);
			g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		}

		//afficher les identifiants d'intersections en noir
		g.setColor(Color.BLACK);

		for (Intersection intersection : intersections) {

			x1 = x + ((float) intersection.getPositionX() / rapportModeleCarte) - 20.0f;
			y1 = y + ((float) intersection.getPositionY() / rapportModeleCarte) - 5.0f;
			g.drawString(intersection.toString(), (int) x1, (int) y1);
		}
	}
}
