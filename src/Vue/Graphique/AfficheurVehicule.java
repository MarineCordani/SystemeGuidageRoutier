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
	private BufferedImage[] imageVehiculeVert;
	private BufferedImage[] imageVehiculeBleu;
	private BufferedImage[] imageVehiculeUtilisateur;

	private int[] decalageX;
	private int[] decalageY;


	/**
	 * Le constructeur de la classe
	 */
	public AfficheurVehicule() {
		try {
			imageVehiculeVert = new BufferedImage[4];
			imageVehiculeVert[0] = ImageIO.read(new File("res/vehicule_vert_est.png"));
			imageVehiculeVert[1] = ImageIO.read(new File("res/vehicule_vert_nord.png"));
			imageVehiculeVert[2] = ImageIO.read(new File("res/vehicule_vert_ouest.png"));
			imageVehiculeVert[3] = ImageIO.read(new File("res/vehicule_vert_sud.png"));
			
			imageVehiculeBleu = new BufferedImage[4];
			imageVehiculeBleu[0] = ImageIO.read(new File("res/vehicule_bleu_est.png"));
			imageVehiculeBleu[1] = ImageIO.read(new File("res/vehicule_bleu_nord.png"));
			imageVehiculeBleu[2] = ImageIO.read(new File("res/vehicule_bleu_ouest.png"));
			imageVehiculeBleu[3] = ImageIO.read(new File("res/vehicule_bleu_sud.png"));			
			
			imageVehiculeUtilisateur = new BufferedImage[4];
			imageVehiculeUtilisateur[0] = ImageIO.read(new File("res/vehicule_utilisateur_est.png"));
			imageVehiculeUtilisateur[1] = ImageIO.read(new File("res/vehicule_utilisateur_nord.png"));
			imageVehiculeUtilisateur[2] = ImageIO.read(new File("res/vehicule_utilisateur_ouest.png"));
			imageVehiculeUtilisateur[3] = ImageIO.read(new File("res/vehicule_utilisateur_sud.png"));		
			
			decalageX = new int[4];
			decalageX[0] = -imageVehiculeBleu[0].getWidth();
			decalageX[1] = -(imageVehiculeBleu[1].getWidth() / 2);
			decalageX[2] = 0;
			decalageX[3] = -(imageVehiculeBleu[3].getWidth() / 2);
			
			decalageY = new int[4];
			decalageY[0] = -(imageVehiculeBleu[0].getHeight() / 2);
			decalageY[1] = 0;
			decalageY[2] = -(imageVehiculeBleu[2].getHeight() / 2);
			decalageY[3] = -imageVehiculeBleu[3].getHeight();
			
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
			g2d.drawImage(imageVehiculeUtilisateur[sens], (int) x1 - (imageVehiculeUtilisateur[sens].getWidth() / 2), (int) y1 - (imageVehiculeUtilisateur[sens].getHeight() / 2),
					null);
			
			g2d.drawImage(imageVehiculeBleu[sens], (int) x1 + decalageX[sens], (int) y1 + decalageY[sens],
					null);
		}
		else {
			g2d.drawImage(imageVehiculeVert[sens], (int) x1 - (imageVehiculeVert[sens].getWidth() / 2), (int) y1 - (imageVehiculeVert[sens].getHeight() / 2),
					null);
		}
	}
}
