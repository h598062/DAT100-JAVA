package no.hvl.dat100;

import java.util.Scanner;

public class OppgaveB5b {
	public static void main(String[] args) {
		/*
		* a)
		* Lag et program som leser inn en poengsum (heltall)
		* som en student har oppnådd på en prøve,
		* og finn og skriv ut den karakteren A-F dette tilsvarer.
		* Grenser for de ulike karakterene skal være:
		* A: 100-90
		* B: 89-80
		* C: 79-60
		* D: 59-50
		* E: 49-40
		* F: 39-0
		*
		* Skriv ut feilmelding ved ugyldig poengsum (negativ verdi eller over 100).
		*
		* b)
		* Utvid programmet i pkt. a) slik at det kan lese inn poengsummer fra 10 studenter
		* og skrive ut karakteren (eller feilmelding) etter hver innlesing.
		* HINT: hvordan kan du bruke en for-løkke til dette.
		*
		* c)
		* Legg inn kontroll på innlesinga i programmet i pkt. b)
		* slik at ugyldige poengsummer (negativ verdi eller over 100) må leses inn på nytt.
		*/
		// lager en scanner for å ta userinput via konsollvindu
		Scanner inputScanner = new Scanner(System.in);

		// for løkke kjører 10 ganger
		for (int i = 0; i < 10; i++) {
			// må legge til 1 i variabel i siden vi begynner på 0
			System.out.println("Student nr: " + (i+1));
			System.out.print("Skriv inn poengsum for å regne ut karakter: ");
			// les input fra konsollvindu
			String inputTxt = inputScanner.nextLine();
			int poengsum = Integer.parseInt(inputTxt);
			String karakter;

			if (poengsum >= 90 && poengsum <= 100){
				karakter = "A";
			} else if (poengsum >= 80 && poengsum <= 89) {
				karakter = "B";
			} else if (poengsum >= 60 && poengsum <= 79) {
				karakter = "C";
			} else if (poengsum >= 50 && poengsum <= 59) {
				karakter = "D";
			} else if (poengsum >= 40 && poengsum <= 49) {
				karakter = "E";
			} else if (poengsum >= 0 && poengsum <= 39) {
				karakter = "F";
			} else {
				karakter = "Ugyldig";
			}
			if (karakter.equals("Ugyldig")) {
				System.out.println("Ugyldig poengsum " + poengsum);
			} else {
				System.out.println("Studenten fikk karakteren " + karakter + " med poengsummen " + poengsum);
			}
		}
	}
}
