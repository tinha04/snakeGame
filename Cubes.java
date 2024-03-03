package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//Edward Kelly and Tin Ha
public class Cubes extends Polygon{
	private Point[] inShape;
	private int stepSize = 5;
	private int type;
	private Color color;
	private Random rand;
	private int y;
//	private Snake s;
//	SnakeGame game;
	//20 + new Random().nextFloat()*510
	public Cubes(Point[] inShape, int type) {
		super(inShape, new Point(0,0), 0);
//		this.brush = brush;
		this.inShape = inShape;
		this.type = type;
//		this.game = game;
//		this.s = s;
//		int y = (int)(20 + new Random().nextFloat()*500);
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
//		case 1:

//			for (Point point : getBody()) {
//				if((point.getY()+stepSize) > 200) {
//					break;
//				}
//		    	point.setY(point.getY() + stepSize);
//
//		    }
//			break;
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
//		case 3:
//			for (Point point : getBody()) {
//				if((point.getY()-stepSize) < -5) {
//			
//				}
//		    	point.setY(point.getY() - stepSize);
//		    }
//			break;
//		}
	}
	public int collision(Snake s) {
//		s.getBody();
//		if(type == 0) {
			for(Point p : this.getBody()) {
				Point point = new Point(p.getX()/2, p.getY()/2);
				if(s.contains(point)) {
					System.out.println("this x= " + inShape[0].getX() + " " +inShape[0].getY());
					Point shape[] = s.getBody();
					System.out.println("the other thing = " + shape[0].getX() +" " + shape[0].getY());
						if(this.color == s.getColor()) {
							return 1;
						}
						else{
							return 2;
						}
						
					}
				}
//			}
		return 0;
		}
}
