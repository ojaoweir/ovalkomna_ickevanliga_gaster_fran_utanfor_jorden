package player;

import hitbox.Hitbox;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Serializable {
	/*
	 * Vår spelare, har position, möjlighet till powerupp m.m.
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int xpos;
	private int ypos;
	private int length;
	private int height;
	private Hitbox hitbox;
	private boolean doubleshot;
	private String pictureFileName;
	
	public Player(int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		doubleshot = false;
		length = 50;
		height = 50;
		pictureFileName = "Spaceship.jpg"; 
		hitbox = new Hitbox(xpos,ypos,length,height); //Sätter hitbox
		addPicture(pictureFileName); //Laddar in bild
		setVisible(true);
		setBounds(xpos,ypos,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Palcerar ut i fönster
	}
	
	
	public boolean doubleShotActivated(){  //Check för om powerup doubleshot är aktiv
		return doubleshot;
	}
	
	public void setdoubleshot(Boolean change) { //Ändrar doublesshots aktivstatus
		doubleshot = change;
	}
	
	public int getXpos() {
		return xpos;
	}
	
	public int getYpos() {
		return ypos;
	}
	
	public void setXpos(int x) { //Ändrar koordinator och flyttar bild
		xpos = x;
		hitbox.setxpos(xpos);
		move();
	}
	
	public void setYpos(int y) {
		ypos = y;
	}	
	
	public void move() {
		setBounds(xpos,ypos,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth());
	} //Flyttar bilden
	
	public Hitbox getHitbox() {
		return hitbox;
	}
	
	protected void addPicture(String filename) {
		try {
			ImageIcon icon;
			icon = new ImageIcon(ImageIO.read(new File(filename)));
			setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
