import java.awt.*;
import java.util.*;
import javax.swing.*;

//Enemy CLASS
public class Enemy {

	//Will store which type of enemy an instance is
	String type;

	//Stores upper left x- and y-coordinates
	int uLeftX, uLeftY;
	
	//Stores x- and y-velocity
	int xVel, yVel;
	
	//Stores width and height
	int width, height;
	
	//Stores health
	int health;

	//Stores the enemies present in the current room. This ArrayList is a copy of the one created in the GameBoard-class
	static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	//Defines if the enemy is alive or not
	public boolean alive = true;
	
	//Stores the file path of the enemy's image
	private String imgSource = "..//img//knight//KnightForwardS.png";
	
	//Sets the enemy's painted image to the one stored in the variable imgSource
	private Image enemyImage = new ImageIcon(imgSource).getImage();

	//Creates an object of the Dungeons-class
	Dungeons dungeons = new Dungeons();

	//CONTRUCTOR
	public Enemy(String type, int uLeftX, int uLeftY, int xVel, int yVel,
			int width, int height, int health) {

		//Defines the local variables as the passed in parameters
		this.type = type;
		this.uLeftX = uLeftX;
		this.uLeftY = uLeftY;
		this.xVel = xVel;
		this.yVel = yVel;
		this.width = width;
		this.height = height;
		this.health = health;

	}
	//CONSTRUCTOR

	//Get-methods
	public String getType() {

		return type;

	}

	public Image getEnemy() {

		return enemyImage;

	}

	public String getEnemySource() {

		return imgSource;

	}

	public int getULeftX() {

		return uLeftX;

	}

	public int getULeftY() {

		return uLeftY;

	}

	public int getXVel() {

		return xVel;

	}

	public int getYVel() {

		return yVel;

	}

	public int getWidth() {

		return width;

	}

	public int getHeight() {

		return height;

	}

	public int getHealth() {

		return health;

	}
	//End of get-methods

	//Set-methods
	public void setType(String type) {

		this.type = type;

	}

	public void setEnemy(Image img) {

		enemyImage = img;

	}

	public void setEnemySource(String imgSrc) {

		imgSource = imgSrc;

	}

	public void setULeftX(int uLeftX) {

		this.uLeftX = uLeftX;

	}

	public void setULeftY(int uLeftY) {

		this.uLeftY = uLeftY;

	}

	public void setXVel(int xVel) {

		this.xVel = xVel;

	}

	public void setYVel(int yVel) {

		this.yVel = yVel;

	}

	public void setWidth(int width) {

		this.width = width;

	}

	public void setHeight(int height) {

		this.height = height;

	}

	public void setHealth(int health) {

		this.health = health;

	}
	//End of set-methods

	//Increases the enemy's x-coordinate by the amount passed in as a parameter
	public void increaseXPos(int incAmt) {

		this.uLeftX += incAmt;

	}

	//Increases the enemy's y-coordinate by the amount passed in as a parameter
	public void increaseYPos(int incAmt) {

		this.uLeftY += incAmt;

	}

	//Creates the enemy's hitbox
	public Rectangle getBounds() {

		return new Rectangle(this.uLeftX, this.uLeftY, this.width, this.height);

	}

	//The enemy's move-method
	public void move(Character character, ArrayList<Projectile> projectiles) {

		//Creates a Rectangle-object of THIS enemy's hitbox
		Rectangle enemyToCheck = this.getBounds();

		//Moves the enemy by calling te increaseXPos- and increaseYPos-methods
		increaseXPos(getXVel());
		increaseYPos(getYVel());

		//An enhanced for-loop that cycles through the "enemies"-ArrayList
		for (Enemy enemy : enemies) {
		
			//If the enemy which THIS enemy is compared to is alive
			if (enemy.alive) {

				//Creates a Rectangle-object of the OTHER enemy
				Rectangle otherEnemy = enemy.getBounds();

				//Checks if the two enemies which are compared are hitting each other
				if (enemy != this && otherEnemy.intersects(enemyToCheck)) {

					// If two enemies bump into each other, they will start
					// moving in the opposite direction than they were
					enemy.setXVel(-(enemy.getXVel()));
					enemy.setYVel(-(enemy.getYVel()));

					this.setXVel(-(this.getXVel()));
					this.setYVel(-(this.getYVel()));

				}

				//An enhanced for-loop that cycles through the "projectiles"-ArrayList which was passed in when this method was called from the GameBoard-class
				for (Projectile projectile : projectiles) {

					//If the current projectile is on the screen
					if (projectile.onScreen) {

						//Checks if the enemy is getting hit and reduces it's health by 1 point if it is
						if (otherEnemy.contains(projectile.getXCenter(),
								projectile.getYCenter())) {

							enemy.setHealth(enemy.getHealth() - 1);

							//Removes the enemy when it's health reaches 0
							if (enemy.getHealth() == 0) {

								enemy.alive = false;
								GameBoard.points++;
								enemy.setULeftX(0 - enemy.getWidth());
								enemy.setULeftY(0 - enemy.getHeight());

							}

							//Since the enemy was hit, the projectile is now removed from the screen
							//Defining onScreen as false will cause it not to be drawn on the screen anymore
							projectile.onScreen = false;

						}

					}

				}

			}

		}

		// If the enemy hits the wall it will start walking in the
		// opposite direction
		if(this.uLeftX < Dungeons.hitboxXCord || (this.uLeftX + width) > (Dungeons.hitboxXCord + Dungeons.hitboxWidth)){
			
			this.xVel = -this.xVel;
			
		}
		
		if(this.uLeftY < Dungeons.hitboxYCord || (this.uLeftY + height) > (Dungeons.hitboxYCord + Dungeons.hitboxHeight)){
			
			this.yVel = -this.yVel;
			
		}

	}

}
//END OF Enemy CLASS
