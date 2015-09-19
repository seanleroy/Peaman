package core.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import core.game.classes.EntityA;
import core.game.libs.Animation;

public class Bullet extends GameObject implements EntityA {

	private BufferedImage image;
	
	private Textures text;
	
	private PeaMan p;
	
	private Animation anim;
	
	public Bullet(final double x, final double y, final Textures text , final PeaMan p) {
		super(x, y);
		this.text = text;
		this.p = p;
		
		anim = new Animation(5, text.bullet[0], text.bullet[1], text.bullet[2]);
	}
	
	public void tick() {
		x += 10;
		
		anim.runAnimation();
	}
	
	public void render( Graphics g ) {
		anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds() {
		return new Rectangle( (int) x, (int) y, 32, 32 );
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
