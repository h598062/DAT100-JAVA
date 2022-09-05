package no.hvl.dat100;

public class OppgaveG4 {

	public static void main(String[] args) {

		int i;

		System.out.println("Test av repetisjonssetning.");
		System.out.println("***************************");

		i = 1;

		while (i <= 10) {
			System.out.println("Dette er linje nr. " + i);

			if (i == 4) {
				System.out.println();
			}

			if (i == 8) {
				System.out.println();
			}

			i = i + 1;
		}
	}
}