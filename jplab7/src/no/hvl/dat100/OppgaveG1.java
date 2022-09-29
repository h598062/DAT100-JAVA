package no.hvl.dat100;

public class OppgaveG1 {
	public static void main(String[] args) {
		Kvadrat kv3 = new Kvadrat(3);
		Kvadrat kv4 = new Kvadrat(4);
		Kvadrat kv5 = new Kvadrat(5);

		kv3.printInfo();
		kv4.printInfo();
		kv5.printInfo();
		System.out.println("kv4 = kv3 + kv5: " + kv4.sum(kv3, kv5));
		System.out.println("kv5 = kv3 + kv4: " + kv5.sum(kv3, kv4));
	}
}

class Kvadrat {
	int sidelengde;

	public Kvadrat(int sidelengde) {
		this.sidelengde = sidelengde;
	}

	public int beregnAreal() {
		int s = this.sidelengde;
		return s * s;
	}

	public int beregnOmkrets() {
		int s = this.sidelengde;
		return s * 4;
	}

	public double beregnDiagonal() {
		double s = this.sidelengde;
		return trimDesimal(Math.sqrt(2*s*s));
	}

	public void drawConsole() {
		for (int i = 0; i < this.sidelengde; i++) {
			for (int j = 0; j < this.sidelengde; j++) {
				System.out.print("*  ");
			}
			System.out.println();
		}
	}

	public void printInfo() {
		int a = this.beregnAreal();
		int o = this.beregnOmkrets();
		double d = this.beregnDiagonal();
		System.out.println("--------------------");
		System.out.println("Sidelengde: " + this.sidelengde + "\nAreal: " + a + "\nOmkrets: " + o + "\nDiagonal: " + d);
		this.drawConsole();
		System.out.println("--------------------");
	}

	public boolean sum(Kvadrat k1, Kvadrat k2) {
		return k1.beregnAreal() + k2.beregnAreal() == this.beregnAreal();
	}

	private double trimDesimal(double tall) {
		return Math.floor(tall*100)/100;
	}
}
