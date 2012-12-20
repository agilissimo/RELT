package org.agilissimo.relt;

import java.util.ArrayList;

import javax.naming.Context;

import org.agilissimo.tree.BasicNode;
import org.agilissimo.tree.BasicTree;

public class Relt2Html {

	final int NNODES = 5;
	ReltFileWRiter fw;


	private BasicTree tree;
	private BasicNode story = new BasicNode();
	private BasicNode[] features = new BasicNode[NNODES];
	private BasicNode[] tasks = new BasicNode[NNODES];
	private BasicNode[] subtasks = new BasicNode[NNODES];
	
	public Relt2Html () {
		InitialTree();
	}

	public void generateHtml(ArrayList<NodeArea> areas, String fileName,int sizeX,int sizeY, int leftMargin, int topMargin) {
		
		ReltFileWRiter fw = new ReltFileWRiter(fileName);
		fw.write("<!DOCTYPE HTML>");
		fw.write("<html>");
		fw.write(" <head>");
		fw.write("  <style>");
		fw.write("   body {margin: 0px;padding: 0px;");
		fw.write("</style>");
		fw.write(" </head>");
		fw.write("  <body>");
		fw.write("  <h1> Radial Edgesless Tree visualisation example</h1>");

		fw.write("<canvas id='reltCanvas' width='"+sizeX+"' height='"+sizeY+"'></canvas>");
		fw.write("  <script>");
		fw.write("var canvas = document.getElementById('reltCanvas');");
		fw.write("var context = canvas.getContext('2d');");
		for (NodeArea area: areas) {
			ArrayList<Line> lines = area.getLines();
			int labelPositionX = (int)( area.getLabelPosition().getX()+leftMargin);
			int labelPositionY = (int)( area.getLabelPosition().getY()+ topMargin);

			double rotationAngle = area.getOrientation();
			fw.write("context.font ='700 9pt Verdana'");
			fw.write("context.font ='700 9pt Verdana'");
			fw.write("context.save();");
			fw.write("context.translate("+labelPositionX+","+labelPositionY+");");;
			fw.write("context.rotate("+rotationAngle+");");

			//fw.write("context.fillText('"+area.getLabel()+"',+"+labelPositionX+","+labelPositionY+");");
			fw.write("context.fillText('"+area.getLabel()+"',0,0);");

			rotationAngle *=-1;
			fw.write("context.rotate("+rotationAngle+");");
			fw.write("context.restore();");



			for(Line line: lines) {
				fw.write("context.beginPath();");

				int x1 = (int) line.getP1().getX()+leftMargin;
				int y1 = (int) line.getP1().getY()+topMargin;
				int x2 = (int) line.getP2().getX()+leftMargin;
				int y2 = (int) line.getP2().getY()+topMargin;
				fw.write("context.moveTo("+x1+','+y1+");");
				fw.write("context.lineTo("+x2+','+y2+");");
				fw.write("context.stroke();");
	
				fw.write("context.closePath();");

				//g2d.drawLine((int) line.getP1().getX()+leftMargin,(int)line.getP1().getY() +topMargin, 
					//	(int)line.getP2().getX()+leftMargin,(int)line.getP2().getY() +topMargin);
			}
		}
		fw.write("  </script>");

		
		fw.close();
	}
	
	public BasicTree getTree() {
		return this.tree;
	}
	public void InitialTree() {
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
		
		tree.addNode(tasks[1],subtasks[0]);
		tree.addNode(tasks[1],subtasks[1]);
		tree.addNode(tasks[0],subtasks[2]);
		
	}
}
