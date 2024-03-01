package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Snake extends Polygon implements KeyListener{
	private int currentLength;
	private int stepSize = 5;
	private Graphics brush;
	private SnakeGame game;
	private Point[] inShape;
	private int currentDirection;
	private boolean isOver = false;
	private Point pos;
	int speed = 10;
	Color color = Color.green;
	
	public Snake(Point[] inShape, Point inPosition, double inRotation, Graphics brush
			,SnakeGame game) {
		super(inShape, inPosition, inRotation);
		this.pos = inPosition;
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
	        	if(!isOver) {
	        		move(); //Call a method to move the snake
	        		game.repaint();
	        	} else {
//	            	brush.setColor(Color.black);
//	            	brush.fillRect(200,200,400,400);
//	            	speed = 200;
//	            	return;
	        	}
	        }
	    }, 100, speed); //Change the second parameter to control the speed of the snake
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
		
		brush.setColor(color);
		brush.fillPolygon(arrayX, arrayY, array.length);
    }
	
	public void move() {
		if(isOver) {
			
			gameOver();
			return;
		}
		switch (currentDirection){
		case 0:
			for (Point point : getBody()) {
				if(point.getX() + stepSize > ((game.getWidth()*2) -110)) {
					isOver = true;
					break;
				}
					point.setX(point.getX() + stepSize);
			}
			break;
		case 1:

			for (Point point : getBody()) {
				if((point.getY()+stepSize) > game.getHeight()*2-150) {
					isOver = true;
					break;
				}
		    	point.setY(point.getY() + stepSize);

		    }
			break;
		case 2:
			for (Point point : getBody()) {
				if((point.getX() - stepSize) < -10) {
					isOver = true;
					break;
				}
		    	point.setX(point.getX() - stepSize);
		    }
			break;
		case 3:
			for (Point point : getBody()) {
				if((point.getY()-stepSize) < -5) {
					isOver = true;
					break;
				}
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
		 //&& currentDirection != 3
		 if (key == KeyEvent.VK_DOWN ) {
		    currentDirection = 1;
		     
		 } else if (key == KeyEvent.VK_RIGHT) {
			 currentDirection = 0;
		     
		 } else if (key == KeyEvent.VK_UP) {
		    currentDirection = 3;
		     
		 } else if (key == KeyEvent.VK_LEFT) {
		     currentDirection = 2;
		 }
		 else if(key == KeyEvent.VK_SPACE) {
			 if(color == color.green) {
				 color = color.red;
			 }else {
				 color = color.green;
			 }
			 
			 this.paint(brush);
//			 game.repaint();
		 }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	public void gameOver() {
		brush.setColor(Color.red);
		paint(brush);
	}
	public boolean collidesWithBorder(Point p) {
		if(game.rightBorder.collides(p) || game.leftBorder.collides(p) 
				|| game.upperBorder.collides(p) || game.lowerBorder.collides(p)) {
			return true;
		}
		return false;
	}
}
