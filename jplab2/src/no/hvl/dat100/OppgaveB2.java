package no.hvl.dat100;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class OppgaveB2 {
	public static void main(String[] args) {
		int pris = Integer.parseInt(showInputDialog("Skriv inn en pris"));
		int belop = Integer.parseInt(showInputDialog("Skriv inn et beløp"));
		int vekslepenger = belop - pris;
		int tiere = vekslepenger / 10;
		int enere = vekslepenger % 10;

		String output =
				"Vekslepenger: " + vekslepenger +
						" Tiere: " + tiere +
						" Enere: " + enere;
		showMessageDialog(null, output);
	}
}
