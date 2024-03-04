package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//Edward Kelly and Tin Ha
public class Snake extends Polygon implements KeyListener{
	private int stepSize = 5;
	private Graphics brush;
	private SnakeGame game;
	private Point[] inShape;
	private int currentDirection;
	private boolean isOver = false;
	int speed = 10;
	Color color = Color.green;
	
	public Snake(Point[] inShape, Point inPosition, double inRotation, Graphics brush, SnakeGame game) {
		super(inShape, inPosition, inRotation);
//		currentLength = 0;
		this.brush = brush;
		this.game = game;
		this.inShape = inShape;
		currentDirection = 0;
		//0: Right //1: Up //2: Left //3: Down
		
		for(Point p: inShape) {
			p.setX(p.getX()+35);
			p.setY(p.getY()+35);
		}
		//Schedule a task to move the snake every 200 milliseconds
	    Timer timer = new Timer(true);
	    timer.scheduleAtFixedRate(new TimerTask() {
	        @Override
	        public void run() {
	        	if(!isOver) {
	        		move(); //Call a method to move the snake
	        	} else {
	        		resetSnake();
	        		isOver = false;
	        	}
	        }
	    }, 100, speed); //Change the second parameter to control the speed of the snake
	}
	
	 public void setGraphics(Graphics brush) {
		 this.brush = brush;
		    if (brush != null) {
		        paint(brush);
		    }
	    }
	public void resetSnake() {
		inShape[0] = new Point(35,35);
		inShape[1] = new Point(55,35);
		inShape[2] = new Point(55, 55);
		inShape[3] = new Point(35,55);
		currentDirection = 0;
	}
//	public int getCurrentLength() {
//		return currentLength;
//	}
	
//	public void addLength() {
//		currentLength++;
//	}
	
	public Point[] getBody() {
		Point[] newA = new Point[inShape.length];
		for(int i = 0; i < inShape.length; i++) {
			Point p1 = inShape[i];
			newA[i] = p1;
		}
		return newA;
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
			for (Point point : inShape) {
				if(point.getX() + stepSize > game.getWidth()*2-60) {
					isOver = true;
					break;
				}
					point.setX(point.getX() + stepSize);
			}
			break;
		case 1:

			for (Point point : inShape) {
				if((point.getY()+stepSize) > game.getHeight()*2-100) {
					isOver = true;
					break;
				}
		    	point.setY(point.getY() + stepSize);

		    }
			break;
		case 2:
			for (Point point : inShape) {
				if((point.getX() - stepSize) < 20) {
					isOver = true;
					break;
				}
		    	point.setX(point.getX() - stepSize);
		    }
			break;
		case 3:
			for (Point point : inShape) {
				if((point.getY()-stepSize) < 20) {
					isOver = true;
					break;
				}
		    	point.setY(point.getY() - stepSize);
		    }
			break;
		}
		
	    paint(brush);
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 int key = e.getKeyCode();

		 // Update the direction based on the key pressed
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
		 }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	public void gameOver() {
		brush.setColor(Color.red);
		paint(brush);
	}

	public Color getColor(){
		return color;
	}
	
	public class Cubes extends Polygon {
		private Point[] inShape;
		private int stepSize = 5;
		private int type;
		private Color color;

		public Cubes(Point[] inShape, int type) {
			super(inShape, new Point(0,0), 0);
			this.inShape = inShape;
			this.type = type;
			int y = (int)(40 + new Random().nextFloat()*1020);
			inShape[0] = new Point(0, y);
			inShape[1] = new Point(20, y);
			inShape[2] = new Point(20, y+20);
			inShape[3] = new Point(0, y+20);
			if(Math.round(Math.random()) == 0) {
				color = Color.red;
			}else {
				color = Color.green;
			}
			
			
		    Timer timer = new Timer(true);
		    timer.scheduleAtFixedRate(new TimerTask() {
		        @Override
		        public void run() {
		        	move();
		        }
		    }, 100, 10); //Change the second parameter to control the speed of the snake
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
		
		public Point[] getBody() {
			return inShape;
		}
		
		public void move() {
			switch (type){
			case 0:
				for (Point point : getBody()) {
					if(point.getX() + stepSize > 1700) {
						for(Point reset: getBody()) {
							reset.setX(reset.getX()-1710);
						}
					}
						point.setX(point.getX() + stepSize);
				}
				break;
			case 2:
				for (Point point : getBody()) {
					if((point.getX() - stepSize) < -10) {
						for(Point reset: getBody()) {
							reset.setX(reset.getX()+1700);
						}		
					}
			    	point.setX(point.getX() - stepSize);
			    }
				break;
			}
		}
		
		public int collision(Snake s) {
			for(Point p : this.getBody()) {
				Point point = new Point(p.getX()/2, p.getY()/2);
				if(s.contains(point)) {
						if(this.color == s.getColor()) {
							return 1;
						}
						else{
							return 2;
						}
						
					}
				}
			return 0;
		}
	}
}