import java.awt.*;

//Projectile CLASS
public class Projectile {

	//Defines GBWidth and GBHeight as the width and height of the GameBoard window
	int GBWidth = GameBoard.boardWidth;
	int GBHeight = GameBoard.boardHeight;
	
	//Defines xCord and yCord as the upper right corner of the room
	int xCord = Dungeons.xCord;
	int yCord = Dungeons.yCord;
	
	//Defines width and height as the room's width and height
	int width = Dungeons.width;
	int height = Dungeons.height;

	//Defines centerX and centerY
	private double centerX, centerY;

	//Defines the projectile's width and height
	private int projectileWidth = 6, projectileHeight = 6;

	//This controls if the projectile shall be drawn on the screen or not
	public boolean onScreen = false;

	//Defines the projectiles x- and y-velocity
	private double xVelocity, yVelocity;

	//This object will store the image which will be drawn
	Image projectile;

	//CONSTRUCTOR
	public Projectile(double centerX, double centerY, double directionX,
			double directionY, Image img) {

		//Defines the local variables as the values passed in as parameters
		this.centerX = centerX;
		this.centerY = centerY;

		//Sets onScreen = true so that the projectile will be painted on the screen
		this.onScreen = true;

		this.setXVelocity(directionX);
		this.setYVelocity(directionY);
		
		//Sets the projectile's image as the one passed in
		this.setProjectile(img);

	}
	//END OF CONTSTRUCTOR
	
	//Get-methods
	public Image getProjectile() {

		return projectile;

	}

	public double getXCenter() {

		return centerX;

	}

	public double getYCenter() {

		return centerY;

	}
	
	public double getXVelocity() {

		return xVelocity;

	}

	public double getYVelocity() {

		return yVelocity;

	}
	
	public int getWidth() {

		return projectileWidth;

	}

	public int getHeight() {

		return projectileHeight;

	}
	//End of get-methods
	
	//Set-methods
	public void setProjectile(Image img) {
		
		projectile = img;
		
	}

	public void setXCenter(double xCent) {

		this.centerX = xCent;

	}

	public void setYCenter(double yCent) {

		this.centerY = yCent;

	}
	
	public void setXVelocity(double xVel) {

		this.xVelocity = xVel;

	}

	public void setYVelocity(double yVel) {

		this.yVelocity = yVel;

	}
	//End of set-methods

	//These methods changes the x- and y-coordinates by the amounts passed in as parameters
	public void changeXPos(double changeAmt) {

		this.centerX += changeAmt;

	}

	public void changeYPos(double changeAmt) {

		this.centerY += changeAmt;

	}

	//Creates the projectile's hitbox
	public Rectangle getBounds() {

		return new Rectangle((int) getXCenter() - getWidth(),
				(int) getYCenter() - getHeight(), getWidth(), getHeight());

	}

	//The projectile's move-method
	public void move() {

		//If the projectile is on the screen
		if (this.onScreen) {

			//Moves the projectile along the x-axis
			this.changeXPos(this.getXVelocity());

			//If the projectile hits the edge of the room it will disappear by setting onScreen = false
			if (this.getXCenter() < xCord) {

				this.onScreen = false;

			}

			else if (this.getXCenter() > xCord + width) {

				this.onScreen = false;

			}

			//Moves the projectile along the y-axis
			this.changeYPos(this.getYVelocity());

			//If the projectile hits the edge of the room it will disappear by setting onScreen = false
			if (this.getYCenter() < yCord) {

				this.onScreen = false;

			}

			else if (this.getYCenter() > (yCord + height - 20)) {

				this.onScreen = false;

			}

		}

	}
}
//END OF Projectile CLASS
