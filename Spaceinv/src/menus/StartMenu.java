package menus;

import gui.GameWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartMenu extends JPanel{
	/*
	 * vår startmeny/huvudmeny som kommer då du startar programmet eller förlorat och går bort från hishscore,
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton highscore;
	private JButton start;
	private JButton load;
	private JButton quit;
	private JButton help;
	private GameWindow gameWindow;
	
	public StartMenu(GameWindow gameWindow) { //Konstruktor, lägger till alla knappar
		this.gameWindow = gameWindow;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        start = new JButton("Starta nytt spel");
        load = new JButton("Ladda gammalt spel");
        help = new JButton("Hjälp");
		highscore = new JButton("Highscore");
		quit = new JButton("Stäng av spel");
		
		addListeners();
		
		add(start);
		add(load);
		add(help);
		add(highscore);
		add(quit);
		setVisible(true);
	}
	
	private void addListeners() { //Lägger till listeners på alla knappar
		quit.addActionListener(new ActionListener() { 
		      public void actionPerformed(ActionEvent e) { 
		    	  gameWindow.dispose();
		      } 
		    } );
		
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
		
		start.addActionListener(new ActionListener() { 
		      public void actionPerformed(ActionEvent e) { 
		    	  gameWindow.startNewGame();
		      } 
		    } );
		
		load.addActionListener(new ActionListener() { 
		      public void actionPerformed(ActionEvent e) { 
		    	  gameWindow.loadGame();
		      } 
		    } );
	}
}
