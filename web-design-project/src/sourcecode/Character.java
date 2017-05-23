import java.awt.*;

//Character CLASS
public class Character {

	// The x- and t-velocity variables
	private double xVelocity = 0, yVelocity = 0;

	// Defines the local variables GBWidth and GBHeight as the width and height
	// of the GameBoard
	int GBWidth = GameBoard.boardWidth;
	int GBHeight = GameBoard.boardHeight;

	// Defines the center-x and -y as the middle of the GameBoard window
	private double centerX = GBWidth / 2, centerY = GBHeight / 2;

	// Sets the width and height of the character
	private double charWidth = 27, charHeight = 30;

	// Sets the characte's starting uLeft-X and -Y positions
	private double uLeftXPos = getXCenter() - charWidth / 2;
	private double uLeftYPos = getYCenter() - charHeight / 2;

	// Sets the character's health
	private int health = 100;

	// Defines alive as true
	static boolean alive = true;

	// Get-methods
	public double getXCenter() {

		return centerX;

	}

	public double getYCenter() {

		return centerY;

	}

	public double getULeftXPos() {

		return uLeftXPos;

	}

	public double getULeftYPos() {

		return uLeftYPos;

	}

	public double getCharWidth() {

		return charWidth;

	}

	public double getCharHeight() {

		return charHeight;

	}

	public double getXVelocity() {

		return xVelocity;

	}

	public double getYVelocity() {

		return yVelocity;

	}

	public int getHealth() {

		return health;

	}

	// End of get-methods

	// Set-methods
	public void setXCenter(double centerX) {

		this.centerX = centerX;

	}

	public void setYCenter(double centerY) {

		this.centerY = centerY;

	}

	public void setULeftXPos(double uLeftXPos) {

		this.uLeftXPos = uLeftXPos;

	}

	public void setULeftYPos(double uLeftYPos) {

		this.uLeftYPos = uLeftYPos;

	}

	public void setXVelocity(double xVel) {

		this.xVelocity = xVel;

	}

	public void setYVelocity(double yVel) {

		this.yVelocity = yVel;

	}

	public void setHealth(int health) {

		this.health = health;

	}

	// End of set-methods

	// Increases the character's x-position by the amount passed in as a
	// parameter
	public void increaseXPos(double incAmt) {

		this.centerX += incAmt;
		this.uLeftXPos += incAmt;

	}

	// Decreases the character's x-position by the amount passed in as a
	// parameter
	public void decreaseXPos(double decAmt) {

		this.centerX -= decAmt;
		this.uLeftXPos -= decAmt;

	}

	// Increases the character's y-position by the amount passed in as a
	// parameter
	public void increaseYPos(double incAmt) {

		this.centerY += incAmt;
		this.uLeftYPos += incAmt;

	}

	// Decreases the character's y-position by the amount passed in as a
	// parameter
	public void decreaseYPos(double decAmt) {

		this.centerY -= decAmt;
		this.uLeftYPos -= decAmt;

	}

	// Defines the character's hitbox as a rectangle with the height, width, x-
	// and y-coordinates of the character
	public Rectangle getBounds() {

		return new Rectangle((int) getULeftXPos(), (int) getULeftYPos(),
				(int) getCharWidth(), (int) getCharHeight());

	}

