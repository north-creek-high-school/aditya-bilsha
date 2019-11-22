import java.util.Arrays;

public class State {
	
	private int[][] state = new int[3][3];
	private BoardBrain b = new BoardBrain(); 
	
	
	public State( ) {
		for (int row = 0; row < state.length; row++) {
			for (int col = 0; col < state[row].length; col++) {
				state[row][col] = 0; 
			}
		}
	}

	
	public State getCurrentState() {
		int[][] region = b.region; 
		//goes through region array and creates a state array
		//inside a state object that is exactly the same, returns state object
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
		State state = getCurrentState(); 
		`
		
	}
	
}
