package core.game;

import java.awt.image.BufferedImage;

public class Textures {

	private SpriteSheet spritesheet = null;
	
	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage[] bullet = new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[3];
	
	public Textures( final PeaMan peaman) {
		spritesheet = new SpriteSheet(peaman.getSpriteSheet());
		
		getTextures();
	}

	private void getTextures() {
		player[0] = spritesheet.grabImage(1, 1, 64, 64);
		player[1] = spritesheet.grabImage(1, 3, 64, 64);
		player[2] = spritesheet.grabImage(1, 5, 64, 64);
		
		bullet[0] = spritesheet.grabImage(3, 1, 32, 32);
		bullet[1] = spritesheet.grabImage(3, 2, 32, 32);
		bullet[2] = spritesheet.grabImage(3, 3, 32, 32);
		
		enemy[0] = spritesheet.grabImage(4, 1, 32, 32);
		enemy[1] = spritesheet.grabImage(4, 2, 32, 32);
		enemy[2] = spritesheet.grabImage(4, 3, 32, 32);
		
	}
	
}
