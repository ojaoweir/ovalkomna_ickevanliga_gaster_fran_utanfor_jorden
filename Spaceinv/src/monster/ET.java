package monster;

public class ET extends Alien{
	/*
	 * Bygger upp vår ET alien
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ET(int xpos, int ypos) {
		super(xpos, ypos);
		pictureFileName = "ET.png";
		health = 2;
		addPicture(pictureFileName); //Hämtar bild
		setVisible(true);
		setBounds(xpos,ypos,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Placerar ut
	}
}
