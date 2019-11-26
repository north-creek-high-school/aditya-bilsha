import java.util.*;

public class BrainCell {
	
	//positions contains all the positions for the AI to place its mark
	//AI learns by adding the same position repeatedly or removing a certain position
	public List<Integer> positions = new ArrayList<>();
	
	/**
	 * Creates a Braincell with a starting set of all the possible positions for the AI
	 * to place its move relative to the specific board state
	 * @param boardState [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
	 */
	public BrainCell(int[][] boardState) {
		int position = 0;
		for (int row = 0; row < boardState.length; row++) {
			for (int col = 0; col < boardState[row].length; col++) {
				if (boardState[row][col] == 0); {
					positions.add(position);
				}
				position++;
			}
		}
	}
	
	//add the moves into braincell to increase likelihood of being picked again
	public void addMove(int move) {
		positions.add(move);
	}
	
	//remove the moves into braincell to decrease likelihood of being picked again
	public void removeMove(int move) {
		if (positions.contains(move)) {
			positions.remove(move);
		}
		if (!positions.contains(move)) {
			positions.add(move);
		}
	}
	
}