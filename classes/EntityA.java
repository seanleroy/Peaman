package core.game.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityA {

	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds(final int width, final int height);
	
	public double getX();
	public double getY();
	
}
