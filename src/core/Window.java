package core;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 * Represents the window of the game and contains
 * the game panel class "Game"
 * @author Sérgio
 */
public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Initializes the game window with the given title, width and height.
	 * A game instance is also given which allows the window to add it as
	 * its child
	 * @param title The title of the window
	 * @param game The game which will have this window as its parent
	 * @param width The width of the window (in pixels)
	 * @param height The height of the window (in pixels)
	 */
	public Window (String title, Game game, int width, int height) {
		super(title);
		
		/*
		 * For some reason if i use this method of creating the window
		 * (with the canvas having a preferedSize) the colisions of the paddle
		 * with the sides of the window work fine (before the paddle was going
		 * way too much to the right).
		 * I used the solution from (long link): 
		 * https://www.linuxquestions.org/questions/programming-9/java-how-to-get-a-jpanel-of-a-specific-size-to-fit-perfectly-into-a-jframe-4175433413/
		 */
		
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(game);
		pack(); //Uses the preferedSize method in Game class to size the window 
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		game.start();
	}

}
