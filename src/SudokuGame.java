import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;

public class SudokuGame extends SudokuPanel {

	// Sets diffictuly for the game
	public static int diff;

	public static void main(String[] args) {
		int n = JOptionPane.showConfirmDialog(null,
				"Welcome to Suduku! Want to play?");
		// If player wants to play or not
		if (n != JOptionPane.NO_OPTION && n != JOptionPane.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null,
					"Mouse over square and press 1-9 to set number.");
			diff = Integer.parseInt(JOptionPane
					.showInputDialog("What difficulty? (1-5)"));
			if (diff < 1 || diff > 5) {
				JOptionPane.showMessageDialog(null, "You broke the game");
			} else {
				new SudokuGame();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Sorry, your loss");
		}
	}
	

	/**
	 * Loads a random game and removes a random amount of options
	 */
	public void playRandom() {
		super.reset();
		Random rand = new Random();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (rand.nextInt(diff+1)+1 != 1) {
					board[i][j] = 0;
				}
			}
		}
		play = true;
	}

	/**
	 * Checks to see the it is a valid game
	 */
	public void check() {
		if (checkBoard()) {
			status = "Solved!";
		} else {
			status = "Not Complete";
		}
	}

	/**
	 * Checks rows columns and boxes
	 * @return Valid board
	 */
	private boolean checkBoard() {
		for (int i = 0; i < board.length; i++) {
			Set<Integer> rowCheck = new HashSet<Integer>();
			Set<Integer> colCheck = new HashSet<Integer>();
			// Set<Integer> boxCheck = new HashSet<Integer>();
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 0 || board[j][i] == 0) {
					return false;
				}
				if (!rowCheck.contains(board[i][j])) {
					rowCheck.add(board[i][j]);
				} else {
					return false;
				}
				if (!colCheck.contains(board[j][i])) {
					colCheck.add(board[j][i]);
				} else {
					return false;
				}
				// if(!boxCheck.contains(_board[j-j%3][j]))

			}
			Set<Integer> box = new HashSet<Integer>();
			for (int r = i - i % 3; r < i - i % 3 + 3; r++) {
				for (int c = i % 3 * 3; c < i % 3 * 3 + 3; c++) {
					if (board[r][c] != 0 && !box.contains(board[r][c])) {
						box.add(board[r][c]);
					} else {
						return false;
					}
				}
			}

		}

		return true;
	}

	/**
	 * Solves current Sudoku board (if possible) Called when (S) is pressed.
	 */
	@Override
	public void solve() {
		if (checkBoard()) {
			status ="Game is finished!";
		} else {
			if (!checkBoard() && solve(0, 0)) {
				status = "Solved!";
			} else {
				status= "Not Possible!";
			}
		}
	}

	/**
	 * Solves the remaining Suduku board
	 * @param row - Row to test
	 * @param col - Column to test
	 * @return - If it is a working solution or not
	 */
	private boolean solve(int row, int col) {
		if (col == 9) {
			col = 0;
			row++;
			if (row == 9) {
				return true && checkBoard();
			}

		}
		if (board[row][col] != 0) {
			return solve(row, col + 1);
		}
		for (int i : possible(row, col)) {
			board[row][col] = i;
			if (solve(row, col + 1)) {
				return true && checkBoard();
			}
		}
		board[row][col] = 0;
		return false;
	}

	/**
	 * Checks either row or column to see if they number can work there
	 * @param num - Number being tested
	 * @param rowOrCol - The row or column being tested
	 * @param isRow - If it checking the row or column
	 * @return - If the row or column can contain the number
	 */
	private boolean roc(int num, int rowOrCol, boolean isRow) {
		for (int other = 0; other < 9; other++) {
			if (isRow && board[rowOrCol][other] == num) {
				return true;
			} else if (!isRow && board[other][rowOrCol] == num) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checking to see if box contains number
	 * @param num - Number being tested
	 * @param ro - Row being tested
	 * @param co - Column being tested
	 * @return If that is a valid option or not
	 */
	private boolean boxContains(int num, int ro, int co) {
		int rowStart = ro - ro % 3;
		int colStart = co - co % 3;
		for (int r = rowStart; r < rowStart + 3; r++) {
			for (int c = colStart; c < colStart + 3; c++) {
				if (board[r][c] == num) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param row - Current row being tested
	 * @param col - Current col being tested
	 * @return - All possible number that work in the spot
	 */
	public Set<Integer> possible(int row, int col) {
		Set<Integer> possible = new TreeSet<Integer>();
		for (int i = 1; i <= 9; i++) {
			if (!roc(i, row, true) && !roc(i, col, false)
					&& !boxContains(i, row, col)) {
				possible.add(i);
			}
		}
		return possible;
	}
	
	public void hint() {
		int[][] temp = board;
		
		if(solve(0,0)) {
			option4 = "Correct";
		} else {
			option4 = "Not quite";
		}
		board = temp;
	}
	
	public void write() {
		for(int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}