package org.agilissimo.relt;
import java.awt.geom.Point2D;


public class RectArea {
	double sizeX;
	double sizeY;
	
	Point2D topLeft,topRight,bottomLeft,bottomRight;

	public double getSizeX() {
		return sizeX;
	}

	public void setSizeX(double d) {
		this.sizeX = d;
	}

	public double getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public Point2D getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Point2D topLeft) {
		this.topLeft = topLeft;
		this.setSizeX(topRight.getX()-topLeft.getX());
	}

	public Point2D getTopRight() {
		return topRight;
	}

	public void setTopRight(Point2D topRight) {
		this.topRight = topRight;
	}

	public Point2D getBottomLeft() {
		return bottomLeft;
	}

	public void setBottomLeft(Point2D bottomLeft) {
		this.bottomLeft = bottomLeft;
	}

	public Point2D getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(Point2D bottomRight) {
		this.bottomRight = bottomRight;
	}

	
}
