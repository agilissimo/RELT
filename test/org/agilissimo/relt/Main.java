package org.agilissimo.relt;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Relt2Html relt2Html = new Relt2Html();
		final int SCREENSIZEX = 860;
		final int SCREENSIZEY = 860;
		
		Relt relt = new Relt(relt2Html.getTree(), 800, 800);

		ArrayList<NodeArea> areas = relt.computeAreas();
		relt2Html.generateHtml(areas,"relt.html", SCREENSIZEX,SCREENSIZEY, 50, 50);
	
	}
}
