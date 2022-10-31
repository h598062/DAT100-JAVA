package no.hvl.dat100.oppgaveb4;

import java.util.Random;

public class Terning {

	// aktuell verdi for terningen
	private       int    oyne;
	private final Random rnd = new Random();

	public Terning() {
		trill();
	}

	public int getOyne() {
		return this.oyne;
	}

	public void trill() {
		this.oyne = rnd.nextInt(1, 7);
	}
}