/**
 * 
 * @author Aditya Krishna & Bilsha Molin
 * Class Brain
 * Roles:
 * 1) Storing X and O values in corresponding regions for win evaluation
 * 2) Evaluating win by testing for 3 of the same values in a row
 * 3) Resetting 2D region array
 */
public class BoardBrain {
	
	/**
	 * region 3 x 3 matrix holds values of 0, 3 or 4
	 * 0 for Empty, 3 for X, and 4 for O
	 * Values 3 and 4 were chosen because of their relationship
	 * 3 values of 3 in a row added up to 9, for 4 the sum was 12
	 * unlike 1 and 2 or 2 and 3, where 1 + 2 added up to a row of 1
	 * or 3 + 3 added up to a row of 2,
	 * 3 and 4 didn't share any sums when added up in any unique way
	 */
	public int[][] region = new int[3][3];
	
	/**
	 * Method resetRegion()
	 * Role: resets all values in array to 0 to signify a new empty board state
	 */
	public void resetRegion() {
		for (int row = 0; row < region.length; row++) {
			for (int col = 0; col < region[row].length; col++) {
				region[row][col] = 0;
			}
		}
	}
	
	/**
	 * Method winCheck()
	 * runs multiple tests to check if any player has won 
	 * @return value of board state evaluation: 0 for further evaluation,
	 * 1 for state where X has won, and 2 for state where Y has won
	 */
	public int winCheck() {
		int current;
		current = allHorizontal();
		if (current > 0) {
			return current;
		}
		current = allVertical();
		if (current > 0) {
			return current;
		}
		current = allDiagonal();
		return current;
	}
	
	/**
	 * Method allHorizontal()
	 * check all horizontal rows for 3 values of the same type
	 * @return value of board state evaluation: 0 for further evaluation,
	 * 1 for state where X has won, and 2 for state where Y has won
	 */
	private int allHorizontal() {
		//holds the sum to check if there is 3 in a row of the same value
		int sumOfVals = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				//add up all values in that row
				sumOfVals += region[row][col];
			}
			if (sumOfVals == 9) {
				return 1;
			} else if (sumOfVals == 12) {
				return 2;
			}
			//sets back to 0 after search of each row
			sumOfVals = 0;
		}
		return 0;
	}
	
	/**
	 * Method allVertical()
	 * check all vertical columns for 3 values of the same type
	 * @return value of board state evaluation: 0 for further evaluation,
	 * 1 for state where X has won, and 2 for state where Y has won
	 */
	private int allVertical() {
		//holds the sum to check if there is 3 in a row of the same value
		int sumOfVals = 0;
		for (int col = 0; col < 3; col++) {
			for (int row = 0; row < 3; row++) {
				//add up all values in that column
				sumOfVals += region[row][col];
			}
			if (sumOfVals == 9) {
				return 1;
			} else if (sumOfVals == 12) {
				return 2;
			}
			//sets back to 0 after search of each column
			sumOfVals = 0;
		}
		return 0;
	}
	
	/**
	 * Method allDiagonal()
	 * check all diagonal paths for 3 values of the same type
	 * @return value of board state evaluation: 0 for further evaluation,
	 * 1 for state where X has won, and 2 for state where Y has won
	 */
	private int allDiagonal() {
		//holds the sum to check if there is 3 in a row of the same value
		int sumOfVals = 0;
		for (int dia = 0; dia < 3; dia++) {
			//add up all values in that path
			sumOfVals += region[dia][dia];
		}
		if (sumOfVals == 9) {
			return 1;
		} else if (sumOfVals == 12) {
			return 2;
		}
		//sets back to 0 after search of each path
		sumOfVals = 0;
		for (int dia = -1; dia < 2; dia++) {
			//add up all values in that path
			sumOfVals += region[1 + dia][1 - dia];
		}
		if (sumOfVals == 9) {
			return 1;
		} else if (sumOfVals == 12) {
			return 2;
		}
		return 0;
	}
}