/**@author Aditya Krishna and Bilsha Molin Mohan Das
 * Period 3
 * Post APCS
 */
import java.util.*;

/**
 * Class AIBrain is the AI class that interacts with the game
 * Roles and responsibilities:
 * 1) Set up a map of "cups" linked to specific boardStates that's constantly learning after each win and lose
 * 2) Makes moves by evaluating the map 
 */
public class AIBrain {
	
	//where all the states are stored and in correlation to a specific BrainCell (cup)
	public static HashMap<State, BrainCell> longTermMemory = new HashMap<>();
	private Set<Integer> positionsMemory = new HashSet<>();
	private State state = new State(); 
	
	public AIBrain() {
		
	}
	
	
	/**
	 * chooseMove() method roles:
	 * 1) make a move by choosing a position on the board to make a move at using
	 * 2) the braincell linked to the current boardState
	 * 
	 * @param boardState: the current state of the board
	 * @return chosenPosition after evaluating possibilities
	 */
	public int getMove() {
		BoardBrain b = new BoardBrain(); //<-- completely new, un-updated brain for the board with region full of null values
		int[][] correctMatch = b.region;
		BrainCell cup = null; 
		
		//finds the correct match and accesses the cup responsible for that state
		for (Map.Entry<State, BrainCell> entry : longTermMemory.entrySet())  {
			State p = entry.getKey(); 
			int[][] potentialMatch = p.getState(); 
			
			//finding the match and proceeding if match is found
			if ( Arrays.deepEquals(correctMatch, potentialMatch) ) {
				cup = entry.getValue(); 
			}
		}

		//picks random number from associated cup
		int size = cup.positions.size(); 
		int randomNumber = (int)(Math.random()*size); 
		int region = cup.positions.get(randomNumber); 
		return region;
	}
	
	/**
	 * learn() method roles:
	 * 1) depending on whether AI won, AI goes back and adds or removes more of the moves it made 
	 * 
	 * @param win: did the AI win or not
	 */
	public void learn(boolean win) {
		//If AI won, go back through last match's board positions and increase likelihood of each position for each braincell
		if (win) {
			for (int i = 0; i < positionsMemory.size(); i++) {
				
			}
			//If AI won, go back through last match's board positions and decrease likelihood of each position for each braincell
		} else {
			for (int i = 0; i < positionsMemory.size(); i++) {
				
			}
		}
		positionsMemory.clear();
	}


	
	public void forget() {
		
	}
}
