package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objects.Paddle;
/**
 * This class listens for the keyboard inputs and performs
 * the necessary actions
 * @author Sérgio
 */
public class KeyInput implements KeyListener {
	
	private Paddle player;
	/**
	 * Creates the keyListener and receives the 
	 * player which will play the game
	 * @param player The player of the game
	 */
	public KeyInput(Paddle player) {
		this.player = player;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT && player.canMoveLeft()) player.moveLeft(); 
		if(key == KeyEvent.VK_RIGHT && player.canMoveRight()) player.moveRight();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not required for now
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not required for now
	}



}
