package highscore;

import java.io.Serializable;

public class HighscoreArray implements Serializable {
	
	/*
	 *Hanterar själva "listan" av rekord som kan ses på highscoremenyn
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int listLength = 10; 	//Hur många personer som är sparade i listan
	private HighscorePerson[] hsList = new HighscorePerson[listLength];
	
	public HighscoreArray() { //Tom konstruktor
	}

	public void setPersonAt(int i, HighscorePerson player) { 	 //sparar en person på plats i i listan
		hsList[i] = player;
	}
	
	public boolean makeList(int newScore) { 	 // kontrollerar om inkommande poäng kommer med på highscorelistan
		for(int i = 0; i < getLength(); i++) {
			if(newScore >= hsList[i].getScore()) {
				return true;
			}
		}
		return false;
	}
	
	public int getLength() {  //hämtar längden på array
		return hsList.length;
	}
	
	public HighscorePerson getPersonAt(int i) {	//returnar person på plats i
		return hsList[i];
	}
	
	public int getPositionOfScore(int newScore) {  	//säger vilken plats en viss poäng hamnar
		for(int i = 0; i < getLength(); i++) {
			if(newScore >= hsList[i].getScore()) {
				return i;
			}
		}
		return 0;
	}
	
}
