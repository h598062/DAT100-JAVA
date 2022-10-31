package no.hvl.dat100.oppgaveb4;

public class Utskrift {

	private Utskrift() {
	}

	private static final String[] finTerning = {"[   ]",
	                                            "[ . ]", "[ : ]", "[...]",
	                                            "[: :]", "[:.:]", "[:::]"};


	public static void skrivUt(Terning[] terninger) {
		for (int i = 0; i < terninger.length; i++) {
			if (i % 10 == 0 && i != 0) {
				System.out.println();
			}
			System.out.print(tilFinTerning(terninger[i].getOyne()) + "  ");
		}
	}

	public static String tilFinTerning(int tall) {
		if (tall < 1 || tall > 6) {
			throw new IllegalArgumentException("Tall må være fra 1 til og med 6, fikk: " + tall);
		}
		return finTerning[tall];
	}

	public static void skrivSvarLinje(int tall, int svar) {
		System.out.println("Antall " + tilFinTerning(tall) + "-ere: " + svar);

	}
}
