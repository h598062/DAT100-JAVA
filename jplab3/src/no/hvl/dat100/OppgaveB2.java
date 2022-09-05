package no.hvl.dat100;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class OppgaveB2 {
	public static void main(String[] args) {
		double tall, potens;
		tall = Double.parseDouble(showInputDialog("Skriv inn tallet som skal bli opphøyd", 4));
		do {
			potens = Integer.parseInt(showInputDialog("Skriv potensen tallet skal opphøyes til", 4));
			if (potens < 1) {
				showMessageDialog(null, "Potens må være et heltall større enn null, prøv igjen");
			}
		} while (potens < 1);

		// math.pow
		double mathPowSvar = Math.pow(tall, potens);

		// custom måte
		// potens skal være minst 1, setter initial verdi til å være lik tall
		double customSvar = tall;
		// trekker fra 1 fra potens variabel siden vi har "brukt" en i linjan før
		for (int i = 0; i < potens - 1; i++) {
			// samme som +=, bare med ganging
			customSvar *= tall;
		}
		String msg = "Potens regnet med funksjonen Math.pow(): " + mathPowSvar +
		             "\nPotens regnet med min hjemmelagde metode: " + customSvar;
		showMessageDialog(null, msg);
	}
}
