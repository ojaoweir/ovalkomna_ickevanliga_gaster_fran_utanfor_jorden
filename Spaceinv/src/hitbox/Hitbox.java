package hitbox;

import java.io.Serializable;

public class Hitbox implements Serializable {
	
	/*
	 * Våran hitbox är över alla föremål i spelet, blir någon beskjuten eller åker över en annan så kan man uppfatta det och agera utefter det.
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int xpos;
	private int ypos;
	private int length;
	private int height;
	
	public Hitbox (int xpos, int ypos, int  length, int height) { //konstruerar vår hitbox
		this.xpos = xpos;
		this.ypos = ypos;
		this.length = length;
		this.height = height;
	}

	/*
	 * Get och set nedan
	 */
	
	public int getxpos() {
		return xpos;
	}
	
	public void setxpos(int xpos) {
		this.xpos = xpos;
	}
	
	public int getypos() {
		return ypos;
	}
	
	public void setypos(int ypos) {
		this.ypos = ypos;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isHit(int x,int y) {						//har du träffat en fiende med ditt skott
		if (y <= (ypos + height)) {
			if (x >= xpos && x <= (xpos + length)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean coincide(int pox,int poy) {				//åker du över en powerup
		
		if (pox >= xpos && pox <= (xpos + length)) {
				return true;
			}
		
		if (pox <= xpos && pox >= (xpos - length)) {
			return true;
		}
	
		return false;
	}
	
	public boolean hitBottom(int yBottom) {					//har man nått bottnen av fönstret
		if (ypos + height >= yBottom) {
			return true;
		}
		return false;
	}
}
