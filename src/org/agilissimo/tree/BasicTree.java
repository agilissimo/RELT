package org.agilissimo.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javax.xml.crypto.NodeSetData;

import org.apache.log4j.Logger;


/**
 * Acyclic rooted tree implementation for Tree
 * @author ios
 *
 */
public class BasicTree implements Tree{

	private Node root;
	private Set<Edge> edges;
	private int numberOfNodes;
	private int numberOfEdges;
	private int depth;
	
	// r[i] contains the amount of nodes at level i
	private int[] amountOfNodesAtLevel;
	
	// the nodes at a specific level
	private ArrayList<Set<Node>> nodesAtlevel;
	// Contains the weight of a specific level
	private float[] weightOfLevel;

	
	public BasicTree() {
		root = new BasicNode(true);
		root.setLevel(0);
		numberOfNodes=1;
		numberOfEdges = 0;
		edges = new HashSet<Edge>();
		depth = 1;
	}
	public BasicTree(Node root) {
		this.root = root;
		this.root.setLevel(0);
		(this.root).isRoot(true);
		numberOfNodes=1;
		numberOfEdges = 0;
		edges = new HashSet<Edge>();
		depth = 0;
	}	
	/**
	 * Adds node to the root
	 * @param node
	 */
	public void addNode(Node node) {
		if (node == root) return;
		
		// check if node exists
		for(Node n: root.getChildren()) {
			if (n==node) return;
		}
		root.addChild(node);
		Edge edge = new Edge(root, node);
		edges.add(edge);
		numberOfNodes++;
		numberOfEdges++;
		node.setLevel(1);
		if (depth == 0) depth = 1;
		node.setParent(root);
		if (amountOfNodesAtLevel==null || amountOfNodesAtLevel.length <= 1) 
			countNodesAtLevel();
		else 
			amountOfNodesAtLevel[1]++;
			
		addNode(root,node);
	}
	
	/**
	 * adds child to parent node
	 * @param parent
	 * @param child
	 */
	public void addNode(Node parent, Node child) {
		Boolean foundParent=false,foundChild=false;
		Node a,b;
		
		if (parent == root) {
			addNode(child);
			return;
		}
		for(Edge e:edges) {
			a = e.getA();
			b = e.getB();
			// if edge already exists
			if (a == parent && b == child) return;
			if (a == child && b == parent) return;

			// if parent exists
			if (a == parent || b == parent) {
				foundParent = true;
			}
			// if child already exists
			if(a == child || b == child) {
				foundChild = true;
			}	
		}
		// We do not want to connect to nodes that already exist since
		// we will create a circle
		if (foundParent && foundChild) {
			LOGGER.info("found both parent and child while trying to add child to tree");
			return;
		}
		
		if (foundParent) {
				parent.addChild(child);
				Edge edge = new Edge(parent, child);
				edges.add(edge);
				numberOfNodes++;
				numberOfEdges++;
				child.setParent(parent);
				int level = parent.getLevel()+1;
				// compute maximum depth
				if (level > depth) depth = level;
				child.setLevel(level);
				if (amountOfNodesAtLevel==null || amountOfNodesAtLevel.length <= level) 
					countNodesAtLevel();
				else 
					amountOfNodesAtLevel[level]++;
		}

	}
	
	public void removeNode(Node parent, Node node) {
		// cannot remove a node with children
		if (node.hasChildren()) return;
		parent.getChildren().remove(node);
		countNodesAtLevel();
	}
	
	public void itemInjection(Node node, ItemInjection ip) {
		Node n;
		Stack<Node> nodeStack = new Stack<Node>();
		nodeStack.push(node);
		while (!nodeStack.empty()) {			
			n = nodeStack.pop();
			ip.injectItem(n);
			Iterator iterator = n.getChildren().iterator();
			while(iterator.hasNext()) {
				nodeStack.push((BasicNode) iterator.next());
			}
		}
	}
	
	/**
	 * Counts and returns the amount of nodes per level
	 * root is level 0. There are depth+1 levels
	 * @return
	 */
	private void countNodesAtLevel() {
		this.amountOfNodesAtLevel = new int[depth+1];
		this.nodesAtlevel = new ArrayList<Set<Node>>();
		for (int i=0; i<=depth+1; i++) {
			HashSet<Node> hs = new HashSet<Node>();
			nodesAtlevel.add(i,hs);
		}
		
		for (int i=0; i<this.depth+1; i++) {
			amountOfNodesAtLevel[i]=0;
		}
		Node n;
		Stack<Node> nodeStack = new Stack<Node>();
		nodeStack.push(root);
		while (!nodeStack.empty()) {			
			n = nodeStack.pop();
			int level = n.getLevel();
			amountOfNodesAtLevel[level] += 1;
			(nodesAtlevel.get(level)).add(n);
			Iterator iterator = n.getChildren().iterator();
			while(iterator.hasNext()) {
				nodeStack.push((BasicNode) iterator.next());
			}
		}	
	}
	@Override
	public Set<Node> getNodesAtLevel(int level) {
		countNodesAtLevel();
		return this.nodesAtlevel.get(level);
	}
	@Override
	public int getNumberOfNodesAtLevel(int index) {
		if (amountOfNodesAtLevel==null) countNodesAtLevel();
		if (index >= amountOfNodesAtLevel.length) return -1;
		return amountOfNodesAtLevel[index];
	}
	
	public void computeLevelWeights() {
		if (amountOfNodesAtLevel==null) countNodesAtLevel();
		weightOfLevel = new float[amountOfNodesAtLevel.length];

		for(int i=0; i<depth+1; i++) {
			weightOfLevel[i]= 1+(float) amountOfNodesAtLevel[i]/numberOfNodes; 
		}
	}
	
	public float getLevelWeight(int index) {
		if (weightOfLevel == null) 
			computeLevelWeights();
			
		return weightOfLevel[index];
			
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public int getNumberOfEdges() {
		return numberOfEdges;
	}

	public int getDepth() {
		return depth;
	}

	private final static Logger LOGGER = Logger.getLogger(BasicTree.class.getName());


}
