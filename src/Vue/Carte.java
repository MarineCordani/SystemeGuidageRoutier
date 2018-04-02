package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JPanel;

import Controlleur.MoteurTraitement;
import Modele.Arthere;
import Modele.Intersection;
import Modele.ReseauRoutier;

public class Carte extends JPanel {
	
	private final Color COULEUR_CARTE = new Color(227, 224,217);
	private final int TAILLE_MARGE = 0;
	private final int EPAISSEUR_TRAIT = 5;
	
	
	
	public Carte(){
		this.setBackground(COULEUR_CARTE);
	}	
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ReseauRoutier reseau = MoteurTraitement.getReseauRoutier();
        
        Dimension panelSize = this.getSize();
        Intersection[] coins = reseau.getIntersectionsCoins();
        Rectangle carteSize = new Rectangle(coins[0].getPositionX() - ReseauRoutier.DISTANCE_MAX_ARTHERE, 
        		coins[0].getPositionY() - ReseauRoutier.DISTANCE_MAX_ARTHERE, 
        		coins[1].getPositionX() + ReseauRoutier.DISTANCE_MAX_ARTHERE,
        		coins[1].getPositionY() + ReseauRoutier.DISTANCE_MAX_ARTHERE);
                
        g.setColor(COULEUR_CARTE);
        
        double ratio = 0.0;
        if(carteSize.width > carteSize.height) {
        	ratio = (double)carteSize.height / (panelSize.height - (2 * TAILLE_MARGE));
        }
        else {
        	ratio = (double)carteSize.height / (panelSize.height - (2 * TAILLE_MARGE));
        }
        
        double width = carteSize.width /  ratio;
        double height = carteSize.height / ratio;
        double x = (panelSize.width - width) / 2;
        double y = (panelSize.height - height) / 2;
        
        
        g.fillRect((int)x, (int)y, (int)width, (int)height);      

        Vector<Arthere> artheres = reseau.getArtheres();
        
        g.setColor(new Color(191, 188,183));
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(EPAISSEUR_TRAIT + 2));
        
        double x1 = 0.0;
        double y1 = 0.0;
        double x2 = 0.0;
        double y2 = 0.0;
        
        for (Arthere arthere: artheres){
        	Intersection a = arthere.getA();
        	Intersection b = arthere.getB();
        	
        	x1 = x + ((double)a.getPositionX() / ratio);
        	y1 = y + ((double)a.getPositionY() / ratio);
        	x2 = x + ((double)b.getPositionX() / ratio);
        	y2 = y + ((double)b.getPositionY() / ratio);
        	g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
        }
        
        g.setColor(Color.WHITE);
        g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(EPAISSEUR_TRAIT));
                
        for (Arthere arthere: artheres){
        	Intersection a = arthere.getA();
        	Intersection b = arthere.getB();
        	
        	x1 = x + ((double)a.getPositionX() / ratio);
        	y1 = y + ((double)a.getPositionY() / ratio);
        	x2 = x + ((double)b.getPositionX() / ratio);
        	y2 = y + ((double)b.getPositionY() / ratio);
        	g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
        }
        
        g.setColor(Color.BLACK);    
        Vector<Intersection> intersections = reseau.getIntersections();
        for (Intersection intersection: intersections){
        	        	
        	x1 = x + ((double)intersection.getPositionX() / ratio) - 20.0;
        	y1 = y + ((double)intersection.getPositionY() / ratio) - 5.0;
        	g.drawString(intersection.toString(),(int)x1, (int)y1);
        }
    }
}
