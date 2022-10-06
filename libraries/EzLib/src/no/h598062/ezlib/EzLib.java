package no.h598062.ezlib;

/**
 * <h2>EzLib</h2>
 * <p>
 * EzLib er en enkel library klasse som har noen funksjoner for å enklere printe ut
 * fine tekst strenger til f.eks. console. <br>
 * Den har også noen andre funksjoner som finner lengste streng i en streng-array etc. <br>
 * <br>
 * <a href="https://github.com/h598062/DAT100-JAVA/tree/main/libraries/EzLib">EzLib Source</a>
 * <br>
 *
 * @author h598062 aka Bjørnar
 */
public class EzLib {
	// dette er et library, ikke la noen lage et object av class
	private EzLib() {
	}

	/**
	 * <h3>fillWithChar</h3>
	 * Fyller ut foran og etter en string med noen tegn<br>
	 *
	 * @param input    En vanlig streng
	 * @param fillChar Tegnet som skal brukes til å fylle med. Må være en ikke-tom streng
	 * @param length   Hvor lang hele output strengen skal være totalt inkludert input
	 * @param startpos Hvor mye som skal fylles inn foran input streng
	 *
	 * @return streng med symboler fylt foran og etter input streng
	 */
	public static String fillWithChar(String input, String fillChar, int length, int startpos) {
		if (input.length() >= length) {
			return input;
		} else if (fillChar.length() < 1) {
			System.err.println("EzLib: fillChar må være på minst ett tegn");
			return null;
		} else if (length < startpos) {
			System.err.println("EzLib: Startpos MÅ være mindre enn lengde. DUH\n" +
			                   "Bruk fillEndWithChar() hvis du kun skal fylle slutte nav en streng.");
			return null;
		} else if (input.length() + startpos > length) {
			System.err.println("EzLib: input streng lengde + startpos blir lengre enn max lengde fra length");
			return null;
		}
		String out = "";
		// fyll foran fram til start pos
		out += fillChar.repeat(startpos);
		// legg til input string
		out += input;
		// fyll resten
		if (out.length() < length) {
			out += fillChar.repeat(length - input.length() - startpos);
		}
		return out;
	}

	/**
	 * <h3>fillStartWithChar</h3>
	 * fyller ut starten av en streng med noen tegn
	 *
	 * @param input    En vanlig streng
	 * @param fillChar Tegnet som skal brukes til å fylle med. Må være en ikke-tom streng
	 * @param length   Hvor lang hele output strengen skal være totalt inkludert input
	 *
	 * @return streng med symboler fylt foran input streng
	 */
	public static String fillStartWithChar(String input, String fillChar, int length) {
		if (input.length() >= length) {
			return input;
		} else if (fillChar.length() < 1) {
			System.err.println("EzLib: fillChar må være på minst ett tegn");
			return input;
		}
		String out = "";
		out += fillChar.repeat(length - input.length());
		out += input;
		return out;
	}


	/**
	 * <h3>fillEndWithChar</h3>
	 * fyller ut slutten av en streng med noen tegn
	 *
	 * @param input    En vanlig streng
	 * @param fillChar Tegnet som skal brukes til å fylle med. Må være en ikke-tom streng
	 * @param length   Hvor lang hele output strengen skal være totalt inkludert input
	 *
	 * @return streng med symboler fylt etter input streng
	 */
	public static String fillEndWithChar(String input, String fillChar, int length) {
		if (input.length() >= length) {
			return input;
		} else if (fillChar.length() < 1) {
			System.err.println("EzLib: fillChar må være på minst ett tegn");
			return input;
		}
		String out = "";
		out += input;
		out += fillChar.repeat(length - input.length());
		return out;
	}

	/**
	 * <h3>maxStringLength</h3>
	 * Finner den lengste strengen i en liste med strenger, og returnerer lengden
	 *
	 * @param input En array med strenger
	 *
	 * @return Heltall med lengden på lengste streng
	 */
	public static int maxStringLength(String[] input) {
		int max;
		if (input.length == 0) {
			System.err.println("EzLib: Input string array må ha noen elementer");
		} else if (input.length == 1) {
			return input[0].length();
		}
		max = Math.max(input[0].length(), input[1].length());
		for (int i = 2; i < input.length; i++) {
			max = Math.max(max, input[i].length());
		}
		return max;
	}

	/**
	 * <h3>trimDesimaler</h3>
	 * Trimmer input tall ned til spesifisert antall desimaler.<br>
	 * Hvis ingen antall desimaler er spesifisert trimmer den til 2
	 *
	 * @param tall tallet som skal trimmes
	 *
	 * @return float med antall desimaler
	 */
	public static double trimDesimal(double tall) {
		return trimDesimal(tall, 2);
	}

	/**
	 * <h3>trimDesimaler</h3>
	 * Trimmer input tall ned til spesifisert antall desimaler.<br>
	 * Hvis ingen antall desimaler er spesifisert trimmer den til 2
	 *
	 * @param tall            tallet som skal trimmes
	 * @param antallDesimaler hvor mange desimaler en vil beholde
	 *
	 * @return float med antall desimaler
	 */
	public static double trimDesimal(double tall, int antallDesimaler) {
		int xdes = (int) Math.pow(10, antallDesimaler);
		return Math.floor(tall * xdes) / xdes;
	}
}
