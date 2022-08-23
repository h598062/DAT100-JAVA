package jplab1;

import static javax.swing.JOptionPane.*;
import static java.lang.Double.*;

public class Oppg1 {

	public static void main(String[] args) {
		
		String input1 = showInputDialog("Lengde");
		String input2 = showInputDialog("Bredde");
		
		double inputNum1 = parseDouble(input1);
		double inputNum2 = parseDouble(input2);
		
		double sum = inputNum1 * inputNum2;
		
		String output = "Areal: " + sum;
		
		showMessageDialog(null, output);
	}

}
