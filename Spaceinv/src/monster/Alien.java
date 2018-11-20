package monster;

import hitbox.Hitbox;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Alien extends JLabel implements Serializable {
	/*
	 * abstrakta alien klassen som yoda och et ärver. kan ta skada, dör, förflyttas  etc
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int xpos;
	protected int ypos;
	protected int health;
	private Hitbox hitbox;
	protected int length;
	protected int height;
	private boolean dead;
	protected String pictureFileName;
	
	public Alien (int xpos, int ypos) {  //om vi byter färg då en ailen träffas
			this.xpos = xpos;									 //kan vi låta den dö om den går från tex cit till svart, då behövs inte liv
			this.ypos = ypos;
			dead = false;
			length = 200;
			height = 100;
			hitbox = new Hitbox(xpos, ypos, length, height);
			setVisible(true);
	}
	
	
	public void ticktock() { //Ändrar aliens koordinater
		ypos = ypos + 120;
		hitbox.setypos(ypos);
		move();
	}
	

	public int getYpos() {
		return ypos;
	}
	
	public void move() { //Flyttar alien
		setBounds(xpos,ypos,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth());
	}
	
	public Hitbox getHitbox() {
		return hitbox;
	}


	public void takeDamage() { //Skadar alien
		health = health - 1;
		if (health == 0) {
			dead = true;
		}
	}
	
	public boolean isDead() { //Returnar om den är död eller ej
		return dead;
	}


	public int getHealth() { //Returnar health
		return health;
	}
	

	protected void addPicture(String filename) { //Laddar in bild
		try {
			ImageIcon icon;
			icon = new ImageIcon(ImageIO.read(new File(filename)));
			setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
