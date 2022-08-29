package no.hvl.dat100;

import java.util.ArrayList;
import java.util.Collections;

import static javax.swing.JOptionPane.*;

public class OppgaveV1komplisert {
	public static void main(String[] args) {
		ArrayList<Integer> tall = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			tall.add(Integer.parseInt(showInputDialog("Skriv inn et tall")));
		}
		System.out.println(tall);
		Collections.sort(tall);
		System.out.println(tall);

	}
}