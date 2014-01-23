import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SudokuPanel extends GridGamePanel {

	private static int Hpad = -5, Vpad = 10;
	protected boolean play;
	private ArrayList<String> b;
	// NEED TO ADD MORE BOARDS
	public static final String board1 = 
			"316578492\n"+
			"529134768\n"+
			"487629531\n"+
			"263415987\n"+
			"974863125\n"+
			"851792643\n"+
			"138947256\n"+
			"692351874\n"+
			"745286319";
	public static final String board2 = 
			"134298567\n"+
			"958671324\n"+
			"762345891\n"+
			"516487932\n"+
			"327169458\n"+
			"849532716\n"+
			"475916283\n"+
			"693824175\n"+
			"281753649";
	public static final String board3 = 
			"127354689\n"+
			"356928714\n"+
			"489176253\n"+
			"263415897\n"+
			"794863125\n"+
			"815792436\n"+
			"538247961\n"+
			"972631548\n"+
			"641589372";

	
	public static void main(String[] args) {
		new SudokuPanel();
	}

	public SudokuPanel() {
		super(9);
		underMessage = "'P' Play Game, 'L' Load, 'C' Check, 'S' solve, 'H' for hint(buggy)";
		b = new ArrayList<String>();
		b.add(board1);
		b.add(board2);
		b.add(board3);
		play = false;
	}

	/**
	 * Calls the action user pressed
	 */
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_L) {
			reset();
		} else if (key == KeyEvent.VK_P) {
			playRandom();
		} else if (key == KeyEvent.VK_C) {
			check();
		} else if (key == KeyEvent.VK_S) {
			solve();
		} else if (key == KeyEvent.VK_W) {
			write();
		} else if (key == KeyEvent.VK_H) {
			hint();
		}
	}

	/**
	 * Puts numbers in board
	 */
	public void update() {
		super.update();
		if (this.isKeyDown(KeyEvent.VK_1)) board[mouseX][mouseY] = 1;
		else if (this.isKeyDown(KeyEvent.VK_2)) board[mouseX][mouseY] = 2;
		else if (this.isKeyDown(KeyEvent.VK_3)) board[mouseX][mouseY] = 3;
		else if (this.isKeyDown(KeyEvent.VK_4)) board[mouseX][mouseY] = 4;
		else if (this.isKeyDown(KeyEvent.VK_5)) board[mouseX][mouseY] = 5;
		else if (this.isKeyDown(KeyEvent.VK_6)) board[mouseX][mouseY] = 6;
		else if (this.isKeyDown(KeyEvent.VK_7)) board[mouseX][mouseY] = 7;
		else if (this.isKeyDown(KeyEvent.VK_8)) board[mouseX][mouseY] = 8;
		else if (this.isKeyDown(KeyEvent.VK_9)) board[mouseX][mouseY] = 9;
		else if (this.isKeyDown(KeyEvent.VK_ESCAPE)) board[mouseX][mouseY] = 0;
	}
	
	/**
	 * Places number in box
	 */
	protected void drawElement(int x, int y) {
		if(play) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.white);
		}
		if (board[x][y]==1) 	  g.drawString("1", x*(squareWidth)+squareWidth/2 + Hpad, y*(squareHeight)+squareHeight/2 + Vpad);
		else if (board[x][y]==2) g.drawString("2", x*(squareWidth)+squareWidth/2 + Hpad, y*(squareHeight)+squareHeight/2 + Vpad);
		else if (board[x][y]==3) g.drawString("3", x*(squareWidth)+squareWidth/2 + Hpad, y*(squareHeight)+squareHeight/2 + Vpad);
		else if (board[x][y]==4) g.drawString("4", x*(squareWidth)+squareWidth/2 + Hpad, y*(squareHeight)+squareHeight/2 + Vpad);
		else if (board[x][y]==5) g.drawString("5", x*(squareWidth)+squareWidth/2 + Hpad, y*(squareHeight)+squareHeight/2 + Vpad);
		else if (board[x][y]==6) g.drawString("6", x*(squareWidth)+squareWidth/2 + Hpad, y*(squareHeight)+squareHeight/2 + Vpad);
		else if (board[x][y]==7) g.drawString("7", x*(squareWidth)+squareWidth/2 + Hpad, y*(squareHeight)+squareHeight/2 + Vpad);
		else if (board[x][y]==8) g.drawString("8", x*(squareWidth)+squareWidth/2 + Hpad, y*(squareHeight)+squareHeight/2 + Vpad);
		else if (board[x][y]==9) g.drawString("9", x*(squareWidth)+squareWidth/2 + Hpad, y*(squareHeight)+squareHeight/2 + Vpad);
	}
	
	/**
	 * Loads random board
	 */
	public void reset() {
		Random rand = new Random();
		int n = rand.nextInt(b.size()-1);
		load(b.get(n));
	}
	


	/**
	 * @param loadBoard - True board that it will load
	 */
	public void load(String loadBoard) {
		Scanner scan = new Scanner(loadBoard);
		int x = 0;
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			for (int y = 0; y < board[x].length; y++) {
				board[y][x] = Character.getNumericValue(line.charAt(y));
			}
			x++;
		}
		
	}

	// OVERRIDE these
	public void playRandom() {}
	public void check() {}
	public void solve() {}
	public void write() {}
	public void hint() {}
}
