package core.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import core.game.classes.EntityA;
import core.game.classes.EntityB;
import core.game.libs.Animation;

public class Player extends GameObject implements EntityA {

	private double velX = 0;
	private double velY = 0;
	
	private BufferedImage player;
	
	private Textures text;
	
	private Animation anim;
	private PeaMan game;
	
	private Controller c;
	
	/**
	 *  A simple Constructor
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Player(final double x, final double y, final Textures text, final PeaMan game, final Controller c ) {
		// don't need to initialize x and y variables
		super(x, y);	
		this.text = text;
		this.game = game;
		this.c = c;

		anim = new Animation(5, text.player[0], text.player[1], text.player[2] );
	}
	
	/**
	 * Update method.
	 */
	public void tick() {
		x += velX;
		y += velY;
		
		if ( x <= -13 ) {
			x = -13;
		} else if (x >= 640 - 40) {
			x = 640 - 40;
		} else if (y <= -11) {
			y = -11;
		} else if ( y >= 480 - 50) {
			y = 480 - 50;
		}
		
		for( int i = 0; i < game.eb.size(); i++ ) {
			EntityB tempEnt = game.eb.get(i);
			if ( Physics.Collision(this,  tempEnt)) {
				c.removeEntityB(tempEnt);
				PeaMan.health -= 10;
				game.setEnemyKilled(game.getEnemyKilled() + 1);
			}
		}
		
		anim.runAnimation();
		
	}
	
	/**
	 * Render method to draw the image.
	 * 
	 * @param g the image to draw.
	 */
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
	}
	
	public double getX() {
		return x; 
	}
	
	public double getY() {
		return y; 
	}
	
	public void setX(final double x) {
		this.x = x;
	}
	
	public void setY(final double y) {
		this.y = y;
	}
	
	public void setVelX(final double velX) {
		this.velX = velX;
	}
	
	public void setVelY(final double velY) {
		this.velY = velY;
	}

}
