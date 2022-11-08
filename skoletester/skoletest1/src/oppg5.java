import java.util.Scanner;

public class oppg5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Skriv inn radius: ");
		String input = scanner.nextLine();
		double tall = Double.parseDouble(input);
		System.out.println("Arealet er: " + arealSirkel(tall));
	}

	public static double arealSirkel(double radius) {
		return Math.PI * radius * radius;
	}
}