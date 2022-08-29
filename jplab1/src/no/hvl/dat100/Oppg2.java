package no.hvl.dat100;

import static javax.swing.JOptionPane.*;

public class Oppg2 {

	public static void main(String[] args) {
		
		double input1 = Double.parseDouble(showInputDialog("Tall 1"));
		double input2 = Double.parseDouble(showInputDialog("Tall 2"));
		double input3 = Double.parseDouble(showInputDialog("Tall 3"));
		double input4 = Double.parseDouble(showInputDialog("Tall 4"));
		
		double minNum = Math.min(input1, input2);
		minNum = Math.min(minNum, input3);
		minNum = Math.min(minNum, input4);
			
		String output = "Minste tall: " + minNum;
		
		showMessageDialog(null, output);
		
	}

}
