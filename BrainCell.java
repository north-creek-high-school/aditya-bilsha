import java.util.*;

public class BrainCell {
	
	//positions contains all the positions for the AI to place its mark
	//AI learns by adding the same position repeatedly or removing a certain position
	public Set<List<Integer>> positions = new HashSet<>();
	
	/**
	 * Creates a Braincell with a starting set of all the possible positions for the AI
	 * to place its move relative to the specific board state
	 * @param boardState
	 */
	public void Braincell(int[][] boardState) {
		for (int row = 0; row < boardState.length; row++) {
			for (int col = 0; col < boardState[row].length; col++) {
				if (boardState[row][col] == 0); {
					positions.add(Arrays.asList(row, col));
				}
			}
		}
		for (int row = 0; row < boardState.length; row++) {
			for (int col = 0; col < boardState[row].length; col++) {
				
			}
		}
	}
	
}