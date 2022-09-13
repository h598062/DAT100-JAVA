import java.util.Scanner;

public class oppg1a {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Skriv inn et tall: ");
		String input = scanner.nextLine();
		int a = Integer.parseInt(input);
		if (a > 0) {
			System.out.println(a + " er større enn 0");
		} else if (a == 0) {
			System.out.println(a + " er lik 0");
		} else if (a < 0) {
			System.out.println(a + " er mindre enn 0");
		}
	}
}
