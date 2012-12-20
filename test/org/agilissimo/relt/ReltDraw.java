package org.agilissimo.relt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

public class ReltDraw extends JPanel{
	private ArrayList<NodeArea> areas;
	private int leftMargin,topMargin;
	

	
	ReltDraw(ArrayList<NodeArea> areas, int leftMargin,int topMargin) {
		this.areas = areas;
		this.leftMargin = leftMargin;
		this.topMargin = topMargin;
	}
	
	
	
	public void paint(Graphics g) {
		Logger l =  Logger.getLogger(ReltDraw.class.getName());
		Graphics2D  g2d = (Graphics2D) g;
		for (NodeArea area: areas) {
	
			
			g2d.drawString(area.getLabel(), (int) area.getLabelPosition().getX()+leftMargin, (int) area.getLabelPosition().getY()+topMargin);

			
			ArrayList<Line> lines = area.getLines();
			l.debug("Area "+ area.getLabel());
			for(Line line: lines) {
				l.debug("Line : Starting Point:"+line.getP1().getX()+","+line.getP1().getY()+" End Point :"+line.getP2().getX()+","+line.getP2().getY());
				g2d.drawLine((int) line.getP1().getX()+leftMargin,(int)line.getP1().getY() +topMargin, 
						(int)line.getP2().getX()+leftMargin,(int)line.getP2().getY() +topMargin);
			}
		}
	}
	
	public void setLeftMargin(int m) {
		this.leftMargin = m;
	}

}
