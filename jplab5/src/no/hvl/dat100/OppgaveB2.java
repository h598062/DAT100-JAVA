package no.hvl.dat100;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class OppgaveB2 {
	public static void main(String[] args) {
		int bredde = lesInnTall("Bredde:");
		int hoyde = lesInnTall("Høyde:");
		int a = areal(bredde,hoyde);
		showMessageDialog(null,"Areal: " + a);
	}

	private static int areal(int bredde, int hoyde) {

		return bredde * hoyde;
	}
	private static int lesInnTall(String message) {
		int tall = 0;
		do {
			tall = Integer.parseInt(showInputDialog(message, tall));
		} while (tall < 1);
		return tall;
	}

}
