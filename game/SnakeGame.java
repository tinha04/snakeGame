package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;


public class SnakeGame extends Game{
	static int counter = 0;
	static int width = 800;
	static int height = 600;
	
	private Snake snake;
	
  public SnakeGame() {
    super("Snake",width,height);
    this.setFocusable(true);
	this.requestFocus();
	
	Point[] arr1 = {new Point(0,0), new Point(20,0),
				    new Point(20,20),new Point(0,20)};
	Point pos1 = new Point(width/2,height/2);
	
	snake = new Snake(arr1, pos1, 0, getGraphics(), this);
	this.addKeyListener(snake);
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
    	
    	brush.setColor(Color.green);
    	snake.paint(brush);
  }
  
	public static void main (String[] args) {
		SnakeGame a = new SnakeGame();
		a.repaint();
  }
}