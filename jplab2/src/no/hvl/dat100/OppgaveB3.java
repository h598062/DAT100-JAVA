package no.hvl.dat100;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class OppgaveB3 {
	public static void main(String[] args) {
		login();
	}
	public static void login () {
		String brukernavn = getUsername();
		String passord = getPassword();
		// 0 = ok, 1 = feil passord, 2 = feil brukernavn
		int loginStatus = evalLogin(brukernavn, passord);

		while (loginStatus >= 0) {
			switch (loginStatus) {
				case 0 -> {
					showMessageDialog(null, "Du er nå logget inn :)");
					return;
				}
				case 1 -> {
					showMessageDialog(null, "Feil passord, prøv igjen");
					passord = getPassword();

				}
				case 2 -> {
					showMessageDialog(null, "Feil brukernavn, prøv igjen");
					brukernavn = getUsername();
					passord = getPassword();
				}
				default -> {
					System.out.println("invalid loginstatus " + loginStatus);
					showMessageDialog(null, "Error, start program på nytt");
					return;
				}
			}
			loginStatus = evalLogin(brukernavn, passord);
		}

	}
	private static String getUsername() {
		String usr = showInputDialog("Brukernavn :");
		if (usr == null){
			throw new RuntimeException("user cancelled the input process");
		} else {
			usr = usr.toLowerCase();
		}
		System.out.println("brukernavn: " + usr);
		return usr;
	}

	private static String getPassword() {
		String pwd = showInputDialog("Passord :");
		if (pwd == null){
			throw new RuntimeException("user cancelled the input process");
		}
		System.out.println("Passord: " + pwd);
		return pwd;
	}
	private static int evalLogin(String brukernavn, String passord){
		String riktigBrukernavn = "bjådd";
		String riktigPassord = "1234";
		if (riktigBrukernavn.equals(brukernavn)){
			if (riktigPassord.equals(passord)){
				// riktig login info
				return 0;
			} else {
				// feil passord
				System.out.println("passord: " + passord + " vs " + riktigPassord);
				return 1;
			}
		} else {
			// feil brukernavn
			System.out.println("brukernavn: " + brukernavn + " vs " + riktigBrukernavn);
			return 2;
		}
	}
}
