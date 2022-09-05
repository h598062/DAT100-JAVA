package no.hvl.dat100;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Oblig1 {
	public static void main(String[] args) {

		int trinn1 = 190350;
		int trinn2 = 267900;
		int trinn3 = 643800;
		int trinn4 = 969200;
		int trinn5 = 2000000;

		double trinn1Sats = 0.017;
		double trinn2Sats = 0.04;
		double trinn3Sats = 0.134;
		double trinn4Sats = 0.164;
		double trinn5Sats = 0.174;

		int bruttoInntekt = Integer.parseInt(showInputDialog("Skriv inn bruttoinntekt"));
		System.out.println(bruttoInntekt);
		int skatt        = 0;
		int nettoInntekt = bruttoInntekt;

		if (bruttoInntekt >= trinn5) {
			skatt += (bruttoInntekt - trinn5) * trinn5Sats;
			bruttoInntekt = trinn5 - 1;
		}
		if (bruttoInntekt >= trinn4) {
			skatt += (bruttoInntekt - trinn4) * trinn4Sats;
			bruttoInntekt = trinn4 - 1;
		}
		if (bruttoInntekt >= trinn3) {
			skatt += (bruttoInntekt - trinn3) * trinn3Sats;
			bruttoInntekt = trinn3 - 1;
		}
		if (bruttoInntekt >= trinn2) {
			skatt += (bruttoInntekt - trinn2) * trinn2Sats;
			bruttoInntekt = trinn2 - 1;
		}
		if (bruttoInntekt >= trinn1) {
			skatt += (bruttoInntekt - trinn1) * trinn1Sats;
		}
		nettoInntekt -= skatt;
		System.out.println(skatt);
		System.out.println(nettoInntekt);

		String output = "Nettoinntekt: " + nettoInntekt + " Skatt: " + skatt;
		showMessageDialog(null, output);

	}
}
