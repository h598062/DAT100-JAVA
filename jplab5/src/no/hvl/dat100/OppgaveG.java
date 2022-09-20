package no.hvl.dat100;

import java.util.Arrays;

public class OppgaveG {
	public static void main(String[] args) {
		int[] tabell = {1, 3, 4, 7, 9, 10};
		System.out.println(tabell[2]); // 4
		tabell[2] = 7;
		System.out.println(tabell[2]); // 7
		System.out.println("-------------------");
		System.out.println(Arrays.toString(tabell));
		System.out.println("-------------------");
		skrivUt(tabell);
		System.out.println("-------------------");
		System.out.println(finnes(tabell, 7));
		System.out.println("-------------------");
		skrivUt(skiftfortegn(tabell));
	}

	public static void skrivUt(int[] tab) {
		/*
		for (int i = 0; i < tab.length; i++) {
			System.out.println(tab[i]);
		}*/
		for (int num : tab) {
			System.out.println(num);
		}
	}
	public static boolean finnes(int[] tabell, int tall) {
		int i = 0;
		while (i < tabell.length) {
			if (tabell[i] == tall) {
				return true;
			}
			i++;
		}
		return false;
	}
	public static int[] skiftfortegn (int[] tabell) {
		// lager en ny tabell som er like stor som den som vi får inn
		int[] nyTabell = new int[tabell.length];
		for (int i = 0; i < tabell.length; i++) {
			nyTabell[i] = -tabell[i];
		}
		return nyTabell;
	}
}
