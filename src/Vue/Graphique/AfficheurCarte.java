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
	 * M�thode pour dessiner la carte
	 * 
	 * @param g2d objet qui contient des m�thodes pour dessiner sur le panneau
	 * @param x d�but de la carte sur l'axe des x
	 * @param y d�but de la carte sur l'axe des y
	 * @param intersections vecteur des intersections
	 * @param artheres vecteur des arth�res
	 * @param rapportModeleCarte rapport entre le mod�le et la carte
	 */
	public void dessiner(Graphics g, float x, float y, Vector<Intersection> intersections, Vector<Arthere> artheres,
			float rapportModeleCarte) {
		Graphics2D g2d = (Graphics2D) g;

		//afficher les arth�res un peu large premi�rement en gris		
		this.dessinerArtheres(g2d, x, y, artheres, true, rapportModeleCarte);
		
		//afficher les arth�res plus petites en blanc
		this.dessinerArtheres(g2d, x, y, artheres, false, rapportModeleCarte);

		//afficher les identifiants d'intersections en noir
		this.dessinerIntersectionsIds(g2d, x, y, intersections, rapportModeleCarte);

	}
	/**
	 * M�thode pour dessiner les arth�res sur la carte
	 * 
	 * @param g2d objet qui contient des m�thodes pour dessiner sur le panneau
	 * @param x d�but de la carte sur l'axe des x
	 * @param y d�but de la carte sur l'axe des y 
	 * @param artheres vecteur des arth�res
	 * @param trait drapeau pour voir si on dessine le trait ou l'int�rieur
	 * @param rapportModeleCarte rapport entre le mod�le et la carte
	 */
	private void dessinerArtheres(Graphics2D g2d, float x, float y, Vector<Arthere> artheres, boolean trait, float rapportModeleCarte) {
		float x1 = 0.0f;
		float y1 = 0.0f;
		float x2 = 0.0f;
		float y2 = 0.0f;

		//afficher les arth�res un peu large premi�rement en gris
		for (Arthere arthere : artheres) {
			Intersection a = arthere.getA();
			Intersection b = arthere.getB();

			x1 = x + ((float) a.getPositionX() / rapportModeleCarte);
			y1 = y + ((float) a.getPositionY() / rapportModeleCarte);
			x2 = x + ((float) b.getPositionX() / rapportModeleCarte);
			y2 = y + ((float) b.getPositionY() / rapportModeleCarte);
			
			if(trait) {
				g2d.setPaint(new Color(191, 188, 183));//gris
				g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT + 2));
			}
			else {				
				if(arthere.getAppartenanceTrajetPrincipal()) {//magenta
					g2d.setPaint(new Color(255, 0, 255));
				}
				else if(arthere.getPresenceAccident()) {//rouge
					g2d.setPaint(new Color(237, 85, 100));
				}
				else if(arthere.getPresenceCongestion()) {//bleu
					g2d.setPaint(new Color(153, 213, 221));
				}
				else {//sinon blanc
					g2d.setPaint(Color.WHITE); 
				}
				g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT));				
			}
			g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		}
	}
	
	/**
	 * M�thodes pour dessiner les noms des intersections sur la carte
	 * @param g2d objet qui contient des m�thodes pour dessiner sur le panneau
	 * @param x d�but de la carte sur l'axe des x
	 * @param y d�but de la carte sur l'axe des y
	 * @param intersections vecteur des intersections
	 * @param rapportModeleCarte rapport entre le mod�le et la carte
	 */
	private void dessinerIntersectionsIds(Graphics2D g2d, float x, float y, Vector<Intersection> intersections, float rapportModeleCarte) {
		float x1 = 0.0f;
		float y1 = 0.0f;
		

		g2d.setPaint(Color.BLACK);		

		for (Intersection intersection : intersections) {

			x1 = x + ((float) intersection.getPositionX() / rapportModeleCarte) - 20.0f;
			y1 = y + ((float) intersection.getPositionY() / rapportModeleCarte) - 5.0f;
			g2d.drawString(intersection.toString(), (int) x1, (int) y1);
		}
	}
}
