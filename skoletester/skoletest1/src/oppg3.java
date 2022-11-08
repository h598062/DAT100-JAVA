import java.util.Scanner;

public class oppg3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int x;
		int tall = 0;
		int sum = 0;
		System.out.println("Skriv inn tall for å summere\nSkriv 0 for å avslutte");
		do {
			System.out.print("Skriv inn et tall: ");
			String input = scanner.nextLine();
			x = Integer.parseInt(input);
			tall += 1;
			sum += x;
		} while (x != 0);
		System.out.println("Antall tall: " + tall);
		System.out.println("Sum av tall: " + sum);

	}
}
