package Vue.Graphique;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Classe de la vue pour afficher les véhicules sur la carte
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class AfficheurVehicule {
	private BufferedImage imageHoriz;
	private BufferedImage imageVert;

	/**
	 * Le constructeur de la classe
	 */
	public AfficheurVehicule() {
		try {
			imageHoriz = ImageIO.read(new File("res/vehicule_horiz.png"));
			imageVert = ImageIO.read(new File("res/vehicule_vert.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void dessiner(Graphics g, float x, float y, int posVehiculeX, int posVehiculeY, boolean horizontal,
			float rapportModeleCarte) {
		Graphics2D g2d = (Graphics2D) g;

		float x1 = x + ((float) posVehiculeX / rapportModeleCarte);
		float y1 = y + ((float) posVehiculeY / rapportModeleCarte);

		if (horizontal && imageHoriz != null) {
			g2d.drawImage(imageHoriz, (int) x1 - (imageHoriz.getWidth() / 2), (int) y1 - (imageHoriz.getHeight() / 2),
					null);
		} else if (imageVert != null) {
			g2d.drawImage(imageVert, (int) x1 - (imageVert.getWidth() / 2), (int) y1 - (imageVert.getHeight() / 2),
					null);
		}
	}
}
