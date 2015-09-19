package core.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import core.game.classes.EntityA;
import core.game.classes.EntityB;
import core.game.libs.Animation;

public class Enemy extends GameObject implements EntityB {

	private Random r = new Random();
	
	private Textures text;
	
	private boolean isDestroyed = false;
	
	private int speed = r.nextInt(3) + 1;
	
	private Animation anim;
	
	private PeaMan game;
	private Controller c;
	
	public Enemy(final double x, final double y, final Textures text, final Controller c, final PeaMan game) {
		super(x, y);
		this.text = text;
		anim = new Animation(5, text.enemy[0], text.enemy[1], text.enemy[2]);
		this.c = c;
		this.game = game;
	}
	
	public void tick() {
		x -= speed;

		if ( x < -32 ) {
			speed = r.nextInt(3) + 1; 
			x = PeaMan.WIDTH * PeaMan.SCALE;
			y = r.nextInt(PeaMan.HEIGHT * PeaMan.SCALE);
		}
		
		for (int i = 0; i < game.ea.size(); i++) {
			EntityA tempEnt = game.ea.get(i);
			if ( Physics.Collision(this, tempEnt) ) {
				c.removeEntityB(this);
				game.setEnemyKilled(game.getEnemyKilled() + 1);
			}
			
		}
		
		
		
		anim.runAnimation();
		
	}
	
	public void render(Graphics g ) {
		anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds() {
		return new Rectangle( (int) x, (int) y, 32, 32 );
	}
	
	public double getX() {
		return x;
	}
	
	public void setX( final double x ) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
}
