package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.util.Random;

//Edward Kelly and Tin Ha
public class SnakeGame extends Game{
	static int counter = 0;
	static int width = 800;
	static int height = 600;
	
	private Snake snake;
	private int score = 0;
	
	private Border upperBorder;
	private Border lowerBorder;
	private Border leftBorder;
	private Border rightBorder;
	
	private Cubes[] listR;
	private Cubes cFromRight;
	
	private Cubes[] listL;
	private Cubes cFromLeft;
	
  public SnakeGame() {
    super("Snake",width,height);
    this.setFocusable(true);
	this.requestFocus();
	
	Point[] border1 = {new Point(0,0), new Point(width+50, 0), 
			new Point(width +50, 20), new Point(0, 20)};
	Point one = new Point(150,0);
	upperBorder = new Border(border1, one, 0.0);
	
//	Point[] border2 = {new Point(width, 0), new Point(width-20,0),
//			new Point(width, height), new Point(width-20, height)};
	Point two = new Point(150,height-50);
	lowerBorder = new Border(border1, two, 0.0);
	
	Point[] border3 = {new Point(0,0), new Point(0, height+50), 
			new Point(20, height+50), new Point(20, 0)};
	Point three = new Point(0,70);
	leftBorder = new Border(border3, three, 0.0);
	
//	Point[] border4 = {new Point(0,height-20), new Point(0, height), 
//			new Point(width, height), new Point(width, height-20)};
	Point four = new Point(width-25,70);
	rightBorder = new Border(border3, four, 0.0);
	
	
	Point[] arr1 = {new Point(0,0), new Point(20,0),
				    new Point(20,20),new Point(0,20)};
	Point pos1 = new Point(0,0);
	
	listR = new Cubes[3];
	for(int i = 0; i < listR.length; i++) {
		Point[] arr2 = {new Point(0,0), new Point(20,0),
				new Point(20,20),new Point(0,20)};
//		int y = (int) (20 + new Random().nextFloat()*510);
		cFromRight = new Cubes(arr2,0);	
		listR[i] = cFromRight;
	}
	
	listL = new Cubes[3];
	for(int i = 0; i < listL.length; i++) {
		Point[] arr2 = {new Point(0,0), new Point(20,0),
				new Point(20,20),new Point(0,20)};
		cFromLeft = new Cubes(arr2, 2);	
		listL[i] = cFromLeft;
	}
	
	snake = new Snake(arr1, pos1, 0, getGraphics(), this);
	this.addKeyListener(snake);
	



	
//	Point[] arr2 = {new Point(0,0), new Point(20,0),
//			new Point(20,20),new Point(0,20)};
//	int y = (int) (20 + new Random().nextFloat()*510);
//	cFromLeft = new Cubes(arr2, 0, y, 0);
//	
  }
  public int getWidth() {
	  return width;
  }
  public int getHeight() {
	  return height;
  }
  public void addScore() {
	  this.score++;
  }
  public void resetScore() {
	  this.score = 0;
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted

    	
    	
    	brush.setColor(Color.green);
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
    	for(int i = 0; i<listR.length; i++) {
    		if(listR[i].collision(snake) == 1) {
    			Point[] arr2 = {new Point(0,0), new Point(20,0),
    					new Point(20,20),new Point(0,20)};
    			listR[i] = new Cubes(arr2,0);	
    			addScore();
    		} else if(listR[i].collision(snake) ==2) {
    			snake.resetSnake();
    			resetScore();
    		}
    	}
    	for(int i = 0; i<listL.length; i++) {
    		if(listL[i].collision(snake) == 1) {
    			Point[] arr2 = {new Point(0,0), new Point(20,0),
    					new Point(20,20),new Point(0,20)};
    			listL[i] = new Cubes(arr2,2);	
    			addScore();
    		} else if(listL[i].collision(snake) ==2) {
    			snake.resetSnake();
    			resetScore();
    		}
    	}
    	
    	
    	counter++;
    	brush.setColor(Color.black);
    	brush.drawString("Counter is " + counter,10,10);
    	
    	brush.drawString("Score is " + score, width-100, 10);
  }
	
	public static void main (String[] args) {
		SnakeGame a = new SnakeGame();
		a.repaint();
  }
}