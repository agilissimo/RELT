package org.agilissimo.relt;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

 class RotatedTextArea extends JPanel {
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