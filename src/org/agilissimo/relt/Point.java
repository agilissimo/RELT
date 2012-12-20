package org.agilissimo.relt;
import java.awt.geom.Point2D;

/**
 * Helper class to store and manage 2D points
 * @author ios
 *
 */
public class Point extends Point2D {

	private double x;
	private double y;
	
	public Point() {
		x = y = 0;
	}
	
	public Point (Point point) {
		x = point.getX();
		y = point.getY();
	}
	
	public Point(int a, int b) {
		x = a;
		y = b;
	}
	
	public Point(double a, double b) {
		x = a;
		y = b;
	}
	
	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

}
