package no.hvl.dat100;

public class OppgaveB1 {
	public static void main(String[] args) {
		// true = plass er opptatt
		boolean[][] aud =
				{
						{ false, false, false, true, false, false, true, false, false, true}, // rad 1
						{ false, false, true,  false, false, false, true,  false, false, false}, // rad 2
						{ true,  false, false,  false, true,  false, false,  false, false, true }, // rad 3
						{ true,  false,  false,  true, false, false, true,  false,  false,  true }, // rad 4
						{ false, true, true} // rad 5
				};

		System.out.println("Det er " + antallLedig(aud) + " ledige plasser, av " + antallPlasser(aud));
		System.out.println("Det er " + prosentLedig(aud) + "% ledig");
		System.out.println("Finner en ledig plass....");
		System.out.println(finnLedig(aud, true));
		if (smittevernSjekk(aud)) {
			System.out.println("Posisjonene opprettholder smittevern");
		} else {
			System.out.println("Posisjonene opprettholder IKKE smittevern");
		}
	}

	public static int antallLedig(boolean[][] tabell) {
		int ledige = 0;
		for (boolean[] booleans : tabell) {
			for (boolean erLedig : booleans) {
				if (!erLedig) {
					ledige +=1;
				}
			}
		}
		return ledige;
	}

	public static int antallPlasser(boolean[][] tabell) {
		int plasser = 0;
		for (boolean[] booleans : tabell) {
			plasser += booleans.length;
		}
		return plasser;
	}

	public static double prosentLedig(boolean[][] tabell){
		int ledig = antallLedig(tabell);
		int plasser = antallPlasser(tabell);
		if(plasser == 0){
			System.out.println("tabellen har ingen plasser definert");
			return -1.0;
		}
		double prosent = ((double)ledig*100) / plasser;
		return trimTil2Desimaler(prosent);
	}

	public static double trimTil2Desimaler(double tall) {
		return Math.floor(tall * 100) / 100;
	}

	public static String finnLedig(boolean[][] tabell, boolean sluttTilStart) {
		if (sluttTilStart) {
			for (int i = tabell.length -1; i >= 0; i--) {
				for (int j = tabell[i].length -1; j >= 0; j--) {
					if (!tabell[i][j]) {
						// legg til 1, ingen plasser og rader bruker 0 som start
						return "Rad: " + (i+1) + " Plass: " + (j+1) + " er ledig";
					}
				}
			}
		} else {
			for (int i = 0; i < tabell.length; i++) {
				for (int j = 0; j < tabell[i].length; j++) {
					if (!tabell[i][j]) {
						// legg til 1, ingen plasser og rader bruker 0 som start
						return "Rad: " + (i+1) + " Plass: " + (j+1) + " er ledig";
					}
				}
			}
		}
		return "Fant ingen ledige plasser";
	}

	public static boolean smittevernSjekk(boolean[][] tabell) {
		for (boolean[] booleans : tabell) {
			//System.out.println(Arrays.toString(booleans));
			for (int i = 0; i < booleans.length; i++) {
				//System.out.println(i);
				if (!booleans[i]) {
					// hvis plassen vi sjekker ikke er opptatt, g?? videre til neste plass
					continue; // continue vil avslutte denne runden i for l??kken, og g?? videre til neste runde
					// alts?? hopp over resten av koden og ??k i med 1
				}
				// f??rste, andre, nest siste og siste plass i hver rad m?? ha spesifikk sjekk
				// hvis ikke kan det hende at vi leter etter data utenfor det som er definert i tabellen
				if (i == 0) { // hvis f??rste plass i raden
					if (booleans[i + 1] || booleans[i + 2]) { // hvis en av de to neste plassene er tatt
						return false;
					}
				} else if (i == 1) { // hvis andre plass
					if (booleans.length > 3) {
						// hvis det er mer enn 3 seter slik at det er en plass til venstre og 2 til h??yre
						if (booleans[i - 1] || booleans[i + 2]) { // kan kanskje fjerne i-1
							// sjekk om plass til venstre og to til h??yre
							// plass 1 til h??yre ble sjekket forrige loop
							return false;
						}
					} else { // hvis det er 3 eller f??rre seter
						if (booleans[i - 1]) {
							// sjekk plass til venstre, plass til h??yre ble sjekket f??rste loop
							return false;
						}
					}
				} else if (i == booleans.length - 1) { // hvis siste plass i rekken
					// trenger ikke sjekke lengde p?? rekken, da hadde den blitt plukket opp av forrige sjekk
					if (booleans[i - 1] || booleans[i - 2]) { // sjekk plassene til venstre
						return false;
					}
				} else if (i == booleans.length - 2) { // hvis nest siste plass
					if (booleans[i - 1] || booleans[i - 2] || booleans[i + 1]) { // sjekk plassene rundt
						return false;
					}
				} else { // hvis det er to plasser p?? hver side kj??res denne sjekken
					if (booleans[i - 1] || booleans[i - 2] || booleans[i + 1] || booleans[i + 2]) {
						// sjekk begge plassene p?? hver side
						return false;
					}
				}
			}
		}
		return true;
	}
}
