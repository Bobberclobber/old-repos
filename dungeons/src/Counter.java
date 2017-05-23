import java.util.*;

//Counter CLASS
//This class extends TimerTask which allows it to be used by a Timer-object
public class Counter extends TimerTask{

	//Defines the variables
	public static int counter = 0;
	
	//The run-method
	//This method will be called when an object of the Timer-class calls this method
	public void run(){
		
		//The following if-else statement makes sure that the variable counter always is equal to 1 or 0
		if(counter < 1){
			
			counter++;
			
		}
		
		else{
			
			counter = 0;
			
		}

	}
	
}
//END OF Counter CLASS
