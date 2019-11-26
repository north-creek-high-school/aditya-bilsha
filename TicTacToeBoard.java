import java.awt.*;
import javax.swing.JOptionPane;

/**
 * 
 * @author Bilsha Molin & Aditya Krishna
 *
 * Class TicTacToeBoard
 * Roles: 
 * 1) Handle user/board interactions
 * 2) Update and display state of board
 * 3) Start the game and handle requests to play more games
 * Tools used:
 * 1) DrawingPanel.java to allow class to display board
 * 2) boardBrain.java to have referee/player to decide whether player has won
 * 3) Swing option window to request if player wants to play again
 */
public class TicTacToeBoard {
	
	//Each dimension of the board is 900 pixels long
	private final int DIMENSION = 900;
	//9 regions, each having a dimension a third of the length of the board
	private int regionDimension = DIMENSION/3;
	//Construct turn variable that increments after each move to keep track of who goes next
	private int turn = 0;
	//int value for if AI is going first, 0 is no, 1 is yes
	private int AIisGoingFirst = 0;
	//Variable for what type of game is in progress
	private int typeOfGame = 0;
	//Construct DrawingPanel for display
	private DrawingPanel p = new DrawingPanel(DIMENSION, DIMENSION);
	//Construct Graphics object to alter DrawingPanel object
	private Graphics g = p.getGraphics();
	//Construct boardBrain object to act as the "referee" for the game
	private BoardBrain boardBrain = new BoardBrain(); 
	//Construct an empty AI that will remain dormant until players asks to play agaisnt it
	private AIBrain notHuman = new AIBrain();
	
	
	public static void main(String[] args)  {
		TicTacToeBoard boad = new TicTacToeBoard();
		//playGame() is called when game has started or when player wants to play again
		boad.playNewGame();
		
	}
	
	/**
	 * Method clickHandler(int sx, int sy) 
	 * Roles:
	 * 1) Display an X or O in the region the player clicks
	 * 2) Update storage array for each move and increment turn
	 * Parameters:
	 * @param win: Stores the boardBrain's evaluation of the board state
	 * @param regionX: Stores the region number across where the player clicked
	 * @param regionY: Stores the region number along where the player clicked
	 * @param pixelX: Stores the top left x coordinate of the region the player clicked in
	 * @param pixelY: Stores the top left y coordinate of the region the player clicked in
	 */
	private void hvhClickHandler(int sx, int sy) {
		
		//the x and y coordinates of the mouse are divided by regionDimension to get a value between 0 and 2
		//this value represents the region number across and along the board respectfully 
		int regionX = (sx/regionDimension); 	
		int regionY = (sy/regionDimension);
		//regionX and regionY are multiplied once again to get the top left coordinates of each region
		int pixelX = regionX * regionDimension;
		int pixelY = regionY * regionDimension;
		//boolean variable to determine if location on board is empty to be filled by player
		boolean regionIsEmpty = boardBrain.region[regionY][regionX] == 0;
		//boolean variable to determine if there are turns left in game
		boolean turnsLeft = turn < 9;
		//boolean variable for whose turn it is
		boolean XTurn = turn % 2 == 0;
		
		//use drawString() to put down X or O on board region and 4 or 5 respectfully inside the corresponding region in 2D array
		if (turnsLeft && regionIsEmpty) { 
			if (XTurn) {
				g.drawString("x", pixelX + 50, pixelY + 250);
				//value of 3 chosen for summing up values
				boardBrain.region[regionY][regionX] = 3;
			}
			if (!XTurn) {
				g.drawString("o", pixelX + 40, pixelY + 250);
				//value of 4 chose for summing up values
				boardBrain.region[regionY][regionX] = 4;
			}
			turn++;
		}
		
		boardCheck();
		
	}

	private void avhClickHandler(int sx, int sy) {

		//boolean variable to determine if there are turns left in game
		boolean turnsLeft = turn < 9;
		if (AIisGoingFirst == 1) {
			AImakesMove();
			boardCheck();
			//the x and y coordinates of the mouse are divided by regionDimension to get a value between 0 and 2
			//this value represents the region number across and along the board respectfully 
			int regionX = (sx/regionDimension); 	
			int regionY = (sy/regionDimension);
			//regionX and regionY are multiplied once again to get the top left coordinates of each region
			int pixelX = regionX * regionDimension;
			int pixelY = regionY * regionDimension;
			//boolean variable to determine if location on board is empty to be filled by player
			boolean regionIsEmpty = boardBrain.region[regionY][regionX] == 0;
			if (turnsLeft && regionIsEmpty) {
				g.drawString("o", pixelX + 40, pixelY + 250);
				//value of 4 chose for summing up values
				boardBrain.region[regionY][regionX] = 4;
				turn++;
			}
			boardCheck();
		} else if (AIisGoingFirst == 0){
			//the x and y coordinates of the mouse are divided by regionDimension to get a value between 0 and 2
			//this value represents the region number across and along the board respectfully 
			int regionX = (sx/regionDimension); 	
			int regionY = (sy/regionDimension);
			//regionX and regionY are multiplied once again to get the top left coordinates of each region
			int pixelX = regionX * regionDimension;
			int pixelY = regionY * regionDimension;
			//boolean variable to determine if location on board is empty to be filled by player
			boolean regionIsEmpty = boardBrain.region[regionY][regionX] == 0;
			if (turnsLeft && regionIsEmpty) {
				g.drawString("x", pixelX + 50, pixelY + 250);
				//value of 4 chose for summing up values
				boardBrain.region[regionY][regionX] = 4;
				turn++;
			}
			boardCheck();
			AImakesMove();
			boardCheck();
		}
	}
	
