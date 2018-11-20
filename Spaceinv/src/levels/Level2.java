package levels;

import game.GameModel;
import monster.ET;
import monster.Yoda;
import player.Player;

public class Level2 extends Level{ 
	/*
	 * Bygger upp vår andra level, ny med två aliens (yoda och ET)
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Level2(GameModel theModel) {
		super(theModel);
	}

	public void initiate() {							//lägger upp början av spelet
		theModel.emptyModel();
		theModel.setSpawn(true);
		int xpos = 500;
		
		for (int x = 0 ; x < 2 ; x++) {					//lägger till aliens
			theModel.addAlien(new ET(xpos,0));
			xpos = xpos + 280;
			}
		
		xpos = 60;
		
		for (int x = 0 ; x < 6 ; x++) {					//lägger till aliens
			theModel.addAlien(new Yoda(xpos,120));
			xpos = xpos + 280;
			
			}
		
		theModel.addPlayer(new Player(400, 600));
		theModel.setStartTime();						//startar tiden
		theModel.resetAlienMoveCounter();				//reset på rörelseräknaren
		theModel.randomSpawnTime();
	}


}
