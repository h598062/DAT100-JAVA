package no.hvl.dat100;

import javax.swing.JOptionPane;

public class Oppgave1 {
	public static void main(String[] args) {

		int    b, h, d;
		String btext, htext, dtext;

		// les inn høyde, bredde, dybde
		htext = JOptionPane.showInputDialog("Angi høyde:");
		btext = JOptionPane.showInputDialog("Angi bredde:");
		dtext = JOptionPane.showInputDialog("Angi dybde:");

		// konverter fra tekst til heltal

		h = Integer.parseInt(htext);
		b = Integer.parseInt(btext);
		d = Integer.parseInt(dtext);    // Denne skal parse fra dtext, ikke htext

		int volum = b * h * d;

		String respons = "Volum [" + htext + "," + btext + "," + dtext + "] = " + volum;

		JOptionPane.showMessageDialog(null, respons);
	}
}
