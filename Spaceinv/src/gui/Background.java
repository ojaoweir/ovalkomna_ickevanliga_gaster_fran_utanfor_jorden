package gui;

import javax.swing.JLabel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Background extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pictureFileName;
	
	public Background() { //Fungerar inte som vi vill jsut nu
		pictureFileName = "StartscreenandText.jpg";
		addPicture(pictureFileName); //Hämtar bild
		setVisible(true);
		setBounds(0,0,1800,900); //Placerar ut
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
