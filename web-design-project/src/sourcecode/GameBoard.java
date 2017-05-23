import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;

// GameBoard CLASS
// The class extends JFrame which means that we get access to all the methods in the JFrame-class
public class GameBoard extends JFrame {

	public static final long serialVersionUID = 1L;

	// Sets GameBoard width and height
	public static int boardWidth = 1000;
	public static int boardHeight = 800;

	// Defines the center of the board
	public static int boardCenterX = boardWidth / 2;
	public static int boardCenterY = boardHeight / 2;

	// False if no key is held down
	public static boolean keyHeld = false;

	// These variables are used to differentiate between movement along the
	// x-axis and the y-axis
	public static boolean xKeyHeld = false;
	public static boolean yKeyHeld = false;

	// Gets the code of whichever key is held down
	public static int keyHeldCode;

	// These variables are used to differentiate between movement along the
	// x-axis and the y-axis
	public static int xKeyHeldCode = 0;
	public static int yKeyHeldCode = 0;
	
	// Counts the points
	public static int points = 0;

	// An ArrayList that stores all projectiles on screen
	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	// Creates a new Character object
	Character character = new Character();

	// CONSTRUCTOR
	public GameBoard() {

		// "this" refers to the GameBoard-class
		// Since the GameBoard-class extends JFrame it has access to all the
		// methods in the JFrame class, some of which are called upon here

		// Sets the size of the GameBoard window
		this.setSize(boardWidth, boardHeight);

		// Sets the title of the GameBoard window
		this.setTitle("Dungeons");

		// Makes it so that the program ends when the window is closed
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Puts the window in the middle of the screen
		this.setLocationRelativeTo(null);

		// KEYLISTENER
		// Anonymous inner class which adds a keylistener to the JFrame (the
		// GameBoard)
		addKeyListener(new KeyListener() {

			// Defines what will happen when the user presses a key
			public void keyPressed(KeyEvent e) {

				// If the W key is pressed down...
				if (e.getKeyCode() == 87) {

					// ...set keyHeld = true and get the key code
					keyHeldCode = e.getKeyCode();
					yKeyHeldCode = e.getKeyCode();

					keyHeld = true;
					yKeyHeld = true;

				}

				// If the S key is held down...
				else if (e.getKeyCode() == 83) {

					// ...set keyHeld = true and get the key code
					keyHeldCode = e.getKeyCode();
					yKeyHeldCode = e.getKeyCode();

					keyHeld = true;
					yKeyHeld = true;

				}

				// If the D key is held down...
				else if (e.getKeyCode() == 68) {

					// ...set keyHeld = true and get the key code
					keyHeldCode = e.getKeyCode();
					xKeyHeldCode = e.getKeyCode();

					keyHeld = true;
					xKeyHeld = true;

				}

				// If the A key is held down...
				else if (e.getKeyCode() == 65) {

					// ...set keyHeld = true and get the key code
					keyHeldCode = e.getKeyCode();
					xKeyHeldCode = e.getKeyCode();

					keyHeld = true;
					xKeyHeld = true;

				}

				// Shoot Up if the Up-arrow is pressed
				if (e.getKeyCode() == 38) {

					// Adds an object of the Projectile class to the projectiles
					// ArrayList
					// Uses the image of an upward projectile as the image
					// parameter
					projectiles
							.add(new Projectile(
									GameDrawingPanel.character.getXCenter(),
									GameDrawingPanel.character.getYCenter(),
									0,
									-5,
									new ImageIcon(
											"..//img//projectile//ProjectileUp.png")
											.getImage()));

					// Sets the current image of the character to the first
					// image in the standingStillList in the class
					// CharacterAnimations
					CharacterAnimations.currentImage = CharacterAnimations.standingStillList
							.get(0);

					// Sets keyHeld = true and get the key code
					keyHeldCode = e.getKeyCode();
					keyHeld = true;

				}

				// Shoot Down if the Down-arrow is pressed
				else if (e.getKeyCode() == 40) {

					// Adds an object of the Projectile class to the projectiles
					// ArrayList
					// Uses the image of a downward projectile as the image
					// parameter
					projectiles
							.add(new Projectile(
									GameDrawingPanel.character.getXCenter(),
									GameDrawingPanel.character.getYCenter(),
									0,
									5,
									new ImageIcon(
											"..//img//projectile//ProjectileDown.png")
											.getImage()));

					// Sets the current image of the character to the second
					// image in the standingStillList in the class
					// CharacterAnimations
					CharacterAnimations.currentImage = CharacterAnimations.standingStillList
							.get(1);

					// Sets keyHeld = true and get the key code
					keyHeldCode = e.getKeyCode();
					keyHeld = true;

				}

				// Shoot Right if the Right-arrow is pressed
				else if (e.getKeyCode() == 39) {

					// Adds an object of the Projectile class to the projectiles
					// ArrayList
					// Uses the image of a leftward projectile as the image
					// parameter
					projectiles
							.add(new Projectile(
									GameDrawingPanel.character.getXCenter(),
									GameDrawingPanel.character.getYCenter(),
									5,
									0,
									new ImageIcon(
											"..//img//projectile//ProjectileRight.png")
											.getImage()));

					// Sets the current image of the character to the third
					// image in the standingStillList in the class
					// CharacterAnimations
					CharacterAnimations.currentImage = CharacterAnimations.standingStillList
							.get(2);

					// Sets keyHeld = true and get the key code
					keyHeldCode = e.getKeyCode();
					keyHeld = true;

				}

				// Shoot Left if the Left-arrow is pressed
				else if (e.getKeyCode() == 37) {

					// Adds an object of the Projectile class to the projectiles
					// ArrayList
					// Uses the image of a rightward projectile as the image
					// parameter
					projectiles
							.add(new Projectile(
									GameDrawingPanel.character.getXCenter(),
									GameDrawingPanel.character.getYCenter(),
									-5,
									0,
									new ImageIcon(
											"..//img//projectile//ProjectileLeft.png")
											.getImage()));
					// Sets the current image of the character to the fourth
					// image in the standingStillList in the class
					// CharacterAnimations
					CharacterAnimations.currentImage = CharacterAnimations.standingStillList
							.get(3);

					// Sets keyHeld = true and get the key code
					keyHeldCode = e.getKeyCode();
					keyHeld = true;

				}
				
				//If the character is dead...
				if(!Character.alive){
					
					// ...and the enter key is pressed...
					if(e.getKeyCode() ==  KeyEvent.VK_ENTER){
						
						// ...the game will exit
						System.exit(0);
						
					}
					
				}

			}

			// Defines what will happen when the user releases a key
			public void keyReleased(KeyEvent e) {

				if (keyHeldCode == 37 || keyHeldCode == 38 || keyHeldCode == 39
						|| keyHeldCode == 40) {

					keyHeld = false;

				}

				if (e.getKeyCode() == 87 || e.getKeyCode() == 83) {

					yKeyHeld = false;

				}

				if (e.getKeyCode() == 65 || e.getKeyCode() == 68) {

					xKeyHeld = false;

				}

			}

			// Defines what will happen if the use types
			public void keyTyped(KeyEvent e) {

			}

		});// END OF KEYLISTENER

		// Creates a new object of the GameDrawingPanel-class
		GameDrawingPanel thePanel = new GameDrawingPanel();

		// Adds the object "thePanel" to the center of the GameBoard window
		this.add(thePanel, BorderLayout.CENTER);

		// Creates a new object of the ScheduledThreadPoolExecutor-class
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(
				5);

		// Makes the "executor" object call the run-method in the
		// RepaintTheBoard-class every 20 milliseconds with an initial delay of
		// 0 milliseconds
		executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0L, 20L,
				TimeUnit.MILLISECONDS);

