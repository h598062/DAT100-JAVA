package no.hvl.dat100;

public class OppgaveG1 {
	public static void main(String[] args) {

		double[][] obs = {
				{1.4,1.7,1.9,2.0},
				{5.4,7.8,9.8,7.9,3.0},
				{-1.7,-1.7,-2.8}
		};

		skrivUt(obs);
		// Denne vil kun skrive ut 3 fordi "øverste" tabell har 3 elementer, 3 nye tabeller
		System.out.println("Lengde på obs tabell: " + obs.length);

		// Hvert resultat er lengden på hver under tabell i obs tabellen
		System.out.println("Lengde på tab1: " + obs[0].length);
		System.out.println("Lengde på tab2: " + obs[1].length);
		System.out.println("Lengde på tab3: " + obs[2].length);

		System.out.println("Større enn 7.24 finnes i tabell: " + finnes(obs, 7.9));
		System.out.println("Større enn 11.3 finnes i tabell: " + finnes(obs, 11.3));
		byttRad(obs, 0, 2);
		skrivUt(obs);
		byttRad(obs, 1, 0);
		skrivUt(obs);
	}

	private static void byttRad(double[][] tab, int pos1, int pos2) {
		// lagre en av tabellene midlertidig slik at vi ikke mister den
		double[] tmpTab = tab[pos1];
		tab[pos1] = tab[pos2];
		tab[pos2] = tmpTab;
	}

	public static void skrivUt(double[][] tabell) {
		for (double[] tabell2 : tabell) {
			for (double tall : tabell2) {
				System.out.print(tall + " ");
			}
			System.out.println();
		}
	}

	public static boolean finnes(double[][] tab, double limit) {
		for (double[] tab2 : tab) {
			for (double tall : tab2) {
				if (tall > limit) {
					return true;
				}
			}
		}
		return false;
	}
}
