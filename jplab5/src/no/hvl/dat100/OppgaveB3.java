package no.hvl.dat100;

import easygraphics.EasyGraphics;

import java.util.Scanner;

public class OppgaveB3 extends EasyGraphics {
	/*
	* lag et program som tegner en murvegg
	* annenhver linje starter med en halv murstein
	* mursteinene skal stackes med 50% overlapp
	*/
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		int lengde = 0;
		int rader = 0;
		System.out.println("Dette programmet tegner en murvegg");
		do {
			System.out.print("Skriv inn antall mursteiner per rad:  ");
			String x = scanner.nextLine();
			if (x.length() > 0){
				lengde = Integer.parseInt(x);
			}
		} while (lengde < 2);
		System.out.println("------------");
		do {
			System.out.print("Skriv inn antall rader:  ");
			String x = scanner.nextLine();
			if (x.length() > 0){
				rader = Integer.parseInt(x);
			}
		} while (rader < 1);
		makeWindow("Murvegg", 800, 600);
		drawWall(lengde, rader);

	}

	private void drawWall(int lengde, int rader) {
		for (int i = 0; i < rader; i++) {
			drawRow(lengde, 25+(i*50), i);
		}
	}
	private void drawBrick(int minX, int minY, int width, int height) {
		setColor(0, 0, 0);
		fillRectangle(minX, minY, width, height);
		setColor(150, 50, 50);
		drawRectangle(minX, minY, width, height);
	}
	private void drawHalfBrick(int minX, int minY, int width, int height) {
		setColor(0, 0, 0);
		fillRectangle(minX, minY, width/2, height);
		setColor(150, 50, 50);
		drawRectangle(minX, minY, width/2, height);
	}
	private void drawRow(int lengde, int y, int jall){
		int x = 25;
		for (int i = 0; i < lengde; i++) {
			if (jall % 2 == 1 && i == 0){
				drawHalfBrick(x, y, 100, 50);
				x += 50;
			} else if (jall % 2 == 1 && i == lengde-1){
				drawBrick(x, y, 100, 50);
				x += 100;
				drawHalfBrick(x, y, 100, 50);
			} else {
				drawBrick(x, y, 100, 50);
				x += 100;
			}
		}
	}
}
