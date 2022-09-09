package no.hvl.dat100;

import easygraphics.*;

public class Oppgave3 extends EasyGraphics {
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {
		int windowSizeY = 600;
		int windowSizeX = 800;
		makeWindow("Kjønnsfordeling", windowSizeX, windowSizeY);
		drawBarGraph("A", 0.28, 0);
		drawBarGraph("B", 0.38, 1);
		drawBarGraph("C", 0.46, 2);
	}

	private void drawBarGraph(String navn, double fordeling, int pos) {
		int gSize = (int) (700*fordeling);
		int jSize = 700 - gSize;
		setColor(0, 0 , 0);
		setFont("Arial", 30);
		drawString(navn, 800 / 2, 50 + (pos * 175));
		// gutter
		setColor(0, 255, 0);
		fillRectangle(50, 50 + (pos * 175), gSize, 150);
		setColor(0, 0 , 0);
		setFont("Arial", 20);
		drawString((int)(fordeling * 100) + "%", 50 + (gSize / 2), 50 + 75 + (pos * 175));
		// jenter
		setColor(255, 0, 0);
		fillRectangle(50 + gSize, 50 + (pos * 175), jSize, 150);
		setColor(0, 0 , 0);
		setFont("Arial", 20);
		drawString((int)(fordeling * 100) + "%", 50 + gSize + (jSize / 2), 50 + 75 + (pos * 175));
		// outline
		setColor(0, 0, 0);
		drawRectangle(50, 50 + (pos * 175), 700, 150);
	}
}
