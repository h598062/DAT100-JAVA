package no.hvl.dat100.oppgaveg1;

public class Oppdatering {

	public static void main(String[] args) {

		int[] tab = {1, 2, 3, 4, 5, 6};

		// skriv ut
		System.out.print("[ ");

		for (int x : tab) {
			System.out.print(x + " ");
		}

		System.out.println("]");

		// oppdater
		for (int i = 0; i < tab.length; i++) {
			tab[i]++;
		}

		// skriv ut
		System.out.print("[ ");

		for (int x : tab) {
			System.out.print(x + " ");
		}

		System.out.println("]");
	}

}
