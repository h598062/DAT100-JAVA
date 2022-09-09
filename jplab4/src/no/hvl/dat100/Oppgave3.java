package no.hvl.dat100;

import easygraphics.EasyGraphics;

public class Oppgave3 extends EasyGraphics {
	public static void main(String[] args) {
		launch(args);
	}

	// TODO faktisk gjøre oppgaven
	public void run() {
		makeWindow("Heisann verden");
		setBackground(0, 0, 0);
		setFont("Arial", 50);
		setColor(255, 50, 50);
		drawString("Halvor har stor nese!", 50, 200);
	}
}
