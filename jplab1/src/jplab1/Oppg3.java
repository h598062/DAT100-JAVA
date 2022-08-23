package jplab1;

import static javax.swing.JOptionPane.*;

public class Oppg3 {

	public static void main(String[] args) {
		String year = showInputDialog("Årstall");
		String month = showInputDialog("Måned");
		String day = showInputDialog("Dag");
		
		String date = day + "." + month + "." + year;
		
		showMessageDialog(null, date);
	}

}
