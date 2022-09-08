package no.hvl.dat100;

public class Oppgave2 {
	public static void main(String[] args) {
		a();
		b();
		System.out.println(c(28, 12));  // 4
	}

	private static void a() {
		int     i = 5;
		int     j = 10;
		String  s = "dat100";
		boolean b = true;

		System.out.println(i * j + i);          // (5x10) + 5 = 55
		System.out.println(b && i > 5);         // 'b && i > 5' er alltid false
		System.out.println(!b || j == 10);      // '!b || j == 10' er alltid true
		System.out.println(s.substring(3, 5));  // 10
	}

	private static void b() {
		System.out.println(5 + 3 * 2);          // 5 + (3 * 2) = 11
		System.out.println(9 % 5);              // 4
		System.out.println(9 / 5);              // 1
		int i = 3;
		int j = 7;

		System.out.println((i < 3) && (j >= 5));// '(i < 3) && (j >= 5)' e alltid false
		System.out.println((j != 3) || (i < j));// '(j != 3) || (i < j)' e alltid true
	}

	public static int c(int x, int y) {

		while (x != y) {
			System.out.println("x = " + x + ", y = " + y);
			if (x > y) {
				x = x - y;
			} else {
				y = y - x;
			}
		}

		return x;
	}
}
