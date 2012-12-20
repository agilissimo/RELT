package org.agilissimo.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BasicNode implements Node {

	private Item item;
	protected boolean isRoot;
	protected int level;
	
	protected  Node parent;
	protected ArrayList<Node> children;

	public BasicNode() {
		children = new ArrayList<Node>();
	}
	
	public BasicNode(Item item) {
		children = new ArrayList<Node>();
		this.setItem(item);
	}
	
	public BasicNode(boolean isroot) {
		this();
		isRoot = isroot;
	}
	
	public void isRoot(boolean isroot) {
		this.isRoot = isroot;
	}
	
	public boolean isRoot() {
		return isRoot;
	}
	public Boolean hasChildren() {
		return (children.size()>0)?true:false;
	}
	
	public void addChild(Node node) {
		children.add((BasicNode) node);
	}
	
	public void removeChild(Node node) {
		children.remove(node);
	}
	
	public Iterator iterator() {
		return children.iterator();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ArrayList<Node> getChildren() {
		return  children;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public int numberOfChildren() {
		return children.size();
	}


	
	
	
}

