package no.hvl.dat100;

import static javax.swing.JOptionPane.*;

public class OppgaveG4 {

	public static void main(String[] args) {
		
		int n = 5; // velger antall tall som skal skrives inn
		
		int[] nums = new int[n]; // lager en array med n antall int
		
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(showInputDialog("Skriv et heltall"));
			System.out.println("Tall: " + nums[i]);
		}
		

	}

}