	// The move-method
	public void move() {

		// An enhanced for-loop which cycles through the "enemies"-ArrayList
		// in the Enemy-class
		for (Enemy enemy : Enemy.enemies) {

			// Gets the hitbox of the current enemy
			Rectangle enemyBox = enemy.getBounds();

			// Checks if the character's hitbox intersects with the enemy's
			if (this.getBounds().intersects(enemyBox)) {

				// Reduces the character's health by 1 point if it does
				this.setHealth(this.getHealth() - 1);

			}

			// Runs the death script if the character dies
			if (this.getHealth() <= 0) {

				GameDrawingPanel.enemies.clear();
				Enemy.enemies.clear();
				alive = false;

			}

		}

		// Creates a new object of the Dungeons-class
		Dungeons d = new Dungeons();

		// An enhanced for-loop which cycles through the "doors"-ArrayList
		// in the Doors-class
		for (Rectangle door : Doors.doors) {

			// Checks if the character's hitbox intersects the door's hitbox
			if (this.getBounds().intersects(door)) {

				// Calls the doorWays-method from the Doors-classs
				Doors.doorWays(Doors.doors.indexOf(door));

				// Places the character at the correct spot in the next room
				// If through the left door
				if (this.getULeftXPos() <= (d.getXCord() + Doors.height)) {

					// Places the character at the right end of the room
					this.setULeftXPos(d.getXCord() + d.getWidth()
							- Doors.height - 5 - this.getCharWidth());
					this.setXCenter(d.getXCord() + d.getWidth() - Doors.height
							- 5 - this.getCharWidth() / 2);

					// Clears the "enemies"-ArrayLists in the GameBoard- and
					// the Enemy-classes
					GameDrawingPanel.enemies.clear();
					Enemy.enemies.clear();

					// Calls the enemyPositionGenerator-method from the
					// GameDrawingPanel class
					GameDrawingPanel.enemyPositionGenerator(4);

				}

				// If through the right door
				else if (this.getULeftXPos() + this.getCharWidth() >= (d
						.getXCord() + d.getWidth() - Doors.height)) {

					// Places the character at the left end of the room
					this.setULeftXPos((d.getXCord() + Doors.height + 5));
					this.setXCenter(d.getXCord() + Doors.height + 5
							+ this.getCharWidth() / 2);

					// Clears the "enemies"-ArrayLists in the GameBoard- and
					// the Enemy-classes
					GameDrawingPanel.enemies.clear();
					Enemy.enemies.clear();
					// Calls the enemyPositionGenerator-method from the
					// GameDrawingPanel class
					GameDrawingPanel.enemyPositionGenerator(4);

				}

				// If through the top door
				else if (this.getULeftYPos() <= (d.getYCord() + Doors.height)) {

					// Places the character at the lower end of the room
					this.setULeftYPos(d.getYCord() + d.getHeight()
							- Doors.height - 15 - this.getCharHeight());
					this.setYCenter(d.getYCord() + d.getHeight() - Doors.height
							- 15 - this.getCharHeight() / 2);

					// Doubles the number of enemies if the player is going
					// into the top room
					if (Dungeons.dungeonNumber == "10") {

						// Clears the "enemies"-ArrayLists in the GameBoard-
						// and the Enemy-classes
						GameDrawingPanel.enemies.clear();
						Enemy.enemies.clear();

						// Calls the enemyPositionGenerator-method from the
						// GameDrawingPanel class
						GameDrawingPanel.enemyPositionGenerator(8);

					}

					else {

						// Clears the "enemies"-ArrayLists in the GameBoard-
						// and the Enemy-classes
						GameDrawingPanel.enemies.clear();
						Enemy.enemies.clear();

						// Calls the enemyPositionGenerator-method from the
						// GameDrawingPanel class
						GameDrawingPanel.enemyPositionGenerator(4);

					}

				}

				// If through the bottom door
				else if (this.getULeftYPos() + this.getCharHeight() >= (d
						.getYCord() + d.getHeight() - Doors.height - 10)) {

					// Places the character at the upper end of the room
					this.setULeftYPos((d.getYCord() + Doors.height + 5));
					this.setYCenter(d.getYCord() + Doors.height + 5
							+ this.getCharHeight() / 2);

					// Clears the "enemies"-ArrayLists in the GameBoard- and
					// the Enemy-classes
					GameDrawingPanel.enemies.clear();
					Enemy.enemies.clear();

					// Calls the enemyPositionGenerator-method from the
					// GameDrawingPanel class
					GameDrawingPanel.enemyPositionGenerator(4);

				}

			}

		}

		// Checks if the character is hitting the wall and stops it if it
		// does
		if (!d.getBounds().contains(this.getBounds())
				|| this.getBounds().intersects(d.getBounds())) {

			// If the character hits the left wall
			if (this.getXCenter() <= d.getHitboxXCord()) {

				// Set all movement which decreases the x-coordinates to 0
				this.setXVelocity(0);
				this.decreaseXPos(0);

			}

			// If the character hits the right wall
			else if (this.getXCenter() >= d.getHitboxXCord()
					+ d.getHitboxWidth()) {

				// Set all movement which increases the x-coordinates to 0
				this.setXVelocity(0);
				this.increaseXPos(0);

			}

			// If the character hits the upper wall
			if (this.getYCenter() <= d.getHitboxYCord()) {

				// Set all movement which decreases the y-coordinates to 0
				this.setYVelocity(0);
				this.decreaseYPos(0);

			}

			// If the character hits the lower wall
			else if (this.getYCenter() >= d.getHitboxYCord()
					+ d.getHitboxHeight()) {

				// Set all movement which increases the y-coordinates to 0
				this.setYVelocity(0);
				this.increaseYPos(0);

			}

		}

	}

}
// END OF Character CLASS
