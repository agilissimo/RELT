package org.agilissimo.relt;
import java.util.ArrayList;

import org.agilissimo.tree.BasicNode;


public class NodeArea extends BasicNode{

	private String label;
	private Point labelPosition;
	
	private float orientation;
	
	ArrayList<Line> lines;
	
	public NodeArea() {
		lines = new ArrayList<Line>();
		labelPosition = new Point();
		label = new String();
	}
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
