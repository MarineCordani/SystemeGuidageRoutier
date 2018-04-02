package Vue.Graphique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	 * @param y x début de la carte sur l'axe des y
	 * @param origine arthère d'origine
	 * @param destination arthère de destination
	 * @param rapportModeleCarte rapport entre le modèle et la carte
	 */
	public void dessiner(Graphics g, float x, float y, Arthere origine, Arthere destination, float rapportModeleCarte) {
		Graphics2D g2d = (Graphics2D) g;
		
		//aligner les intersections
		Intersection a;
		Intersection b;
		Intersection c;
		
		if(origine.getA() == destination.getA()) {
			a = origine.getB();
			b = origine.getA();
			c = destination.getB();
		}
		else if(origine.getA() == destination.getB()) {
			a = origine.getB();
			b = origine.getA();
			c = destination.getA();
		}
		else if(origine.getB() == destination.getB()) {
			a = origine.getA();
			b = origine.getB();
			c = destination.getA();
		}
		else {//if(origin.getB() == destination.getA()) {
			a = origine.getA();
			b = origine.getB();
			c = destination.getB();
		}
		
		//la flèche de direction commence et se termine au milieu de l'arthère
		Intersection a1;
		Intersection c1;
		
		if(a.getPositionX() == b.getPositionX()) {
			a1 = new Intersection("",a.getPositionX(), b.getPositionY() - ((b.getPositionY() - a.getPositionY()) / 2));
		}
		else {
			a1 = new Intersection("",b.getPositionX() - ((b.getPositionX() - a.getPositionX()) / 2), a.getPositionY());
		}
		
		if(b.getPositionX() == c.getPositionX()) {
			c1 = new Intersection("", b.getPositionX(), c.getPositionY() - ((c.getPositionY() - b.getPositionY()) / 2));
		}
		else {
			c1 = new Intersection("", c.getPositionX() - ((c.getPositionX() - b.getPositionX()) / 2), b.getPositionY());
		}

		
		/*g.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT + 2));*/

		float x1 = 0.0f;
		float y1 = 0.0f;
		float x2 = 0.0f;
		float y2 = 0.0f;
/*
		x1 = x + ((float) a1.getPositionX() / rapportModeleCarte);
		y1 = y + ((float) a1.getPositionY() / rapportModeleCarte);
		x2 = x + ((float) b.getPositionX() / rapportModeleCarte);
		y2 = y + ((float) b.getPositionY() / rapportModeleCarte);
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		
		x1 = x + ((float) b.getPositionX() / rapportModeleCarte);
		y1 = y + ((float) b.getPositionY() / rapportModeleCarte);
		x2 = x + ((float) c1.getPositionX() / rapportModeleCarte);
		y2 = y + ((float) c1.getPositionY() / rapportModeleCarte);
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);*/
		
		int[] polyX = { 0, 0, 0, 0};
		int[] polyY = { 0, 0, 0, 0};
		   
		//g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT + 5));
		/*if(c1.getPositionX() == b.getPositionX()) {
			polyX[0] = (int)x2;
			polyY[0] = (int)y2;
			
			polyX[1] = (int)x2 + (2 * FLECHE_UNITE);
			polyY[1] = (int)y2 - (1 * FLECHE_UNITE);
			
			polyX[2] = (int)x2;
			polyY[2] = (int)y2 + (3 * FLECHE_UNITE);
			
			polyX[3] = (int)x2 - (2 * FLECHE_UNITE);
			polyY[3] = (int)y2 - (1 * FLECHE_UNITE);
			
		}
		else if(c1.getPositionY() == b.getPositionY()) {
			polyX[0] = (int)x2;
			polyY[0] = (int)y2;
			
			polyX[1] = (int)x2 - (1 * FLECHE_UNITE);
			polyY[1] = (int)y2 + (2 * FLECHE_UNITE);
			
			polyX[2] = (int)x2 + (3 * FLECHE_UNITE);
			polyY[2] = (int)y2;
			
			polyX[3] = (int)x2 - (1 * FLECHE_UNITE);
			polyY[3] = (int)y2 - (2 * FLECHE_UNITE);
		}		
	
		g.fillPolygon(polyX, polyY, polyX.length);*/
		
		g.setColor(new Color(255, 0, 255));
		
		g2d.setStroke(new BasicStroke(EPAISSEUR_TRAIT));

		x1 = x + ((float) a1.getPositionX() / rapportModeleCarte);
		y1 = y + ((float) a1.getPositionY() / rapportModeleCarte);
		x2 = x + ((float) b.getPositionX() / rapportModeleCarte);
		y2 = y + ((float) b.getPositionY() / rapportModeleCarte);
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		
		x1 = x + ((float) b.getPositionX() / rapportModeleCarte);
		y1 = y + ((float) b.getPositionY() / rapportModeleCarte);
		x2 = x + ((float) c1.getPositionX() / rapportModeleCarte);
		y2 = y + ((float) c1.getPositionY() / rapportModeleCarte);
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		
		
		if(c1.getPositionX() == b.getPositionX()) {
			polyX[0] = (int)x2;
			polyY[0] = (int)y2;
			
			polyX[1] = (int)x2 + (2 * FLECHE_UNITE);
			polyY[1] = (int)y2 - (1 * FLECHE_UNITE);
			
			polyX[2] = (int)x2;
			polyY[2] = (int)y2 + (3 * FLECHE_UNITE);
			
			polyX[3] = (int)x2 - (2 * FLECHE_UNITE);
			polyY[3] = (int)y2 - (1 * FLECHE_UNITE);
			
		}
		else if(c1.getPositionY() == b.getPositionY()) {
			polyX[0] = (int)x2;
			polyY[0] = (int)y2;
			
			polyX[1] = (int)x2 - (1 * FLECHE_UNITE);
			polyY[1] = (int)y2 + (2 * FLECHE_UNITE);
			
			polyX[2] = (int)x2 + (3 * FLECHE_UNITE);
			polyY[2] = (int)y2;
			
			polyX[3] = (int)x2 - (1 * FLECHE_UNITE);
			polyY[3] = (int)y2 - (2 * FLECHE_UNITE);
		}
			
		g2d.setStroke(new BasicStroke(0));
		g.fillPolygon(polyX, polyY, polyX.length);
		   
	}
}
