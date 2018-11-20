package levels;

import java.io.Serializable;
import game.GameModel;

public abstract class Level implements Serializable {			//vår abstrakta levelklass
	/*
	 * Vår abstrakta levelklass, se de andra levels för mer specifik information
	 * Level startar en bana som specificeras i varje unik level (banorna är unika)
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GameModel theModel;
	
	public Level(GameModel theModel) {							//konstruktor
		this.theModel = theModel;
	}
	
	public abstract void initiate();							//påbörjar banan
	
	public GameModel getModel() {
		return theModel;
	}
	
	public void setModel(GameModel theModel) {
		this.theModel = theModel;
	}

	public void newWave() {										//ska spawna ny våg, se level 3
	}

}
