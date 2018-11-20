package powerup;

import java.io.Serializable;
import java.util.ArrayList;

public class PowerUpControl implements Serializable {
	/*
	 * Våran kontorllklass för powerups, kan ta bort lägga till powerups etc
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList <PowerUp> powerUpArray = new ArrayList<PowerUp>();
	
	public PowerUpControl () { //Tom konstruktor
		
	}
	
	public void addPowerUp(PowerUp power) {  //Dessa lägger till och tar bort i array
		powerUpArray.add(power);
	}
	
	public void removePowerUp(PowerUp power) {
		powerUpArray.remove(power);
	}
	
	public int getArrayLength() {  //Returnerar storlek på array
		return powerUpArray.size();
	}
	
	public PowerUp getPowerUpAt(int i) { //Returnerar powerup på given plats
		return powerUpArray.get(i);
	}
	
	public boolean emptyList() { //Returnar true om tom
		return powerUpArray.isEmpty();
	}

	public void removeAll() { //Tömmer listan
		powerUpArray.removeAll(powerUpArray);
	}
	
}