		// Makes the GameBoard window visible
		this.setVisible(true);

	}// END OF CONSTRUCTOR

}// END OF GameBoard CLASS

// RepaintTheBoard CLASS
// This class implements Runnable which allows it to be called by an object of
// the ScheduledThreadPoolexecutor-class, in this case the object known as
// "executor"
class RepaintTheBoard implements Runnable {

	// Creates an undefined, local object of the GameBoard-class
	GameBoard theBoard;

	// CONTRUCTOR
	public RepaintTheBoard(GameBoard GameBoard) {

		// Defines the local GameBoard object, "thBoard", as the GameBoard
		// object passed in
		this.theBoard = GameBoard;

	}

	// END OF CONTRUCTOR

	// The run method
	// This is the method called by the ScheduledThreadPoolExecutor
	public void run() {

		// This calls the repaint-method, which is a predefined method from the
		// JFrame-class which calls the paint method from the JComponent-class
		theBoard.repaint();

	}

}// END OF RepaintTheBoard CLASS

// GameDrawingPanel CLASS
// This class extends the JComponent class which means that it will be able to
// use all the methods in the JComponent-class
// Makeing this class inherit the JComponent class will also allow us to use it
// like any other JComponent
@SuppressWarnings("serial")
class GameDrawingPanel extends JComponent {

