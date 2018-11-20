package highscore;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class InGameScorePanel extends Box {
	/*
	 * Hanterar så att du under spelets gång ser högsta highscoore och ditt eget som uppdateras då du dödar fiender eller klarar banor
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int highestScore;
	private int currentScore;
	private JLabel highestLabel;
	private JLabel currentLabel;
	
	public InGameScorePanel(int highestScore, int currentScore) {	//vi kan se högsta highscore och ditt eget highscore under speletgame
		super(BoxLayout.Y_AXIS);
		this.highestScore = highestScore;
		this.currentScore = currentScore;
		highestLabel = new JLabel("Högsta poäng: " + this.highestScore); //Skapar label med högsta poäng
		currentLabel = new JLabel("Nuvarande poäng: " + this.currentScore); //Skapar label med nuvarande pong
		highestLabel.setFont(new Font("SERIF", Font.BOLD, 12));  	//dessa sätter font och färg
		highestLabel.setForeground(Color.RED);		
		currentLabel.setFont(new Font("SERIF", Font.BOLD, 12));  	//dessa sätter font och färg
		currentLabel.setForeground(Color.BLUE);	
		add(highestLabel);
		add(currentLabel);
		setBounds(1600,10,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Placerar ut i fönstret
		setVisible(true);
	}
	public void addToCurrentScore(int newScore) {	//Lägger till poäng på dina nuvarande 
		currentLabel.setVisible(false);
		remove(currentLabel); //måste ta bort för att den ska funka
		currentScore = currentScore + newScore;
		currentLabel = new JLabel("Nuvarande poäng: " + this.currentScore);
		currentLabel.setFont(new Font("SERIF", Font.BOLD, 12));  	//dessa sätter font och färg
		currentLabel.setForeground(Color.BLUE);
		add(currentLabel);
		setBounds(1600,10,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Placerar ut
		currentLabel.setVisible(true);
	}
}
