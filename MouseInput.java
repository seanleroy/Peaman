package core.game;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		/**
		 * public Rectangle playButton = new Rectangle(PeaMan.WIDTH / 2 + 120, 150, 100, 50);
		public Rectangle helpButton = new Rectangle(PeaMan.WIDTH / 2 + 120, 250, 100, 50);
		public Rectangle quitButton = new Rectangle(PeaMan.WIDTH / 2 + 120, 350, 100, 50);
		 */
		
		// Play Game
		if ( mx >= PeaMan.WIDTH / 2 && mx <= PeaMan.WIDTH / 2 + 220 ) {
			if ( my >= 150 && my <= 200 ) {
				// pressed play button
				PeaMan.state = PeaMan.STATE.GAME;
			}
		}
		
		// Help
		if ( mx >= PeaMan.WIDTH / 2 && mx <= PeaMan.WIDTH / 2 + 220 ) {
			if ( my >= 150 && my <= 200 ) {
				// pressed play button
				PeaMan.state = PeaMan.STATE.GAME;
			}
		}
		
		//Quit
		if ( mx >= PeaMan.WIDTH / 2 && mx <= PeaMan.WIDTH / 2 + 220 ) {
			if ( my >= 350 && my <= 400 ) {
				// pressed play button
				System.exit(1);
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