	private void AImakesMove() {
		
		int move = notHuman.getMove();
		//boolean variable to determine if there are turns left in game
		boolean turnsLeft = turn < 9;
		int regionX = 0;
		int regionY = move;
		
		if (move >= 3 && move < 6) {
			regionX = 1;
			regionY = move - 3;
		} else {
			regionX = 2;
			regionY = move - 6;
		}
		
		int pixelX = regionX * regionDimension;
		int pixelY = regionY * regionDimension;
		//boolean variable to determine if location on board is empty to be filled by player
		boolean regionIsEmpty = boardBrain.region[regionY][regionX] == 0;
		if (turnsLeft && regionIsEmpty) {
			if (AIisGoingFirst == 1) {
				g.drawString("x", pixelX + 50, pixelY + 250);
			} else {
				g.drawString("o", pixelX + 40, pixelY + 250);
			}
			//value of 4 chose for summing up values
			boardBrain.region[regionY][regionX] = 4;
			turn++;
		}
		
	}

	private void boardCheck() {
		
		//boardBrain evaluates current board state and returns value
		//1 represents a state where X has won
		//2 represents a state where O has won
		//0 is further evaluated depending on whether there are still turns left
		int win = boardBrain.winCheck();
		
		//if turns are all used up and win is 0, win is set to 3
		//3 represents a Tie state
		if (turn >= 9) {
			win = 3;
		}
		
		//if win continues to be 0, game goes on
		//if not, game has ended
		boolean gameHasEnded = win > 0;
		if (gameHasEnded) {
			winHandler(win);	
		}

	}

	/**
	 * Method winHandler(int win)
	 * Roles:
	 * 1) Handles end of game interaction of prompting another game
	 * 2) Tells player outcome of previous match
	 * 3) Calls reset() to reset game
	 * @param win: boardBrain's evaluation of last game's board state
	 * @param message: boardBrain's evaluation translated into String format
	 */
	private void winHandler(int win) {
		
		//message translates win variable into english
		String message = "";
		
		//if win is:
		//1, X has won
		//2, O has won
		//3, Game is tied
		if (win == 1) {
			message = "X has won";
		} else if (win == 2) {
			message = "O has won";
		} else {
			message = "Tie";
		}
		
		//Display a yes/no/cancel prompt if user wants to play again
		int response = JOptionPane.showConfirmDialog(null, message + "!! Play Again?");
		
		//0 for yes, 1 for no, 2 for cancel
		boolean yes = response == 0;
		if (yes) {
			reset();
		} else {
			System.exit(0);
		}
		
	}
	
	/**
	 * Method reset()
	 * Roles:
	 * 1) Wipes boardBrain's region multi-dimensional array
	 * 2) Starts new game and goes back to 0 turns completed
	 */
	private void reset() {
		
		//wipes 2D region array of boardBrain
		boardBrain.resetRegion();
		
		//start new game
		turn = 0;
		playNewGame();
		
	}
	
	/**
	 * Method playGame()
	 * Roles:
	 * 1) Clears DrawingPanel and draws new clean background
	 * 2) Sets up mouseClick recognizer and passes mouseClick coordinates into clickHandler(int sx, int sy)
	 */
	private void playNewGame() {

		//sets the main Font size for the X and O values for display in game
		g.setFont(new Font("Sanserif", Font.PLAIN, 400));
		p.clear();
		
		//draw lines for background
		drawBackground();
		
		setTypeOfGame();
		
	}
	
	private void setTypeOfGame() {
		
		typeOfGame = JOptionPane.showConfirmDialog(null, "Do you want to play against AI?");
		
		if (typeOfGame == 1) {
			p.onClick( (x,y) ->	 hvhClickHandler(x, y));
		} else if (typeOfGame == 0){
			AIisGoingFirst = JOptionPane.showConfirmDialog(null, "Do you want to go first?");
			if (AIisGoingFirst == 0) {
				//Human goes first
				turn = 0;
			} else {
				//AI goes first
				turn = -1;
			}
			p.onClick( (x,y) ->	 avhClickHandler(x, y));
		}
		
	}

	/**
	 * Method drawBackground()
	 * Roles:
	 * 1) Draws the background
	 */
	private void drawBackground() {
		g.drawLine(0, regionDimension, DIMENSION, regionDimension);
		g.drawLine(0, 2 * regionDimension, DIMENSION, 2 * regionDimension);
		g.drawLine(regionDimension, 0, regionDimension, DIMENSION);
		g.drawLine( 2 * regionDimension, 0, 2 * regionDimension, DIMENSION);
	}
}