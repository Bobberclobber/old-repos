import java.awt.*;
import javax.swing.*;
import java.util.*;

//CharacterAnimations CLASS
public class CharacterAnimations {

	//ArrayLists containing the different images needed for the animations when the character moves in different directions
	//These are filled when the fillLists-method is called from the GameBoard-class' main-method right before the GameBoard is created
	public static ArrayList<Image> movingRightList = new ArrayList<Image>();
	public static ArrayList<Image> movingLeftList = new ArrayList<Image>();
	public static ArrayList<Image> movingUpList = new ArrayList<Image>();
	public static ArrayList<Image> movingDownList = new ArrayList<Image>();
	public static ArrayList<Image> standingStillList = new ArrayList<Image>();
	
	//This defines the current image, this is the image that is drawn by the paint-method in the GameBoard-class
	public static Image currentImage = new ImageIcon("..//img//character//CharacterForwardS.png").getImage();
	
	//The fillLists-method, this is called from the GameBoard-class' main-method
	public static void fillLists() {
		
		movingUpList.add(new ImageIcon("..//img//character//CharacterBackL.png").getImage());
		movingUpList.add(new ImageIcon("..//img//character//CharacterBackR.png").getImage());
		movingDownList.add(new ImageIcon("..//img//character//CharacterForwardL.png").getImage());
		movingDownList.add(new ImageIcon("..//img//character//CharacterForwardR.png").getImage());
		movingRightList.add(new ImageIcon("..//img//character//CharacterRightL.png").getImage());
		movingRightList.add(new ImageIcon("..//img//character//CharacterRightR.png").getImage());
		movingLeftList.add(new ImageIcon("..//img//character//CharacterLeftL.png").getImage());
		movingLeftList.add(new ImageIcon("..//img//character//CharacterLeftR.png").getImage());
		standingStillList.add(new ImageIcon("..//img//character//CharacterBackS.png").getImage());
		standingStillList.add(new ImageIcon("..//img//character//CharacterForwardS.png").getImage());
		standingStillList.add(new ImageIcon("..//img//character//CharacterRightS.png").getImage());
		standingStillList.add(new ImageIcon("..//img//character//CharacterLeftS.png").getImage());
		
		
	}

	
}
//END OF CharacterAnimations CLASS
