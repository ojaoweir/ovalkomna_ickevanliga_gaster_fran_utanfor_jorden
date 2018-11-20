package highscore;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ScorePanel extends JPanel {
	/*
	 * Rutan med alla namn och deras highscore
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Box names;
	private JLabel player[];
	private JLabel score[];
	private Box scores;
	
	public ScorePanel(HighscoreArray allPlayers) { 	//har två boxar, en som listar namn och en som listar
		names = new Box(BoxLayout.Y_AXIS);			// score. De raddas upp nedåt
		scores = new Box(BoxLayout.Y_AXIS);
		setPlayerAndScore(allPlayers);
		setLayout(new FlowLayout());
		addAllPlayersAndScore();
		
	}
	
	private void setPlayerAndScore(HighscoreArray allPlayers) {  //Sparar varje person i listans namn
		player = new JLabel[allPlayers.getLength()];			//samt poäng i jlabels så dessa skrivs ut
		score = new JLabel[allPlayers.getLength()];
		EmptyBorder spaceBorder = new EmptyBorder(5,20,5,20);  //Skapar mellanrum mellan labels
		
		for(int i = 0; i < allPlayers.getLength(); i++) {
			player[i] = new JLabel(allPlayers.getPersonAt(i).getName());
			score[i] = new JLabel(Integer.toString(allPlayers.getPersonAt(i).getScore()));
		    player[i].setFont(new Font("SERIF", Font.BOLD, 12));  	//dessa sätter font och färg
		    player[i].setForeground(Color.BLUE);					//
		    score[i].setFont(new Font("SERIF", Font.BOLD, 12));		//
		    score[i].setForeground(Color.RED);						//
		    player[i].setBorder(spaceBorder); 						//Denna och nedan ger spacing
		    score[i].setBorder(spaceBorder);
		}
		
	}
	
	public void addAllPlayersAndScore() { 			//addar alla labels till sin respektive box
		names.add(new JLabel("Namn:"));				// addar sedan boxar till panel
		scores.add(new JLabel("Poäng:"));
		for(int i = 0; i < player.length; i++) {
			names.add(player[i]);
			scores.add(score[i]);
		}
		add(names);  
		add(scores);
	}
}
