package no.hvl.dat100;

public class OppgaveB1 {
	public static void main(String[] args) {
		printSinCosin(0, 180, 5);
	}

	private static void printSinCosin(double startAngle, double stopAngle, double step) {
		System.out.println("x i grader  x i radianer    sin(x)  cos(x)");
		System.out.println("------------------------------------------");
		for (double i = startAngle; i <= stopAngle; i+=step) {
			double j = Math.toRadians(i);
			System.out.println(
					fillWithSpace(String.valueOf(i), 10) + "  " +
					fillWithSpace(String.valueOf(j), 12) + "    " +
					fillWithSpace(String.valueOf(Math.sin(j)), 6) + "  " +
					fillWithSpace(String.valueOf(Math.cos(j)), 6)
			);
		}
		System.out.println("------------------------------------------");
	}

	private static String fillWithSpace(String input, int length) {
		StringBuilder out        = new StringBuilder();
		String        bedreInput = input;
		if (input.length() > 5) {
			if (bedreInput.charAt(0) == '-'){
				bedreInput = input.substring(0, 6);
			} else {
				bedreInput = input.substring(0, 5);
			}
		}
		// intelliJ gjorde denne om til en stringbuilder
		out.append(" ".repeat(length - bedreInput.length()));
		return out + bedreInput;
	}
}
