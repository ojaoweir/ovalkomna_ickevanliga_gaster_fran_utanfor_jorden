package highscore;

import gui.GameWindow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GameOverScreen extends JComponent {
	
	/*
	 *Gameoverskärmen som kommer upp då du förlorar spelet, skriv in ditt namn så hamnar du på highscorelilstan om du gjorde bra ifrån dig 
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton goToHighscore;
	private GameWindow gameWindow;
	private JTextField nameBox;
	private int score;
	private HighscoreScreen highscore;
	
	public GameOverScreen(GameWindow inGameWindow, HighscoreScreen inhighscore) {	//bygger upp vår GameOverskärm
		setVisible(false);
		setBounds(500,300,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Placerar ut
        setSize(new Dimension(500,300));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.highscore = inhighscore;
		gameWindow = inGameWindow;
		add(new JLabel("GAME OVER. Skriv ditt namn i rutan nedan: "));
		nameBox = new JTextField();
		nameBox.setSize(new Dimension(50,30));
		add(nameBox);
		goToHighscore = new JButton("Fortsätt");
		add(goToHighscore,BorderLayout.SOUTH);
		
		goToHighscore.addActionListener( new ActionListener() { //för oss till highscore efter att vi skrivit in vårt namn och tryckt fortsätt
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameBox.getText(); 
				if(name.equals("")) { //Gör så att listan inte blir konstig om man inte skriver in något
					name = " ";
				}
				highscore.setNewHighscore(name, score); //Sparar highscore och namn
				gameWindow.showHighscore(); //Går til highscore
				gameWindow.hideGameOver();
				nameBox.setText(""); //Tömmer rutan så det inte blir knas om kan kör igen
			}
			
		});
	}

	public void setScore(int score) {	//sätter score som kommer sparas
		this.score = score;
	}
}
