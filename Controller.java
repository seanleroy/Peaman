package core.game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import core.game.classes.EntityA;
import core.game.classes.EntityB;

public class Controller {

	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();

	private EntityA entA;
	private EntityB entB;
	
	private Textures text;
	
	private PeaMan game;
	
	Random r = new Random();
	
	public Controller(final Textures text, final PeaMan game ) {
		this.text = text;
		this.game = game;
	}
	
	public void createEnemy(final int enemyCount) {
		for ( int i = 0; i < enemyCount; i++) {
			addEntityB(new Enemy(650, r.nextInt(PeaMan.HEIGHT + PeaMan.SCALE), text, this, game));
		}
	}
	
	public void tick() {

		// A class
		for ( int i = 0; i < ea.size(); i++ ) {
			entA = ea.get(i);
			entA.tick();
		}
		// B class
		for ( int i = 0; i < eb.size(); i++ ) {
			entB = eb.get(i);
			entB.tick();
		}
	}
	
	public void render(Graphics g) {

		for ( int i = 0; i < ea.size(); i++ ) {
			entA = ea.get(i);
			entA.render(g);
		}
		
		for ( int i = 0; i < eb.size(); i++ ) {
			entB = eb.get(i);
			entB.render(g);
		}
	}
	
	public void addEntityA (final EntityA block) {
		ea.add(block);
	}
	
	public void removeEntityA (final EntityA block ) {
		ea.remove(block);
	}
	
	public void addEntityB (final EntityB block) {
		eb.add(block);
	}
	
	public void removeEntityB (final EntityB block ) {
		eb.remove(block);
	}
	
	public LinkedList<EntityA> getEntityA() {
		return ea;
	}
	
	public LinkedList<EntityB> getEntityB() {
		return eb;
	}
	

}
