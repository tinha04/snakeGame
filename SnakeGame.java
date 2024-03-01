package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.util.Random;


public class SnakeGame extends Game{
	static int counter = 0;
	static int width = 800;
	static int height = 600;
	
	private Snake snake;
	Border upperBorder;
	Border lowerBorder;
	Border leftBorder;
	Border rightBorder;
	
	Cubes[] listR;
	Cubes cFromRight;
	
	Cubes[] listL;
	Cubes cFromLeft;
	
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
	Point pos1 = new Point(22,20);
	

	
	snake = new Snake(arr1, pos1, 0, getGraphics(), this);
	this.addKeyListener(snake);
	

	listR = new Cubes[3];
	for(int i = 0; i < 3; i++) {
		Point[] arr2 = {new Point(0,0), new Point(20,0),
				new Point(20,20),new Point(0,20)};
		int y = (int) (20 + new Random().nextFloat()*510);
		cFromRight = new Cubes(arr2, 0, y, 0);	
		listR[i] = cFromRight;
	}
	
	listL = new Cubes[3];
	for(int i = 0; i < 3; i++) {
		Point[] arr2 = {new Point(0,0), new Point(20,0),
				new Point(20,20),new Point(0,20)};
		int y = (int) (20 + new Random().nextFloat()*510);
		cFromLeft = new Cubes(arr2, 100, y, 2);	
		listL[i] = cFromLeft;
	}

	
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
    	counter++;
    	brush.setColor(Color.black);
    	brush.drawString("Counter is " + counter,10,10);
  }
	
	public static void main (String[] args) {
		SnakeGame a = new SnakeGame();
		a.repaint();
  }
}