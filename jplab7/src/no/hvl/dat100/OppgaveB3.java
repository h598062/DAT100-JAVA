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
			System.out.println(string);
			System.out.println(snuString(string));
		}
		System.out.println("Første string alfabetisk: " + nr1Str(strings));
		System.out.println();
		System.out.print("Skriv inn et mønster til å sjekke strengene: ");
		String s = scanner.nextLine();
		int    j = 0;
		for (String string : strings) {
			if (string.matches(s)) {
				j++;
			}
		}
		System.out.println("Antall som matcher: " + j);

		for (String string : strings) {
			if (string.length() == 1) {
				System.out.println(string);
			} else if (string.length() % 2 == 1) {
				if (string.length() == 3) {
					System.out.println(string);
				} else {
					System.out.println(string.charAt(string.length() / 2 - 1) + " " +
					                   string.charAt(string.length() / 2) + " " +
					                   string.charAt(string.length() / 2 + 1));
				}
			} else {
				if (string.length() == 2) {
					System.out.println(string);
				} else {
					System.out.println(string.charAt(string.length() / 2 - 1) + " " +
					                   string.charAt(string.length() / 2));
				}
			}
		}

		scanner.close();
	}

	public static String snuString(String s) {
		StringBuilder newS = new StringBuilder();
		for (int i = s.length() -1; i >= 0; i--) {
			newS.append(s.charAt(i));
		}
		return newS.toString();
	}

	public static String nr1Str(String[] strings) {
		int i = 0;
		String out = "";
		do {
			int j;
			if (i == 0) {
				j = strings[i].compareTo(strings[i+1]);
				if (j < 0) {
					out = strings[i+1];
				} else {
					out = strings[i];
				}
				i++;
			} else {
				j = out.compareTo(strings[i]);
				if (j < 0) {
					out = strings[i];
				}
			}

			i++;
		} while (i < strings.length);
		return out;
	}
}
