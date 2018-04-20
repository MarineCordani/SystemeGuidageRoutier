package Controleur.Algorithm;

/**
 * Classe du contrôleur pour calculer l’angle entre deux points de position
 * 
 * @author ja72@stackoverflow.com
 *         https://stackoverflow.com/questions/21483999/using-atan2-to-find-angle-between-two-vectors
 *
 */
public class VecteurMath {
	private float x;
	private float y;

	/**
	 * Constructeur
	 * 
	 * @param x
	 * @param y
	 */
	public VecteurMath(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the angle between two vectors
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static public float getAngle(VecteurMath a, VecteurMath b) {
		return (float) Math.atan2(cross(a, b), dot(a, b));
	}

	/**
	 * Calculer la distance entre deux points
	 * 
	 * @return
	 */
	public float magnitude() {
		return (float) Math.sqrt(dot(this, this));
	}

	/**
	 * Le produit scalaire des vecteurs
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static public float dot(VecteurMath a, VecteurMath b) {
		return a.x * b.x + a.y * b.y;
	}

	/**
	 * Le produit vectorielle
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static public float cross(VecteurMath a, VecteurMath b) {
		return a.x * b.y - a.y * b.x;
	}
}
