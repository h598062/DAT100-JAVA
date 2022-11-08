import java.util.Scanner;

public class oppg1b {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Skriv inn et tall: ");
		String input = scanner.nextLine();
		int a = Integer.parseInt(input);
		if (a <= 8 && a >= 5) {
			System.out.println(a + " er innenfor intervallet");
		} else {
			System.out.println(a + " er utenfor intervallet");
		}
	}
}
