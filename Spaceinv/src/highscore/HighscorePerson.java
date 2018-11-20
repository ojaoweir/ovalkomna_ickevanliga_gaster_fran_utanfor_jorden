package highscore;

import java.io.Serializable;

public class HighscorePerson implements Serializable {
	
	/*
	 *Personen som sätts in på highscore listan, du har huvudsakligen ditt namn och poäng
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int score;
	
	public HighscorePerson(String name, int score) { 	//Denna klass är en person i listan, den har ett namn och en poäng
		this.name = name;
		this. score = score;
	}

	/* 
	 * Getters och setters nedan
	*/
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
