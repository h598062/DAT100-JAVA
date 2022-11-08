import java.util.Scanner;

public class TaxiProgram {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean isValid = false;
		Bestilling bestilling;

		System.out.println("Skriv inn hvilken bestilling du ønsker, muligheter er:");
		System.out.println("SnarestMulig eller Tidspunkt");
		while (!isValid) {
			System.out.print("Ønsket bestillingstype: ");
			String bestillingsType = scanner.nextLine();
			String navn;
			String adresse;
			switch (bestillingsType) {
				case "SnarestMulig":
					System.out.print("Skriv inn navn: ");
					navn = scanner.nextLine();
					System.out.print("Skriv inn adresse: ");
					adresse = scanner.nextLine();
					System.out.print("Skriv inn hvor mange minutter du kan vente: ");
					int maxVentetid = Integer.parseInt(scanner.nextLine());

					bestilling = new SnarestMulig(navn, 1000, adresse, maxVentetid);
					isValid = true;
					bestilling.skrivUt();
					break;
				case "Tidspunkt":
					System.out.print("Skriv inn navn: ");
					navn = scanner.nextLine();
					System.out.print("Skriv inn adresse: ");
					adresse = scanner.nextLine();
					System.out.print("Skriv inn dato du vil bli hentet: ");
					String dato = scanner.nextLine();
					System.out.print("Skriv inn tidspunkt du vil bli hentet: ");
					String tidspunkt = scanner.nextLine();
					System.out.println();
					bestilling = new Tidspunkt(navn, 1000,adresse, dato, tidspunkt);
					isValid = true;
					bestilling.skrivUt();
					break;
				default:
					System.out.println("Bestillingstype ikke funnet, prøv igjen");
			}
		}
	}
}
