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
import java.util.Iterator;
import java.util.Stack;



import org.agilissimo.tree.ItemInjection;
import org.agilissimo.tree.Node;
import org.agilissimo.tree.Tree;
import org.apache.log4j.Logger;

/**
 * 
 * @author ios
 *
 */
public class Relt {

	private ArrayList<NodeArea> areas;
	private Tree tree;
	private RectArea screen;

	private LineTracker leftLineTracker = new LineTracker();
	private LineTracker rightLineTracker = new LineTracker();
	public Relt(Tree tree, RectArea screen) {
		areas = new ArrayList<NodeArea>();
		this.tree = tree;
		
		this.screen = screen;
	}

	public ArrayList<NodeArea> computeAreas() {
		
		float levelWeight;
		double stepX;
		int currentLevel;
		
		int width = (int)screen.getSizeX();
		int height =  (int)screen.getSizeY();
		Point currentLocation = new Point();
		Point currentY = new Point();

		// Initialisation
		this.areas.clear();
		calculateNodeWeights();
		currentLevel=0;
		currentLocation.setLocation(new Point(0,0));
		currentY.setLocation(new Point(0,0));
		
		// Process the levels of the tree
		levelWeight = tree.getLevelWeight(currentLevel)-1;
		//draw roor and get its children
		NodeArea area = drawRoot((int) (levelWeight*width));
		areas.add(area);
		leftLineTracker.setCorner(new Point((levelWeight*width),(levelWeight*height)));

		currentLevel = 1;
		levelWeight = tree.getLevelWeight(currentLevel)-1;
		int numOfChildren = tree.getRoot().numberOfChildren();
		stepX = (levelWeight*width);
		rightLineTracker.setCorner(new Point( (stepX+leftLineTracker.getCorner().getX()), (height*levelWeight)));
		drawChildren(tree.getRoot(), leftLineTracker, rightLineTracker);
		
		return areas;
	}
	

	
	public  void drawChildren(Node node, LineTracker left, LineTracker right) {
		LineTracker leftLineTracker = new LineTracker();;
		LineTracker rightLineTracker = new LineTracker();;
		NodeArea area;
		
		leftLineTracker.setCorner(left.getCorner());
		leftLineTracker.setUp(left.getUp());
		leftLineTracker.setLeft(left.getLeft());
		
		rightLineTracker.setCorner(right.getCorner());
		rightLineTracker.setUp(right.getUp());
		rightLineTracker.setLeft(right.getLeft());	
		
		int stepYRight, stepYLeft;

		Iterator iterator=node.iterator();

		Point previousA = new Point(leftLineTracker.getUp());
		Point previousB = new Point(rightLineTracker.getUp());

		Boolean first = true;
		// The length of the right line is the length of the horizontal line plus the length of the vertical line
		double totalLengthRight = (rightLineTracker.getCorner().getY()-rightLineTracker.getUp().getY() +rightLineTracker.getCorner().getX() - rightLineTracker.getLeft().getX());
		// Similar the length of the left line
		double totalLengthLeft =  (leftLineTracker.getCorner().getY()-leftLineTracker.getUp().getY()+leftLineTracker.getCorner().getX() - leftLineTracker.getLeft().getX());
		while(iterator.hasNext()) {
			area = new NodeArea();
			node = (Node) iterator.next();
			float nodeWeigth = ((ReltItem) node.getItem()).getWeight();
	
			// calculate the step down the lines depending on the importance/weight of the node
			stepYRight = (int) (totalLengthRight*nodeWeigth);
			stepYLeft = (int) ( totalLengthLeft*nodeWeigth);
			//drawLine(area, previousA, previousB);
			drawArea(area,leftLineTracker, rightLineTracker);
			// Now track lines
			Point a = leftLineTracker.move(stepYLeft);
			Point b = rightLineTracker.move(stepYRight);
			ReltItem item = (ReltItem) node.getItem();
			Point labelPosition = new Point((previousA.getX()+b.getX())/2, (previousA.getY()+b.getY())/2);

			area.setLabel(item.getLabel());
			area.setLabelPosition(labelPosition);
			double dy = b.getY() - a.getY();
			double dx = b.getX() - a.getX();
			double orientation = Math.atan2(dy,dx);
			orientation *= 180*Math.PI;
			area.setOrientation((float)orientation);
			drawLine(area, a,b);

			areas.add(area);	

			if (node.hasChildren()) {
				Point p = new Point();
				// if it is the first node we draw for this level
				if (first) {
					previousA.setLocation(leftLineTracker.getUp());
					previousB.setLocation(rightLineTracker.getUp());
					first = false;

				}
				
				float levelWeight = tree.getLevelWeight((node.getLevel())+1)-1;
				double stepX = levelWeight*screen.getSizeX();
				
				LineTracker tmpLeftLineTracker = new LineTracker();
				LineTracker tmpRightLineTracker = new LineTracker();
											
				tmpLeftLineTracker.setUp(previousB);
				p.setLocation(tmpLeftLineTracker.getUp().getX(),b.getY());
				tmpLeftLineTracker.setCorner(p);
				tmpLeftLineTracker.setLeft(b);

				p = computeIntersection(previousA, previousB,tmpLeftLineTracker.getUp().getX()+stepX );
	
				tmpRightLineTracker.setUp(p);
				
				p = computeIntersection(a, b, tmpRightLineTracker.getUp().getX());
				
				tmpRightLineTracker.setLeft(p);
				p.setLocation(tmpRightLineTracker.getUp().getX(), tmpRightLineTracker.getLeft().getY());
				tmpRightLineTracker.setCorner(p);

				drawChildren(node, tmpLeftLineTracker, tmpRightLineTracker);
			}
			previousA.setLocation(a);
			previousB.setLocation(b);
			first = false;
		}// end of while
	
	}
	
