package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class Cubes extends Polygon{
	private Point[] inShape;
	private int stepSize = 5;
	private int type;
	private Color color;
	private Random rand;
//	SnakeGame game;
	//20 + new Random().nextFloat()*510
	public Cubes(Point[] inShape, int x, int y, int type) {
		super(inShape, new Point(0, 20 + new Random().nextFloat()*510), 0);
//		this.brush = brush;
		this.inShape = inShape;
		this.type = type;
//		this.game = game;
		
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
						reset.setX(reset.getX()-1700);
					}
				}
					point.setX(point.getX() + stepSize);
			}
			break;
		case 1:

			for (Point point : getBody()) {
				if((point.getY()+stepSize) > 200) {
					break;
				}
		    	point.setY(point.getY() + stepSize);

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
		case 3:
			for (Point point : getBody()) {
				if((point.getY()-stepSize) < -5) {
			
				}
		    	point.setY(point.getY() - stepSize);
		    }
			break;
		}
	}
	
}
