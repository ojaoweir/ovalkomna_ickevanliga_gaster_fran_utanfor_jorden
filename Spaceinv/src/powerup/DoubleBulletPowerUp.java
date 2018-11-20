package powerup;

public class DoubleBulletPowerUp extends PowerUp {
	/*
	 * Uppbyggnad av powerup vars effekt är att få två skott som du kan skjuta med
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoubleBulletPowerUp(int xpos, int ypos) {
		super(xpos, ypos);
		pictureFileName = "DoubleBullet.jpg";
		effect = 2;
		addPicture(pictureFileName); //Laddar bild
		setVisible(true);
		setBounds(xpos,ypos,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Placerar ut på window
	}
}
