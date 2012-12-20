package org.agilissimo.relt;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.agilissimo.tree.BasicNode;
import org.agilissimo.tree.BasicTree;
import org.agilissimo.tree.Node;
import org.agilissimo.tree.Tree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReltTest {
	private Node node = new BasicNode();
	private Tree tree = new BasicTree(node);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test 
	public void testSimpleCaseTwoChildren() {

		ReltItem item = new BasicReltItem("Root");
		node.setItem(item);
		tree.addNode(node);
		Relt relt = new Relt(tree, 500, 500);
		
		// does not work with a tree with one node 
		assertTrue(relt.computeAreas() == null);
		int i =0;
		do {
			Node n =  new BasicNode();
			ReltItem item1 = new BasicReltItem("child-"+i);
			n.setItem(item1);
			tree.addNode(n);
			i++;
		}while(i<2);
		
		ArrayList<NodeArea> areas = relt.computeAreas();
		
		assertEquals(areas.get(0).getLabel(), "Root"); 
		assertEquals(areas.get(1).getLabel(), "child-0"); 
		assertEquals(areas.get(2).getLabel(), "child-1"); 
		
		Line line = areas.get(1).getLines().get(3);

		assertEquals((int) line.getP1().getX(), 166); 
		assertEquals((int) line.getP1().getY(), 166); 
		assertEquals((int) line.getP2().getX(), 500); 
		assertEquals((int) line.getP2().getY(), 500); 

		
	}
	

	@Test 
	public void testSimpleCaseThreeChildren() {

		ReltItem item = new BasicReltItem("Root");
		node.setItem(item);
		tree.addNode(node);
		Relt relt = new Relt(tree, 500, 500);
		
		// does not work with a tree with one node 
		assertTrue(relt.computeAreas() == null);
		int i =0;
		do {
			Node n =  new BasicNode();
			ReltItem item1 = new BasicReltItem("child-"+i);
			n.setItem(item1);
			tree.addNode(n);
			i++;
		}while(i<3);
		
		ArrayList<NodeArea> areas = relt.computeAreas();
		
		Line line = areas.get(1).getLines().get(3);

		assertEquals((int) line.getP1().getX(), 125); 
		assertEquals((int) line.getP1().getY(), 83); 
		assertEquals((int) line.getP2().getX(), 500); 
		assertEquals((int) line.getP2().getY(), 333); 

		
	}
	
	
	@Test 
	public void testSimpleCaseThreeLevels() {

		ReltItem item = new BasicReltItem("Root");
		node.setItem(item);
		tree.addNode(node);
		Node [] nodes = new BasicNode[3];
		Node [] level2 = new BasicNode[3];

		Relt relt = new Relt(tree, 500, 500);
		
		// does not work with a tree with one node 
		assertTrue(relt.computeAreas() == null);
		int i =0;
		do {
			Node n =  new BasicNode();
			ReltItem item1 = new BasicReltItem("level-1-"+i);
			n.setItem(item1);
			tree.addNode(n);
			nodes[i] = n;
			i++;
		}while(i<3);

		i=0;

		do {
			Node n =  new BasicNode();
			ReltItem item1 = new BasicReltItem("level-2-"+i);
			n.setItem(item1);
			tree.addNode(nodes[i],n);
			level2[i] = n;
			i++;
		}while(i<3);

		i=0;
		do {
			Node n =  new BasicNode();
			ReltItem item1 = new BasicReltItem("level-3-"+i);
			n.setItem(item1);
			tree.addNode(level2[i],n);
			i++;
		}while(i<3);
		
		ArrayList<NodeArea> areas = relt.computeAreas();
		
		
		//Line line = areas.get(1).getLines().get(2);

	
		
		

		
	}
}
