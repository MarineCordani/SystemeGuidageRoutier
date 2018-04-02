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
import Vue.Graphique.Auto;

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
        
        float ratio = 0.0f;
        if(carteSize.width > carteSize.height) {
        	ratio = (float)carteSize.height / (panelSize.height - (2 * TAILLE_MARGE));
        }
        else {
        	ratio = (float)carteSize.height / (panelSize.height - (2 * TAILLE_MARGE));
        }
        
        float width = carteSize.width /  ratio;
        float height = carteSize.height / ratio;
        float x = (panelSize.width - width) / 2;
        float y = (panelSize.height - height) / 2;
        
        
        g.fillRect((int)x, (int)y, (int)width, (int)height);      

        Vector<Arthere> artheres = reseau.getArtheres();
        
        g.setColor(new Color(191, 188,183));
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(EPAISSEUR_TRAIT + 2));
        
        float x1 = 0.0f;
        float y1 = 0.0f;
        float x2 = 0.0f;
        float y2 = 0.0f;
        
        for (Arthere arthere: artheres){
        	Intersection a = arthere.getA();
        	Intersection b = arthere.getB();
        	
        	x1 = x + ((float)a.getPositionX() / ratio);
        	y1 = y + ((float)a.getPositionY() / ratio);
        	x2 = x + ((float)b.getPositionX() / ratio);
        	y2 = y + ((float)b.getPositionY() / ratio);
        	g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
        }
        
        g.setColor(Color.WHITE);
        g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(EPAISSEUR_TRAIT));
                
        for (Arthere arthere: artheres){
        	Intersection a = arthere.getA();
        	Intersection b = arthere.getB();
        	
        	x1 = x + ((float)a.getPositionX() / ratio);
        	y1 = y + ((float)a.getPositionY() / ratio);
        	x2 = x + ((float)b.getPositionX() / ratio);
        	y2 = y + ((float)b.getPositionY() / ratio);
        	g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
        }
        
        g.setColor(Color.BLACK);    
        Vector<Intersection> intersections = reseau.getIntersections();
        for (Intersection intersection: intersections){
        	        	
        	x1 = x + ((float)intersection.getPositionX() / ratio) - 20.0f;
        	y1 = y + ((float)intersection.getPositionY() / ratio) - 5.0f;
        	g.drawString(intersection.toString(),(int)x1, (int)y1);
        }
        
        Auto au = new Auto();
        x1 = x + ((float)coins[0].getPositionX() / ratio);
    	y1 = y + ((float)coins[0].getPositionY() / ratio);
        au.dessiner(g, (int)x1, (int)y1, true);
        
        x1 = x + ((float)coins[1].getPositionX() / ratio);
    	y1 = y + ((float)coins[1].getPositionY() / ratio);
        au.dessiner(g, (int)x1, (int)y1, false);
    }
}
