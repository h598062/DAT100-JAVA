public class ToDoListe {
	private ToDoElement[] todoTab;
	private int antall;

	public ToDoListe(int maksAntall) {
		this.todoTab = new ToDoElement[maksAntall];
		this.antall = 0;
	}

	public void leggTil(ToDoElement tde) {
		if (tde == null) {
			System.out.println("Kan ikke legge inn element, elementet er null");
			throw new NullPointerException("Kan ikke legge inn element, elementet er null");
		}
		if (this.antall < todoTab.length) {
			this.todoTab[this.antall] = tde;
			this.antall++;
		} else {
			System.out.println("Kan ikke legge inn element, listen er full");
			throw new RuntimeException("Kan ikke legge inn element, listen er full");
		}
	}

	public ToDoElement finnViktig() {
		if (this.antall == 0) {
			System.out.println("Kan ikke finne viktig da det ikke eksisterer noen elementer i listen");
			throw new RuntimeException("Kan ikke finne viktig da det ikke eksisterer noen elementer i listen");
		}
		int maxIndex = 0;
		for (int i = 1; i < this.antall; i++) {
			if (this.todoTab[i].getPrioritet() > this.todoTab[maxIndex].getPrioritet()) {
				maxIndex = i;
			}
		}
		return this.todoTab[maxIndex];
	}
}
