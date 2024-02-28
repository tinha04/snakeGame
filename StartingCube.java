package game;
import java.awt.*;
public class StartingCube extends Polygon{
//	private Point pos;
//	private Point[] shape = {new Point(0,20),new Point(20,0), new Point(20,20), new Point(0,20)};
//	private Point p = new Point(100, 100);
	
	public StartingCube(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
//		shape = inShape;
//		pos = inPosition;
	}
	public void paint(Graphics brush) {
//		Graphics brush = YourGameName.getBrush();
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
		
	}
}
