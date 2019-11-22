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
	public HashMap<State, BrainCell> longTermMemory = new HashMap<>();
	private Set<Integer> positionsMemory = new HashSet<>();
	
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
	public int choosePosition(int[][] boardState) {
		
		return 0;
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