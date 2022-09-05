package no.hvl.dat100;

public class OppgaveG1 {
	public static void main(String[] args) {
		int n = 10; // sett denne til whatever > 1
		// hvis n<i så vil ikke for løkken kjøre, da vil statement i<n være false og løkken vil aldri starte
		for (int i = 1; i < n; i++) {
			System.out.println(i);
		}
	}
}
