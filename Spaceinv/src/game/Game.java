package game;


import gui.GameWindow;
import highscore.InGameScorePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Game extends JComponent implements ActionListener, KeyListener{
	
	/*
	 * Vår gameklass hanterar, uppritning och uppdatering av föremål samt knapptryck som orsakar förflyttningen eller kommandon
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Timer tm = new Timer(5, this);
	private int xpos = 750;
	private int ypos;
	private int xvel = 0;
	private GameModel theModel;
	private boolean pause = false;
	private GameWindow gameWindow;
	private InGameScorePanel currentScore;
	
	public Game(GameModel inModel, GameWindow gameWindow) { //Sätter upp vårt spel
		setBounds(0,0,1800,900); //Placerar ut
		this.gameWindow = gameWindow;
		if (inModel == null)  { //Startar ny model om inte load
			theModel = new GameModel(this.gameWindow,this);
			theModel.initiate();
		} else { //Laddar model
			theModel = inModel;
			theModel.load(this.gameWindow, this);
			xpos = theModel.getPlayer().getXpos();
		}
		tm.start();
	    ypos = gameWindow.getHeight() - 100;
		addKeyListener(this);
		currentScore = new InGameScorePanel(this.gameWindow.getHighestHighscore(),theModel.getscore()); //Skapar scorepanel i hörnet
        add(currentScore);
	}

	@Override
    public void keyPressed(KeyEvent e) { //Listerners för alla knappar
       
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xvel = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xvel = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){
        	theModel.addFiredBullet(xpos + 17, ypos);
        	
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	if (pause) {
            	gameWindow.unPauseGame();
        	} else {
            	gameWindow.pauseGame();        		
        	}
        }
    }
	
	public GameModel getModel() { //Returnerar model
		return theModel;
	}

	public void hitPauseGame() { //pausar model
    	pause = !pause;
    	if (pause) {
    		theModel.Pause();
    	} else {
    		theModel.unPause();
      	}
    }
	
    @Override
    public void keyReleased(KeyEvent e) { //Måste finnas
    	  xvel = 0;
    	  
    	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        }
    }
    
	protected void paintComponent(Graphics g) {
       if(!pause ) { 
    	   g.setColor(Color.BLACK);
    	   g.fillRect(0,0, getWidth(), getHeight()); //ritar upp den svarta ritytan
    	   theModel.bulletMovement(); //Flyttar bilder
    	   theModel.AlienMovement();
       }
    }
	@Override
	public void keyTyped(KeyEvent e) {
		//Måste ha
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        
		if (!pause){
		if (xpos < 20){
			xvel = 0;
			xpos = 20;
	          
		} else if (xpos > getWidth()-70){
			xvel = 0;
			xpos = getWidth()-70;

		}
		xpos = xpos + xvel;
		theModel.setPlayerXY(xpos, ypos);
		repaint();
		}
	}

	public int getScore() { //Hämtar nuvarande poäng
		return theModel.getscore();
	}
	
	public void addToCurrentScore(int newScore) { //Lägger till poäng i scorepanel i hörnet
		currentScore.addToCurrentScore(newScore);
	}

}








