package gui;

import game.Game;
import menus.MenusComponent;
import game.GameModel;
import highscore.GameOverScreen;
import highscore.HighscoreScreen;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import menus.ErrorWindow;
import menus.HelpScreen;
import java.awt.Dimension;
import javax.swing.JFrame;

public class GameWindow extends JFrame implements Serializable {
	/*
	 * Bygger upp vårt huvudfönster som vi använder  
	 */
	
	private static final long serialVersionUID = 1L;

	private HighscoreScreen highscore;
	private Game game;
	private MenusComponent menus;
	private GameOverScreen gameOver;
	private Container p;
	private HelpScreen help;
	private Title title;
	
	public GameWindow() {     //bygger upp huvudfönstret
        super("Ovälkommna icke-vänliga gäster från utanför jorden");
        title = new Title();
        add(title);
        help = new HelpScreen(this);
        add(help);
        help.setVisible(false);
	    p = getContentPane(); 
		p.setLayout(null);
        highscore = new HighscoreScreen(this);
	    add(highscore);
	    highscore.setVisible(false); 
	    menus = new MenusComponent(this);
        setSize(new Dimension(1800, 900));
        add(menus);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        gameOver = new GameOverScreen(this,highscore);
        add(gameOver);
    }
	
	public void showHighscore() {			//går över till highscoremenyn
		menus.setVisible(false);
		highscore.setVisible(true);
	}

	public void showHelp() {			//går över till help
		menus.setVisible(false);
		help.setVisible(true);
		
	}
	
	public void startNewGame() {     		//skapar ett nytt spel
		startGame(null);
	}
	
	public void saveGame() {				//sparar spelet
		game.getModel();
		File saveFile = new File("SavedGame.si");
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile));
			game.getModel().Pause();
			out.writeObject(game.getModel());
			out.close();
		} catch (IOException e) {	
			e.printStackTrace();
		}
    } 

	public void loadGame() {				//laddar spelet
		File f = new File("SavedGame.si");
		GameModel loadedModel;
		
		try {								//försöker öppna
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			loadedModel = (GameModel) in.readObject();
			in.close();
			loadedModel.unPause();
			startGame(loadedModel);
		} catch (FileNotFoundException a) {
			new ErrorWindow("Inget gammalt spel sparat");
		} catch (IOException e) {
			e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void startGame(GameModel theModel) {	//bygger upp spelet, ändrar menyer och visibility
		title.setVisible(false);
		menus.switchMenu();							
		menus.setVisible(false);
		highscore.setVisible(false);
	    game = new Game(theModel, this);
		add(game);
		game.setVisible(true);
	    game.requestFocusInWindow();
	}
	
	public void returnTo() {						//återgår från highscoremenyn till föragående menyn
		highscore.setVisible(false);
		help.setVisible(false);
		menus.setVisible(true);
	}

	public void pauseGame() {						//pausar spelet, öppnar pausskärm
		game.hitPauseGame();
		title.setVisible(true);
		game.setVisible(false);
		menus.setVisible(true);
	}
	
	public void unPauseGame() {						//spelet återgår efter paus, vislibility ändras
		title.setVisible(false);
		menus.setVisible(false);
		game.setVisible(true);
		game.hitPauseGame();
	    game.requestFocusInWindow();
	}

	public void gameOver() {						//spelet har förlorats, hämtar dit highscore, tar bort dit spel och tar upp gameoverskärmen
		title.setVisible(true);
		menus.switchMenu();
		gameOver.setScore(game.getScore());
		game.setVisible(false);
		remove(game);
		gameOver.setVisible(true);
	}

	public void switchMenu() {						//byter mellan menyer
		menus.switchMenu();
	}
	
	public Game getGame() {							//hämtar spelet
		return game;
	}

	public int getHighestHighscore() {				//hämtar högsta highscore
		return highscore.getHighest();
	}

	public void hideGameOver() {  //Gömmer gameOverskärmen
		gameOver.setVisible(false);
	}

}
