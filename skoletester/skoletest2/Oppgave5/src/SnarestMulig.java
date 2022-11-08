public class SnarestMulig extends Bestilling {
	private int maxTid;

	public SnarestMulig(String navn, int kundenummer, String adresse, int maxTid) {
		super(navn, kundenummer, adresse);
		this.maxTid = maxTid;
	}

	public int getMaxTid() {
		return maxTid;
	}

	public void setMaxTid(int maxTid) {
		this.maxTid = maxTid;
	}

	@Override
	public void skrivUt() {
		System.out.println("Kundenummer: " + this.getKundenummer() +
		                   "\nNavn: " + this.getKundenavn() +
		                   "\nAdresse: " + this.getAdresse() +
		                   "\nMax ventetid: " + this.maxTid);
	}
}
