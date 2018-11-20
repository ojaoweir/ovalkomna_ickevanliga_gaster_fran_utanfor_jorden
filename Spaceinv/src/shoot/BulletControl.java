package shoot;

import java.io.Serializable;
import java.util.ArrayList;

public class BulletControl implements Serializable {
	/*
	 * Vår kontrollklass av skott, lägger till dem i vår array vi använder för att hantera alla skott som skjuts
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList <Bullet> bulletArray = new ArrayList <Bullet>();
	private Bullet temp;
	
	public BulletControl () { //Tom konstruktor
		
	}
	
	
	public void ticktock() {
		for (int i = 0; i < bulletArray.size(); i ++) {  //Gör kallar ticktock-metoden för alla bullets
			temp = bulletArray.get(i);
			
			if (temp.getYpos() < 0) {
				removeFiredBullet(temp); //Om bullet lämnar fönstret tas den bort
			}
			temp.ticktock();
		}
	}
	
	public void addFiredBullet(Bullet shot) {  //Lägger till bullet i array
		bulletArray.add(shot);
	}
	
	public void removeFiredBullet(Bullet shot) { //Tar bort bullet ur array
		bulletArray.remove(shot);
	}
	
	public int getArrayLength() {  //Returnar hur stor arrayen är 
		return bulletArray.size();
	}
	
	public Bullet getBulletAt(int i) {  //Returnar bullet på given plats
		return bulletArray.get(i);
	}
	

	public void changeVisibility() {  //Ändrar så alla bullets byter visibility. Används för omritning
		for(int i = 0; i < getArrayLength(); i++) {
			bulletArray.get(i).setVisible(!bulletArray.get(i).isVisible());
		}
	}


	public void removeAll() {  //Tömmer listan
		bulletArray.removeAll(bulletArray);
	}
}
