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
 * This is an example implementation of ReltItem to demonstrate the
 * RELT algorithm. ReltItems can be of course more complex that only
 * having the basic elements of label and weight 
 * @author ios
 *
 */
public class BasicReltItem implements ReltItem {
	
	private String label;
	private float weight;
	
	public BasicReltItem(String s){
		label = s;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}

}
