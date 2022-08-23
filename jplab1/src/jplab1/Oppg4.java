package jplab1;

import static javax.swing.JOptionPane.*;

public class Oppg4 {

	public static void main(String[] args) {
		int secTot = Integer.parseInt(showInputDialog("Skriv inn sekunder som skal konverteres"));
		int sec = secTot % 60;
		int minTot = secTot / 60;
		int min = minTot % 60;
		int hr = secTot / 3600;
		
		String output = "" + hr + " timer, " + min + " minutter, " + sec + " sekunder.";
		showMessageDialog(null, output );
		
	}

}
