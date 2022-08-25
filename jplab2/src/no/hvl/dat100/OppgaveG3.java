package no.hvl.dat100;

import static javax.swing.JOptionPane.*;

public class OppgaveG3 {

	public static void main(String[] args) {
		int a, b;
		
		// hent inn tall fra bruker og konverter til int
		a = Integer.parseInt(showInputDialog("Skriv et heltall"));
		b = Integer.parseInt(showInputDialog("Skriv et annet heltall som ikke er 0"));
		
		// det andre tallet kan ikke være 0
		if (b == 0) {
			throw new RuntimeException("Det andre tallet kan ikke være 0!!!");
		}
		int out = a / b;
		String output = a + " delt på " + b + " er " + out;
		
		showMessageDialog(null, output);
	}

}