	// Creates an ArrayList which will hold all the enemies
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	// Defines the local variables width and height as the width and height of
	// the GameBoard window
	int width = GameBoard.boardWidth;
	int height = GameBoard.boardHeight;

	// Creates a new object of the Character class
	static Character character = new Character();

	// CONSTRUCTOR
	public GameDrawingPanel() {

		// Calls the enemyPositionGenerator-method, the parameter representing
		// the number of enemies in each room
		enemyPositionGenerator(4);

	}

	// CONSTRUCTOR

	// The enemyPositionGenerator-method
	public static void enemyPositionGenerator(int num) {

		// This for-loop is what decides how many enemies will be in each room
		for (int i = 0; i < num; i++) {

			// Defines the random x-coordinate as a random number slightly less
			// than the rooms' maximum x-value
			int randomStartXPos = (int) (Math.random() * (Dungeons.width - 40) + 1);

			// Makes sure that the enemies don't get an x-coordinate which is
			// less than the rooms minimum x-value
			while (randomStartXPos < Dungeons.xCord + 40) {

				randomStartXPos = (int) (Math.random() * (Dungeons.width - 40) + 1);

			}

			// Defines the random y-coordinate as a random number slightly less
			// than the rooms' maximum y-value
			int randomStartYPos = (int) (Math.random() * (Dungeons.height - 40) + 1);

			// Makes sure that the enemies don't get an y-coordinate which is
			// less than the rooms' minimum y-value
			while (randomStartYPos < Dungeons.yCord + 40) {

				randomStartYPos = (int) (Math.random() * (Dungeons.height - 40) + 1);

			}

			// Adds the enemy to the "enemy"-ArrayList, passing in the randomly
			// generated x- and y-coordinates as the starting coordinates
			enemies.add(new Enemy("Enemy", randomStartXPos, randomStartYPos, 4,
					4, 40, 45, 5));

			// Defines the "enemies"-ArrayList in the Enemy class as the
			// "enemies"-ArrayList in this class
			Enemy.enemies = enemies;

		}

	}

