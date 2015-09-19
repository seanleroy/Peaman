package core.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private PeaMan peaman;
	
	public KeyInput( final PeaMan peaman) {
		// used as we want to call the key input within the game class.
		// can used key inputs andytime this way, not just when player on screen.
		this.peaman = peaman;
	}

	/**
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		peaman.keyPressed(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		peaman.keyReleased(e);
	}
}
