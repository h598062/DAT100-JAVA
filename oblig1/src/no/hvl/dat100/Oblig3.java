package no.hvl.dat100;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Oblig3 {
	public static void main(String[] args) {
		// Lag et program som leser inn et heltall n > 0,
		// beregner verdien n! (n fakultet)
		// og skriver verdien til n! ut på skjermen, der n! = 1*2*3* … *(n-1)*n.

		// har ingen håndtering for hvis fakultet av tall blir større enn en long kan holde
		long tall;
		do {
			tall = Long.parseLong(showInputDialog("Skriv inn et heltall større enn 0", 4));
		} while (tall <= 1);
		long fakultetSum = fakultRekursiv(tall);
		System.out.println(fakultetSum);
		showMessageDialog(null, tall + "! blir " + fakultetSum);
	}

	// alle metoder er objektivt bedre hvis de er rekursive ;)
	private static long fakultRekursiv(long tall, long sum) {
		if (tall <= 1) {
			return sum;
		} else {
			long nesteTall = tall - 1;
			long nySum     = sum * tall;
			return fakultRekursiv(nesteTall, nySum);
		}

	}

	// enkel overload for første kall fra main
	private static long fakultRekursiv(long tall) {
		// Setter sum til 1 fordi inntall skal alltid være 1 eller større
		// dette gjør også rekursjonskoden enklere
		return fakultRekursiv(tall, 1);
	}
}
