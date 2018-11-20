package shoot;

import hitbox.Hitbox;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bullet extends JLabel implements Serializable {
	/*
	 * Vår Bullet klass är de skott vi avfyrar, när hitbox träffar en fiende sedan tar de skada
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
	private String pictureFileName;
	
	public Bullet (int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		length = 15;
		height = 45;
		hitbox = new Hitbox(xpos,ypos,length,height); //Skapar hitbox
		pictureFileName = "Bullet.jpg";
		addPicture(pictureFileName);
		setVisible(true);
		setBounds(xpos,ypos,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Placerar i rutan
	}
	
	public void ticktock() {
		ypos = ypos - 2;
		hitbox.setypos(ypos);
		move();
	} //ändrar bullets koordinater
	

	public int getYpos() {
		return ypos;
	}

	public void move() {
		setBounds(xpos,ypos,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth());
	}//Flyttar bullet
	
	public Hitbox getHitbox() {
		return hitbox;
	}

	protected void addPicture(String filename) {  //Läser in bilden för bullet som ska ritas ut
		try {
			ImageIcon icon;
			icon = new ImageIcon(ImageIO.read(new File(filename)));
			setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
