package Vue.Graphique;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Auto {
	private BufferedImage imageHoriz;
	private BufferedImage imageVert;
	
	public Auto() {
		 try {
			 imageHoriz = ImageIO.read(new File("res/vehicule_horiz.png"));
			 imageVert = ImageIO.read(new File("res/vehicule_vert.png"));
         } catch (IOException ex) {
             ex.printStackTrace();
         }
	}
	
	public void dessiner(Graphics g, int x, int y, boolean horizontal) {
		 Graphics2D g2d = (Graphics2D) g.create();
         
         if (horizontal && imageHoriz != null) {
             g2d.drawImage(imageHoriz, x - (imageHoriz.getWidth() / 2), y - (imageHoriz.getHeight() / 2), null);
         }
         else if (imageVert != null) {
             g2d.drawImage(imageVert, x - (imageVert.getWidth() / 2), y - (imageVert.getHeight() / 2), null);
         }
         g2d.dispose();
	}
}
