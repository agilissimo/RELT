package org.agilissimo.relt;

import java.util.ArrayList;

import org.agilissimo.tree.BasicNode;
import org.agilissimo.tree.BasicTree;
import org.agilissimo.tree.Node;
import org.agilissimo.tree.Tree;

public class Main {
	
	private static Tree tree;
	final static int NNODES = 5;


	private static BasicNode story = new BasicNode();
	private static BasicNode[] features = new BasicNode[NNODES];
	private static BasicNode[] tasks = new BasicNode[NNODES];
	private static BasicNode[] subtasks = new BasicNode[NNODES];

	public static void main(String[] args) {
		Relt2Html relt2Html = new Relt2Html();
		final int SCREENSIZEX = 860;
		final int SCREENSIZEY = 860;
		
		Relt relt = new Relt(tree, 800, 800);
		InitialTree();
		ArrayList<NodeArea> areas = relt.computeAreas(tree);
		relt2Html.generateHtml(areas,"relt.html", SCREENSIZEX,SCREENSIZEY, 50, 50);
	
	}
	
	public static void InitialTree() {
		BasicReltItem item = new BasicReltItem("STORYID");
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
		tree.addNode(features[3],tasks[3]);
		
		tree.addNode(tasks[1],subtasks[0]);
		tree.addNode(tasks[1],subtasks[1]);
		tree.addNode(tasks[1],subtasks[3]);
		tree.addNode(tasks[1],subtasks[4]);

		tree.addNode(tasks[0],subtasks[2]);
		
		tree.addNode(tasks[2],subtasks[3]);
	}
}
