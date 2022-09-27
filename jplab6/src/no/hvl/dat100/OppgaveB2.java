package no.hvl.dat100;

import easygraphics.EasyGraphics;

public class OppgaveB2 extends EasyGraphics {

	// nesten dekket hvis mindre en fra havoverflaten
	int NESTEN_DEKKET = 1;

	int RADIUS = 20; // radius for cirkler som skal tegnes
	int MARGIN = 50; // venstre/høyre margin i tegneområdet

	// terreng hogde
	int[][] terreng = {{0, 0, 0, 3, 3, 3, 6, 7, 8, 10},
	                   {0, 0, 0, 3, 3, 3, 6, 7, 8, 10},
	                   {0, 0, 0, 3, 3, 3, 6, 7, 8, 10}};

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("FLOODING", 800, 300);

		visualiser();
	}

	public void visualiser() {

		System.out.println("Angi havhøyde i tegnevinduet ...");
		int hav = Integer.parseInt(getText("havhøyde"));

		while (hav >= 0) {
			for (int i = 0; i < terreng.length; i++) {
				for (int j = 0; j < terreng[i].length; j++) {
					if (terreng[i][j] < hav) {
						setColor(0,0,255); // blå - vann
					} else if (terreng[i][j] >= hav && terreng[i][j] <= hav + 1) {
						setColor(255,140,0); // oransje - søle
					} else {
						setColor(222,184,135); // lysebrun - tørt
					}
					fillCircle(MARGIN + j * 2 * RADIUS,MARGIN + i * 2* RADIUS,RADIUS);
				}
			}
			System.out.println("Angi havhøyde i tegnevinduet ...");
			hav = Integer.parseInt(getText("havhøyde"));
		}
	}
}
