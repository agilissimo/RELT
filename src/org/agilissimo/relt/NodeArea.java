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
import java.util.ArrayList;

import org.agilissimo.tree.BasicNode;


/**
 * This is the class used to store the area for a node
 * It mainly contains a list of lines that form the area
 * and a label
 * @author ios
 *
 */
public class NodeArea extends BasicNode{


	// the label for the node
	private String label;
	// the position of the label on the "screen"
	private Point labelPosition;
	// the orientation of the label
	private float orientation;
	
	// the list of lines that form the area
	ArrayList<Line> lines;
	
	/**
	 * Constructor
	 */
	public NodeArea() {
		lines = new ArrayList<Line>();
		labelPosition = new Point();
		label = new String();
	}
	/**
	 * Adds a new line in the list of lines
	 * @param e the line
	 */
	public void addLine(Line e) {
		Line line = new Line();
		line.setLine(e);
		lines.add(line);
	}
	
	public ArrayList<Line> getLines() {
		return this.lines;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Point getLabelPosition() {
		return labelPosition;
	}
	public void setLabelPosition(Point labelPosition) {
		this.labelPosition.setLocation(labelPosition);
	}
	public float getOrientation() {
		return orientation;
	}
	public void setOrientation(float orientation) {
		this.orientation = orientation;
	}
	
}
