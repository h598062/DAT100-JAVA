package no.hvl.dat100;

import static javax.swing.JOptionPane.*;

public class Oppg2 {

	public static void main(String[] args) {
		
		int n = 4;
		if (n < 3) {
			throw new RuntimeException("var n må være større enn 2");
		}
		
		double[] input = new double[n+1];
		
		for (int i = 0; i < n; i++) {
			input[i] = Double.parseDouble(showInputDialog("Tall " + (i+1)));
		}
		double minNum;
		minNum = Math.min(input[0], input[1]);
		for (int i = 2; i < n-1; i++) {
			minNum = Math.min(minNum, input[i]);
		}
			
		String output = "Minste tall: " + minNum;
		
		showMessageDialog(null, output);
		
	}

}
