import java.util.Scanner;

public class oppg4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Skriv inn et tall: ");
		String input1 = scanner.nextLine();
		int    tall1  = Integer.parseInt(input1);
		System.out.print("Skriv inn et tall til: ");
		String input2 = scanner.nextLine();
		int    tall2  = Integer.parseInt(input2);
		System.out.print("Skriv inn siste tall: ");
		String input3 = scanner.nextLine();
		int    tall3  = Integer.parseInt(input3);
		System.out.println(minst(tall1, tall2, tall3) + " er minst");
	}

	public static int minst(int x, int y, int z) {
		int minst = Math.min(x, y);
		minst = Math.min(minst, z);
		return minst;
	}
}

