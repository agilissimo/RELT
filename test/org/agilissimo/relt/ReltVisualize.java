package org.agilissimo.relt;
import javax.swing.JFrame;

import org.agilissimo.tree.Item;
import org.agilissimo.tree.BasicNode;
import org.agilissimo.tree.BasicTree;

import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import com.cedarsoftware.util.io.*;
/**
 * Use this class to test and view the tree creation
 * @author ios
 *
 */
public class ReltVisualize {

	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
		j.setTitle("Experimental RELT");
	
		
		BasicNode node = new BasicNode();
		BasicTree tree = new BasicTree(node);
		BasicReltItem item = new BasicReltItem("I want to RELT a tree");
		node.setItem(item);


		BasicNode child1 = new BasicNode();
		BasicReltItem item1 = new BasicReltItem("child01");
		child1.setItem(item1);
		
		BasicNode child2 = new BasicNode();
		BasicReltItem item2 = new BasicReltItem("child02");
		child2.setItem(item2);
		

		BasicNode child3 = new BasicNode();
		BasicReltItem item3 = new BasicReltItem("child03");
		child3.setItem(item3);

		BasicNode child4 = new BasicNode();
		BasicReltItem item4 = new BasicReltItem("child04");
		child4.setItem(item4);
		
		BasicNode child5 = new BasicNode();
		BasicReltItem item5 = new BasicReltItem("child05");
		child5.setItem(item5);
		
		tree.addNode(child1);
		tree.addNode(child2);

		tree.addNode(child1,child3);

		tree.addNode(child4);
		tree.addNode(child4, child5);

		String jsonString = JsonWriter.toJson(tree);
		System.out.println(jsonString);

		FileOutputStream foStream;
		File file;
		
		try {
			file = new File("tree.json");
			foStream = new FileOutputStream(file);
			
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] stringInByte = jsonString.getBytes();
			
			foStream.write(stringInByte);
			foStream.flush();
			foStream.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}			
		
		//tree.addNode(child5);
		
		RectArea screen = new RectArea();
		screen.setSizeX(500);
		screen.setSizeY(500);
		
		Relt relt = new Relt(tree, 500,500);
		
		ReltDraw drawComponent = new ReltDraw(relt.computeAreas(),10,10);

		j.add(drawComponent);
		
		j.setSize(550,550);
		j.setLocationRelativeTo(null);
		
		j.setVisible(true);
	}
}
