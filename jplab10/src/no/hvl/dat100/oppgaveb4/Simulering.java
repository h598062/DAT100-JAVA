package no.hvl.dat100.oppgaveb4;

public class Simulering {
	public static void main(String[] args) {
		int[]     svar      = new int[7];
		Terning[] terninger = new Terning[100];
		for (int i = 0; i < terninger.length; i++) {
			terninger[i] = new Terning();
		}
		for (Terning terning : terninger) {
			terning.trill();
			svar[terning.getOyne()] += 1;
		}
		Utskrift.skrivUt(terninger);
		System.out.println();
		System.out.println();
		System.out.println("Antall kast: " + terninger.length);
		for (int i = 1; i < svar.length; i++) {
			Utskrift.skrivSvarLinje(i, svar[i]);
		}
		System.out.println();

		System.out.println("Gjennomsnittskast: " + finnGjennomsnitt(terninger));
		System.out.println();

		System.out.println("Antall kast for å få den første " + Utskrift.tilFinTerning(6) + "-eren: " +
		                   finnForste6(terninger));
		System.out.println();
		System.out.println("Terningverdien det var flest av: " + Utskrift.tilFinTerning(finnFlest(svar)));
	}

	private static int finnFlest(int[] svar) {
		int max = Math.max(svar[1], svar[2]);
		max = Math.max(max, svar[3]);
		max = Math.max(max, svar[4]);
		max = Math.max(max, svar[5]);
		max = Math.max(max, svar[6]);
		for (int i = 0; i < svar.length; i++) {
			if (svar[i] == max) {
				return i;
			}
		}
		return 0;
	}

	private static int finnForste6(Terning[] terninger) {
		int     i      = 0;
		boolean funnet = false;
		while (i < terninger.length && !funnet) {
			if (terninger[i].getOyne() == 6) {
				funnet = true;
			} else {
				i++;
			}
		}
		return i;
	}

	private static String finnGjennomsnitt(Terning[] terninger) {
		double sum = 0;
		for (Terning terning : terninger) {
			sum += terning.getOyne();
		}
		double gjennomsnitt = sum / terninger.length;
		return String.format("%.3f", gjennomsnitt);
	}
}
