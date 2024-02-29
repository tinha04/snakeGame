package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Snake extends Polygon implements KeyListener{
	private int currentLength;
	private int stepSize = 25;
	private Graphics brush;
	private SnakeGame game;
	private Point[] inShape;
	private int currentDirection;
	
	public Snake(Point[] inShape, Point inPosition, double inRotation, Graphics brush
			,SnakeGame game) {
		super(inShape, inPosition, inRotation);
		currentLength = 0;
		this.brush = brush;
		this.game = game;
		this.inShape = inShape;
		currentDirection = 0;
		//0: Right //1: Up //2: Left //3: Down
		
		//Schedule a task to move the snake every 200 milliseconds
	    Timer timer = new Timer(true);
	    timer.scheduleAtFixedRate(new TimerTask() {
	        @Override
	        public void run() {
	            move(); //Call a method to move the snake
	            game.repaint();
	        }
	    }, 100, 400); //Change the second parameter to control the speed of the snake
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
	
	public void move() {
		switch (currentDirection){
		case 0:
			for (Point point : getBody()) {
		    	point.setX(point.getX() + stepSize);
		    }
			break;
		case 1:
			for (Point point : getBody()) {
		    	point.setY(point.getY() + stepSize);
		    }
			break;
		case 2:
			for (Point point : getBody()) {
		    	point.setX(point.getX() - stepSize);
		    }
			break;
		case 3:
			for (Point point : getBody()) {
		    	point.setY(point.getY() - stepSize);
		    }
			break;
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
		    currentDirection = 1;
		     
		 } else if (key == KeyEvent.VK_RIGHT) {
			 currentDirection = 0;
		     
		 } else if (key == KeyEvent.VK_UP) {
		    currentDirection = 3;
		     
		 } else if (key == KeyEvent.VK_LEFT) {
		     currentDirection = 2;
		     
		 }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
