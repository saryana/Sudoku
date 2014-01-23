import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;


public class GamePanel extends DrawingPanel {

	private boolean mouseDown;
	private int mouseX, mouseY;
	protected Graphics g;
	private Set<Integer> keysDown;
	
	public GamePanel(int width, int height) {
		super(width, height);
		g = this.getGraphics();
		keysDown = new HashSet<Integer>();
	}

	/**
	 * Current mouse position
	 */
	public void mouseMoved(MouseEvent arg0) {
		mouseX = arg0.getX();
		mouseY = arg0.getY();
	}
	
	/**
	 * Mouse pressed
	 */
	public void mousePressed(MouseEvent arg0) {
		mouseDown = true;
	}
	
	/**
	 * Mouse released
	 */
	public void mouseReleased(MouseEvent arg0) {
		mouseDown = false;
	}
	
	public void keyPressed(KeyEvent arg0) {
		keysDown.add(arg0.getKeyCode());
	}
	
	public void keyReleased(KeyEvent arg0) {
		keysDown.remove(arg0.getKeyCode());
	}
	
	
	/**
	 * Clears the board
	 */
	public void clear() {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
	}
	
	/**
	 * @return- If mouse is pressed
	 */
	public boolean mouseDown() {
		return mouseDown;
	}
	
	/**
	 * @return - X position of mouse
	 */
	public int getMouseX() {
		return mouseX;
	}
	
	/**
	 * @return - Y position of mouse
	 */
	public int getMouseY() {
		return mouseY;
	}
	
	public boolean isKeyDown(int key) {
		return keysDown.contains(key);
	}
}
