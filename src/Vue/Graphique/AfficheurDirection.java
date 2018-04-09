package Vue.Graphique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Vector;

import Modele.Arthere;
import Modele.Intersection;

/**
 * Classe de la vue pour afficher les directions sur la carte
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class AfficheurDirection {

	private static final int EPAISSEUR_TRAIT = 8;
	public static final int FLECHE_UNITE = 10;

	/**
	 * Méthode pour dessiner les directions sur la carte
	 * 
	 * @param g objet qui contient des méthodes pour dessiner sur le panneau
	 * @param x début de la carte sur l'axe des x
	 * @param y début de la carte sur l'axe des y
	 * @param origine arthère d'origine
	 * @param destination arthère de destination
	 * @param rapportModeleCarte rapport entre le modèle et la carte
	 */
	public void dessiner(Graphics g, float x, float y, Arthere origine, Arthere destination, float rapportModeleCarte) {
		Graphics2D g2d = (Graphics2D) g;
		
		//aligner les intersections
		Intersection a;
		Intersection a1;
		Intersection b;
		Intersection c;
		Intersection c1;
		
		if(origine.getA() == destination.getA()) {
			a1 = origine.getB();
			b = origine.getA();
			c1 = destination.getB();
		}
		else if(origine.getA() == destination.getB()) {
			a1 = origine.getB();
			b = origine.getA();
			c1 = destination.getA();
		}
		else if(origine.getB() == destination.getB()) {
			a1 = origine.getA();
			b = origine.getB();
			c1 = destination.getA();
		}
		else {//if(origin.getB() == destination.getA()) {
			a1 = origine.getA();
			b = origine.getB();
			c1 = destination.getB();
		}
		
		//la flèche de direction commence et se termine au milieu de l'arthère
		if(a1.getPositionX() == b.getPositionX()) {
			a = new Intersection("",a1.getPositionX(), b.getPositionY() - ((b.getPositionY() - a1.getPositionY()) / 2));
		}
		else {
			a = new Intersection("",b.getPositionX() - ((b.getPositionX() - a1.getPositionX()) / 2), a1.getPositionY());
		}
		
		if(b.getPositionX() == c1.getPositionX()) {
			c = new Intersection("", b.getPositionX(), c1.getPositionY() - ((c1.getPositionY() - b.getPositionY()) / 2));
		}
		else {
			c = new Intersection("", c1.getPositionX() - ((c1.getPositionX() - b.getPositionX()) / 2), b.getPositionY());
		}
				
		//dessiner les lignes un peu large premièrement en noir
		this.dessinerLignes(g2d, x, y, a, b, c, true, rapportModeleCarte);
		
		//dessiner les lignes plus petites en magenta
		this.dessinerLignes(g2d, x, y, a, b, c, false, rapportModeleCarte);
		
		//dessiner flèche
		this.dessinerFleche(g2d, x, y, a, b, c, rapportModeleCarte);
	}
	
	/**
	 * Méthode pour dessiner les lignes de la flèche de direction
	 * 
	 * @param g2d objet qui contient des méthodes pour dessiner sur le panneau
	 * @param x début de la carte sur l'axe des x
	 * @param y début de la carte sur l'axe des y
	 * @param a première intersection
	 * @param b deuxième intersection
	 * @param c troisième intersection
	 * @param trait drapeau pour voir si on dessine le trait ou l'intérieur
	 * @param rapportModeleCarte rapport entre le modèle et la carte
	 */
	public void dessinerLignes(Graphics2D g2d, float x, float y, Intersection a, Intersection b, Intersection c, boolean trait, float rapportModeleCarte) {
		float x1 = 0.0f;
		float y1 = 0.0f;
		float x2 = 0.0f;
		float y2 = 0.0f;
		
		if(trait) {
			g2d.setPaint(Color.BLACK);
			g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT + 2));			
		}
		else {
			g2d.setPaint(new Color(0, 255, 0));		
			g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT));			
		}
		
		x1 = x + ((float) a.getPositionX() / rapportModeleCarte);
		y1 = y + ((float) a.getPositionY() / rapportModeleCarte);
		x2 = x + ((float) b.getPositionX() / rapportModeleCarte);
		y2 = y + ((float) b.getPositionY() / rapportModeleCarte);
		g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		
		x1 = x + ((float) b.getPositionX() / rapportModeleCarte);
		y1 = y + ((float) b.getPositionY() / rapportModeleCarte);
		x2 = x + ((float) c.getPositionX() / rapportModeleCarte);
		y2 = y + ((float) c.getPositionY() / rapportModeleCarte);
		g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}

	/**
	 * Méthode pour dessiner la flèche de direction
	 * 
	 * @param g2d objet qui contient des méthodes pour dessiner sur le panneau
	 * @param x début de la carte sur l'axe des x
	 * @param y début de la carte sur l'axe des y
	 * @param origine arthère d'origine
	 * @param a première intersection
	 * @param b deuxième intersection
	 * @param c troisième intersection
	 * @param trait drapeau pour voir si on dessine le trait ou l'intérieur
	 * @param rapportModeleCarte rapport entre le modèle et la carte
	 */
	public void dessinerFleche(Graphics2D g2d, float x, float y, Intersection a, Intersection b, Intersection c, float rapportModeleCarte) {
		float x1 = 0.0f;
		float y1 = 0.0f;
		
		x1 = x + ((float) c.getPositionX() / rapportModeleCarte);
		y1 = y + ((float) c.getPositionY() / rapportModeleCarte);
		
		int[] polyX = { 0, 0, 0, 0};
		int[] polyY = { 0, 0, 0, 0};
		
		if(c.getPositionX() == b.getPositionX()) {		
			polyX[0] = (int)x1;
			polyY[0] = (int)y1;
			
			if(c.getPositionY() > b.getPositionY()) {					
				polyX[1] = (int)x1 + (2 * FLECHE_UNITE);
				polyY[1] = (int)y1 - (1 * FLECHE_UNITE);
				
				polyX[2] = (int)x1;
				polyY[2] = (int)y1 + (3 * FLECHE_UNITE);
				
				polyX[3] = (int)x1 - (2 * FLECHE_UNITE);
				polyY[3] = (int)y1 - (1 * FLECHE_UNITE);
			}
			else {				
				polyX[1] = (int)x1 + (2 * FLECHE_UNITE);
				polyY[1] = (int)y1 + (1 * FLECHE_UNITE);
				
				polyX[2] = (int)x1;
				polyY[2] = (int)y1 - (3 * FLECHE_UNITE);
				
				polyX[3] = (int)x1 - (2 * FLECHE_UNITE);
				polyY[3] = (int)y1 + (1 * FLECHE_UNITE);
			}
			
		}
		else if(c.getPositionY() == b.getPositionY()) {
			polyX[0] = (int)x1;
			polyY[0] = (int)y1;
			
			if(c.getPositionX() > b.getPositionX()) {		
				polyX[1] = (int)x1 - (1 * FLECHE_UNITE);
				polyY[1] = (int)y1 + (2 * FLECHE_UNITE);
				
				polyX[2] = (int)x1 + (3 * FLECHE_UNITE);
				polyY[2] = (int)y1;
				
				polyX[3] = (int)x1 - (1 * FLECHE_UNITE);
				polyY[3] = (int)y1 - (2 * FLECHE_UNITE);
			}
			else {
				polyX[1] = (int)x1 + (1 * FLECHE_UNITE);
				polyY[1] = (int)y1 + (2 * FLECHE_UNITE);
				
				polyX[2] = (int)x1 - (3 * FLECHE_UNITE);
				polyY[2] = (int)y1;
				
				polyX[3] = (int)x1 + (1 * FLECHE_UNITE);
				polyY[3] = (int)y1 - (2 * FLECHE_UNITE);
			}
		}
				
		g2d.setStroke(new BasicStroke(1));
		
		Polygon poly = new Polygon(polyX, polyY, polyX.length);
		g2d.setPaint(new Color(0, 255, 0));
		g2d.fill(poly);
				
		g2d.setPaint(Color.BLACK);
		g2d.draw(poly);
	}
}
