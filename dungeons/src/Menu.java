import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import javax.swing.*;

//Menu CLASS
//The class extends JFrame which means that we get access to all the methods in the JFrame-class
public class Menu extends JFrame {

	//For all future reference: This variable serves no purpose whatsoever. The only reason it is in here is so that an error won't appear
	private static final long serialVersionUID = 1L;

	//Sets the menu's width and height
	public static int mainWidth = 350;
	public static int mainHeight = 350;

	boolean startHover = false;
	boolean quitHover = false;

	// Variables used to paint the menu
	Image image;
	Graphics graphics;

	// Defines the menu buttons
	Rectangle startButton = new Rectangle(mainWidth/2 - 50, mainHeight/2 - 20, 100, 25);
	Rectangle quitButton = new Rectangle(mainWidth/2 - 50, mainHeight/2 + 20, 100, 25);

	// Creates a new object of the class Dimension and defines it as the width and height of the menu
	Dimension screenSize = new Dimension(mainWidth, mainHeight);

	// CONSTRUCTOR
	public Menu() {

		//Defines the different properties of the window
		this.setTitle("Dungeons");
		this.setSize(screenSize);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		
		//These two lines adds a MouseListener and a MouseMotionListener to our menu-window
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseHandler());
		
		this.setLocationRelativeTo(null);

	}
	//END OF CONSTRUCTOR

	//Main-method
	public static void main(String[] args) {

		//Creates a new menu
		new Menu();

	}
	
	//Paints the menu
	public void paint(Graphics g) {

		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);

	}

	//Draws the text and the buttons
	public void draw(Graphics g) {

		// Draws the string "Dungeon" in arial, red color, bold font and size 35
		g.setFont(new Font("Arial", Font.BOLD, 35));
		g.setColor(Color.RED);
		g.drawString("Dungeons", mainWidth - 260, 100);

		//Changes the color depending on if the pointer is hovering over the start-button button
		if (!startHover) {

			g.setColor(Color.RED);

		}

		else {

			g.setColor(Color.GREEN);

		}

		// Draws the start-button
		g.fillRect(startButton.x, startButton.y, startButton.width,
				startButton.height);

		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(Color.BLACK);
		g.drawString("Start Game", startButton.x + 20, startButton.y + 17);

		//Changes the color depending on if the pointer is hovering over the quit-button button
		if (!quitHover) {

			g.setColor(Color.RED);

		}

		else {

			g.setColor(Color.GREEN);

		}

		// Draws the quit-button
		g.fillRect(quitButton.x, quitButton.y, quitButton.width,
				quitButton.height);
		g.setColor(Color.BLACK);
		g.drawString("Quit Game", quitButton.x + 20, quitButton.y + 17);

		//Calls the paint-method
		repaint();
	}

	//Defines what will happen when the user uses the mouse
	public class MouseHandler extends MouseAdapter {

		//Checks if the pointer is hovering over a button and changes the hover-variables if it does
		public void mouseMoved(MouseEvent e) {

			//Gets the mouse's coordinates
			int mx = e.getX();
			int my = e.getY();

			//If the pointer is within the coordinates of the start-button, the variable startHover = true
			if (mx > startButton.x && mx < startButton.x + startButton.width
					&& my > startButton.y
					&& my < startButton.y + startButton.height) {

				startHover = true;
			}

			else {

				startHover = false;

			}

			//If the pointer is within the coordinates of the quit-button, the variable quitHover = true
			if (mx > quitButton.x && mx < quitButton.x + quitButton.width
					&& my > quitButton.y
					&& my < quitButton.y + quitButton.height) {

				quitHover = true;

			}

			else {

				quitHover = false;

			}

		}

		//Defines what will happen if the user presses the mouse
		public void mousePressed(MouseEvent e) {

			//Gets the mouse's coordinates
			int mx = e.getX();
			int my = e.getY();

			//If the user presses the start-button, a new game will start
			if (mx > startButton.x && mx < startButton.x + startButton.width
					&& my > startButton.y
					&& my < startButton.y + startButton.height) {

				// Fills the lists in the CharacterAnimations-class
				CharacterAnimations.fillLists();

				// Creates the GameBoard by creating an object of the GameBoard-class
				new GameBoard();

				// Creates a new object of the class Timer, which is a predefines class
				// from the Java.library java.util
				Timer timer = new Timer();

				// Makes the timer call the run-method from the Counter-class and makes
				// it repeat the method every 175 milliseconds with an initial delay of
				// 0 milliseconds
				timer.schedule(new Counter(), 0, 175);
				
				setVisible(false);

			}

			//If the user presses the quit-button, the game will exit
			if (mx > quitButton.x && mx < quitButton.x + quitButton.width
					&& my > quitButton.y
					&& my < quitButton.y + quitButton.height) {

				System.exit(0);

			}

		}

	}

}