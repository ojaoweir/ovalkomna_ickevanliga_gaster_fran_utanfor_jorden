package levels;

import monster.ET;
import monster.Yoda;
import player.Player;
import game.GameModel;

public class Level3 extends Level{ 
	/*
	 * Sista banan, stort sett designad för att döda dig, du spelara till du dör
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RandomInteger randomPos;
	private RandomInteger randomNumberOfET;
	
	public Level3(GameModel theModel) {
		super(theModel);
		randomPos = new RandomInteger(6);
		randomNumberOfET = new RandomInteger(6);		//genererar random siffror
	}

	public void initiate() {							//bygger upp början av banan
		theModel.emptyModel();
		int xpos = 60;
		for (int x = 0 ; x < 6 ; x++) {
			theModel.addAlien(new ET(xpos,0));
			xpos = xpos + 280;
			}
		xpos = 60;
		for (int x = 0 ; x < 6 ; x++) {
			theModel.addAlien(new Yoda(xpos,120));
			xpos = xpos + 280;
			
			}
		
		theModel.addPlayer(new Player(400, 600));
		theModel.setStartTime();
		theModel.resetAlienMoveCounter();
		theModel.randomSpawnTime();
		theModel.setSpawn(true);
	}
	
	public void newWave() {					//gör att vi skapar vågor av aliens
		int xpos = 60;
		theModel.setAlienMoveThreshold(theModel.getAlienMoveThreshold() - 200 );
		int numberOfET = randomNumberOfET.getRandomInt();
		int posET[] = new int[numberOfET];
		
		for (int i = 0; i < numberOfET; i++) {
			posET[i] = randomPos.getRandomInt();
		}
		
		xpos = 60;
		int ypos = 0;

		for (int alienCounter = 0 ; alienCounter < 6 ; alienCounter++) {
			if(xIsET(alienCounter, posET)) {
				theModel.addAlien(new ET(xpos,ypos));
			} else {
				theModel.addAlien(new Yoda(xpos,ypos));
			}
			xpos = xpos + 280;
		}
		theModel.makeAllVisible();
	}

	private boolean xIsET(int alienCounter, int[] posET) {		//kollar om alien äen en ET
		for(int i = 0; i < posET.length; i++) {
			if (alienCounter == posET[i]) {
				return true;
			}
		}
		return false;
	}
	
}
