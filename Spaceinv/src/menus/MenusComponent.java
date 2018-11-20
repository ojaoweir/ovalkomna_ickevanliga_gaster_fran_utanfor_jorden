package menus;

import java.awt.BorderLayout;
import java.awt.Dimension;

import gui.GameWindow;

import javax.swing.JComponent;

public class MenusComponent extends JComponent {
	/*
	 * Hanterar våra pausmenyer och startmenyer, kan skifta mellan dem, remove/add nödvändigt  pga visivilityproblem m.m.
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GameWindow gameWindow;
	private StartMenu startMenu;
	private PauseMenu pauseMenu;
	
	public MenusComponent(GameWindow inGameWindow) { //Konstruktor, skapar en startmeny
		gameWindow = inGameWindow;
		setBounds(800,300,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Placerar ut
		setLayout(new BorderLayout());
		setSize(new Dimension(500,300));
		startMenu = new StartMenu(gameWindow);
		add(startMenu);
		
	}
	
	public void switchMenu() { //Ändrar från startmeny till pausmeny
		if(startMenu.isVisible()) {
			startMenu.setVisible(false);
			remove(startMenu);
			pauseMenu = new PauseMenu(gameWindow);
			add(pauseMenu);
		} else {
			pauseMenu.setVisible(false);
			remove(pauseMenu);
			startMenu = new StartMenu(gameWindow);
			add(startMenu);
		}
	}

}
