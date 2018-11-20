package game;

import gui.GameWindow;
import hitbox.HitboxControl;
import java.io.Serializable;
import levels.Level;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.RandomInteger;
import player.Player;
import powerup.PowerUp;
import shoot.Bullet;
import shoot.BulletControl;
import monster.Alien;
import monster.EnemyControl;
import powerup.DoubleBulletPowerUp;
import powerup.PowerUpControl;
import powerup.BulletSpeedPowerUp;

public class GameModel implements Serializable {

	/*
	 * En stor klass hanterar all data i spelet, allt från spelare till kontoller av fiender, bullets och powerups
	 * uppladdningar tid m.m.
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EnemyControl enemies;
	private BulletControl bullets;
	private HitboxControl hitboxes;
	private PowerUpControl powerups;
	private boolean haspower;
	private boolean doMonstersExist;
	private Player ripley;
	private int levelNumber;
	private int score;
	private Level[] level;
	private long startTime;
	private int alienMoveCounter;
	private long alienMoveThreshold;
	private long lastBulletFiredAt;
	private int bulletWaitTime;
	private GameWindow gameWindow;
	private int gameOverHeight;
	private long pausedAtPlayTime;
	private long lastBulletFiredAtPlayTime;
	private Game game;
	private boolean spawn = true;
	private RandomInteger randomTime;
	private RandomInteger randomPos1;
	private RandomInteger randomPos2;
	private int spawnTime;
	private int bulletMoveTreshold;
	
	public GameModel(GameWindow gameWindow, Game game) { //Konstruktor, Skapar vår model
		this.game = game;
		this.gameWindow = gameWindow;
		ripley = new Player(500,600);
		haspower = false;
		enemies = new EnemyControl();
		bullets = new BulletControl();
		powerups = new PowerUpControl();
		hitboxes = new HitboxControl(enemies, bullets, powerups, this);
		score = 0;
		level = new Level[3];
		level[0] = new Level1(this);
		level[1] = new Level2(this);
		level[2] = new Level3(this);
		levelNumber = 1;
		alienMoveCounter = 1;
		alienMoveThreshold = 5000;
		lastBulletFiredAt = 0;
		bulletMoveTreshold = 10;
		bulletWaitTime = 1000;
		gameOverHeight = gameWindow.getHeight() - 300;

		randomTime = new RandomInteger(10);
		randomPos1 = new RandomInteger(1700);
		randomPos2 = new RandomInteger(1700);
		
	}
	
	public void randomSpawnTime() { //Gör random spawntime för powerup
		spawnTime = 1000 * randomTime.getRandomInt();
	}
	
	public void setBulletWaitTime(int i) { //Ändrar tiden mellan skott
		bulletWaitTime = i;
	}
	
	public void setSpawn(Boolean change) { //Ändrar boolean spawn till givet
		spawn = change;
	}
	
	public long getAlienMoveThreshold() { //Hämtar tidsgränsen för flytta alien
		return alienMoveThreshold;
	}


	public void setAlienMoveThreshold(long alienMoveThreshold) { //Ändrar tidsgränsen för att flytta alien
		this.alienMoveThreshold = alienMoveThreshold;
	}


	public void setStartTime() { //Sätter starttid för banan
		startTime = System.currentTimeMillis();
	}
	
	public void resetAlienMoveCounter() { //Återställer analet flytt alien gjort
		alienMoveCounter = 1;
	}
	
	public void addAlien(Alien newAlien) { //Lägger till alien i array och fönster
		enemies.addEnemy(newAlien);
		game.add(newAlien);
		if (!doMonstersExist) {
			doMonstersExist = true;
		}
	}
	
	
	public boolean noMonster() { //Kollar om tomt med monster		
    	return doMonstersExist;
	}

	public void addPlayer(Player player) { //Lägger till spelare i model och fönster
		ripley = player;
		game.add(ripley);
	}

	public void setPlayerXY(int xpos, int ypos) { //Flyttar spelare
		ripley.setXpos(xpos);
		ripley.setYpos(ypos);
		hitboxes.catchPowerUp();
		if (spawn) {
			long runTime = System.currentTimeMillis() - startTime;
			if (runTime >= spawnTime) { //Om tid har nått viss gräns kommer powerups spawna
				addPower(new DoubleBulletPowerUp(randomPos1.getRandomInt(), 800));
				addPower(new BulletSpeedPowerUp(randomPos2.getRandomInt(), 800));
				spawn = false; //Gör så att de inte spawnar igen
			}
		}
	}
	public Player getPlayer() { //Returnerar player
		return ripley;
	}
	
	public void setCurrentLevelNumber(int levelNumber) { //Ändrar vilken level som är just nu
		this.levelNumber = levelNumber;
	}
	
	public void addFiredBullet(int x, int y) { //Kägger till skott i array och fönster
		long timeSinceLast =System.currentTimeMillis() - lastBulletFiredAt;
		
		if(timeSinceLast >= bulletWaitTime) { //kollar om tillräckligt lång tid gått mellan skott
			if(ripley.doubleShotActivated()) { //Om dubbelskott akriv skjuts 2
				Bullet doubleShot = new Bullet(x + 10, y);
				bullets.addFiredBullet(doubleShot);	
				game.add(doubleShot);
				x = x - 10;
			}
			Bullet shotsfired = new Bullet(x, y); //Om ej aktiv skjuts bara denna
			lastBulletFiredAt = System.currentTimeMillis();
			bullets.addFiredBullet(shotsfired);	
			game.add(shotsfired);
			
			
			
		}
	}
	
	public boolean noPower() {			
    	return haspower;
	}
	
	public void addPower(PowerUp power) { //Lägger till powerup i array och fönster
		powerups.addPowerUp(power);
		game.add(power);
		
		if (!haspower) {
			haspower = true;
		}
	}
	
	public void bulletMovement() { //Flyttar bullets
		long runTime = System.currentTimeMillis() - startTime;
		if(runTime >= bulletMoveTreshold) {
			bullets.changeVisibility();
			ticktockBullets();
			addScore(40 * hitboxes.targetHit()); //Lägger till poäng om den träffat någon
			 if (enemies.emptyList()) { //Om alla döda får 100 poäng, om ej bana 3 körs nästa bana
				 addScore(100);
				 if (levelNumber < level.length) {
					 level[levelNumber].initiate();
					 levelNumber++;
				 }
			 }
			bullets.changeVisibility();
			
		}
	}
	
	public void AlienMovement() { //Flyttar ner aliens efter given tid gått
		long runTime = System.currentTimeMillis() - startTime;
		if(runTime >= alienMoveCounter * alienMoveThreshold) {
			enemies.changeVisibility();
			enemies.ticktock();
			if(levelNumber == 3) {
				level[levelNumber - 1].newWave();
			}
			
			if (hitboxes.reachedBottom(gameOverHeight)) { //Kolla rom de når botten i så fall game over
				gameWindow.gameOver();
			}
			alienMoveCounter++;
			enemies.changeVisibility();
		}
	}
	
	public void initiate() { //Startar ny bana
		level[levelNumber - 1].initiate();
	}

	public void ticktockBullets() { //Sköter flytt av bullets
		for (int i = 0; i < bullets.getArrayLength(); i ++) {
			Bullet temp = bullets.getBulletAt(i);
			
			if (temp.getYpos() < 0) {
				bullets.removeFiredBullet(temp);
			}
			temp.ticktock();
		}
	}

	public void Pause() { //Pausar model
		pausedAtPlayTime = System.currentTimeMillis() - startTime;
		lastBulletFiredAtPlayTime = System.currentTimeMillis() - lastBulletFiredAt;
	}
	
	public void unPause() { //Unpausar model
		startTime = System.currentTimeMillis() - pausedAtPlayTime;
		lastBulletFiredAt = startTime + lastBulletFiredAtPlayTime;
	}

	public int getscore() {
		return score;
	}
	
	public void changeVisibility() { //Ändrar enemies visibility
		enemies.changeVisibility();
	}

	public void makeAllVisible() { //Gör enemies visible
		enemies.makeAllVisible();		
	}

	public void addScore(int newScore) { //Lägger till poäng 
		if(score + newScore > score) {
			score = score + newScore;
			game.addToCurrentScore(newScore);
		}
	}
	

	public void emptyModel () { //Tömmer model och fönster på fiender powerups och enemies smat player
		for(int i = 0; i < bullets.getArrayLength(); i ++) {
			bullets.getBulletAt(i).setVisible(false);
			game.remove(bullets.getBulletAt(i));
			bullets.removeFiredBullet(bullets.getBulletAt(i));
		}
		for(int i = 0; i < powerups.getArrayLength(); i ++) {
			powerups.getPowerUpAt(i).setVisible(false);
			game.remove(powerups.getPowerUpAt(i));
			powerups.removePowerUp(powerups.getPowerUpAt(i));
		}
		enemies.removeAll();
		ripley.setVisible(false);
		game.remove(ripley);
	}

	public void load(GameWindow gameWindow, Game game) { //Laddar gammalt spel
		
		this.game = game;
		this.gameWindow = gameWindow;
		//Ritar ut allt i fönster
		for(int i = 0; i < bullets.getArrayLength(); i ++) {
			game.add(bullets.getBulletAt(i));
			bullets.getBulletAt(i).setVisible(true);
		}
		for(int i = 0; i < powerups.getArrayLength(); i ++) {
			game.add(powerups.getPowerUpAt(i));
			powerups.getPowerUpAt(i).setVisible(true);
		}
		for(int i = 0; i < enemies.getArrayLength(); i ++) {
			game.add(enemies.getAlienAt(i));
			enemies.getAlienAt(i).setVisible(true);
		}		
		game.add(ripley);		
	}
	
}
