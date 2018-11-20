package highscore;

import gui.GameWindow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JComponent;

public class HighscoreScreen extends JComponent {
	
	/*
	 * Bygger upp vår highscoreskärm där vi använder oss av highscorelistan mend personer etc
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ScorePanel listOfScores;
	private HighscoreArray highscoreList;
	private JButton goBack;
	private GameWindow gameWindow;
	
	public HighscoreScreen(GameWindow inGameWindow) {	//bygger upp vår highscoreskärm
		highscoreList = new HighscoreArray();
		setBounds(599,200,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Placerar ut i fönster
		gameWindow = inGameWindow;
		openHighscore();
		setLayout(new BorderLayout());
		listOfScores = new ScorePanel(highscoreList); //skapar poängpanel
		setSize(new Dimension(500,300));
		add(listOfScores); //lägger tillpoängpanel
		listOfScores.setVisible(true);
		goBack = new JButton("Tillbaka"); //Skapar knapp för att gå tillbaka
		add(goBack,BorderLayout.SOUTH);
		
		goBack.addActionListener( new ActionListener() {	//tar oss tillbaka till tidigare meny
			@Override
			public void actionPerformed(ActionEvent e) {
				gameWindow.returnTo();				
			}
			
		});
		
	}  
	
	public void setNewHighscore(String newName, int newScore) {	//sätter ett nytt highscore och namn, givet att den kommer med på lsita
		if(highscoreList.makeList(newScore)) {
			int scorePos = highscoreList.getPositionOfScore(newScore); //Hämtar position för nytt rekord
			setHighscoreAt(newName,scorePos,newScore); //Sparar nytt rekord på hämtad plats
			listOfScores = new ScorePanel(highscoreList); //Skapar om poängpanel
			add(listOfScores);
			saveHighscore(); //SParar listan
		}
	}
	
	public void saveHighscore() {	//sparar highscore
		File saveFile = new File("Highscores.si");
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile));
			out.writeObject(highscoreList);
			out.close();
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	
	public void openHighscore() {	//Laddar highscore																	//läser in vår highscorefil och hämter informationen
		File f = new File("Highscores.si");
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			highscoreList = (HighscoreArray) in.readObject();
			in.close();
		} catch (FileNotFoundException a) {
			for(int i = 0; i < highscoreList.getLength(); i ++) {
				highscoreList.setPersonAt(i, new HighscorePerson("---",0));
			}
		} catch (IOException e) {
			e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void setHighscoreAt(String newName, int scorePos, int newScore) {	// sätter en spelare på positionen som highscore  hamnar
		for(int i = (highscoreList.getLength() - 1); i > scorePos; i--) {
			highscoreList.setPersonAt(i,highscoreList.getPersonAt(i-1));	//Loopar i setHighscore, flyttar ned alla under nytt rekord
		}
		
		listOfScores.setVisible(false);
	  	remove(listOfScores);
	  	highscoreList.setPersonAt(scorePos, new HighscorePerson(newName, newScore)); //Lägger till person på given plats
		
	}

	public int getHighest() {		//hämtar highscore på högsta positionen
		return highscoreList.getPersonAt(0).getScore();
	}
}
