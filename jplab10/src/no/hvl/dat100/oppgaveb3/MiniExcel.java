package no.hvl.dat100.oppgaveb3;

public class MiniExcel {

	private static int[][] data = { 	
			{ 1, 2, 0 }, 
			{ 3, 4, 0 },
			{ 5, 6, 0 }, 
			{ 0, 0, 0 } 
			
	};

	public static void skrivUt() {

		System.out.println("---------------");
		for (int[] ints : data) {
			for (int i : ints) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		System.out.println("---------------");
	}

	public static void beregnSum() {

		// for hver rad (med unntak av siste rad) summer elementene
		// og skriv summen i den siste posisjonen for den aktuelle raden

		// for hver kolonne (med unntak av siste kolonne) summer elementene
		// og skriv summen i siste rad i posisjonen svarende til den aktuelle kolonnen

		for (int i = 0; i < data.length-1; i++) {
			for (int j = 0; j < data[i].length-1; j++) {
				data[i][data[i].length-1] += data[i][j];
				data[data.length-1][j] += data[i][j];
			}
		}

	}

	public static void main(String args[]) {

		skrivUt();

		beregnSum();

		skrivUt();
	}
}
