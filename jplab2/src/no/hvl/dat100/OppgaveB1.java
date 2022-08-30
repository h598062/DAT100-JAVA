package no.hvl.dat100;


import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class OppgaveB1 {
	public static void main(String[] args) {

		double radius = Double.parseDouble(
				showInputDialog("Skriv radius til en kule for å regne ut volum"));

		double volume = (4.0/3.0) * Math.PI * Math.pow(radius, 3);

		String output = "Volum av kule med radius " + radius + " er " + volume;

		showMessageDialog(null, output);
	}
}