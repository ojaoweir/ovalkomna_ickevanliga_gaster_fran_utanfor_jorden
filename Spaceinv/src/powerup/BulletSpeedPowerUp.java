package powerup;

public class BulletSpeedPowerUp extends PowerUp {
	/*
	 * Uppbyggnad av powerup vars effekt är att fördubbla hastigheten som du kan skjuta med
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BulletSpeedPowerUp(int xpos, int ypos) {
		super(xpos, ypos);
		pictureFileName = "BulletSpeed.jpg";
		effect = 1;
		addPicture(pictureFileName); //HÄmtar bild
		setVisible(true);
		setBounds(xpos,ypos,(int) getPreferredSize().getWidth(),(int) getPreferredSize().getWidth()); //Ritar ut på fönster
	}
}
