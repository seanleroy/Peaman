package core.game;

import java.util.LinkedList;

import core.game.classes.EntityA;
import core.game.classes.EntityB;

public class Physics {

	public static boolean Collision(final EntityA entA, final EntityB entB) {
		
		
		if ( entA.getBounds(64, 64).intersects(entB.getBounds() ) ) {
			return true;
		}
		
		return false;
		
	}
	
	public static boolean Collision(final EntityB entB, final EntityA entA) {
		
		if ( entB.getBounds().intersects(entA.getBounds(64, 64) ) ) {
			return true;
		}
		
		return false;
		
	}
	
}
