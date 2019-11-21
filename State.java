import java.util.Arrays;

public class State {
	
	private int[][] state = new int[3][3];
	
	public State( ) {
		for (int row = 0; row < state.length; row++) {
			for (int col = 0; col < state[row].length; col++) {
				state[row][col] = 0; 
			}
		}
		
		
	}

}
