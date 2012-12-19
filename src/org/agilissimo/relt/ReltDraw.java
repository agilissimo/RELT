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
	
	private class RotatedTextArea extends JPanel {
		private double angle;
		
		public RotatedTextArea( double angle) {
			this.angle = angle;
		}
		
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.rotate(angle);
			super.paint(g);
		}
		public void setAngle(double angle) {
			this.angle = angle;
		}
	}
	
	ReltDraw(ArrayList<NodeArea> areas, int leftMargin,int topMargin) {
		this.areas = areas;
		this.leftMargin = leftMargin;
		this.topMargin = topMargin;
	}
	
	
	
	public void paint(Graphics g) {
		Logger l =  Logger.getLogger(ReltDraw.class.getName());
		Graphics2D  g2d = (Graphics2D) g;
		for (NodeArea area: areas) {
			JPanel textPanel = new RotatedTextArea(area.getOrientation());
			JTextArea textArea = new JTextArea(area.getLabel(),5,20);
			textArea.setEditable(false);
			textArea.setText(area.getLabel());
			textPanel.setSize(5,30);
			java.awt.Point p = new java.awt.Point();
			p.setLocation(area.getLabelPosition().getX()+leftMargin, area.getLabelPosition().getY()+topMargin);
			textPanel.setLocation(p);
			textPanel.add(textArea);

			textPanel.setVisible(true);
			
			g2d.drawString(area.getLabel(), (int) area.getLabelPosition().getX()+leftMargin, (int) area.getLabelPosition().getY()+topMargin);

			
			this.add(textPanel);
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
