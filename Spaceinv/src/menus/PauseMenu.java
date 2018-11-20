package menus;

import gui.GameWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PauseMenu extends JPanel{
	/*
	 * vår pausmeny som uppkommer när du klickar på ESC i game
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton highscore;
	private JButton save;
	private JButton help;
	private JButton quit;
	private JButton goback;
	private JButton mainMenu;
	private GameWindow gameWindow;
		
		public PauseMenu (GameWindow gameWindow) { //Konstrukor lägger til alla knappar
			this.gameWindow = gameWindow;
	        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			goback = new JButton("fortsätt spela");
	        save = new JButton("Spara");
	        help = new JButton("Hjälp");
			highscore = new JButton("Highscore");
			mainMenu = new JButton("Huvudmeny");
			quit = new JButton("Stäng av spel");
			
			addListeners();

			add(goback);
			add(save);
			add(help);
			add(highscore);
			add(mainMenu);
			add(quit);
			setVisible(true);
		}
		
		private void addListeners() { //Skapar listeners för alla knappar
			goback.addActionListener(new ActionListener() { 
			      public void actionPerformed(ActionEvent e) { 
			    	  gameWindow.unPauseGame();
			      } 
			    } );
			save.addActionListener(new ActionListener() { 
			      public void actionPerformed(ActionEvent e) { 
			    	  gameWindow.saveGame();
			      }
			});
			
			
			help.addActionListener(new ActionListener() { 
			      public void actionPerformed(ActionEvent e) { 
			    	  gameWindow.showHelp();
			      } 
			    } );
			highscore.addActionListener(new ActionListener() { 
			      public void actionPerformed(ActionEvent e) { 
			    	  gameWindow.showHighscore();
			      } 
			    } );
			
			mainMenu.addActionListener(new ActionListener() { 
			      public void actionPerformed(ActionEvent e) { 
			    	  gameWindow.switchMenu();
			      } 
			    } );
			
			quit.addActionListener(new ActionListener() { 
			      public void actionPerformed(ActionEvent e) { 
			    	  gameWindow.dispose();
			      } 
			    } );
			
		}
}
