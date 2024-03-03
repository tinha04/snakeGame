package game;
import java.awt.*;

//import game.Point;

//Edward Kelly and Tin Ha
public class Border extends Polygon{
//	private Graphics brush;
//	private Point[] inShape;
	public Border(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
//		this.brush = brush;
		
	}
	public void paint(Graphics brush) {
		Point[] array = this.getPoints();
		int[] arrayX = new int[array.length];
		int[] arrayY = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			arrayX[i] = (int)array[i].getX();
			arrayY[i] = (int)array[i].getY();
		}
		
		brush.setColor(Color.white);
		brush.fillPolygon(arrayX, arrayY, array.length);
    }
	public boolean collides(Point p) {
		Point[] points = this.getPoints();
		for(Point point: points) {
			if(p.getX() == point.getX() && p.getY() == point.getY()) {
				return true;
			}
		}
		return false;
	}
}
