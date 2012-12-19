package org.agilissimo.tree;

public class Edge {

	private Node a;
	private Node b;
	
	public Edge(Node a, Node b) {
		this.a = a;
		this.b = b;
	}

	public Node getA() {
		return a;
	}

	public void setA(Node a) {
		this.a = a;
	}

	public Node getB() {
		return b;
	}

	public void setB(Node b) {
		this.b = b;
	}
	
}
