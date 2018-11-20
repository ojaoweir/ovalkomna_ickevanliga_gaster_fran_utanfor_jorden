package monster;

public class Yoda extends Alien{
	/*
	 * Bygger upp vår yoda alien
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Yoda(int xpos, int ypos) {
		super(xpos, ypos);
		pictureFileName = "YODA.jpg";
		health = 1;
		addPicture(pictureFileName); //Hämtar bild
		setVisible(true);
		setBounds(xpos,ypos,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Placerar ut
	}
}
