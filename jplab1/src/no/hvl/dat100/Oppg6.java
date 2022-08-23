package no.hvl.dat100;

import easygraphics.EasyGraphics;
import static javax.swing.JOptionPane.*;

@SuppressWarnings("serial")
public class Oppg6 extends EasyGraphics {

	  public static void main(String[] args) {
		
		  launch(args);
	  }

	  public void run() {
		  String input = showInputDialog("Skriv in prosent skalering av flagg", 100);
		  int scale = Integer.parseInt(input);
		  
		  int xSize = 250 * (scale / 100);
		  int ySize = 200 * (scale / 100);
		  int xPos = 50;
		  int yPos = 50;
		  makeWindow("Det tyske flagget");

		  setColor(0, 0, 0);
		  fillRectangle(xPos, yPos, xSize, ySize/3);
		  setColor(255, 0, 0);
		  fillRectangle(xPos, yPos+(ySize/3), xSize, ySize/3);
		  setColor(255, 255, 0);
		  fillRectangle(xPos, yPos+(ySize/3)+(ySize/3), xSize, ySize/3);
		  setColor(0, 0, 0);
		  drawRectangle(xPos, yPos, xSize, ySize);
	  }

	}