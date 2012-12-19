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
package org.agilissimo.tree;

import java.util.Set;

/**
 * An interface which lets you implement your own version
 * of Tree to use with RELT
 * @author ios
 *
 */
public interface Tree {
	public void addNode(Node node);
	
	/**
	 * adds child to parent node
	 * @param parent
	 * @param child
	 */
	public void addNode(Node parent, Node child);
		
	/**
	 * removes a node given a parent. If the node has children it 
	 * will not be removed
	 * @param parent The parent of the node
	 * @param node The node to be deleted
	 */
	public void removeNode(Node parent, Node node);
	
	/**
	 * Experimental
	 * @param node
	 * @param ip
	 */
	public void itemInjection(Node node, ItemInjection ip);


	/**
	 * Returns the root of the tree
	 * @return The root
	 */
	public Node getRoot() ;

	/**
	 * Sets the root of the tree
	 * @param root
	 */
	public void setRoot(Node root);
	
	/**
	 * Returns the total amount of nodes of the tree
	 * @return The total amount of nodes
	 */
	public int getNumberOfNodes();

	/**
	 * Returns the total amount of edges of the tree
	 * @return The total amount of edges
	 */
	public int getNumberOfEdges();

	/**
	 * Returns the depth of the tree. Root has depth 1 is at level 0
	 * @return
	 */
	public int getDepth();
	
	/**
	 * Returns the number of nodes at a specific level of the tree
	 * @param index
	 * @return
	 */
	public int getNumberOfNodesAtLevel(int index);
	
	/**
	 * Returns a set with the nodes at a specific level
	 * @param level The level in the tree
	 * @return The set of nodes at this level
	 */
	public Set<Node> getNodesAtLevel(int level);
	/**
	 * Returns the weight for a level, which is used by RELT to 
	 * estimate how much area is needed for this level
	 * @param index
	 * @return
	 */
	public float getLevelWeight(int index);
}
