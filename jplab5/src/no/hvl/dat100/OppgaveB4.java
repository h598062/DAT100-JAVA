package no.hvl.dat100;

import java.util.Scanner;

public class OppgaveB4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double[] temperaturer = new double[7];
		for (int i = 0; i < temperaturer.length; i++) {
			String input = "";
			do {
				System.out.print("Hva var temperaturen " + (i+1) + ". dag?  ");
				input = scanner.nextLine();
			} while (input.length() == 0);
			temperaturer[i] = Double.parseDouble(input);
		}
		double avgtmp = trimDesimal(sumDoubleArray(temperaturer) / temperaturer.length);
		System.out.println("Gjennomsnittstemperaturen for uken var: " + avgtmp);
		System.out.println("Høyeste temperatur denne uken var: " + trimDesimal(findBiggest(temperaturer)));
	}

	private static double trimDesimal(double tall) {
		return Math.floor(tall * 100) / 100;
	}
	private static double sumDoubleArray(double[] liste) {
		double sum = 0;
		for (double tall : liste) {
			sum += tall;
		}
		return sum;
	}

	private static double findBiggest(double[] liste) {
		int i = 0;
		double out = 0;
		while (i < liste.length-1) {
			if (i == 0) {
				out = Math.max(liste[i], liste[i+1]);
				i++;
			} else {
				out = Math.max(out, liste[i]);
			}
			i++;
		}
		return out;
	}
}
