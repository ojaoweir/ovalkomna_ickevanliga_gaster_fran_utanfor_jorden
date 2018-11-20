package levels;

import player.Player;
import monster.Yoda;
import game.GameModel;

public class Level1 extends Level { 
	/*
	 * Bygger upp vår level 1 med en våg aliens (yoda för att vara specifik)
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Level1(GameModel theModel) {
		super(theModel);
	}

	public void initiate() {							//lägger upp början av spelet
		theModel.emptyModel();
		theModel.setSpawn(true);
		int xpos = 60;
		
		for (int x = 0 ; x < 6 ; x++) {
			theModel.addAlien(new Yoda(xpos,0));
			xpos = xpos + 280;
			
		}
		
		theModel.addPlayer(new Player(400, 600));
		theModel.setStartTime();						//startar tiden
		theModel.resetAlienMoveCounter();				//reset på rörelseräknaren	
		theModel.randomSpawnTime();
	}

}
