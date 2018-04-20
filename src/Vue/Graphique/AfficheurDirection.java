package Vue.Graphique;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Controleur.Algorithm.VecteurMath;
import Modele.Artere;
import Modele.Intersection;

/**
 * Classe de la vue pour afficher les directions sur la carte
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class AfficheurDirection {

	private BufferedImage[] imageDirection;

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
	 * @param g
	 *            objet qui contient des méthodes pour dessiner sur le panneau
	 * @param x
	 *            début de la carte sur l'axe des x
	 * @param y
	 *            début de la carte sur l'axe des y
	 * @param origine
	 *            arthère d'origine
	 * @param destination
	 *            arthère de destination
	 * @param rapportModeleCarte
	 *            rapport entre le modèle et la carte
	 */
	public void dessiner(Graphics g, float x, float y, Artere origine, Artere destination, float rapportModeleCarte) {
		Graphics2D g2d = (Graphics2D) g;

		// aligner les intersections
		Intersection a;
		Intersection b;
		Intersection c;

		if (origine.getA() == destination.getA()) {
			a = origine.getB();
			b = origine.getA();
			c = destination.getB();
		} else if (origine.getA() == destination.getB()) {
			a = origine.getB();
			b = origine.getA();
			c = destination.getA();
		} else if (origine.getB() == destination.getB()) {
			a = origine.getA();
			b = origine.getB();
			c = destination.getA();
		} else {// if(origin.getB() == destination.getA()) {
			a = origine.getA();
			b = origine.getB();
			c = destination.getB();
		}

		int virage = 0;

		VecteurMath v1 = new VecteurMath((float) b.getPositionX() - (float) a.getPositionX(),
				(float) b.getPositionY() - (float) a.getPositionY());
		VecteurMath v2 = new VecteurMath((float) c.getPositionX() - (float) b.getPositionX(),
				(float) c.getPositionY() - (float) b.getPositionY());

		int angle = (int) (VecteurMath.getAngle(v1, v2) * 180.0f / (float) Math.PI);

		if (angle == 90) {
			virage = 0;
		} else if (angle == -90) {
			virage = 2;
		} else {
			virage = 1;
		}

		g2d.drawImage(imageDirection[virage], (int) x - (imageDirection[virage].getWidth() / 2),
				(int) y - (imageDirection[virage].getHeight() / 2), null);
	}
}
