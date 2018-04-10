package Vue.Graphique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import Modele.Arthere;
import Modele.Intersection;

/**
 * Classe de la vue pour afficher les directions sur la carte
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class AfficheurDirection {

	private BufferedImage[] imageDirection;
	
	private static final int EPAISSEUR_TRAIT = 8;
	public static final int FLECHE_UNITE = 10;

	/**
	 * Le constructeur de la classe
	 */
	public AfficheurDirection() {
		try {			
			imageDirection = new BufferedImage[3];
			imageDirection[0] = ImageIO.read(new File("res/direction_droite.png"));
			imageDirection[1] = ImageIO.read(new File("res/direction_milieu.png"));
			imageDirection[2] = ImageIO.read(new File("res/direction_gauche.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
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
		
		int virage = 0;
		
		if((a.getPositionX() == b.getPositionX() && a.getPositionX() == c.getPositionX()) || 
		   (a.getPositionY() == b.getPositionY() && a.getPositionY() == c.getPositionY())) {			
			virage = 1;
		}
		else {
			virage = 0;
		}
				
				
		float x1 = x + ((float) 100 / rapportModeleCarte);
		float y1 = y + ((float) 100 / rapportModeleCarte);
		g2d.drawImage(imageDirection[virage], (int) x1 - (imageDirection[virage].getWidth() / 2), (int) y1 - (imageDirection[virage].getHeight() / 2),
				null);
	}
}
