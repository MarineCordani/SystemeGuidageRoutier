package Vue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;

public class EcranGPS extends JFrame {
	private EntreeUtilisateur entreeUtilisateur;
	private JournalEvenement journalEvenement;
	private Carte carte;
	private JSplitPane horizontalSplitPane;
	private JSplitPane verticalSplitPane;
	
	
	public EcranGPS(String titre){
		super(titre);
		
		ImageIcon icon = new ImageIcon("res/icon.png");
		this.setIconImage(icon.getImage());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		horizontalSplitPane = new JSplitPane();
		horizontalSplitPane.setResizeWeight(0.7);
		horizontalSplitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(horizontalSplitPane);
		this.carte = new Carte();
		horizontalSplitPane.setLeftComponent(carte);
		carte.setBackground(Color.RED);
		carte.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		
		verticalSplitPane = new JSplitPane();
		verticalSplitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		verticalSplitPane.setResizeWeight(0.3);
		verticalSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		horizontalSplitPane.setRightComponent(verticalSplitPane);
		
		this.entreeUtilisateur = new EntreeUtilisateur();
		verticalSplitPane.setLeftComponent(entreeUtilisateur);
		entreeUtilisateur.setBackground(Color.PINK);
		FlowLayout fl_entreeUtilisateur = new FlowLayout(FlowLayout.LEFT, 5, 5);
		entreeUtilisateur.setLayout(fl_entreeUtilisateur);
		this.journalEvenement = new JournalEvenement();
		verticalSplitPane.setRightComponent(journalEvenement);
		journalEvenement.setBackground(Color.BLUE);
		journalEvenement.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
	}
}
