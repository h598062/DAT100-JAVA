package no.hvl.dat100;

import static javax.swing.JOptionPane.showInputDialog;

public class OppgaveV1b {
	public static void main(String[] args) {

		int tall1 = Integer.parseInt(showInputDialog("Skriv inn første tall"));
		int tall2 = Integer.parseInt(showInputDialog("Skriv inn andre tall"));
		int tall3 = Integer.parseInt(showInputDialog("Skriv inn siste tall"));

		int minst = Math.min(tall1, tall2);
		minst = Math.min(minst, tall3);
		int nestMinst;
		int minstNt;
		if (minst == tall1){
			nestMinst = Math.min(tall2, tall3);
			minstNt = Math.max(tall2, tall3);
		} else if (minst == tall2) {
			nestMinst = Math.min(tall1, tall3);
			minstNt = Math.max(tall1, tall3);
		} else {
			nestMinst = Math.min(tall1, tall2);
			minstNt = Math.max(tall1, tall2);
		}
		System.out.println("Minst: " + minst + " Nest Minst: " + nestMinst + " Størst: " + minstNt);

	}
}