	// The paint-method, which is predefined method from the JComponent-class
	public void paint(Graphics g) {

		// Creates an object of the Graphics2D-class
		Graphics2D graphicSettings = (Graphics2D) g;

		// Turns on AntiAliasing for everything painted using the
		// "graphicSettings"-object
		graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Creates a new object of the Dungeons-class
		Dungeons d = new Dungeons();

		// Sets the color used to black
		g.setColor(Color.BLACK);

		// Fills the entire screen with black color
		g.fillRect(0, 0, (int) GameBoard.boardWidth,
				(int) GameBoard.boardHeight);

		// Draws the dungeon which the character is currently in on the middle
		// of the screen
		// Chooses which dungeons to draw by checking the variable dungeonNumber
		// from the Dungeons-class
		switch (Dungeons.dungeonNumber) {

		// If "dungeonNumber" = 1...
		case ("1"):

			// ...draw the image dungeon1 from the Dungeons class
			graphicSettings.drawImage(
					new ImageIcon(Dungeons.dungeon1).getImage(), d.getXCord(),
					d.getYCord(), null);

			break;

		// If "dungeonNumber" = 2...
		case ("2"):

			// ...draw the image dungeon2 from the Dungeons class
			graphicSettings.drawImage(
					new ImageIcon(Dungeons.dungeon2).getImage(), d.getXCord(),
					d.getYCord(), null);

			break;

		// If "dungeonNumber" = 3...
		case ("3"):

			// ...draw the image dungeon3 from the Dungeons class
			graphicSettings.drawImage(
					new ImageIcon(Dungeons.dungeon3).getImage(), d.getXCord(),
					d.getYCord(), null);

			break;

		// If "dungeonNumber" = 4...
		case ("4"):

			// ...draw the image dungeon4 from the Dungeons class
			graphicSettings.drawImage(
					new ImageIcon(Dungeons.dungeon4).getImage(), d.getXCord(),
					d.getYCord(), null);

			break;

		// If "dungeonNumber" = 5...
		case ("5"):

			// ...draw the image dungeon5 from the Dungeons class
			graphicSettings.drawImage(
					new ImageIcon(Dungeons.dungeon5).getImage(), d.getXCord(),
					d.getYCord(), null);

			break;

		// If "dungeonNumber" = 6...
		case ("6"):

			// ...draw the image dungeon6 from the Dungeons class
			graphicSettings.drawImage(
					new ImageIcon(Dungeons.dungeon6).getImage(), d.getXCord(),
					d.getYCord(), null);

			break;

		// If "dungeonNumber" = 7...
		case ("7"):

			// ...draw the image dungeon7 from the Dungeons class
			graphicSettings.drawImage(
					new ImageIcon(Dungeons.dungeon7).getImage(), d.getXCord(),
					d.getYCord(), null);

			break;

		// If "dungeonNumber" = 8...
		case ("8"):

			// ...draw the image dungeon8 from the Dungeons class
			graphicSettings.drawImage(
					new ImageIcon(Dungeons.dungeon8).getImage(), d.getXCord(),
					d.getYCord(), null);

			break;

		// If "dungeonNumber" = 9...
		case ("9"):

			// ...draw the image dungeon9 from the Dungeons class
			graphicSettings.drawImage(
					new ImageIcon(Dungeons.dungeon9).getImage(), d.getXCord(),
					d.getYCord(), null);

			break;

		// If "dungeonNumber" = 10...
		case ("10"):

			// ...draw the image dungeon10 from the Dungeons class
			graphicSettings.drawImage(
					new ImageIcon(Dungeons.dungeon10).getImage(), d.getXCord(),
					d.getYCord(), null);

			break;

		}

		//Paints the defeat screen
		if(!Character.alive){
			
			graphicSettings.drawImage(new ImageIcon("..//img//rooms//Defeat.png").getImage(), 0, 0, null);
			
			g.setColor(Color.WHITE);
			g.drawString("Press ENTER to exit", 435, 600);
			
		}
		
		// Sets the color to white
		g.setColor(Color.WHITE);

		// Draws the points-counter on the upper left hand side of the screen
		graphicSettings.drawString("Points: " + GameBoard.points, 20, 35);

		// Types "Health: " in the upper right hand corner of the screen
		graphicSettings.drawString("Health: ", GameBoard.boardWidth - 170, 35);

		// Sets the color to green, yellow or red depending on the amount of health left
		
		if(character.getHealth() >= 66){
			
			g.setColor(Color.GREEN);
			
		}
		
		else if(character.getHealth() >= 33 && character.getHealth() < 66){
			
			g.setColor(Color.YELLOW);
			
		}
		
		else if(character.getHealth() >= 0 && character.getHealth() < 33){
			
			g.setColor(Color.RED);
			
		}

		// Draws the health bar next to the word "Health: "
		// The health bar is a rectangle with a width corresponding to the
		// amount of health the character has left
		graphicSettings.fillRect(GameBoard.boardWidth - 120, 20,
				character.getHealth(), 20);

		// Sets the color to white
		g.setColor(Color.WHITE);

		// Draws the % health the player has left in the middle of the health
		// bar
		graphicSettings.drawString(character.getHealth() + "%",
				GameBoard.boardWidth - 85, 35);

		// This is and enhanced for-loop which cycles through the selected
		// ArrayList (the left element) and assigns the value of the current
		// element in the ArrayList to the temporary object (the right element)
		for (Enemy enemy : enemies) {

			// Checks if the enemy currently checked is alive
			if (enemy.alive) {

				// Calls the move-method for the current enemy
				enemy.move(character, GameBoard.projectiles);

				// Draws the current enemy
				graphicSettings.drawImage(enemy.getEnemy(),
						(int) enemy.getULeftX(), (int) enemy.getULeftY(), null);

			}

		}

		// Creates the doors
		new Doors();

		// Moves the character to the right if the D key is held
		if (GameBoard.xKeyHeld == true && GameBoard.keyHeldCode == 68) {

			// Checks if the character hits the right wall and sets keyHeld
			// =
			// false if it does
			if (character.getXCenter() + character.getCharWidth() >= d
					.getHitboxXCord() + d.getHitboxWidth()) {

				GameBoard.xKeyHeld = false;

			}

			// If the character doesn't hit the wall, the characters's
			// velocity
			// and image is set
			else {

				character.setXVelocity(5);

				// Sets the character's image to the image, from one of the
				// animation lists from the CharacterAnimations-class, which
				// corresponds to the number of the variable counter from
				// the
				// Counter-class
				// Since this variable changes over time, so will the image
				CharacterAnimations.currentImage = CharacterAnimations.movingRightList
						.get(Counter.counter);

			}

			// Increases the character's position depending on the
			// characters'
			// velocity
			character.increaseXPos(character.getXVelocity());

		}

		// Moves the character to the left if the A key is held down
		else if (GameBoard.xKeyHeld == true && GameBoard.keyHeldCode == 65) {

			// Checks if the character hits the left wall and sets keyHeld =
			// false if it does
			if (character.getXCenter() <= d.getHitboxXCord()) {

				GameBoard.xKeyHeld = false;

			}

			// If the character doesn't hit the wall, the characters's
			// velocity
			// and image is set
			else {

				character.setXVelocity(-5);

				// Sets the character's image to the image, from one of the
				// animation lists from the CharacterAnimations-class, which
				// corresponds to the number of the variable counter from
				// the
				// Counter-class
				// Since this variable changes over time, so will the image
				CharacterAnimations.currentImage = CharacterAnimations.movingLeftList
						.get(Counter.counter);

			}

			// Increases the character's position depending on the
			// characters'
			// velocity
			character.increaseXPos(character.getXVelocity());

		}

		// Moves the character upward if the W key is held down
		else if (GameBoard.yKeyHeld == true && GameBoard.keyHeldCode == 87) {

			// Checks if the character hits the upper wall and sets keyHeld
			// =
			// false if it does
			if (character.getYCenter() <= d.getHitboxYCord()) {

				GameBoard.yKeyHeld = false;

			}

			// If the character doesn't hit the wall, the characters's
			// velocity
			// and image is set
			else {

				character.setYVelocity(-5);

				// Sets the character's image to the image, from one of the
				// animation lists from the CharacterAnimations-class, which
				// corresponds to the number of the variable counter from
				// the
				// Counter-class
				// Since this variable changes over time, so will the image
				CharacterAnimations.currentImage = CharacterAnimations.movingUpList
						.get(Counter.counter);

			}

			// Increases the character's position depending on the
			// characters'
			// velocity
			character.increaseYPos(character.getYVelocity());

		}

		// Moves the character downward if the S key is held down
		else if (GameBoard.yKeyHeld == true && GameBoard.keyHeldCode == 83) {

			// Checks if the character hits the lower wall and sets keyHeld
			// =
			// false if it does
			if (character.getYCenter() + character.getCharHeight() >= d
					.getHitboxYCord() + d.getHitboxHeight()) {

				GameBoard.yKeyHeld = false;

			}

			// If the character doesn't hit the wall, the characters's
			// velocity
			// and image is set
			else {

				character.setYVelocity(5);

				// Sets the character's image to the image, from one of the
				// animation lists from the CharacterAnimations-class, which
				// corresponds to the number of the variable counter from
				// the
				// Counter-class
				// Since this variable changes over time, so will the image
				CharacterAnimations.currentImage = CharacterAnimations.movingDownList
						.get(Counter.counter);

			}

			// Increases the character's position depending on the
			// characters'
			// velocity
			character.increaseYPos(character.getYVelocity());

		}

		// If yKeyHeld = false...
		else if (GameBoard.yKeyHeld == false) {

			// ...set y velocity to 0
			character.setYVelocity(0);

		}

		// If xKeyHeld = false...
		else if (GameBoard.xKeyHeld == false) {

			// ...set x velocity to 0
			character.setXVelocity(0);

		}

		// Calls the move method from the Character-class by using the
		// "character"-object
		character.move();

		// Uses an enhanced for-loop to cycle through the
		// "projectiles"-ArrayList
		for (Projectile projectile : GameBoard.projectiles) {

			// Calls the move method from the Projectiles-method
			projectile.move();

			// Draws the projectiles if the variable onScreen (from the
			// Projectiles-class) = true
			if (projectile.onScreen) {

				// Draws the projectile
				graphicSettings.drawImage(projectile.getProjectile(),
						(int) projectile.getXCenter(),
						(int) projectile.getYCenter(), null);

			}

		}

		// If the character is alive...
		if (Character.alive) {

			// ...draws the character
			graphicSettings.drawImage(CharacterAnimations.currentImage,
					(int) character.getULeftXPos(),
					(int) character.getULeftYPos(), null);

		}

	}

}// END OF GameDrawingPanel CLASS