package no.hvl.dat100;

import java.util.Scanner;

public class OppgaveB3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Skriv inn antall ord (heltall): ");
		int antall = Integer.parseInt(scanner.nextLine());
		System.out.println();
		String[] strings = new String[antall];
		for (int i = 0; i < strings.length; i++) {
			System.out.print("Skriv inn en streng: ");
			strings[i] = scanner.nextLine();
		}
		for (String string : strings) {
			System.out.println("String vanlig: " + string);
			System.out.println("String snudd: " + snuString(string));
		}
		System.out.println("Første string alfabetisk: " + nr1Str(strings));
		System.out.println();
		System.out.print("Skriv inn et mønster til å sjekke strengene: ");
		String s = scanner.nextLine();
		int    j = 0;
		for (String string : strings) {
			if (string.matches(s)) { // regex pattern
				j++;
			}
		}
		System.out.println("Antall som matcher: " + j);

		for (String string : strings) {
			if (string.length() == 1) {
				System.out.println(string);
			} else if (string.length() % 2 == 1) {
				if (string.length() == 3) {
					// hvis den kun er 3 lang så skal alle printes uansett
					System.out.println(string);
				} else {
					// eks: 9 / 2 = 4, 4 er midterste tall siden vi begynner fra 0
					// 4-1 og 4+1 blir bokstavene ved sidenav
					System.out.println(string.charAt(string.length() / 2 - 1) + " " +
					                   string.charAt(string.length() / 2) + " " +
					                   string.charAt(string.length() / 2 + 1));
				}
			} else {
				if (string.length() == 2) {
					System.out.println(string);
				} else {
					// eks: 10 / 2 = 5, 5 blir teknisk sett bokstav nr 6 siden begynner på 0
					// ved partall skal de to i midten printes, altså for 10 blir det bokstav nr 5 og 6
					System.out.println(string.charAt(string.length() / 2 - 1) + " " +
					                   string.charAt(string.length() / 2));
				}
			}
		}

		scanner.close();
	}

	public static String snuString(String s) {
		StringBuilder newS = new StringBuilder();
		// start for løkke ved siste posisjon i string og gå nedover mot 0
		for (int i = s.length() - 1; i >= 0; i--) {
			// legg til hver char i string inn i stringbuilder
			newS.append(s.charAt(i));
		}
		return newS.toString();
	}

	public static String nr1Str(String[] strings) {
		int    i   = 0;
		String out = "";
		do {
			int j;
			if (i == 0) { // unik håndtering "første" runde
				// lagre svaret i j variabel
				j = strings[i].compareTo(strings[i + 1]);
				// hvis j er mer enn 0 så er strings[i] "større" enn strings[i+1]
				// dette er da større i som at den kommer etter alfabetisk
				if (j > 0) {
					// lagre den første av stringsene i alfabetiske rekkefølge i variabel out
					out = strings[i + 1];
				} else {
					out = strings[i];
				}
				i++;
			} else {
				// samme som i if statement, bare at vi sammenligner med den vi fant forrige loop
				j = out.compareTo(strings[i]);
				if (j > 0) {
					out = strings[i];
				}
			}

			i++;
		} while (i < strings.length);
		return out;
	}
}
