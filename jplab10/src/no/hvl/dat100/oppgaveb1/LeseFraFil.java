package no.hvl.dat100.oppgaveb1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class LeseFraFil {

	// måtte endre mappebanen slik at det funker for meg
	static String MAPPE_STR = System.getProperty("user.dir") + "/jplab10/src/no/hvl/dat100/oppgaveb1/";

	public static void main(String[] args) throws FileNotFoundException {
		int attempts = 5;

		do {
			String filnavn;
			filnavn = JOptionPane.showInputDialog("Filnavn i mappen " + MAPPE_STR);
			try {
				BufferedReader file   = new BufferedReader(new FileReader(MAPPE_STR + filnavn));
				Scanner        reader = new Scanner(file);

				int linenumber = 1;

				// les innhold i filen linje for linje
				String line;

				while (reader.hasNextLine()) {
					line = reader.nextLine();
					System.out.println(linenumber + " " + line);
					linenumber++;
				}

				reader.close();
				attempts = 0;
			} catch (FileNotFoundException e){
				JOptionPane.showMessageDialog(null, "Filen " + filnavn +
				                                    " finnes ikke. \n" + e.getMessage());
				attempts--;
			}
		} while (attempts > 0);

	}
}
