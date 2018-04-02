package Vue.Graphique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import Modele.Arthere;
import Modele.Intersection;

public class AfficheurDirection {

	private static final int EPAISSEUR_TRAIT = 8;
	public static final int FLECHE_UNITE = 10;

	public AfficheurDirection() {

	}

	public void dessiner(Graphics g, float x, float y, Arthere origin, Arthere destination, float rapportModeleCarte) {
		Graphics2D g2d = (Graphics2D) g;
		
		Intersection a;
		Intersection b;
		Intersection c;
		
		if(origin.getA() == destination.getA()) {
			a = origin.getB();
			b = origin.getA();
			c = destination.getB();
		}
		else if(origin.getA() == destination.getB()) {
			a = origin.getB();
			b = origin.getA();
			c = destination.getA();
		}
		else if(origin.getB() == destination.getB()) {
			a = origin.getA();
			b = origin.getB();
			c = destination.getA();
		}
		else {//if(origin.getB() == destination.getA()) {
			a = origin.getA();
			b = origin.getB();
			c = destination.getB();
		}
		
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
