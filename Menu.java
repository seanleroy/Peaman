package core.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

	public Rectangle playButton = new Rectangle(PeaMan.WIDTH / 2 + 120, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(PeaMan.WIDTH / 2 + 120, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(PeaMan.WIDTH / 2 + 120, 350, 100, 50);
	
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		Font ariel = new Font("ariel", Font.BOLD, 50);
		g.setFont(ariel);
		g.setColor(Color.white);
		g.drawString("PeaMan", PeaMan.WIDTH / 2, 100);
		
		Font fnt1 = new Font("ariel", Font.BOLD, 30); 
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 19, playButton.y + 35);
		g2.draw(playButton);
		g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
		g2.draw(helpButton);
		g.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
		g2.draw(quitButton);
	}
	
}
