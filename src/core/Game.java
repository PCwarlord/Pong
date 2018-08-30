package core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import objects.Ball;
import objects.Paddle;

/**
 * Represents the game of Pong. Rendering and the update of game logic
 * are done here.
 * @author Sérgio
 */
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;

	private final int WIDTH = 600, HEIGTH = 600;
	
	private Thread thread;
	private boolean isRunning;
	
	private Paddle player1;
	private Ball gameBall;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game () {
		this.setPreferredSize(new Dimension(WIDTH, HEIGTH));
		new Window("Pong", this, WIDTH, HEIGTH);
	}
	/**
	 * Starts the game
	 */
	public void start() {
		this.setFocusable(true);
		player1 = new Paddle(WIDTH / 2, HEIGTH - HEIGTH / 8, WIDTH);
		gameBall = new Ball(HEIGTH / 2, WIDTH / 2, WIDTH, HEIGTH);
		this.addKeyListener(new KeyInput(player1));
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		System.exit(0);
	}

	@Override
	/*
	 * Game loop taken from : http://www.koonsolo.com/news/dewitters-gameloop/
	 * Everything used is explained there way better then i could here
	 * EDIT: interpolation is not used because the game looks fine without it
	 * at least on my end.
	 */
	public void run() {
		
		int ticksPerSec = 25, skipTicks = 1000 / ticksPerSec, 
			maxFrameskip = 5, loops;

	    long next_game_tick = System.currentTimeMillis();
	    //double interpolation;

	    while(isRunning) {

	        loops = 0;
	        while(System.currentTimeMillis() > next_game_tick && loops < maxFrameskip) {
	            update();
	            next_game_tick += skipTicks;
	            loops++;
	        }

	        /*interpolation = ((double)System.currentTimeMillis() + skipTicks - next_game_tick)
	                        / ((double)skipTicks);
	        render(interpolation);*/
	        render();
	    }
		
		stop();
	}
	
	/**
	 * Draws game objects onto screen
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			bs = this.getBufferStrategy();
			//Some tutorials use "return;" but i think this will provide the same results
			this.createBufferStrategy(3);
		}
		
		Graphics g = bs.getDrawGraphics();
		//Set the background color
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGTH);
		
		player1.draw(g);
		gameBall.draw(g);
		
		g.dispose();
		bs.show();
	}
	
	/**
	 * Updates game logic and advances gameplay
	 */
	private void update() {
		gameBall.update(player1);
		
		if (!gameBall.isStillInGame()) {
			isRunning = false;
		}
	}
}
