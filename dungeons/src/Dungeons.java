import java.awt.*;

//Dungeons CLASS
public class Dungeons {
	
	//Defines the upper left right corner of the rooms
	public static int xCord = 150;
	public static int yCord = 150;
	
	//Defines the width and height of the rooms
	public static int width = 700;
	public static int height = 510;
	
	//Defines the upper left right corner of the rooms' hitbox
	public static int hitboxXCord = 220;
	public static int hitboxYCord = 220;
	
	//Defines the width and height of the rooms' hitbox
	public static int hitboxWidth = 560;
	public static int hitboxHeight = 380;
	
	//The number of the current dungeon. The dungeons are numbered as follows:
	/*    [10]
	   [2] [9] [3]
	   [5] [8] [6]
	   [1] [7] [4]
	*/
	public static String dungeonNumber = "7";

	//Stores the file paths for the different rooms as strings
	static String dungeon1 = "..//img//rooms//BottomLeft.png";
	static String dungeon2 = "..//img//rooms//TopLeft.png";
	static String dungeon3 = "..//img//rooms//TopRight.png";
	static String dungeon4 = "..//img//rooms//BottomRight.png";
	static String dungeon5 = "..//img//rooms//Left.png";
	static String dungeon6 = "..//img//rooms//Right.png";
	static String dungeon7 = "..//img//rooms//Bot.png";
	static String dungeon8 = "..//img//rooms//Mid.png";
	static String dungeon9 = "..//img//rooms//Mid.png";
	static String dungeon10 = "..//img//rooms//Top.png";

	//Get-methods
	public int getXCord() {

		return xCord;

	}
	
	public int getWidth(){
		
		return width;
		
	}
	
	public int getHeight(){
		
		return height;
		
	}

	public int getYCord() {

		return yCord;

	}
	
	public int getHitboxXCord(){
		
		return hitboxXCord;
		
	}
	
	public int getHitboxYCord(){
		
		return hitboxYCord;
		
	}
	
	public int getHitboxWidth(){
		
		return hitboxWidth;
		
	}
	
	public int getHitboxHeight(){
		
		return hitboxHeight;
		
	}
	//End of get-methods

	//Set-methods
	public void setXCord(int xc) {

		xCord = xc;

	}

	public void setYCord(int yc) {

		yCord = yc;

	}

	public void setWidth(int w){
		
		width = w;
		
	}
	
	public void setHeight(int h){
		
		height = h;
		
	}
	
	public void setHitboxXCord(int xc){
		
		hitboxXCord = xc;
		
	}
	
	public void setHitboxYCord(int yc){
		
		hitboxYCord = yc;
		
	}
	
	public void setHitboxWidth(int w){
		
		hitboxWidth = w;
		
	}
	
	public void setHitboxHeight(int h){
		
		hitboxHeight = h;
		
	}
	//Ends of set-methods
	
	//Creates the room's hitbox
	public Rectangle getBounds() {

		return new Rectangle((int) (getXCord() + 70), (int) (getYCord() + 70), (width - 140), (height - 140));

	}
	
}
//END OF Dungeons CLASS
