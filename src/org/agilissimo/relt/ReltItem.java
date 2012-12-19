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

import org.agilissimo.tree.Item;

/**
 * Provides an interface to use the Relt Class/Algorithm
 * The items that are stored in your tree to be displayed via Relt
 * have to implement this interface
 * @author ios
 *
 */
public interface ReltItem extends Item{
	/**
	 * Gets the item label. The label is used for display
	 * and is the textual description for this node
	 * @return The item label as a String
	 */
	public String getLabel();
	/**
	 * Sets the label for the item. The label is used for display
	 * and is the textual description for this node
	 * @param label
	 */
	public void setLabel(String label);
	/**
	 * Returns the weight for this item or node.
	 * @return The item's weight
	 */
	public float getWeight();
	/**
	 * Sets the weight for the item/node.  The weight is assigned 
	 * by the Relt algorithm to each node, depending for examle on the amount of
	 * children, thus the area the node requires in order to be displayed
	 * @param weight The item's weight
	 */
	public void setWeight(float weight);
}
