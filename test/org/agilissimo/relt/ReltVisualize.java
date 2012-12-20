package org.agilissimo.relt;
import javax.naming.InitialContext;
import javax.swing.JFrame;

import org.agilissimo.tree.Item;
import org.agilissimo.tree.BasicNode;
import org.agilissimo.tree.BasicTree;
import org.agilissimo.tree.Node;
import org.agilissimo.tree.Tree;

import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
/**
 * Use this class to test and view the tree creation
 * @author ios
 *
 */
public class ReltVisualize {

	final int NNODES = 3;
	private BasicTree tree;
	private BasicNode story = new BasicNode();
	private BasicNode[] features = new BasicNode[NNODES];
	private BasicNode[] tasks = new BasicNode[NNODES];
	private BasicNode[] subtasks = new BasicNode[NNODES];
	
	
	public void InitialTree() {
		BasicReltItem item = new BasicReltItem("This is the story");
		story.setItem(item);
		tree = new BasicTree(story);
		
		int i=0;
		do {
			BasicReltItem it0 = new BasicReltItem("feature-"+i);
			BasicReltItem it1 = new BasicReltItem("task-"+i);
			BasicReltItem it2= new BasicReltItem("subtask-"+i);

			
			BasicNode task = new BasicNode(it1);
			tasks[i]= task; 
			BasicNode subtask = new BasicNode(it2);
			subtasks[i]= subtask; 
			BasicNode feature = new BasicNode(it0);
			features[i]= feature; 
			tree.addNode(feature);
			i++;
		}while(i< NNODES);
		
		tree.addNode(features[0],tasks[0]);
		tree.addNode(features[0],tasks[1]);
		tree.addNode(features[0],tasks[2]);
		
		tree.addNode(tasks[1],subtasks[0]);
		tree.addNode(tasks[1],subtasks[1]);
		tree.addNode(tasks[0],subtasks[2]);
		
	}
	
	public BasicTree getTree() {
		return this.tree;
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
		j.setTitle("Visualization of RELT");
		
		ReltVisualize v = new ReltVisualize();
			
		v.InitialTree();
		// use RectArea helper class to simulate screen
		RectArea screen = new RectArea();
		screen.setSizeX(800);
		screen.setSizeY(800);
		
		//initialize the Relt algorithm
		Relt relt = new Relt(v.getTree(), 800,800);
		
		//let it draw with a margin of 5 pixels to the left and to the top
		
		ReltDraw drawComponent = new ReltDraw(relt.computeAreas(),5,5);

		j.add(drawComponent);
		
		j.setSize(840,840);
		j.setLocationRelativeTo(null);
		
		j.setVisible(true);
	}
}
