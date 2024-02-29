package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake extends Polygon implements KeyListener{
	private int currentLength;
	private int stepSize = 20;
	private Graphics brush;
	private SnakeGame game;
	private Point[] inShape;
	
	public Snake(Point[] inShape, Point inPosition, double inRotation, Graphics brush
			,SnakeGame game) {
		super(inShape, inPosition, inRotation);
		currentLength = 0;
		this.brush = brush;
		this.game = game;
		this.inShape = inShape;
	}
	
	 public void setGraphics(Graphics brush) {
	        this.brush = brush;
	    }
	
	public int getCurrentLength() {
		return currentLength;
	}
	
	public void addLength() {
		currentLength++;
	}
	
	public Point[] getBody() {
		return inShape;
	}
	
	public void paint(Graphics brush) {
		Point[] array = this.getPoints();
		int[] arrayX = new int[array.length];
		int[] arrayY = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			arrayX[i] = (int)array[i].getX();
			arrayY[i] = (int)array[i].getY();
		}
		
		brush.setColor(Color.green);
		brush.fillPolygon(arrayX, arrayY, array.length);
    }
	
	public void move(int x, int y) {
	    for (Point point : getBody()) {
	    	point.setX(point.getX() + x);
	    	point.setY(point.getY() + y);
	    }
	    
	    paint(brush);
	    game.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();

		    // Update the direction based on the key pressed
		    if (key == KeyEvent.VK_DOWN) {
		        move(0, stepSize); // Move UP
		    } else if (key == KeyEvent.VK_RIGHT) {
		        move(stepSize, 0); // Move RIGHT
		    } else if (key == KeyEvent.VK_UP) {
		        move(0, -stepSize); // Move DOWN
		    } else if (key == KeyEvent.VK_LEFT) {
		        move(-stepSize, 0); // Move LEFT
		    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
