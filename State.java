import java.util.Arrays;

public class State {
	
	private int[][] state = new int[3][3];
	
	
	public State() {
		for (int row = 0; row < state.length; row++) {
			for (int col = 0; col < state[row].length; col++) {
				state[row][col] = 0; 
			}
		}
	}
	
	public int[][] getState() {
		return state;
	}

	//goes through region array and creates a state array
	//inside a state object that is exactly the same, returns state object
	public State getCurrentState() {
		BoardBrain b = new BoardBrain(); //<-- Whenever you make a new BoardBrain,
		//the region is a completely new region than the one that is meant for the game
		int[][] region = b.region; //<-- b.region is full of null values, 
		//the int[][] that represents the game board is in TicTacToeBoard called boardBrain.region
		State s = new State(); 
		int[][] st = s.state; 
		for (int row = 0; row < region.length; row++) { 
			for (int col = 0; col < region[row].length; col++) {
				st[row][col] = region[row][col]; 
			}
		}
		return s; 
	}
	
	
	public void createNewState(int[] a) {
		State s = getCurrentState(); 
		int[][] state = s.state; 
		BrainCell b = new BrainCell(state); 
		AIBrain.longTermMemory.put(s, b); 
	}
	
	
	
}
