package objects;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	private int x, y, xSpeed, ySpeed = 6;
	private final int WIDTH = 12, HEIGTH = 12, GAME_WIDTH, GAME_HEIGTH;
	private boolean stillInGame;

	public Ball(int x, int y, int gameWidth, int gameHeigth) {
		this.x = x;
		this.y = y;
		GAME_WIDTH = gameWidth;
		GAME_HEIGTH = gameHeigth;
		stillInGame = true;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x - WIDTH / 2, y + HEIGTH / 2, WIDTH, HEIGTH);
	}

	public void update(Paddle player1) {
		int newPositionYUp = y - ySpeed - HEIGTH / 2;
		int newPositionYDown = y - ySpeed + HEIGTH / 2;
		int newPositionXLeft = x + xSpeed - WIDTH / 2;
		int newPositionXRight = x + xSpeed + WIDTH / 2;
		int paddleXPos = player1.getX();
		int paddleYPos = player1.getY();
		int paddleWidth = player1.getWidth();
		int paddleHeigth = player1.getHeigth();
		
		if (newPositionYUp <= 0) {
			ySpeed = -ySpeed;
		}
		
		if (newPositionXLeft <= 0 || newPositionXRight >= GAME_WIDTH) {
			xSpeed = -xSpeed;
		}
		
		if (newPositionYDown >= paddleYPos - paddleHeigth / 2 &&
			newPositionXRight >= paddleXPos - paddleWidth / 2 &&
			newPositionXLeft <= paddleXPos + paddleWidth / 2) {
			ySpeed = -ySpeed;
			
			if (newPositionXRight >= paddleXPos - paddleWidth / 2 && 
				newPositionXRight <= paddleXPos - paddleWidth / 2 + paddleWidth / 3) {
				xSpeed = -1;
			} else if (newPositionXLeft <= paddleXPos + paddleWidth / 2 && 
					newPositionXLeft >= paddleXPos + paddleWidth / 2 - paddleWidth / 3) {
					xSpeed = 1;
			} else {
				xSpeed = 0;
			}
		}
		
		if (newPositionYDown > GAME_HEIGTH) {
			stillInGame = false;
		}
		
		y -= ySpeed;
		x += xSpeed;
		
	}

	public boolean isStillInGame() {
		return stillInGame;
	}

	
}