	/************************************ Private support function **************/
	private void drawArea(NodeArea area, LineTracker leftLineTracker, LineTracker rightLineTracker) {
		
		Point currentLocation = new Point();
		Point nextLocation = new Point();
		// Draw the level
		// First draw the upper line by determining 
		// the left and right ponts on the left and right lines
		currentLocation.setLocation(leftLineTracker.getUp().getX(),
									leftLineTracker.getUp().getY());
		nextLocation.setLocation(rightLineTracker.getUp().getX(),
								rightLineTracker.getUp().getY());
		// draw the horizontal from left to right
		drawLine(area,	currentLocation,nextLocation);
	
		// draw the vertical line from top to bottom
		drawLine(area, 	nextLocation, 
						rightLineTracker.getCorner());
		// draw the horizontal line from right to left 
		nextLocation.setLocation(rightLineTracker.getLeft().getX(),
								rightLineTracker.getCorner().getY());
		drawLine(area, rightLineTracker.getCorner(), nextLocation);
	}
	
	/**
	 * Calculates the intersection point of line a,b with the line (refX,0) , (refX, y) 
	 * so that we find the location of the next line tracker positions
	 * @param a Point a
	 * @param b Point b
	 * @param refX x used as a reference to compute the f(x) = x*slope +offset
	 * @return The interesection Point
	 */
	private Point computeIntersection(Point a, Point b, double refX) {
		Point p = new Point();
		double y;
		// find out the linear function between a and b
		double slope = (b.getY() - a.getY()) / (b.getX() - a.getX()) ;
		double offset = a.getY() - a.getX()*slope;
		y = refX*slope + offset;
		
		if (y > screen.getSizeY()) {
			y = screen.getSizeY();
			p.setLocation((y - offset)/slope,y); 
		}
		else {
			p.setLocation(refX,y);
		}
		return p;
	}

	// draws the first node of the tree
	private NodeArea drawRoot(int step) {
		Point nextLocation = new Point(step,0);
		Point currentLocation=new Point(0,0);

		NodeArea area = new NodeArea();
		// draw from left to right
		currentLocation.setLocation(drawLine(area, currentLocation,nextLocation));
		ReltItem item = (ReltItem) tree.getRoot().getItem();
		area.setLabel(item.getLabel());
		area.setLabelPosition(new Point(step/2,step/2-10));
		// draw from top to bottom
		nextLocation.setLocation(currentLocation.getX(),currentLocation.getY()+step);
		currentLocation.setLocation(drawLine(area, currentLocation,nextLocation));
		// draw from right to left
		nextLocation.setLocation(currentLocation.getX()-step,currentLocation.getY());
		currentLocation.setLocation(drawLine(area, currentLocation,nextLocation));
		//draw from bottom to top
		nextLocation.setLocation(currentLocation.getX(),currentLocation.getY()-step);
		drawLine(area, currentLocation,nextLocation);
		
		return area;
	}	
	
	/**
	 * Calculates the weights for the nodes based on the amount of children that 
	 * they have
	 */
	private void calculateNodeWeights() {
		Node n;
		Stack<Node> nodeStack = new Stack<Node>();
		nodeStack.push(tree.getRoot());
		while (!nodeStack.empty()) {			
			n = nodeStack.pop();
			ReltItem item;
			if (!n.isRoot()) {
				 // for all the siblings of the node
				float totalWeight =0;
				 Iterator siblingIterator = n.getParent().iterator();
				 while(siblingIterator.hasNext()) {
					 Node sibling = (Node) siblingIterator.next();
					 item = (ReltItem) sibling.getItem();
					 int nNodes =  tree.getNumberOfNodesAtLevel(sibling.getLevel());
					 // As a node I get some space plus some more
					 float weight = ((float) 1/nNodes+(float) sibling.numberOfChildren()) /nNodes;
					 item.setWeight(weight);
					 totalWeight += weight;
				 }
				 // Now normalize
				 siblingIterator = n.getParent().iterator();
				 while(siblingIterator.hasNext()) {
					 Node sibling = (Node) siblingIterator.next();
					 item = (ReltItem) sibling.getItem();
					 item.setWeight(item.getWeight()/totalWeight);
				 }
			}
			else {
				((ReltItem) n.getItem()).setWeight(1);
			}
			Iterator iterator = n.getChildren().iterator();
			while(iterator.hasNext()) {
				nodeStack.push((Node) iterator.next());
			}
		}
		
	}

	/**
	 * Draws a line 
	 * @param area the area on which the line should be assigned
	 * @param start Starting point
	 * @param end Ending point
	 * @return The end-coordinate of the line
	 */
	private Point drawLine(NodeArea area, Point start, Point end) {
		Line line = new Line();
		line.setLine(start,end);
		area.addLine(line);
		return end;
	}
	private final static Logger LOGGER = Logger.getLogger(Relt.class.getName());

}
