package no.hvl.dat100;

import easygraphics.*;

@SuppressWarnings("serial")
public class Oppg5 extends EasyGraphics {

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {
		makeWindow("Grafikk", 200, 350);
		drawCircle(100, 100, 75);
		drawCircle(100, 260, 85);
	}
}