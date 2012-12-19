package org.agilissimo.tree;

import java.util.Iterator;
import java.util.Set;

public interface Node {
	
	public boolean isRoot();
	public void isRoot(boolean value);
	public Boolean hasChildren();
	public void addChild(Node node);
	public void removeChild(Node node);
	public Iterator iterator();
	public int getLevel();
	public void setLevel(int level);
	public Item getItem();
	public void setItem(Item item);
	public Set<Node> getChildren();
	public int numberOfChildren();
	public Node getParent();
	public void setParent(Node parent);
}
