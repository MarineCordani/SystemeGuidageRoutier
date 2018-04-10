package Vue.Graphique;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Classe de la vue pour afficher le véhicule sur la carte
 * 
 * @author Marine Cordani, Mouna Slimen, Vestine Mukeshimana
 *
 */
public class AfficheurVehicule {
	private BufferedImage imageHorizontalVert;
	private BufferedImage imageVerticalVert;
	private BufferedImage imageHorizontalRouge;
	private BufferedImage imageVerticalRouge;
	private BufferedImage[] imageUtilisateur;


	/**
	 * Le constructeur de la classe
	 */
	public AfficheurVehicule() {
		try {
			imageHorizontalVert = ImageIO.read(new File("res/vehicule_horizontal_vert.png"));
			imageVerticalVert = ImageIO.read(new File("res/vehicule_vertical_vert.png"));
			imageHorizontalRouge = ImageIO.read(new File("res/vehicule_horizontal_rouge.png"));
			imageVerticalRouge = ImageIO.read(new File("res/vehicule_vertical_rouge.png"));
			
			imageUtilisateur = new BufferedImage[4];
			imageUtilisateur[0] = ImageIO.read(new File("res/utilisateur_est.png"));
			imageUtilisateur[1] = ImageIO.read(new File("res/utilisateur_nord.png"));
			imageUtilisateur[2] = ImageIO.read(new File("res/utilisateur_ouest.png"));
			imageUtilisateur[3] = ImageIO.read(new File("res/utilisateur_sud.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Méthode pour dessiner le véhicule sur la carte
	 * 
	 * @param g objet qui contient des méthodes pour dessiner sur le panneau
	 * @param x début de la carte sur l'axe des x
	 * @param y début de la carte sur l'axe des y
	 * @param posVehiculeX position du véhicule sur l'axe des x
	 * @param posVehiculeY position du véhicule sur l'axe des y
	 * @param utilisateur drapeau pour savoir si le véhicule est celui de l'utilisateur
	 * @param sens drapeau pour savoir si le véhicule est verticale où horizontal sur la carte
	 * @param rapportModeleCarte rapport entre le modèle et la carte
	 */
	public void dessiner(Graphics g, float x, float y, int posVehiculeX, int posVehiculeY, boolean utilisateur, int sens, float rapportModeleCarte) {
		Graphics2D g2d = (Graphics2D) g;

		float x1 = x + ((float) posVehiculeX / rapportModeleCarte);
		float y1 = y + ((float) posVehiculeY / rapportModeleCarte);
		if(utilisateur) {
			g2d.drawImage(imageUtilisateur[sens], (int) x1 - (imageUtilisateur[sens].getWidth() / 2), (int) y1 - (imageUtilisateur[sens].getHeight() / 2),
					null);
		}
		else {
			if (sens == 0 || sens == 2) {
				g2d.drawImage(imageHorizontalVert, (int) x1 - (imageHorizontalVert.getWidth() / 2), (int) y1 - (imageHorizontalVert.getHeight() / 2),
						null);
			} else if (sens ==  1 || sens == 3) {
				g2d.drawImage(imageVerticalVert, (int) x1 - (imageVerticalVert.getWidth() / 2), (int) y1 - (imageVerticalVert.getHeight() / 2),
						null);
			}
		}
	}
}
