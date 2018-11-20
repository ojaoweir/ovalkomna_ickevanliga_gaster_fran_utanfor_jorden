package menus;
import gui.GameWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TestMenu extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton highscore;
	private JButton start;
	private JButton load;
	private JButton quit;
	private GameWindow gameWindow;
	
	public TestMenu (GameWindow gameWindow) {
		this.gameWindow = gameWindow;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        start = new JButton("Starta nytt spel");
        load = new JButton("Ladda gammalt spel");
		highscore = new JButton("Highscore");
		quit = new JButton("Stäng av spel");
		
		addListeners();
		
		add(start);
		add(load);
		add(highscore);
		add(quit);
		setVisible(true);
	}
	
	private void addListeners() {
		quit.addActionListener(new ActionListener() { 
		      public void actionPerformed(ActionEvent e) { 
		    	  gameWindow.dispose();
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


