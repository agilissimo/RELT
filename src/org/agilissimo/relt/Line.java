package org.agilissimo.relt;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Line extends Line2D {
	Line2D line2d;
	Point startP;
	Point endP;
	
	public Line() {
		startP = new Point();
		endP = new Point();
	}
	
	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point2D getP1() {
		return startP;
	}

	@Override
	public Point2D getP2() {
		return endP;
	}

	@Override
	public double getX1() {
		return startP.getX();
	}

	@Override
	public double getX2() {
		return endP.getX();
	}

	@Override
	public double getY1() {
		return startP.getY();
	}

	@Override
	public double getY2() {
		return endP.getY();
	}

	@Override
	public void setLine(double x1, double y1, double x2, double y2) {
		startP.setLocation(x1,y1);
		endP.setLocation(x2,y2);
	}
}
