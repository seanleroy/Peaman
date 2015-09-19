package core.game;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet( final BufferedImage image ) {
		this.image = image;
	}
	
	public BufferedImage grabImage(final int col, final int row, final int width, final int height) {
		// grab the image;
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}
	
}
