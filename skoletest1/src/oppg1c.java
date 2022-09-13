import java.util.Scanner;

public class oppg1c {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Skriv inn et tall: ");
		String input = scanner.nextLine();
		int tall = Integer.parseInt(input);
		String svar;
		switch (tall) {
			case 0:
				svar = "null";
				break;
			case 1:
				svar = "en";
				break;
			case 2:
				svar = "to";
				break;
			case 3:
				svar = "tre";
				break;
			case 4:
				svar = "fire";
				break;
			default:
				svar = "ulovlig verdi";
				break;
		}
		System.out.println(svar);
	}
}
