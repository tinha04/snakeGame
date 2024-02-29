package game;

import java.util.Random;

public class Apple extends Polygon {

	public Apple(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}

    public Point getPosition() {
        return position;
    }

}
