package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;

import game.Border.BorderInitializer;
import game.Snake.Cubes;

//Edward Kelly and Tin Ha
public class SnakeGame extends Game{
	static int counter = 0;
	static int width = 800;
	static int height = 600;
	
	private Snake snake;
	private Score score;
	
	private Border upperBorder;
	private Border lowerBorder;
	private Border leftBorder;
	private Border rightBorder;
	
	private Cubes[] listR;
	private Cubes cFromRight;
	private Cubes[] listL;
	private Cubes cFromLeft;
	private Cubes[] listU;
	private Cubes cFromUp;
	private Cubes[] listB;
	private Cubes cFromBottom;
	
	private Collision collision = () -> {
		for(int i = 0; i<listR.length; i++) {
    		if(listR[i].collision(snake) == 1) {
    			Point[] arr2 = {new Point(0,0), new Point(20,0),
    					new Point(20,20),new Point(0,20)};
    			listR[i] = snake.new Cubes(arr2, 0);	
    			score.addScore();
    		} else if(listR[i].collision(snake) == 2) {
    			snake.resetSnake();
    			score.resetScore();
    		}
    	}
		
    	for(int i = 0; i<listL.length; i++) {
    		if(listL[i].collision(snake) == 1) {
    			Point[] arr2 = {new Point(0,0), new Point(20,0),
    					new Point(20,20),new Point(0,20)};
    			listL[i] = snake.new Cubes(arr2,2);	
    			score.addScore();
    		} else if(listL[i].collision(snake) == 2) {
    			snake.resetSnake();
    			score.resetScore();
    		}
    	}
	};
	
  public SnakeGame() {
    super("Snake",width,height);
    this.setFocusable(true);
	this.requestFocus();
	
	score = new Score(0);
	
	Point[] arr1 = {new Point(0,0), new Point(20,0),
		    new Point(20,20),new Point(0,20)};
	Point pos1 = new Point(0,0);
	snake = new Snake(arr1, pos1, 0, getGraphics(), this);
	this.addKeyListener(snake);
	
	upperBorder = BorderInitializer.upperBorder(width, height);
	lowerBorder = BorderInitializer.lowerBorder(width, height);
	leftBorder = BorderInitializer.leftBorder(width, height);
	rightBorder = BorderInitializer.rightBorder(width, height);
	
	listR = new Cubes[3];
	for(int i = 0; i < listR.length; i++) {
		Point[] arr2 = {new Point(0,0), new Point(20,0),
				new Point(20,20),new Point(0,20)};
		cFromRight = snake.new Cubes(arr2,0);	
		listR[i] = cFromRight;
	}
	
	listL = new Cubes[3];
	for(int i = 0; i < listL.length; i++) {
		Point[] arr2 = {new Point(0,0), new Point(20,0),
				new Point(20,20),new Point(0,20)};
		cFromLeft = snake.new Cubes(arr2, 2);	
		listL[i] = cFromLeft;
	}	
	
	listU = new Cubes[3];
	for(int i = 0; i < listU.length; i++) {
		Point[] arr2 = {new Point(0,0), new Point(20,0),
				new Point(20,20),new Point(0,20)};
		cFromUp = snake.new Cubes(arr2,0);	
		listU[i] = cFromUp;
	}
	
	listB = new Cubes[3];
	for(int i = 0; i < listB.length; i++) {
		Point[] arr2 = {new Point(0,0), new Point(20,0),
				new Point(20,20),new Point(0,20)};
		cFromBottom = snake.new Cubes(arr2, 2);	
		listB[i] = cFromBottom;
	}	
  }
  
	public void paint(Graphics brush) {
		 // Draw the checker board background
	    for (int x = 0; x < width; x += 20) {
	        for (int y = 0; y < height; y += 20) {
	            Color currentColor = ((x / 20) + (y / 20)) % 2 == 0 
	            		? Color.lightGray : Color.BLACK;
	            brush.setColor(currentColor);
	            brush.fillRect(x, y, 20, 20);
	        }
	    }

    	upperBorder.paint(brush);
    	lowerBorder.paint(brush);
    	leftBorder.paint(brush);
    	rightBorder.paint(brush);
    	
    	for(int i = 0; i<listR.length; i++) {
    		listR[i].paint(brush);
    	}
    	for(int i = 0; i<listL.length; i++) {
    		listL[i].paint(brush);
    	}
    		
    	snake.paint(brush);
    	collision.action();
    	
    	counter++;
    	brush.setColor(Color.black);
    	brush.drawString("Counter is " + counter,10,10);
    	brush.drawString("Score is " + score.getScore(), width-100, 10);
  }
	
	public static void main (String[] args) {
		SnakeGame a = new SnakeGame();
		a.repaint();
  }
}