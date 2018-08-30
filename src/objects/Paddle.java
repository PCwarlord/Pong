package objects;

import java.awt.Color;
import java.awt.Graphics;
/**
 * This class represents the game paddle
 * @author Sérgio
 *
 */
public class Paddle {
	
	/*
	 * x and y correspond to the starting position of the paddle
	 * in the beggining of the game.
	 * xSpeed was defined in the Game class but after some thought
	 * I concluded that it should be defined here.
	 * 
	 */
	private int x, y;
	private final int GAME_WIDTH, WIDTH = 100, HEIGTH = 10, X_SPEED = -6;
	
	public Paddle (int x, int y, int gameWidth) {
		this.x = x;
		this.y = y;
		GAME_WIDTH = gameWidth;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		/*
		 * The arithmetic operations guarantee that the paddle is in the center
		 * of the screen taking to account the way objects are drawn when using
		 * this function
		 */
		g.fillRect(x - WIDTH / 2, y + HEIGTH / 2, WIDTH, HEIGTH);
	}

	public void moveLeft() {
		x += X_SPEED;
	}

	public void moveRight() {
		x -= X_SPEED;
	}
	
	public boolean canMoveLeft() {
		return x - WIDTH / 2 > 0; 
	}
	
	public boolean canMoveRight() {
		return x + WIDTH / 2 < GAME_WIDTH;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeigth() {
		return HEIGTH;
	}
}
