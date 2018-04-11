package Controleur.Algorithm;

public class VecteurMath {
	private float x;
	private float y;

	public VecteurMath(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the angle between two vectors
	 * @param a
	 * @param b
	 * @return
	 */
	static public float getAngle(VecteurMath a, VecteurMath b) {
		return (float) Math.atan2(cross(a, b), dot(a, b));
	}

	public float magnitude() {
		return (float)Math.sqrt(dot(this, this));
	}

	static public float dot(VecteurMath a, VecteurMath b) {
		return a.x * b.x + a.y * b.y;
	}

	static public float cross(VecteurMath a, VecteurMath b) {
		return a.x * b.y - a.y * b.x;
	}
}
