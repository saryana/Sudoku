import java.awt.*;

public class GridGamePanel extends GamePanel {

	protected static int DEFAULT_WIDTH = 500, DEFAULT_HEIGHT = 500;
	protected static int sideBar = 175;
	protected static int underBar = 40;
	protected static Stroke THICK_STROKE = new BasicStroke(7),
			THIN_STROKE = new BasicStroke(2);
	protected int dim;
	protected int width, height;
	protected int squareWidth, squareHeight;
	protected int mouseX, mouseY;
	protected String underMessage = "Setting game up...";
	protected String game = "Game Status:", status = "Not Complete",
			soFar = "So Far:", option4 = "", delete = "'esc' to delete";
	protected int[][] board;

	public static void main(String[] args) {
		new GridGamePanel(9);
	}
	public GridGamePanel(int dim) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, dim);
	}

	public GridGamePanel(int width, int height, int dim) {
		super(width + sideBar, height + underBar);
		this.dim = dim;
		this.width = width;
		this.height = height;
		this.squareHeight = height / dim;
		this.squareWidth = width / dim;
		board = new int[dim][dim];
		g.setFont(new Font(null, 21, 21));
	}

	public void update() {
		setMouse();
		drawBoard();
	}

	/**
	 * Draws the board
	 * need to put these in seperate methods
	 */
	private void drawBoard() {
		this.clear();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.blue);
		g.fillRect(mouseX * squareWidth, mouseY * squareHeight, squareWidth,
				squareHeight);

		for (int i = squareWidth, count = 0; i <= squareWidth * dim; i += squareWidth) {
			count++;
			if (count % 3 == 0) {
				g.setColor(Color.cyan);
				((Graphics2D) this.g).setStroke(THICK_STROKE);
			} else {
				g.setColor(Color.YELLOW);
				((Graphics2D) this.g).setStroke(THIN_STROKE);
			}

			g.drawLine(i, 0, i, height);

		}

		for (int i = squareHeight, count = 0; i <= squareHeight * dim; i += squareHeight) {
			count++;
			if (count % 3 == 0) {
				g.setColor(Color.cyan);
				((Graphics2D) this.g).setStroke(THICK_STROKE);
			} else {
				g.setColor(Color.YELLOW);
				((Graphics2D) this.g).setStroke(THIN_STROKE);
			}
			g.drawLine(0, i, width, i);
		}

		g.setColor(Color.PINK);
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				drawElement(x, y);
			}
		}

		g.setColor(Color.gray);
		g.fillRect(width - 2, 0, width + sideBar, height + underBar);
		g.fillRect(0, height - 2, width + sideBar, height + underBar);
		g.setColor(Color.black);
		g.drawString(underMessage, 10, height + 30);
		g.drawString(game, width + 10, 100);
		g.drawString(status, width + 10, 140);
		g.drawString(soFar, width+10, 300);
		g.drawString(option4, width+10, 330);
		g.drawString(delete, width + 10, 400);

	}

	/**
	 * Sets the mouse position
	 */
	private void setMouse() {
		if ((this.getMouseX() > 0 && this.getMouseX() < width)
				&& (this.getMouseY() > 0 && this.getMouseY() < height)) {
			mouseX = this.getMouseX() * dim / width;
			mouseY = this.getMouseY() * dim / height;
		}
	}

	// Override
	protected void drawElement(int x, int y) {}
	public void clicked() {}
	public void reset() {}
}
