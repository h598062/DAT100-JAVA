package no.hvl.dat100;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class OppgaveB1 {
	public static void main(String[] args) {
		// finn oddetall
		int    lowerNum, upperNum;
		String msg = "Oddetall: ";

		do {
			lowerNum = Integer.parseInt(showInputDialog("Skriv inn start tall", 1));
			upperNum = Integer.parseInt(showInputDialog("Skriv inn sluttall", 20));
			System.out.println("lowerNum " + lowerNum + " upperNum " + upperNum);
			if (lowerNum > upperNum) {
				showMessageDialog(null,
						"yoooo, minstetall " + lowerNum + " e større enn størstetall " + upperNum +
						"\nprøv igjen");
			}
		} while (lowerNum > upperNum);
		for (int i = lowerNum; i <= upperNum; i++) {
			if (i % 2 == 1) {
				// ignorer StringBuilder feilmeldingen :)
				msg = msg + i + " ";
			}
		}
		System.out.println(msg);
		showMessageDialog(null, msg);

	}

}
