package no.hvl.dat100;

import easygraphics.EasyGraphics;

public class OppgaveB5 extends EasyGraphics {
	/*
	* tegn en blink
	* sirkler 2-10 fra input
	*
	*
	*/
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void run() {
		makeWindow("Blink", 800, 600);
		setBackground(100,100,100);
		int antallRinger = 0;
		do {
			String text = getText("Skriv inn antall ringer, mellom 2 og 10");
			if (text.length() > 0 && text.length() < 3) {
				antallRinger = Integer.parseInt(text);
			}
		} while (antallRinger < 2 || antallRinger > 10);
		int maxRadius = (antallRinger*25);
		for (int i = 0; i < antallRinger; i++) {
			if (i % 2 == 1) {
				setColor(0,0,0);
			} else {
				setColor(255,255,255);
			}
			fillCircle(400, 300, maxRadius-(i*25));
		}
		setColor(255,0,0);
		drawCircle(400, 300, maxRadius);
	}
}
