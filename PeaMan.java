package core.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import core.game.classes.EntityA;
import core.game.classes.EntityB;



public class PeaMan extends Canvas implements Runnable {

	private static final long serialVersionID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "PeaMan"; 
	
	private JFrame frame;
	
	private boolean running = false;
	
	private Thread thread;
	
	// buffer the whole image
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spritesheet = null;
	private BufferedImage background = null;
	
	private Player p;
	private Controller c;
	private Textures text;
	
	private int enemyCount = 3;
	private int enemyKilled = 0;
	
	private boolean isShooting = false;

	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	private Menu menu;
	
	public static int health = 200;
	
	/**
	 * Should create separate class for enums.
	 * 
	 * @author Sean
	 *
	 */
	public static enum STATE {
		MENU,
		GAME
	}
	
	public static STATE state = STATE.MENU;
	
	/**
	 * A simple constructor.
	 */
	public PeaMan() {
		
		setPreferredSize(new Dimension( WIDTH * SCALE, HEIGHT * SCALE ));
		setMaximumSize(new Dimension( WIDTH * SCALE, HEIGHT * SCALE ));
		setMinimumSize(new Dimension( WIDTH * SCALE, HEIGHT * SCALE ));
		
		frame = new JFrame(TITLE);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	public void init() {
		
		ImageLoader loader = new ImageLoader();
		try {
			// load the file
			spritesheet = loader.loadImage("/spritesheet.png");
			background = loader.loadImage("/background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// must come before player
		addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		// must be before player to know what image it wants
		text = new Textures(this);
		
		c = new Controller(text, this);
		// must place player p below loading of spritesheet
		p = new Player(200, 200, text, this, c);
		menu = new Menu();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		c.createEnemy(enemyCount);
		
	}
	
	/**
	 * Start the game.
	 */
	private synchronized void start() {
		
		if(running) {
			return;
		}
		
		// true when game starts
		running = true;
		new Thread(this).start();
	}
	
	/**
	 * Stop the game
	 */
	private synchronized void stop() {
			running = false;
	}	

	/**
	 * Run method with a solid game loop.
	 */
	@Override
	public void run() {
		// requestfocus means you don't have to click the frame in order
		// to move the object.
		requestFocus();
		
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if ( delta >= 1 ) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if ( System.currentTimeMillis() - timer > 1000 ) {
				timer += 1000;
				System.out.println( updates + " Ticks, FPS " + frames );
				updates = 0;
				frames = 0;
			}
		}
		stop();
		
	}

	private void tick() {
		
		if (state == STATE.GAME) {
			p.tick();
			c.tick();
		}

		if( enemyKilled >= enemyCount ) {
			enemyCount += 2;
			enemyKilled = 0;
			c.createEnemy(enemyCount);
		}
	}
	
	private void render() {
		
		// "this" refers to the canvas class
		BufferStrategy bs = this.getBufferStrategy();
		if ( bs == null ) {
			// triple buffer images, do not use more
			createBufferStrategy(3);
			return;
		}
		
		// creates context for drawing buffers
		Graphics g = bs.getDrawGraphics();
		//////////////////////////////////////
		
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		// place inside state if statement if want different background
		// for start menu
		g.drawImage(background, 0, 0, null);
		
		if ( state == STATE.GAME ) {
			p.render(g);
			c.render(g);
			
			g.setColor(Color.green);
			g.fillRect(5,  5,  health, 25);
			
			g.setColor(Color.white);
			g.drawRect(5,  5,  200,  25);
			
		} else if ( state == STATE.MENU ) {
			menu.render(g);
		}

		// dispose of context and releases system resources
		g.dispose();
		// makes next available buffer visible
		bs.show();
		
	}
	
	/**
	 * Key events of when keys pressed.
	 * 
	 * @param e the key event
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();    
		
		if ( state == STATE.GAME ) {
			if ( key == KeyEvent.VK_RIGHT){ 
				p.setVelX(5);
			} else if (key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			} else if (key == KeyEvent.VK_UP) {
				p.setVelY(-5);
			} else if (key == KeyEvent.VK_DOWN) {
				p.setVelY(5);
			} else if ( key == KeyEvent.VK_SPACE && !isShooting ) {
				isShooting = true;
				c.addEntityA(new Bullet(p.getX() + 38, p.getY() + 17, text, this));
			}
		}
	} 
	
	/**
	 * Key events of when keys released.
	 * 
	 * @param e the key event
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		// if you don't put 0 when released object keeps moving
		if ( key == KeyEvent.VK_RIGHT){ 
			p.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_UP) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		} else if( key == KeyEvent.VK_SPACE ) {
			isShooting = false;
		}
	}
	
	public static void main (String[] args) {
		
		new PeaMan().start();
		
	}
	
	/**
	 * Return the spritesheet used in the game.
	 * 
	 * @return spritesheet the sprite sheet used.
	 */
	public BufferedImage getSpriteSheet() {
		return spritesheet;
	}
	
	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(final int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void setEnemyKilled(final int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}
	
}
