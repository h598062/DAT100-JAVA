package no.hvl.dat100;

// fjern static fra import, og importer selve JOptionPane klassen
import javax.swing.JOptionPane;

// Ikke-statiske funksjoner krever at klassen som skal bruke dem må ha implementert
// klassen som eier funksjonen enten gjennom interface eller å lage et nytt objekt av den klassen
public class OppgaveV2 extends JOptionPane {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		double radius = Double.parseDouble(
				showInputDialog("Skriv radius til en kule for å regne ut volum"));

		// Math klassen kan bli nådd ved å skrive klassenavn Math og funksjonen du vil bruke med "dot" notasjon
		// Vi kan da altså skrive Math.PI eller Math.pow() uten å importere denne klassen
		double volume = (4.0/3.0) * Math.PI * Math.pow(radius, 3);

		String output = "Volum av kule med radius " + radius + " er " + volume;

		showMessageDialog(null, output);
	}
}
