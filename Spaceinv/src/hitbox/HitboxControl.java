package hitbox;

import java.io.Serializable;
import shoot.BulletControl;
import monster.Alien;
import monster.EnemyControl;
import powerup.PowerUpControl;
import game.GameModel;

public class HitboxControl implements Serializable {
	
	/*
	 * vår kontrollklass för allas hitboxes
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EnemyControl enemies;
	private BulletControl bullets;
	private PowerUpControl powerups;
	private GameModel theModel;
	
	public HitboxControl(EnemyControl enemies, BulletControl bullets, PowerUpControl powerups, GameModel theModel) {
		this.enemies = enemies;																													//konstruerar vå hitboxcontrol
		this.bullets = bullets;
		this.powerups = powerups;
		this.theModel = theModel;
	}

	public EnemyControl getEnemies() { //Returnerar enemies array
		return enemies;
	}

	public void setEnemies(EnemyControl enemies) { //Sparar enemies-array
		this.enemies = enemies;
	}

	public BulletControl getBullets() { //returnerar bulletsarray
		return bullets;
	}

	public void setBullets(BulletControl bullets) { //Sätter bulletsarray
		this.bullets = bullets;
	}

	public PowerUpControl getPowerUp() { //returnerar poweruparray
		return powerups;
	}

	public void setPowerUps(PowerUpControl powerups) { //sätter poweruparray
		this.powerups = powerups;
	}
	
	public void addEnemy(Alien newAlien) { //Lägger till alien i enemies
		enemies.addEnemy(newAlien);
	}
	
	public void updateAll(EnemyControl enemies, BulletControl bullets,PowerUpControl powerups) { //updaterar allt
		setEnemies(enemies);
		setBullets(bullets);
		setPowerUps(powerups);
	}
	
	public int targetHit() {		//kontrollerar om du träffar en motståndare
		int bulletX;
		int bulletY;
		int numberOfHits = 0;
		for (int enemyCounter = 0; enemyCounter < enemies.getArrayLength(); enemyCounter++) {
			for (int bulletCounter = 0; bulletCounter < bullets.getArrayLength(); bulletCounter++) {
				
				bulletX = bullets.getBulletAt(bulletCounter).getHitbox().getxpos();		//hämtar hitbox koordinater för skottet
				bulletY = bullets.getBulletAt(bulletCounter).getHitbox().getypos();
				
				if(enemies.getAlienAt(enemyCounter).getHitbox().isHit(bulletX,bulletY)) {//Kollar skottit kommit lika högt eller högre än alien
					bullets.removeFiredBullet(bullets.getBulletAt(bulletCounter));	//Tar bort bullet och skadar alien
					enemies.damageEnemy(enemyCounter);
					numberOfHits++;
				}
			}
		}
		return numberOfHits; //Returnar hur mänga träffar det blir för att ge poäng
	}
	
	
	public void catchPowerUp() {	//Kontrollerar om player tar upp powerup																		//lite motsvarande target hit men med krock mellan spelare och power up
		int x;
		int y;																								//får man en powerup akriveras dess effekt;
		
		for (int counter = 0; counter < powerups.getArrayLength(); counter ++) {
			x = powerups.getPowerUpAt(counter).getHitbox().getxpos(); //Hämtar x och y position för powerup 
			y = powerups.getPowerUpAt(counter).getHitbox().getypos();
			
			if(theModel.getPlayer().getHitbox().coincide(x, y)) { // kollar om palyer och powerup krockar
				if (powerups.getPowerUpAt(counter).activateEffect() == 1) {
					theModel.setBulletWaitTime(500);
				}
				
				if (powerups.getPowerUpAt(counter).activateEffect() == 2) {
					theModel.getPlayer().setdoubleshot(true);
				}
				theModel.addScore(25);
				powerups.getPowerUpAt(counter).setVisible(false); //Tar bort en powerup om den blir tagen
				powerups.removePowerUp(powerups.getPowerUpAt(counter));	
				
			}			
		}	
	}
	
	
	public void updateBullets(BulletControl bullets) {														//uppdaterar skott
		setBullets(bullets);
	}

	public boolean reachedBottom(int yBottom) {			 //kollar om alien nått botten													//om fiender har nått bottnen
		for(int i = 0; i < enemies.getArrayLength(); i++) {
			if (enemies.getAlienAt(i).getHitbox().hitBottom(yBottom)) {
				return true;
			}
		}
		return false;
	}
	
}
