public abstract class Bestilling {
	private int    kundenummer;
	private String kundenavn;
	private String adresse;

	protected Bestilling(String navn, int kundenummer, String adresse) {
		this.kundenummer = kundenummer;
		this.kundenavn   = navn;
		this.adresse = adresse;
	}

	public int getKundenummer() {
		return kundenummer;
	}

	public void setKundenummer(int kundenummer) {
		this.kundenummer = kundenummer;
	}

	public String getKundenavn() {
		return kundenavn;
	}

	public void setKundenavn(String kundenavn) {
		this.kundenavn = kundenavn;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void skrivUt() {
		System.out.println("Kundenummer: " + this.kundenummer +
		                   "\nNavn: " + this.kundenavn +
		                   "\nAdresse: " + this.adresse);
	}
}
