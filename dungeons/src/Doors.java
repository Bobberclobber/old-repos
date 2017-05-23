//A class to set the coordinates for the doors
import java.util.*;
import java.awt.*;

//Doors CLASS
public class Doors{

	//Defines the width and height of the doors
	public static int width = 75;
	public static int height = 70;

	//Arrays of which rooms the doors lead to, starting from the left-most for each room
	static String[] dungeon1Array = {"0", "5", "7", "0"};
	static String[] dungeon2Array = {"0", "0", "9", "5"};
	static String[] dungeon3Array = {"9", "0", "0", "6"};
	static String[] dungeon4Array = {"7", "6", "0", "0"};
	static String[] dungeon5Array = {"0", "2", "8", "1"};
	static String[] dungeon6Array = {"8", "3", "0", "4"};
	static String[] dungeon7Array = {"1", "8", "4", "0"};
	static String[] dungeon8Array = {"5", "9", "6", "7"};
	static String[] dungeon9Array = {"2", "10", "3", "8"};
	static String[] dungeon10Array = {"0", "0", "0", "9"};
	
	//An ArrayList that will be used to hold all the door-rectangles for the room the player is currently in
	static ArrayList<Rectangle> doors = new ArrayList<Rectangle>();

	//CONSTRUCTOR
	public Doors() {

		//Adds the right number of doors depending on which room the player is currently in
		switch (Dungeons.dungeonNumber) {

		case ("1"):

			//Clears the "doors"-ArrayList so that doors from previous rooms won't appear
			doors.clear();
			doors.add(0, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(1, new Rectangle(GameBoard.boardWidth/2 - width/2, 150, width, height)); //Door on the top wall
			doors.add(2, new Rectangle(GameBoard.boardWidth - height - 150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the right wall
			doors.add(3, new Rectangle(0, 0, 0, 0)); //Empty element
			break;

		case ("2"):

			//Clears the "doors"-ArrayList so that doors from previous rooms won't appear
			doors.clear();
			doors.add(0, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(1, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(2, new Rectangle(GameBoard.boardWidth - height - 150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the right wall
			doors.add(3, new Rectangle(GameBoard.boardWidth/2 - width/2, GameBoard.boardHeight - height - 150, width, height)); //Door on the bottom wall
			break;

		case ("3"):

			//Clears the "doors"-ArrayList so that doors from previous rooms won't appear
			doors.clear();
			doors.add(0, new Rectangle(150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the left wall
			doors.add(1, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(2, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(3, new Rectangle(GameBoard.boardWidth/2 - width/2, GameBoard.boardHeight - height - 150, width, height)); //Door on the bottom wall
			break;

		case ("4"):

			//Clears the "doors"-ArrayList so that doors from previous rooms won't appear
			doors.clear();
			doors.add(0, new Rectangle(150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the left wall
			doors.add(1, new Rectangle(GameBoard.boardWidth/2 - width/2, 150, width, height)); //Door on the top wall
			doors.add(2, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(3, new Rectangle(0, 0, 0, 0)); //Empty element
			break;

		case ("5"):

			//Clears the "doors"-ArrayList so that doors from previous rooms won't appear
			doors.clear();
			doors.add(0, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(1, new Rectangle(GameBoard.boardWidth/2 - width/2, 150, width, height)); //Door on the top wall
			doors.add(2, new Rectangle(GameBoard.boardWidth - height - 150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the right wall
			doors.add(3, new Rectangle(GameBoard.boardWidth/2 - width/2, GameBoard.boardHeight - height - 150, width, height)); //Door on the bottom wall
			break;

		case ("6"):

			//Clears the "doors"-ArrayList so that doors from previous rooms won't appear
			doors.clear();
			doors.add(0, new Rectangle(150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the left wall
			doors.add(1, new Rectangle(GameBoard.boardWidth/2 - width/2, 150, width, height)); //Door on the top wall
			doors.add(2, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(3, new Rectangle(GameBoard.boardWidth/2 - width/2, GameBoard.boardHeight - height - 150, width, height)); //Door on the bottom wall
			break;

		case ("7"):

			//Clears the "doors"-ArrayList so that doors from previous rooms won't appear
			doors.clear();
			doors.add(0, new Rectangle(150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the left wall
			doors.add(1, new Rectangle(GameBoard.boardWidth/2 - width/2, 150, width, height)); //Door on the top wall
			doors.add(2, new Rectangle(GameBoard.boardWidth - height - 150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the right wall
			doors.add(3, new Rectangle(0, 0, 0, 0)); //Empty element
			break;

		case ("8"):

			//Clears the "doors"-ArrayList so that doors from previous rooms won't appear
			doors.clear();
			doors.add(0, new Rectangle(150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the left wall
			doors.add(1, new Rectangle(GameBoard.boardWidth/2 - width/2, 150, width, height)); //Door on the top wall
			doors.add(2, new Rectangle(GameBoard.boardWidth - height - 150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the right wall
			doors.add(3, new Rectangle(GameBoard.boardWidth/2 - width/2, GameBoard.boardHeight - height - 150, width, height)); //Door on the bottom wall
			break;

		case ("9"):

			//Clears the "doors"-ArrayList so that doors from previous rooms won't appear
			doors.clear();
			doors.add(0, new Rectangle(150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the left wall
			doors.add(1, new Rectangle(GameBoard.boardWidth/2 - width/2, 150, width, height)); //Door on the top wall
			doors.add(2, new Rectangle(GameBoard.boardWidth - height - 150, GameBoard.boardHeight/2 - width/2, height, width)); //Door on the right wall
			doors.add(3, new Rectangle(GameBoard.boardWidth/2 - width/2, GameBoard.boardHeight - height - 150, width, height)); //Door on the bottom wall
			break;

		case ("10"):

			//Clears the "doors"-ArrayList so that doors from previous rooms won't appear
			doors.clear();
			doors.add(0, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(1, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(2, new Rectangle(0, 0, 0, 0)); //Empty element
			doors.add(3, new Rectangle(GameBoard.boardWidth/2 - width/2, GameBoard.boardHeight - height - 150, width, height)); //Door on the bottom wall
			break;

		}

	}
	
	//The doorWays-method
	//This method is called from the Character-class' move-method when the character hits a door's hitbox
	//The parameter passed in is the number of the door which the character went through (1 = left, 2 = top etc.)
	public static void doorWays(int door){
		
		//Checks which room the character was in when it hit the door-box and uses the corresponding array to determine which room the character will appear in next
		switch(Dungeons.dungeonNumber){
		
		case("1"):
			
			Dungeons.dungeonNumber = dungeon1Array[door];
			
			break;
		
		case("2"):
			
			Dungeons.dungeonNumber = dungeon2Array[door];
			
			break;
		
		case("3"):
			
			Dungeons.dungeonNumber = dungeon3Array[door];
			
			break;
		
		case("4"):
			
			Dungeons.dungeonNumber = dungeon4Array[door];
			
			break;
		
		case("5"):
			
			Dungeons.dungeonNumber = dungeon5Array[door];
			
			break;
		
		case("6"):
			
			Dungeons.dungeonNumber = dungeon6Array[door];
			
			break;
		
		case("7"):
			
			Dungeons.dungeonNumber = dungeon7Array[door];
			
			break;
		
		case("8"):
			
			Dungeons.dungeonNumber = dungeon8Array[door];
			
			break;
		
		case("9"):
			
			Dungeons.dungeonNumber = dungeon9Array[door];
			
			break;
		
		case("10"):
			
			Dungeons.dungeonNumber = dungeon10Array[door];
			
			break;
		
		}
		
	}

}
//END OF Doors CLASS
