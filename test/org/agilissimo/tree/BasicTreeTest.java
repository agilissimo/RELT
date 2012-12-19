package org.agilissimo.tree;

import static org.junit.Assert.*;

import org.agilissimo.relt.BasicReltItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicTreeTest {

	private BasicTree tree;

	
	@Before
	public void setUp() throws Exception {
		BasicReltItem item = new BasicReltItem("The root");
		BasicNode node = new BasicNode();
		node.setItem(item);
		tree = new BasicTree(node);
		assertTrue(tree.getRoot() == node);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		assertEquals(1, tree.getNumberOfNodes());
		assertEquals(0, tree.getNumberOfEdges());
		assertEquals(0, tree.getDepth());
		assertTrue(tree.getRoot().isRoot());
	}
	
	@Test
	public void testAddNode() {
		BasicReltItem item = new BasicReltItem("This is a child");
		BasicNode node1 = new BasicNode();
		node1.setItem(item);
		tree.addNode(node1);
		assertEquals(2, tree.getNumberOfNodes());
		assertEquals(1, tree.getNumberOfEdges());
		assertEquals(1, tree.getDepth());	
		assertEquals(1, node1.getLevel());
		assertFalse(node1.isRoot());
		
		BasicReltItem item1 = new BasicReltItem("This is another child");
		BasicNode node2 = new BasicNode();
		node1.setItem(item1);
		tree.addNode(node2);
		assertEquals(3, tree.getNumberOfNodes());
		assertEquals(2, tree.getNumberOfEdges());
		assertEquals(1, tree.getDepth());	
		assertEquals(1, node2.getLevel());
	
		BasicReltItem item2 = new BasicReltItem("This is the third child");
		BasicNode node3 = new BasicNode();
		node3.setItem(item2);
		tree.addNode(node2, node3);
		assertEquals(4, tree.getNumberOfNodes());
		assertEquals(3, tree.getNumberOfEdges());
		assertEquals(2, tree.getDepth());	
		assertEquals(2, node3.getLevel());
		
		assertTrue(node2 == node3.parent);
	}
	
	@Test 
	public void testCountNodesAtLevel() {
		BasicReltItem item = new BasicReltItem("This is a child");
		BasicNode node1 = new BasicNode();
		node1.setItem(item);
		tree.addNode(node1);

		
		BasicReltItem item1 = new BasicReltItem("This is another child");
		BasicNode node2 = new BasicNode();
		node1.setItem(item1);
		tree.addNode(node2);

	
		BasicReltItem item2 = new BasicReltItem("This is the third child");
		BasicNode node3 = new BasicNode();
		node3.setItem(item2);
		tree.addNode(node2, node3);

		// there is one node at level 0
		assertEquals(1, tree.getNumberOfNodesAtLevel(0));
		assertEquals(2, tree.getNumberOfNodesAtLevel(1));
		assertEquals(1, tree.getNumberOfNodesAtLevel(2));


	}

}
