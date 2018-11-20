package gui;

import javax.swing.JLabel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Title extends JLabel {
	
	
	/*
	 * Hanterar vårt spels titel som kan ses i våra menyer 
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Title() {
		addPicture("Title.jpg"); // hämtar bild
		setVisible(true);
		setBounds(0,0,1800, 200);// placerar ut
	}
	
	protected void addPicture(String filename) { //Hämtar bild
		try {
			ImageIcon icon;
			icon = new ImageIcon(ImageIO.read(new File(filename)));
			setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
