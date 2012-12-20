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

/**
 * Helper class to track the left and right edges of a level and draw the nodes
 * @author ios
 *
 */
class LineTracker {
	private Point up;
	private Point left;

	private Point corner;
	private Point currentPosition;
	private Point previousPosition;
	private Boolean vertical;

	public LineTracker() {
		up = new Point();
		left = new Point();
		corner = new Point();

		currentPosition = new Point();
		previousPosition = new Point();

		corner.setLocation(0,0);
		currentPosition.setLocation(0,0);
		vertical=true;
	}

	protected Point move(int step) {

		if (vertical) {
			float cy = (int) currentPosition.getY();
			// check if we still moving along the vertical line
			if ( cy+step < corner.getY()) {
				currentPosition.setLocation(currentPosition.getX(), cy+step);
			}
			else {
				// calculate how much we are already in the horizontal line
				float dif = (float) (cy+step - corner.getY());
				currentPosition.setLocation(corner.getX()-dif, corner.getY());
				vertical=false;
			}
		}
		else {
			float cx = (int) currentPosition.getX();
			// check if we still moving along the vertical line
			if ( cx-step >0) {
				currentPosition.setLocation(cx-step, currentPosition.getY());
			}
			else {
				currentPosition.setLocation(left.getX(), currentPosition.getY());
			}


		}
		return currentPosition;
	}
	protected Point getCorner() {
		return corner;
	}
	protected void setCorner(Point corner) {
		this.corner.setLocation(corner);
		// the up location has the same x-coordinate
		this.up.setLocation(corner.getX(), up.getY());
		this.setCurrentPosition(new Point((int) up.getX(),(int) up.getY()));
		// the bottom left location has the same y-coordinate
		this.left.setLocation(left.getX(), corner.getY());

	}
	protected Point getCurrentPosition() {
		return currentPosition;
	}
	
	protected Point getPreviousPosition() {
		return this.previousPosition;
	}
	
	protected void setCurrentPosition(Point currentPosition) {
		this.previousPosition.setLocation(this.currentPosition);
		this.currentPosition.setLocation(currentPosition);
	}

	protected Point getUp() {
		return up;
	}

	protected void setUp(Point up) {
		this.up.setLocation(up);
		this.corner.setLocation(up.getX(), corner.getY());
		this.currentPosition.setLocation(up);

	}

	protected Point getLeft() {
		return left;
	}

	protected void setLeft(Point left) {
		this.left.setLocation(left);
		this.corner.setLocation(corner.getX(),left.getY());
	}
	
	protected void setLeft(int x, int y) {
		this.setLeft(new Point(x,y));
	}


}