package no.hvl.dat100;

import java.util.Arrays;

public class Sortering {
	public static void main(String[] args) {
		int[] sortertTab = {1, 3, 5, 7, 7, 9, 11};
		int[] usortertTab = {111, 3, 5, 0, 7, 6, 9, 11};

		System.out.println(erSortert(sortertTab));
		System.out.println(erSortert(usortertTab));

		for (int tall : usortertTab) {
			System.out.print(tall + " ");
		}

		System.out.println();
		plukkSortering(usortertTab);

		for (int tall : usortertTab) {
			System.out.print(tall + " ");
		}
		System.out.println(" ");

		int[] nyTab = {0, 1, 7, 9, 16};
		System.out.println(Arrays.toString(settInnSortert(nyTab, 8)));

	}

	public static void byttOm(int[] tab, int i, int j) {
		int tmp = tab[i];
		tab[i] = tab[j];
		tab[j] = tmp;
	}

	public static boolean erSortert(int[] tab) {
		boolean sortert = true;
		int i = 0;
		while(i < tab.length -1 && sortert) {
			if (tab[i] > tab[i+1]){
				sortert = false;
			}
			i++;
		}
		System.out.println("i er: " + i);
		return sortert;
	}

	public static int[] settInnSortert(int[] tab, int tall) {
		int[] nyTab = new int[tab.length + 1];
		int i = 0;
		int j = 0;

		while (i < tab.length && tab[i] <= tall) {
			nyTab[j] = tab[i];
			i++;
			j++;
		}
		nyTab[j] = tall;
		j++;

		while (i < tab.length) {
			nyTab[j] = tab[i];
			i++;
			j++;
		}

		return nyTab;
	}

	public static int finnMinstePos(int[] tab, int j) {
		int mP = j;
		for (int i = j + 1; i < tab.length; i++) {
			if (tab[i] < tab[mP]){
				mP = i;
			}
		}
		return mP;
	}

	public static void plukkSortering(int[] a){
		for (int i = 0; i < a.length; i++) {
			int p = finnMinstePos(a, i);
			byttOm(a, i, p);
		}

	}
}
