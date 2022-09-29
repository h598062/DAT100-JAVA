package no.hvl.dat100;

public class OppgaveG2 {
	public static void main(String[] args) {
		Konto k1 = new Konto(123456);
		Konto k2 = new Konto(987654);
		Konto k3 = new Konto(3);
		System.out.println("Konto1: " + k1.getKontonummer() + " Saldo: " + k1.getSaldo());
		System.out.println("Konto2: " + k2.getKontonummer() + " Saldo: " + k2.getSaldo());
		System.out.println("Konto3: " + k3.getKontonummer() + " Saldo: " + k3.getSaldo());

		System.out.println("Setter inn 4000 på k1: " + k1.settInn(4000));
		System.out.println("Tar ut 300 på k2: " + k2.taUt(300));
		System.out.println("Setter inn 23432132 på k3: " + k3.settInn(23432132));
		System.out.println("Tar ut 65462 på k3: " + k3.taUt(-65462));

		System.out.println("Konto1: " + k1.getKontonummer() + " Saldo: " + k1.getSaldo());
		System.out.println("Konto2: " + k2.getKontonummer() + " Saldo: " + k2.getSaldo());
		System.out.println("Konto3: " + k3.getKontonummer() + " Saldo: " + k3.getSaldo());
	}
}

class Konto {
	private final int kontonummer;
	private       int saldo;

	public Konto(int kontonummer) {
		this.kontonummer = kontonummer;
		this.saldo = 0;
	}

	public boolean settInn(int mengde) {
		if (mengde < 0) {
			System.out.println("Negativ verdi oppdaget, bruk removeFunds()");
			return false;
		}
		this.saldo += mengde;
		System.out.println("La til " + mengde + ", saldo er nå " + this.saldo);
		return true;
	}

	public boolean taUt(int mengde) {
		if (mengde < 0) {
			mengde *= -1;
		}
		if (mengde > this.saldo) {
			System.out.println("Kan ikke ta ut penger, " + mengde + " er mer enn saldo " + this.saldo);
			return false;
		}
		this.saldo -= mengde;
		System.out.println("Tok ut " + mengde + ", saldo er nå " + this.saldo);
		return true;
	}

	public int getSaldo() {
		return saldo;
	}

	public int getKontonummer() {
		return kontonummer;
	}

}
