/*
 * Copyright 2012 Ioannis Strikos
 * 
 * This file is part of agilissimo
 * agilissimo is free software: you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation, either version 3 of 
 * the License, or (at your option) any later version. You should have received a copy of the 
 * GNU General Public License along with agilissimo. 
 * If not, see http://www.gnu.org/licenses/.
 */
package org.agilissimo.relt;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Helper class to store 2D lines
 * @author ios
 *
 */
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
