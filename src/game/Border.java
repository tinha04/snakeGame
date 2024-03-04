package game;
import java.awt.*;

//import game.Point;

//Edward Kelly and Tin Ha
public class Border extends Polygon{
	
	public Border(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
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
	
	 // Inner class for initializing Border instances
    public static class BorderInitializer {
        public static Border upperBorder(int width, int height) {
            Point[] borderPoints = {new Point(0, 0), new Point(width + 50, 0),
                    new Point(width + 50, 20), new Point(0, 20)};
            Point position = new Point(150, 0);
            double rotation = 0.0;
            return new Border(borderPoints, position, rotation);
        }

        public static Border lowerBorder(int width, int height) {
            Point[] borderPoints = {new Point(0, 0), new Point(width + 50, 0),
                    new Point(width + 50, 20), new Point(0, 20)};
            Point position = new Point(150, height - 40);
            double rotation = 0.0;
            return new Border(borderPoints, position, rotation);
        }

        public static Border leftBorder(int width, int height) {
            Point[] borderPoints = {new Point(0, 0), new Point(0, height + 50),
                    new Point(20, height + 50), new Point(20, 0)};
            Point position = new Point(0, 70);
            double rotation = 0.0;
            return new Border(borderPoints, position, rotation);
        }

        public static Border rightBorder(int width, int height) {
            Point[] borderPoints = {new Point(0, 0), new Point(0, height + 50),
                    new Point(20, height + 50), new Point(20, 0)};
            Point position = new Point(width - 10, 70);
            double rotation = 0.0;
            return new Border(borderPoints, position, rotation);
        }
        
      

    }
}