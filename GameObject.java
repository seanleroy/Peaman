package core.game;

import java.awt.Rectangle;

public class GameObject {

	public double x, y;
	
	public GameObject(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds(final int width, final int height) {
		return new Rectangle( (int) x, (int) y, width, height );
	}
}
