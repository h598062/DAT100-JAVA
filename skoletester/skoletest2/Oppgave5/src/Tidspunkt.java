public class Tidspunkt extends Bestilling {
	private String dato;
	private String tidspunkt;

	public Tidspunkt(String navn, int kundenummer, String adresse, String dato, String tidspunkt) {
		super(navn, kundenummer, adresse);
		this.dato = dato;
		this.tidspunkt = tidspunkt;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public String getTidspunkt() {
		return tidspunkt;
	}

	public void setTidspunkt(String tidspunkt) {
		this.tidspunkt = tidspunkt;
	}

	@Override
	public void skrivUt() {
		System.out.println("Kundenummer: " + this.getKundenummer() +
		                   "\nNavn: " + this.getKundenavn() +
		                   "\nAdresse: " + this.getAdresse() +
		                   "\nDato og klokkeslett: " + this.dato + " klokken " + this.tidspunkt);
	}
}
