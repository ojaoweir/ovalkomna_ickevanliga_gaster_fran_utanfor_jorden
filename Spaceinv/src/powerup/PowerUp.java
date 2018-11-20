package powerup;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import hitbox.Hitbox;

public abstract class PowerUp extends JLabel implements Serializable {
	/*
	 * vår abstrakta powerupklass den har position, hitbox, effekt, vilken kommer till användning i hitboxcontrol .catchpowerup 
	 * där effekten aktiveras om du åker över en powerup
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int effect;
	protected int xpos;
	protected int ypos;
	protected int length;
	protected int height;
	private Hitbox hitbox;
	protected String pictureFileName;
	
	public PowerUp (int xpos, int ypos) {
		
		this.xpos = xpos;
		this.ypos = ypos;
		length = 40;
		height = 20;
		hitbox = new Hitbox(xpos,ypos,length,height);
		
		setBounds(xpos,ypos,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Sätter ut i fönster
	}
	
	public int activateEffect() { //Returnar vilken powerup det är, samt vilken effekt
		return effect;
	}
	
	public Hitbox getHitbox() { //Returnar hitbox
		return hitbox;
	}
	
	public int getYpos() {
		return ypos;
	}
	
	public int getXpos() {
		return xpos;
	}
	
	protected void addPicture(String filename) { //Läser in bild som ska skrivas ut
		try {
			ImageIcon icon;
			icon = new ImageIcon(ImageIO.read(new File(filename)));
			setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}
