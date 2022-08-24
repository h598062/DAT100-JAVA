package easygraphics;
/*  Filen inneholder alle klassene i EasyGraphics:
 *
 *    EasyGraphics.java
 *    EGArc.java
 *    EGBuffer.java
 *    EGCanvas.java
 *    EGCircle.java
 *    EGCommon.java
 *    EGEllipse.java
 *    EGErrorStep.java
 *    EGFigure.java
 *    EGFinishStep.java
 *    EGGlobalStep.java
 *    EGGui.java
 *    EGInput.java
 *    EGLatch.java
 *    EGLine.java
 *    EGMakeStep.java
 *    EGMoveStep.java
 *    EGPauseStep.java
 *    EGPrintStep.java
 *    EGRectangle.java
 *    EGResizeStep.java
 *    EGRunner.java
 *    EGShowStep.java
 *    EGStep.java
 *    EGText.java
 *    EGWindowStep.java
 */





import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.*;










/* ============================================================
 * EasyGraphics.java
 * ============================================================
 */







/** <p>Klassen EasyGraphics tilbyr metoder for å tegne og flytte ("animere")
  * linjer, rektangel, sirkler, ellipser, sirkelbuer og tekst.</p>
  *
  * <p>Klassen er et lite overbygg på Swing. Ved å bruke EasyGraphics kan 
  * man bli kjent med grafikk og animasjon på en enkel måte.</p>
  *
  * <p>Her er et eksempelprogram som gjør følgende:</p>
  *
  * <ul>
  * <li>Setter opp grafikkvinduet med en tegneflate med tittel "GrafikkDemo" 
  * og bredde=høyde=500 (makeWindow).</li>
  * <li>Setter halv fart på animasjonen (setSpeed).</li>
  * <li>Tegner et blått rektangel med bredde=40 og høyde=80 (drawRectangle). 
  * Det øvre, venstre hjørnet blir plassert i (x=200, y=300).</li>
  * <li>Fyller en sirkel med gul farge (fillCircle). Sirkelen har radius=10 og
  * midtpunkt (x=100, y=100).</li>
  * <li>Flytter (det øverste, venstre hjørnet i) rektangelet til (x=300, y=400) 
  * med metode moveRectangle.</li>
  * <li>Flytter (midtpunktet i) sirkelen til (x=200, y=200) med metoden
  * moveCircle.</li>
  * </ul>
  *
  * <p>&nbsp;</p>
  *
  * <pre>
  * public class GrafikkDemo extends EasyGraphics {
  *
  *   public static void main(String[] args) {
  *     launch(args);
  *   }
  *
  *   public void run() {
  *     makeWindow("GrafikkDemo", 500, 500);
  *     setSpeed(5);
  *     setColor(0, 0, 255);
  *     int rid = drawRectangle(200, 300, 40, 80);
  *     setColor(255, 255, 0);
  *     int sid = fillCircle(100, 100, 10);
  *     moveRectangle(rid, 300, 400);
  *     moveCircle(sid, 200, 200);
  *   }
  * }
  *  </pre>
  * 
  * <p>For to-dimensjonale geometriobjekt kan man velge å tegne omriss 
  * (drawRectangle) eller å fylle med farge (fillRectangle). Aktiv farge 
  * settes med en egen metode setColor. Fonten til tekststrenger kan settes 
  * med metoden setFont.</p>
  *
  * <p>Metoder som tegner objektene (f.eks. drawCircle og fillCircle) returnerer 
  * en unik node-id, som senere kan brukes for å flytte eller reskalere objektet
  * (tekstobjekt kan ikke reskaleres). Alle objekt kan skjules/vises (setVisible).</p>
  *
  * <p>Geometriobjekt posisjoneres, flyttes og reskaleres i forhold til et bestemt 
  * punkt på objektet:</p>
  *
  * <ul>
  * <li>Linjer: Startpunktet</li>
  * <li>Rektangel: Øverste, venstre hjørne</li>
  * <li>Sirkler: Midpunktet</li>
  * <li>Ellipser: Midtpunktet</li>
  * <li>Sirkelbuer: Midtpunktet (i sirkelen)</li>
  * <li>Tekststrenger: Nedre (fotlinjen), venstre hjørne</li>
  * </ul>
  *
  * <p>Farten på en animasjon blir bestemt ved metoden setSpeed, der  
  * parameter 1 = langsom, ..., 10 = hurtig. Det er også mulig å legge inn
  * pauser i en animasjon (metode pause).</p>
  *
  * <p>Nettsidene til
  * <a href="http://www.nettressurser.no/programmering" target="_top">
  * Forstå programmering</a> forklarer hvordan man kan kompilere og
  * kjøre EasyGraphics-program.</p>
  *
  * @version 1.1
  *
  * <p>Sist endret: 19.03.2013</p>
  *
  */
public abstract class EasyGraphics extends JFrame {

  private static final Integer ANY_TYPE       = 0;
  private static final Integer ARC_TYPE       = 1;
  private static final Integer CIRCLE_TYPE    = 2;
  private static final Integer ELLIPSE_TYPE   = 3;
  private static final Integer LINE_TYPE      = 4;
  private static final Integer RECTANGLE_TYPE = 5;
  private static final Integer STRING_TYPE    = 6;

  
  private Color  background = EGCommon.STD_BACKGROUND;
  private Color  color      = EGCommon.STD_COLOR;
  private Font   font       = new Font(EGCommon.STD_FONT, Font.PLAIN, EGCommon.STD_FONT_SIZE);
  private int    speed      = EGCommon.STD_SPEED;
  
  // Inndata fra brukeren
  private String response;        
  
  // For å sjekke korrekt figurtype ved move/resize
  private ArrayList<Integer> figTypes = new ArrayList<Integer>();
  
  // Neste ledige figur-id
  private int figCount   = 0;  
  
  // For å sjekke at brukerprogrammet kaller på launch først
  private static boolean launchedCalled = false;
  
  private static EGGui   gui;
  

  
  // *************************************************
  // Oppstart-metodene
  // *************************************************
  
  
  /** 
   *  Setter opp vinduet og kaller run. Kalles fra main.
   *
   *  @param args kommandolinjeparametre (brukes ikke foreløpig)
   *
   */
  public static void launch(String[] args) {
    launchedCalled = true;
    EGCommon.startup();
    
    // Opprett GUI på event-tråden
    EasyGraphics app = getApp();
    gui = new EGGui(app);
    SwingUtilities.invokeLater(gui);
    EGCommon.latch.awaitStartup();
    
    // Kjør brukerprogrammet på en egen tråd
    EGRunner runner = new EGRunner(app);
    Thread thread = new Thread(runner);
    thread.start();
  }

 
 
  /** 
   *  Utfører tegne- og animasjons-operasjoner.
   *  Denne metoden må deklareres (overstyres).
   *  I grafikkprogram spiller denne rollen som "sjefsmetode" (a la main).
   *
   */
  public abstract void run();
  
  
  
  
  // *************************************************
  // Tegnemetoder
  // *************************************************
  
  
  /** 
   *  Tegner en sirkelbue med aktiv farge. Returnerer en unik id som kan
   *  brukes for å flytte sirkelbuen.
   *
   *  @param centerX x-koordinat til midtpunktet
   *  @param centerY y-koordinat til midtpunktet
   *  @param radiusX "horisontal radius", må være større enn 0
   *  @param radiusY "vertikal radius", må være større enn 0
   *  @param startAngle startvinkel
   *  @param angleLength vinkellengde
   *  @return unik node-id
   *
   */
  public int drawArc(int centerX, int centerY, 
                     int radiusX, int radiusY, 
                     int startAngle, int angleLength) {
    return makeArc(centerX, centerY, radiusX, radiusY, startAngle, angleLength, false);
  }
  
  
  /** 
   *  Tegner en sirkel med aktiv farge. Returnerer en unik id som kan 
   *  brukes for å flytte sirkelen.
   *
   *  @param centerX x-koordinat til midtpunktet
   *  @param centerY y-koordinat til midtpunktet
   *  @param radius radius, må være større enn 0
   *  @return unik node-id
   *
   */
  public int drawCircle(int centerX, int centerY, int radius) {
    return makeCircle(centerX, centerY, radius, false);
  }
  
  
  /** 
   *  Tegner en ellipse med aktiv farge. Returnerer en unik id som kan 
   *  brukes for å flytte ellipsen.
   *
   *  @param centerX x-koordinat til midtpunktet
   *  @param centerY y-koordinat til midtpunktet
   *  @param radiusX "horisontal radius", må være større enn 0
   *  @param radiusY "vertikal radius", må være større enn 0
   *  @return unik node-id
   *
   */
  public int drawEllipse(int centerX, int centerY, int radiusX, int radiusY) {
    return  makeEllipse(centerX, centerY, radiusX, radiusY, false);
  }
  
  
  /** 
   *  Tegner en rett linje med aktiv farge. Returnerer en unik id som kan 
   *  brukes for å flytte linjen.
   *
   *  @param startX x-koordinat for startpunkt
   *  @param startY y-koordinat for startpunkt
   *  @param endX x-koordinat for endepunkt
   *  @param endY y-koordinat for endepunkt
   *  @return unik node-id
   *
   */
  public int drawLine(int startX, int startY, int endX, int endY) {
    return makeLine(startX, startY, endX, endY, false);
  }
  
  
  /** 
   *  Tegner et rektangel med aktiv farge. Returnerer en unik id som kan 
   *  brukes for å flytte rektangelet.
   *
   *  @param minX x-koordinat for venstre side
   *  @param minY y-koordinat for øvre side
   *  @param width bredde, må være større enn 0
   *  @param height høyde, må være større enn 0
   *  @return unik node-id
   *
   */
  public int drawRectangle(int minX, int minY, int width, int height) {
    return makeRectangle(minX, minY, width, height, false);
  }
  
  
  /** 
   *  Tegner tekst med aktiv font og farge. Returnerer en unik id som kan 
   *  brukes for å flytte teksten.
   *
   *  @param x x-koordinat til venstre kant
   *  @param y y-koordinat til nedre kant
   *  @param str tekststrengen
   *  @return unik node-id
   *
   */
  public int drawString(String str, int x, int y) {
    return makeString(x, y, str, false);
  }
  
  
  /** 
   *  Fyller en sirkelbue ("kakestykke") med aktiv farge. Returnerer en 
   *  unik id som kan brukes for å flytte sirkelbuen.
   *
   *  @param centerX x-koordinat til midtpunktet
   *  @param centerY y-koordinat til midtpunktet
   *  @param radiusX "horisontal radius", må være større enn 0
   *  @param radiusY "vertikal radius", må være større enn 0
   *  @param startAngle startvinkel
   *  @param angleLength vinkellengde
   *  @return unik node-id
   *
   */
  public int fillArc(int centerX, int centerY, int radiusX, 
                     int radiusY, int startAngle, int angleLength) {
    return makeArc(centerX, centerY, radiusX, radiusY, startAngle, angleLength, true);
  }
  
  
  /** 
   *  Fyller en sirkel med aktiv farge. Returnerer en unik id som kan 
   *  brukes for å flytte sirkelen.
   *
   *  @param centerX x-koordinat til midtpunktet
   *  @param centerY y-koordinat til midtpunktet
   *  @param radius radius, må være større enn 0
   *  @return unik node-id
   *
   */
  public int fillCircle(int centerX, int centerY, int radius) {
    return makeCircle(centerX, centerY, radius, true);
  }
  
  
  /** 
   *  Tegner en ellipse med aktiv farge. Returnerer en unik id som kan 
   *  brukes for å flytte ellipsen.
   *
   *  @param centerX x-koordinat til midtpunktet
   *  @param centerY y-koordinat til midtpunktet
   *  @param radiusX "horisontal radius", må være større enn 0
   *  @param radiusY "vertikal radius", må være større enn 0
   *  @return unik node-id
   *
   */
  public int fillEllipse(int centerX, int centerY, 
                         int radiusX, int radiusY) {
    return makeEllipse(centerX, centerY, radiusX, radiusY, true);
  }
  
  
  /** 
   *  Fyller et rektangel med aktiv farge. Returnerer en unik id som kan 
   *  brukes for å flytte rektangelet.
   *
   *  @param minX x-koordinat for venstre side
   *  @param minY y-koordinat for øvre side
   *  @param width bredde, må være større enn 0
   *  @param height høyde, må være større enn 0
   *  @return unik node-id
   *
   */
  public int fillRectangle(int minX, int minY, int width, int height) {
    return makeRectangle(minX, minY, width, height, true);
  }
  
  
  /** 
   *  Leser inn en tekststreng fra brukeren, via et tekstfelt
   *  øverst i vinduet.
   *
   *  @param msg meldingen til bruker
   *
   */ 
  public String getText(String msg) {
    checkLaunched();
    
    // La eventuelle animasjoner avslutte  
    EGFinishStep finish = new EGFinishStep();
    addStep(finish);
    addQueue();
    EGCommon.latch.awaitAnimation();
    
    // Vent på inndata fra brukeren
    EGInput reader = new EGInput(gui, msg);
    SwingUtilities.invokeLater(reader);
    EGCommon.latch.awaitUser();
    
    String response = gui.getResponse();
    return response;
  }
  
  
  /** 
   *  Konfigurerer vinduet med standard størrelse bredde=høyde=800 piksler.
   *
   *  @param title tittelen til vinduet
   *
   */
  public void makeWindow(String title) {
    makeWindow(title, EGCommon.STD_WIDTH, EGCommon.STD_HEIGHT);
  }
 
  
  /** 
   *  Konfigurerer vinduet.
   *
   *  @param title tittelen til vinduet
   *  @param width bredden til vinduet, må være større enn 0
   *  @param height høyden til vinduet, må være større enn 0
   *
   */
  public void makeWindow(String title, int width, int height) {
    checkLaunched();
    checkPositive(width, "width");
    checkPositive(height, "height");
    EGWindowStep step = new EGWindowStep(gui, title, width, height);
    addStep(step);
  }
  
  
  /** 
   *  Flytter ("animerer") figuren med gitt node-id til et gitt punkt
   *  langs en rett linje. Se tilsvarende moveXXX-metoder tilpasset
   *  de ulike figurtypene for hvor på figuren punktet (x,y) refererer. 
   *
   *  @param id node-id
   *  @param x x-koordinatet til ny posisjon
   *  @param y y-koordinatet til ny posisjon
   *
   */
  public void move(int id, int x, int y) {
    moveFigure(id, x, y, ANY_TYPE);
  }
  
  
  /** 
   *  Flytter ("animerer") sirkelbuen med gitt node-id til et gitt punkt
   *  langs en rett linje. Den nye posisjonen vil være midtpunktet 
   *  i "tilhørende" sirkel.
   *
   *  @param id node-id
   *  @param x x-koordinatet til ny posisjon
   *  @param y y-koordinatet til ny posisjon
   *
   */
  public void moveArc(int id, int x, int y) {
    moveFigure(id, x, y, ARC_TYPE);
  }
  
  
  /** 
   *  Flytter ("animerer") sirkelen med gitt node-id til et gitt punkt 
   *  langs en rett linje. Den nye posisjonen vil være midtpunktet i sirkelen.
   *
   *  @param id node-id
   *  @param x x-koordinatet til ny posisjon
   *  @param y y-koordinatet til ny posisjon
   *
   */
  public void moveCircle(int id, int x, int y) {
    moveFigure(id, x, y, CIRCLE_TYPE);
  }
  
  
  /** 
   *  Flytter ("animerer") ellipsen med gitt node-id til et gitt punkt langs
   *  en rett linje. Den nye posisjonen vil være midtpunktet i ellipsen.
   *
   *  @param id node-id
   *  @param x x-koordinatet til ny posisjon
   *  @param y y-koordinatet til ny posisjon
   *
   */
  public void moveEllipse(int id, int x, int y) {
    moveFigure(id, x, y, ELLIPSE_TYPE);
  }
  
  
  /** 
   *  Flytter ("animerer") linjen med gitt node-id til et gitt punkt langs 
   *  en rett linje. Den nye posisjonen vil være startpunktet til linjen.
   *
   *  @param id node-id
   *  @param x x-koordinatet til ny startposisjon
   *  @param y y-koordinatet til ny startposisjon
   *
   */
  public void moveLine(int id, int x, int y) {
    moveFigure(id, x, y, LINE_TYPE);
  }
  
  
  /** 
   *  Flytter ("animerer") rektangelet med gitt node-id til et gitt punkt langs
   *  en rett linje. Den nye posisjonen vil være øverste, venstre hjørne i
   *  rektangelet.
   *
   *  @param id node-id
   *  @param x x-koordinatet til ny posisjon
   *  @param y y-koordinatet til ny posisjon
   *
   */
  public void moveRectangle(int id, int x, int y) {
    moveFigure(id, x, y, RECTANGLE_TYPE);
  }
  
  
  /** 
   *  Flytter ("animerer") teksten med gitt node-id til et gitt punkt langs
   *  en rett linje. Den nye posisjonen vil være nedre, venstre punkt
   *  i teksten.
   *
   *  @param id node-id
   *  @param x x-koordinatet til ny posisjon
   *  @param y y-koordinatet til ny posisjon
   *
   */
  public void moveString(int id, int x, int y) {
    moveFigure(id, x, y, STRING_TYPE);
  }
  
  
  /** 
   *  Legger inn en pause i animasjonen.
   *
   *  @param ms varighet i antall millisekunder, må være større enn 0
   *
   */
  public void pause(int ms) {
    checkLaunched();
    checkPositive(ms, "ms");
    EGPauseStep step = new EGPauseStep(ms);
    addStep(step);
  }
  
 
  /** 
   *  Skriver ut en tekst til konsollet på riktig
   *  tidspunkt (i forhold til pågående animasjoner).
   *
   *  @param msg meldingen
   *
   */
  public void print(String msg) {
    checkLaunched();
    EGPrintStep step = new EGPrintStep(msg);
    addStep(step);
  }
  
  
  /** 
   *  Skriver ut en tekst med linjeskift til konsollet
   *  på riktig tidspunkt (i forhold til pågående animasjoner).
   *
   *  @param msg meldingen
   *
   */
  public void println(String msg) {
    checkLaunched();
    EGPrintStep step = new EGPrintStep(msg + "\n");
    addStep(step);
  }
  
  
  /** 
   *  Endrer størrelse ("animerer") på sirkelbuen med gitt node-id.
   *
   *  @param id node-id
   *  @param rX ny "x-radius", må være større enn 0
   *  @param rY ny "y-radius", må være større enn 0
   *
   */
  public void resizeArc(int id, int rX, int rY) {
    checkLaunched();
    checkPositive(rX, "rX");
    checkPositive(rY, "rY");
    resize(id, 2*rX, 2*rY, ARC_TYPE);
  }
  
  
  /** 
   *  Endrer størrelse ("animerer") på sirkelen med gitt node-id.
   *
   *  @param id node-id
   *  @param r ny radius, må være større enn 0
   *
   */
  public void resizeCircle(int id, int r) {
    checkLaunched();
    checkPositive(r, "r");
    resize(id, 2*r, 2*r, CIRCLE_TYPE);
  }
  
  
  /** 
   *  Endrer størrelse ("animerer") på ellipsen med gitt node-id.
   *
   *  @param id node-id
   *  @param rX ny "x-radius", må være større enn 0
   *  @param rY ny "y-radius", må være større enn 0
   *
   */
  public void resizeEllipse(int id, int rX, int rY) {
    checkLaunched();
    checkPositive(rX, "rX");
    checkPositive(rY, "rY");
    resize(id, 2*rX, 2*rY, ELLIPSE_TYPE);
  }
  
  
  /** 
   *  Endrer størrelse ("animerer") på linjen med gitt node-id.
   *  Det gjøres ved å forskyve endepunktet, startpunktet ligger fast.
   *
   *  @param id node-id
   *  @param w ny bredde, må være større enn 0
   *  @param h ny høyde, må være større enn 0
   *
   */
  public void resizeLine(int id, int w, int h) {
    checkLaunched();
    checkPositive(w, "w");
    checkPositive(h, "h");
    resize(id, w, h, LINE_TYPE);
  }
  
  
  /** 
   *  Endrer størrelse ("animerer") på rektangelet med gitt node-id.
   *
   *  @param id node-id
   *  @param w ny bredde, må være større enn 0
   *  @param h ny høyde, må være større enn 0
   *
   */
  public void resizeRectangle(int id, int w, int h) {
    checkLaunched();
    checkPositive(w, "w");
    checkPositive(h, "h");
    resize(id, w, h, RECTANGLE_TYPE);
  }

  
  /** 
   *  Setter bakgrunnsfarge med et RGB-trippel.
   *
   *  @param r rød verdi mellom 0 og 255
   *  @param g grønn verdi mellom 0 og 255
   *  @param b blå verdi mellom 0 og 255
   *
   */
  public void setBackground(int r, int g, int b) {
    checkLaunched();
    checkInterval(r, 0, 255, "r");
    checkInterval(g, 0, 255, "g");
    checkInterval(b, 0, 255, "b");
    EGGlobalStep step = new EGGlobalStep(r, g, b);
    addStep(step);
  } 
  
  
  /** 
   *  Setter aktiv farge med et RGB-trippel. Geometriobjekt som blir
   *  tegnet vil heretter få denne fargen (helt fram til neste kall
   *  på setColor).
   *
   *  @param r rød verdi mellom 0 og 255
   *  @param g grønn verdi mellom 0 og 255
   *  @param b blå verdi mellom 0 og 255
   *
   */
  public void setColor(int r, int g, int b) {
    checkLaunched();
    boolean ok = checkInterval(r, 0, 255, "r")
              && checkInterval(g, 0, 255, "g")
              && checkInterval(b, 0, 255, "b");
    if (ok)
      color = new Color(r, g, b);
  }
  
  
  /** 
   *  Setter aktiv font. Tekststrenger som blir tegnet vil heretter få
   *  denne fargen (helt fram til neste kall på setFont).
   *
   *  @param name navn på font-familien
   *  @param size fontstørrelsen, må være større enn 0
   *
   */
  public void setFont(String name, int size) {
    checkLaunched();
    boolean ok = checkPositive(size, "size");
    if (ok)
      font = new Font(name, Font.PLAIN, size);
  }
  
  
  /** 
   *  <p>Setter farten på animasjonen.</p>
   *
   *  <p>Metoden tilbyr en meget enkel måte å styre farten til animasjoner
   *   på. Den setter kun inn en kort pause mellom hvert "steg".</p>
   *  <p>Hastigheten til en animasjon vil bla. variere med hvilken maskin 
   *  man bruker, og om man f.eks. gjør (få og) lange eller (mange og) korte
   *  forflytninger/reskaleringer. I det siste tilfellet bør endringen være
   *  4 eller kortere i både x- og y-retning.</p>
   *  <p>For å oppnå ønsket fart må man eksperimentere litt i hvert enkelt
   *  tilfelle.</p>
   *  
   *  @param speed farten fra 1 (langsomt) til 10 (hurtig)
   *
   */
  public void setSpeed(int speed) {
    checkLaunched();
    checkInterval(speed, 1, 10, "speed");
    EGGlobalStep step = new EGGlobalStep(speed);
    addStep(step);
    
    // Flere korte forflytninger på ulike objekt blir utført i parallell.
    // Konstanten EGFigure.DELTA (privat i denne klassen) bestemmer øvre
    // grense for lengden på slike operasjoner.
  }
  
  
  /** 
   *  Viser/skjuler et geometriobjekt.
   *
   *  @param id node-id
   *  @param visible skal objektet vises?
   *
   */
  public void setVisible(int id, boolean visible) {
    checkLaunched();
    EGShowStep step = new EGShowStep(id, visible);
    addStep(step);
  }
  
  
  
  // *************************************************
  // Hjelpemetoder.
  // *************************************************

  
  /*  Legger til et nytt animasjonssteg bakerst
   *  i inn-køen.
   */
  private void addStep(EGStep step) {
    EGCommon.stepBuffer.addStep(step);
  }
 

  /*  Legger inn-køen inn til i bufferet av køer
   *  for behandling på event-tråden.
   */ 
  private void addQueue() {
    EGCommon.stepBuffer.addQueue();
  }
  
 

  /*  Sjekker at det for en gitt id er opprettet en
   *  figur av korrekt type.
   */   
  private void checkFigure(int id, Integer figType) {
    if (id < 0 || id >= figTypes.size()) {
      String msg = "Unknown figure id : " + id;
      EGErrorStep step = new EGErrorStep(msg);
      addStep(step);
    }  
    else {
      Integer ft = figTypes.get(id);
       
      if (ft != figType && figType != ANY_TYPE) {
        String msg = "Figure " + id + " is not a " + figTypeToString(figType);
        EGErrorStep step = new EGErrorStep(msg);
        addStep(step);
      }
    }
  } 

  
  /*  Sjekker at en parameter til en av EasyGraphics-
   *  metodene er innenfor et gitt intervall.
   *  Se også checkPositive over.
   */   
  private boolean checkInterval(int param, int from, int to, String name) {
    if (param < from || to < param) {
      String msg = "Parameter " + name + " must be between " + from + " and " + to + "!";
      EGErrorStep step = new EGErrorStep(msg);
      addStep(step);
      return false;
    }  
    
    return true;
  }  
  
  
  /*  Sjekker at brukerprogrammet er startet opp med
   *  kall på launch (fra main).
   */   
  private void checkLaunched() {
    if (!launchedCalled)
      EGCommon.fatalError("EasyGraphics method launch must be called from main!");
  }  
  
  
  /*  Sjekker at en parameter til en av EasyGraphics-
   *  metodene er positiv. For å levere eventuell
   *  feilmelding på riktig tidspunkt (og deretter
   *  avbryte programkjøring), gjøres dette i et 
   *  animasjonssteg.
   */   
  private boolean checkPositive(int param, String name) {
    if (param < 0) {
      String msg = "Parameter " + name + " must be positive!";
      EGErrorStep step = new EGErrorStep(msg);
      addStep(step);
      return false;
    }      
    
    return true;
  }
  
  
  /*  Returnerer tekstlig representasjon av en figurtype.
   */
  private String figTypeToString(Integer figType) {
    String str = "unknown figure";

    if (figType == ARC_TYPE)
      str = "arc";
    else if (figType == CIRCLE_TYPE)
      str = "circle";
    else if (figType == ELLIPSE_TYPE)
      str = "ellipse";
    else if (figType == LINE_TYPE)
      str = "line";
    else if (figType == RECTANGLE_TYPE)
      str = "rectangle";
    else if (figType == STRING_TYPE)
      str = "string";
      
    return str;
  }

  
  /*  Oppretter en instans av applikasjonen. Navnet på applikasjonen 
   *  (programmet som brukeren skriver) avleses fra kallstakken til
   *  den aktive tråden. Det blir sjekket at den kjørbare klassen
   *  (den som deklarerer main) er en subklasse av EasyGraphics.
   *  Ved hjelp av ClassLoader-klassen blir det opprettet et objekt
   *  av den kjørbare klassen (fra navnet på klassen).
   */
  private static EasyGraphics getApp() {
    EasyGraphics obj = null;
    String className = null;
    
    try {
      StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
      int pos = 0;
      boolean found = false;
      while (!found && (pos < stackTraces.length)) {
        StackTraceElement element = stackTraces[pos];
        className = element.getClassName();
        String methodName = element.getMethodName();

        if (methodName.equals("main")) {
          EGCommon.logMessage("Main class: " + className);
          
          ClassLoader loader = ClassLoader.getSystemClassLoader();
          Class theClass  = Class.forName(className, false, loader);
          Class sc = theClass.getSuperclass();
          String scName = sc.getSimpleName();
          
          if (scName.equals("EasyGraphics")) {
            obj = (EasyGraphics) theClass.newInstance();
            found = true;
          }
          else {
            EGCommon.fatalError("Main class is not a subclass (extends) of EasyGraphics");
          }
        }
        else
          pos++;
      }
    }
    catch (ClassNotFoundException e) {
      EGCommon.fatalError("Unable to load class: " + e.toString());
    }
    catch (InstantiationException e) {
      EGCommon.fatalError("Unable to instantiate class: " + e.toString());
    }
    catch (IllegalAccessException e) {
      EGCommon.fatalError("Illegal access: " + e.toString());
    }
    
    if (obj == null)
      EGCommon.fatalError("Main class not found.");
    
    return obj;
  }

  
  /*  Oppretter og viser en sirkelbue.
   */
  private int makeArc(int centerX, int centerY, 
                      int radiusX, int radiusY, 
                      int startAngle, int angleLength,
                      boolean fill) {
    checkLaunched();
    checkPositive(radiusX, "radiusX");
    checkPositive(radiusY, "radiusY");
    EGArc arc = new EGArc(centerX, centerY, radiusX, radiusY, startAngle, angleLength);
    arc.setColor(color);
    arc.setFill(fill);
    EGMakeStep step = new EGMakeStep(arc);    
    addStep(step);
    return nextFigId(ARC_TYPE);
  }
  
  
  /*  Oppretter og viser en sirkel.
   */
  private int makeCircle(int centerX, int centerY, int radius, 
                         boolean fill) {
    checkLaunched();
    checkPositive(radius, "radius");
    EGCircle circle = new EGCircle(centerX, centerY, radius);
    circle.setColor(color);
    circle.setFill(fill);
    EGMakeStep step = new EGMakeStep(circle);    
    addStep(step);
    return nextFigId(CIRCLE_TYPE);
  }
  
  
  /*  Oppretter og viser en ellipse.
   */
  private int makeEllipse(int centerX, int centerY, 
                          int radiusX, int radiusY, 
                          boolean fill) {
    checkLaunched();
    checkPositive(radiusX, "radiusX");
    checkPositive(radiusY, "radiusY");
    EGEllipse ellipse = new EGEllipse(centerX, centerY, radiusX, radiusY);
    ellipse.setColor(color);
    ellipse.setFill(fill);
    EGMakeStep step = new EGMakeStep(ellipse);    
    addStep(step);
    return nextFigId(ELLIPSE_TYPE);
  }
  
  
  /*  Oppretter og viser en linje.
   */
  private int makeLine(int startX, int startY, 
                       int endX, int endY, 
                       boolean fill) {
    checkLaunched();
    EGLine line = new EGLine(startX, startY, endX, endY);
    line.setColor(color);
    line.setFill(fill);
    EGMakeStep step = new EGMakeStep(line);    
    addStep(step);
    return nextFigId(LINE_TYPE);
  }
  
  
  /*  Oppretter og viser et rektangel.
   */
  private int makeRectangle(int minX, int minY, 
                            int width, int height, 
                            boolean fill) {
    checkLaunched();
    checkPositive(width, "width");
    checkPositive(height, "height");
    EGRectangle rectangle = new EGRectangle(minX, minY, width, height);
    rectangle.setColor(color);
    rectangle.setFill(fill);
    EGMakeStep step = new EGMakeStep(rectangle);    
    addStep(step);
    return nextFigId(RECTANGLE_TYPE);
  }
  
  
  /*  Oppretter og viser en tekst.
   */
  private int makeString(int x, int y, String s, boolean fill) {
    checkLaunched();
    EGText str = new EGText(x, y, s, font);
    str.setColor(color);
    str.setFill(fill);
    EGMakeStep step = new EGMakeStep(str);    
    addStep(step);
    return nextFigId(STRING_TYPE);
  }
  
  
  /*  Flytter et geometriobjekt.
   */
  private void moveFigure(int id, int x, int y, Integer figType) {
    checkLaunched();
    checkFigure(id, figType);
    EGMoveStep step = new EGMoveStep(id, x, y);
    addStep(step);
  }
  
  
 
  /*  Returnerer neste figur-id.
   */ 
  private int nextFigId(Integer figType) {
    figTypes.add(figType);
    figCount++;
    return figCount-1;
  }
  
  
  /*  Endrer størrelse på et geometriobjekt.
   */
  private void resize(int id, int w, int h, Integer figType) {
    checkLaunched();
    checkFigure(id, figType);
    EGResizeStep step = new EGResizeStep(id, w, h);    
    addStep(step);
  }

}





/* ============================================================
 * EGArc.java
 * ============================================================
 */







/*  Et objekt av denne klassen representerer en sirkelbue
 *  (uten fyll), eller et "kakestykke" (med fyll).
 */
class EGArc extends EGFigure {

  private int radiusX;
  private int radiusY;
  private int startAngle;
  private int angleLength;
  
  
  public EGArc(int x, int y,
               int radiusX, int radiusY, 
               int startAngle, int angleLength) {
    super(x, y);
    this.radiusX = radiusX;
    this.radiusY = radiusY;
    this.startAngle = startAngle;
    this.angleLength = angleLength;
  }
  

  public void draw(Graphics g) {
    if (visible) {
      super.draw(g);
      if (fill)
        g.fillArc(x-radiusX,  y-radiusY,
                  2*radiusX,  2*radiusY, 
                  startAngle, angleLength);
      else
        g.drawArc(x-radiusX,  y-radiusY, 
                  2*radiusX,  2*radiusY, 
                  startAngle, angleLength);
    }
  }

  
  public void setRadiusX(int radiusX) {
    this.radiusX = radiusX;
  }
  
  
  public int getRadiusX() {
    return radiusX;
  }
  
  
  public void setRadiusY(int radiusY) {
    this.radiusY = radiusY;
  }
  
  
  public int getRadiusY() {
    return radiusY;
  }
  
  
  public int minX() {
    return x-radiusX;
  }
  
  
  public int minY() {
    return y-radiusY;
  }
  
  
  public int maxX() {
    return x+radiusX;
  }
  
  
  public int maxY() {
    return y+radiusY;
  }
  
  
  public void setWidth(int width) {
    this.radiusX = width/2;
  }
  
  
  public int getWidth() {
    return radiusX*2;
  }
  
  
  public void setHeight(int height) {
    this.radiusY = height/2;
  }
  
  
  public int getHeight() {
    return radiusY*2;
  }
  
  
  public EGFigure copy() {
    EGArc copy = new EGArc(x, y, radiusX, radiusY, startAngle, angleLength);
    copy.copyColor(color);
    copy.setFill(fill);
    copy.setVisible(visible);
    return copy;
  }
  

  public String toString() {
    return "[EGArc x=" + x + ", y=" + y
      + ", radiusX=" + radiusX + ", radiusY=" + radiusY
      + ", startAngle= " + startAngle + ", angleLength" + angleLength + "]";
  }
  
}





/* ============================================================
 * EGBuffer.java
 * ============================================================
 */






/*  Klassen håndterer operasjonene (steg) som skal bli
 *  utført, typisk tegneoperasjoner. Datastrukturen er
 *  logisk sett en kø, der hoved-tråden setter steg-objekt
 *  inn bakerst og event-tråden henter ut først i køen.
 *  For å redusere synkronisering er datastrukturen egentlig
 *  en kø av køer, med synkroniserte metoder for å legge inn
 *  og ta ut en enkelt kø.
 *
 *  Hvis det ikke er plass til å sette inn en ny kø, vil
 *  hoved-tråden vente. Hvis det ikke er noen kø å hente
 *  vil event-tråden bare ignorere det (se EGCanvas).
 */
class EGBuffer {

  private ArrayDeque<ArrayDeque<EGStep>> buffer; // Kø av stegkøer
  private ArrayDeque<EGStep> inQueue;            // Kø av steg fra hoved-tråden
  private ArrayDeque<EGStep> outQueue = null;    // Kø av steg til event-tråden
  
  private boolean windowVisible = false;         // Er makeWindow utført?
  
  
  public EGBuffer() {
    buffer = new ArrayDeque<ArrayDeque<EGStep>>(EGCommon.MAX_QUEUES);
    inQueue = new ArrayDeque<EGStep>(EGCommon.INIT_STEPS);
    outQueue = new ArrayDeque<EGStep>();
    windowVisible = false;
  }
  
  
  /*  Legger til en ny stegkø (brukes fra hovedtråden).
   */
  public synchronized boolean addQueue(ArrayDeque<EGStep> q) {
    if (buffer.size() >= EGCommon.MAX_QUEUES-1)
      return false;
    else {
      buffer.offer(q);
      return true;
    }
  }
  
  
  /*  Henter ut første stegkø (brukes fra event-tråden).
   */
  public synchronized ArrayDeque<EGStep> getQueue() {
    ArrayDeque<EGStep> q = null;
    int size = buffer.size();
    if (size > 0)
      q = buffer.poll();
    return q;  
  } 
  
  
  /*  Legger til et nytt animasjonssteg bakerst i inn-køen.
   */
  public void addStep(EGStep step) {  
    if (!windowVisible) {
      if (step instanceof EGWindowStep)
        windowVisible = true;
      else
        EGCommon.fatalError("Method makeWindow must be invoked before all other EasyGraphics methods!");
    }
    
    if (inQueue.size() >= EGCommon.MAX_STEPS) {
      addQueue();
      inQueue = new ArrayDeque<EGStep>(EGCommon.INIT_STEPS);
    }
    inQueue.offer(step);
  }
  

  /*  Legger inn-køen til i bufferet for behandling på event-tråden.
   */
  public void addQueue() {
    boolean success = addQueue(inQueue); // EGCommon.stepBuffer.addQueue(inQueue);
    while (!success) {
      try {
        Thread.sleep(EGCommon.WAIT_TIME);
      }
      catch (InterruptedException e) {
        EGCommon.logMessage("Interrupt in EGBuffer.addQueue().");
      }
      success = addQueue(inQueue); // EGCommon.stepBuffer.addQueue(inQueue);
    }
  }
  
  
  /*  Henter neste steg fra ut-køen.
   */
  public EGStep getStep() {
    EGStep step = null;
    if (outQueue == null)
      outQueue = EGCommon.stepBuffer.getQueue(); 
    if (outQueue != null)
      step = (EGStep) outQueue.poll();
    if (step == null)
      outQueue = null;
    return step;
  }
  
}





/* ============================================================
 * EGCanvas.java
 * ============================================================
 */







/*  Klassen representerer tegneflaten. Den har en liste med
 *  figur-objekt og får en kø av operasjoner (steg) for utførelse.
 *  Brukerprogrammet, som kjører på en egen tråd, legger nye
 *  steg (f.eks. en move-operasjon) inn på slutten av køen,
 *  mens tegneflaten, som kjører som del av event-løkka, tar
 *  ut steg fra begynnelsen av den samme køen.
 *
 *  Tegne-operasjonene kontrolleres av en timer (javax.swing.Timer).
 *  Den kaller jevnlig opp en hendelsesmetode (actionPerformed) med noen
 *  millisekunders pause. Hver gang blir ett eller flere steg behandlet
 *  (noen steg blir kun delvis behandlet), og oppdatert figurliste
 *  blir tegnet på nytt.
 */
class EGCanvas extends JPanel implements ActionListener {
  
  public static  int minX = 0;
  public static  int minY = 0;
  public static  int maxX = EGCommon.STD_WIDTH;
  public static  int maxY = EGCommon.STD_HEIGHT;
  
  private static int width  = EGCommon.STD_WIDTH;
  private static int height = EGCommon.STD_HEIGHT;;
  
  private static Color background = EGCommon.STD_BACKGROUND;
  private static int   speed      = EGCommon.STD_SPEED;
  
  private static Timer timer    = null;
  private static long  delay    = speedToDelay(speed);
  private static long  previous = System.nanoTime();
  
  private static ArrayList<EGFigure> figList  = new ArrayList<EGFigure>();
  private        EGStep              currStep = null;
  
  

  public EGCanvas() {
    setBackground(background);
    initRect();
    previous = System.nanoTime();
    timer = new Timer(0, this);
    timer.start();
  }
   
  
  /*  Vis alle figurene.
   */
  public void paint(Graphics g) {
    super.paintComponent(g);
    setBackground(background); 
    
    // EGCommon.logMessage(figList.toString());
    
    for (EGFigure f : figList) {
      f.draw(g);
    }
  }
  

  /*  Hent steg fra køen for utførelse. For å effektivisere
   *  animasjoner kan flere steg bli utført i ett kall.
   *  Dette vil f.eks. gjelde flere, korte flyttinger av  
   *  forskjellige figurer. Motsatt, blir lengre flyttinger
   *  og større reskaleringer behandlet over flere kall.
   */
  public void actionPerformed(ActionEvent e) {
    // Eventuell kort pause for å styre farten
    if ( keepWaiting() )
      return;
    
    // Initierer "dirty region" 
    // (den delen av vinduet som skal tegnes på nytt)
    initRect();
    
    // Henter og utfører neste steg på køen
    if (currStep == null)
      currStep = EGCommon.stepBuffer.getStep();
    if (currStep == null) {
      return; 
    }
    boolean finishedCurrent = currStep.execute();
    
    // Flere korte forflytninger/reskaleringer blir utført
    // samlet før alle blir tegnet (se EGFigure).
    while (finishedCurrent) {
      currStep = EGCommon.stepBuffer.getStep();
      if (currStep == null)
        finishedCurrent = false;
      else if (currStep.continueStep())
        finishedCurrent = currStep.execute();
      else
        finishedCurrent = false;
    }
    
    // Tegner kun den delen av vinduet som er endret.
    repaint(minX, minY, maxX-minX, maxY-minY); 
    //repaint();
  }


  
  /*  Legg til en figur i figurlisten.
   *
   *  TODO Følgende "avhengighet" bør fjernes:
   *  Figurene får først en (unik) id i hoved-tråden
   *  (se EasyGraphics), og blir tildelt id igjen
   *  ved innsetting nå, gitt ved posisjonen i figurlisten.
   *  Brukerprogrammet kan referere til slik id'er, og
   *  det er avgjørende at figurene blir opprettet i
   *  samme rekkefølge de to stedene i koden.
   *
   *  Mulig løsning: Utvid figur-objekt med id første gang
   *  de opprettes, og la dette definere posisjonen i listen.
   */
  public static void addFig(EGFigure figure) {
    figList.add(figure);
  }
  
  
  /*  Hent figur med gitt id.
   */
  public static EGFigure getFig(int id) {
    if (id < 0 || id > figList.size())
      EGCommon.fatalError("Unknown figure id: " + id);
    return figList.get(id);
  }
  
  
  /*  Sett bakgrunnsfargen.
   */
  public static void setBackgroundColor(Color newwBackground) {
    background = newwBackground;
  }
  

  /*  Sett størrelsen på tegneflaten.
   */ 
  public void setCanvasSize(int width, int height) {
    this.width = width;
    this.height = height;
    Dimension dim = new Dimension(width, height);
    setPreferredSize(dim);
    setMinimumSize(dim);
    setMaximumSize(dim);
  }
  
  
  /*  Objektvariablene (minX, minY, maxX, maxY) tar vare
   *  på den delen av tegneflaten som er endret siden forrige
   *  tegneoperasjon (paintComponent). Dette brukes for å
   *  effektivisere tegning (kun den delen som er endret blir
   *  tegnet på nytt, se kall på repaint i actionPerformed).
   */ 
  public static void setUpdateRect(int minX1, int minY1, int maxX1, int maxY1) {
    minX = Math.min(minX, minX1)-1;
    minY = Math.min(minY, minY1)-1;
    maxX = Math.max(maxX, maxX1)+1;
    maxY = Math.max(maxY, maxY1)+1;
    if (minX < 0)
      minX = 0;
    if (minY < 0)
      minY = 0;
    if (maxX > width)
      maxX = width;
    if (maxY > height)
      maxY = height;
  }
  
  
  /* Initierer rektangelet som er endret. Se setUpdateRect.
   */
  public static void initRect() {
    minX = width;
    minY = height;
    maxX = 0;
    maxY = 0;
  }
  
  
  /*  Sett farten til påfølgende animasjoner.
   */  
  public static void setSpeed(int newSpeed) {
    speed = newSpeed;
    delay = speedToDelay(newSpeed);
    previous = System.nanoTime();
  }
  

  /*  Brukes for å styre farten til animasjoner.
   */    
  private boolean keepWaiting() {
    if (delay == 0)
      return false;
      
    long now = System.nanoTime();
    
    // System.out.println("keepWaiting: " + previous + " - " + delay + " - " + now
    //   + " => " + (now-previous));
    
    if (previous + delay > now) {
        return true;
    }
    
    previous = now;
    return false;
  }
  
  
  /* Oversetter fart til forsinkelse (lav fart = stor forsinkelse).
   */
  private static long speedToDelay(int speed) {
    long nano = 1000000; // 1/1000 s
    return ((speed*-1)+10) * EGCommon.STD_SPEED_SLOWDOWN * nano;
  }
  
}





/* ============================================================
 * EGCircle.java
 * ============================================================
 */







/*  Et objekt av denne klassen representerer en sirkel.
 */
class EGCircle extends EGFigure {

  private int radius;
  
  
  public EGCircle(int x, int y, int radius) {
    super(x, y);
    this.radius = radius;
  }
  

  public void draw(Graphics g) {   
    if (visible) {
      super.draw(g);
      if (fill)
        g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
      else
        g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
    }
  }

  
  public void setRadius(int radius) {
    this.radius = radius;
  }
  
  
  public int getRadius() {
    return radius;
  }
  
  
  public int minX() {
    return x-radius;
  }
  
  
  public int minY() {
    return y-radius;
  }
  
  
  public int maxX() {
    return x+radius;
  }
  
  
  public int maxY() {
    return y+radius;
  }
  
  
  public void setWidth(int width) {
    this.radius = width/2;
  }
  
  
  public int getWidth() {
    return radius*2;
  }
  
  
  public void setHeight(int height) {
    this.radius = height/2;
  }
  
  
  public int getHeight() {
    return radius*2;
  }
  
  
  public EGFigure copy() {
    EGCircle copy = new EGCircle(x, y, radius);
    copy.copyColor(color);
    copy.setFill(fill);
    copy.setVisible(visible);
    return copy;
  }
  
  
  public String toString() {
    return "[EGCircle x=" + x + ", y=" + y
      + ", radius=" + radius + "]";
  }
  
}





/* ============================================================
 * EGCommon.java
 * ============================================================
 */






/*  Klassen inneholder diverse konstanter,
 *  referanse til køen av animasjonssteg,
 *  og fellesmetoder for bla. utskrift av feilmeldinger.
 */
class EGCommon {

  // Skal settes til false i ferdig løsning
  public  static final boolean DEBUG = false;
  
  // Skal tegnevinduet lukkes og programmet avsluttes
  // når alle operasjoner er utført? Hvis ikke, må
  // brukeren selv lukke vinduet.
  public  static final boolean EXIT_ON_FINISH = false;
  
  // Standardstørrelse på tegnevinduet
  public  static final int     STD_WIDTH = 800;
  public  static final int     STD_HEIGHT = 800;
  
  // Standardfarger
  public  static final Color   STD_BACKGROUND = Color.WHITE;
  public  static final Color   STD_COLOR = Color.BLACK;
  
  // Standardfont
  public  static final String  STD_FONT = "Arial";
  public  static final int     STD_FONT_SIZE = 12;
  
  // Standardfart og hvor mye farten skal "skaleres".
  public  static final int     STD_SPEED = 10;
  public  static final int     STD_SPEED_SLOWDOWN = 1;
  
  // Stegkøene
  public  static final int     INIT_STEPS = 500;  // Antall steg i animasjonskø ved opprettelse
  public  static final int     MAX_STEPS  = 5000; // Max steg i én animasjonskø
  public  static final int     MAX_QUEUES = 10;   // Max antall animasjonskøer (før venting)
  public  static final int     WAIT_TIME  = 500;  // Ventetid mellom forsøk på innsetting av kø
  
  // Datastrukturen som håndterer stegkøene
  public  static EGBuffer      stepBuffer;
  
  // Objekt for å synkronisere hoved-tråden og event-tåden
  public  static EGLatch       latch = new EGLatch();
  
  // For måling av tidsbruk (brukes kun under testing)
  private static long          startTime;
  
  
  
  public static void logMessage(String msg) {
    if (DEBUG)
      System.out.println(msg);
  }
  
  
  public static void fatalError(String msg) {
    System.out.println("Fatal error: " + msg);
    System.exit(-1);
  }
  
  
  public static void startup() {
    startTime = System.currentTimeMillis();
    stepBuffer = new EGBuffer();
  }
  
  
  public static void finish() {
    if (EGCommon.DEBUG) {
      long duration = System.currentTimeMillis() - startTime;
      System.out.println("Duration: " + duration/1000.0 + "s.");
    }
    
    if (EGCommon.EXIT_ON_FINISH)  
      System.exit(0);
    else
      System.out.println("Lukk tegnevinduet...");
  }

}





/* ============================================================
 * EGEllipse.java
 * ============================================================
 */







/*  Et objekt av denne klassen representerer en ellipse.
 */
class EGEllipse extends EGFigure {

  private int radiusX;
  private int radiusY;
  
  
  public EGEllipse(int x, int y, int radiusX, int radiusY) {
    super(x, y);
    this.radiusX = radiusX;
    this.radiusY = radiusY;
  }

  
  public void draw(Graphics g) {
    if (visible) {
      super.draw(g);
      if (fill)
        g.fillOval(x-radiusX, y-radiusY, 2*radiusX, 2*radiusY);
      else
        g.drawOval(x-radiusX, y-radiusY, 2*radiusX, 2*radiusY);
    }
  }
  
  
  public void setRadiusX(int radiusX) {
    this.radiusX = radiusX;
  }
  
  
  public int getRadiusX() {
    return radiusX;
  }
  
  
  public void setRadiusY(int radiusY) {
    this.radiusY = radiusY;
  }
  
  
  public int getRadiusY() {
    return radiusY;
  }

  
  public int minX() {
    return x-radiusX;
  }
  
  
  public int minY() {
    return y-radiusY;
  }
  
  
  public int maxX() {
    return x+radiusX;
  }
  
  
  public int maxY() {
    return y+radiusY;
  }
  
  
  public void setWidth(int width) {
    this.radiusX = width/2;
  }
  
  
  public int getWidth() {
    return radiusX*2;
  }
  
  
  public void setHeight(int height) {
    this.radiusY = height/2;
  }
  
  
  public int getHeight() {
    return radiusY*2;
  }
  
  
  public EGFigure copy() {
    EGEllipse copy = new EGEllipse(x, y, radiusX, radiusY);
    copy.copyColor(color);
    copy.setFill(fill);
    copy.setVisible(visible);
    return copy;
  }
  
  
  public String toString() {
    return "[EGEllipse x=" + x + ", y=" + y
      + ", radiusX=" + radiusX + ", radiusY=" + radiusY + "]";
  }
  
}





/* ============================================================
 * EGErrorStep.java
 * ============================================================
 */





/*  Klassen brukes for å avbryte programkjøringen.
 *  Hvis brukerprogrammet kaller EasyGraphics-metoder
 *  med ulovlige parametre skal programkjøring avbrytes.
 *  For at dette skal skje på korrekt tidspunkt i
 *  forhold til en animasjon, blir operasjonen lagt inn
 *  som et steg i køen på vanlig måte.
 */
class EGErrorStep extends EGStep {

  private String msg; // Feilmelding
  

  public EGErrorStep(String msg) {
    super();
    this.msg = msg;
  }
  
  
  public boolean continueStep() {
    return false;
  }
  
  
  public boolean execute() {
    EGCommon.fatalError(msg);
    return true;
  }
  
  
  public String toString() {
    return "EGErrorStep: msg=" + msg;
  }
  
}





/* ============================================================
 * EGFigure.java
 * ============================================================
 */







/*  Abstrakt superklasse for alle figur-objekt.
 */
abstract class EGFigure {
  
  // Ved forflytning og reskalering blir endringer
  // mindre enn DELTA piksler utført i ett steg,
  // og ellers i steg på STEP piksler.
  private final static int DELTA = 4;
  private final static int STEP  = 2;
  
  protected int     x;
  protected int     y;
  protected Color   color;
  protected boolean fill;
  
  protected boolean visible;  // Synlig?
  protected boolean touched;  // Endret i siste operasjon?
  
  // Nye koordinater ved forflytning
  private   int     x1;
  private   int     y1;
  
  // Ny størrelse ved reskalering
  private   int     width1;
  private   int     height1;
  
  // Hjelpevariabler brukt ved forflytning/reskalering
  private   int     dx;
  private   int     sx;
  private   int     dy;
  private   int     sy;
  private   int     err;
  
  
  public EGFigure(int x, int y) {
    this.x = x;
    this.y = y;
    color = EGCommon.STD_COLOR;
    fill = false;
    visible = true;
    touched = false;
  }
  
  
  public void draw(Graphics g) {
    g.setColor(color);
    touched = false;
  }
  
  
  public void setX(int x) {
    this.x = x;
  }
  
  
  public int getX() {
    return x;
  }
  
  
  public void setY(int y) {
    this.y = y;
  }
  
  
  public int getY() {
    return y;
  }
  
  
  public void setColor(Color color) {
    this.color = color;
  }
  
  
  public Color getColor() {
    return color;
  }
  
  
  public void setFill(boolean fill) {
    this.fill = fill;
  }
  
  
  public boolean getFill() {
    return fill;
  }
  
  
  public void setVisible(boolean visible) {
    this.visible = visible;
  }
  
  
  
  /* For å effektivisere tegning under animasjon,
   * blir kun den delen av vinduet som er oppdatert
   * tegnet på nytt. Metodene minX, minY, maxX og maxY
   * leverer det omsluttende rektangelet til hvert
   * geometriobjekt, og overlap sjekker om dette
   * rektangelet overlapper med den delen av vinduet
   * som er oppdatert.
   */
   
  public abstract int minX();

  public abstract int minY();

  public abstract int maxX();

  public abstract int maxY();
  
  
  
  // Operasjoner på omsluttende rektangler.
  // Brukes ved endring av størrelse.
  
  public abstract void setWidth(int width);
  
  public abstract int getWidth();
  
  public abstract void setHeight(int height);

  public abstract int getHeight();
  
  
  
  // For å sikre at kode i event-løkka og i hoved-tråden
  // ikke deler figur-objekt, blir det tatt kopier av
  // innholdet i alle slike objekt.
  
  public abstract EGFigure copy();
  
  public void copyColor(Color color) {
    setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
  }
  

  
  //  For å effektivisere tegning blir figur-objekt
  //  som er berørt av et animasjonssteg merket.
  //  Brukes for å utføre flere, "små" steg (på ulike
  //  figur-objekt) i parallell.

  public void touch() {
    touched = true;
  }
  
 
  public boolean touched() {
    return touched;
  }
  
  
  
  // Forflytning langs en rett linje gjøres ved en
  // variant av Bresenham's algoritme. Standardutgaven
  // av denne algoritmen bygger opp en sekvens av punkt
  // med ei løkke. Her blir initieringsdelen av algoritmen
  // utført av initMove, og hvert kall på move finner
  // neste punkt. 
  public void initMove(int x1, int y1) {
    this.x1 = x1;
    this.y1 = y1;
    
    dx = Math.abs(x1 - x);
    dy = Math.abs(y1 - y);
    
    sx = x < x1 ? STEP : -STEP;
    sy = y < y1 ? STEP : -STEP;
    err = (dx > dy ? dx : -dy) / 2;
  }
  
  
  public boolean move() {
    EGCanvas.setUpdateRect(minX(), minY(), maxX(), maxY());
    boolean finished = false;
    
    if (Math.abs(x1 - x) < DELTA && Math.abs(y1 - y) < DELTA) {
      x = x1;
      y = y1;
      finished = true;
    }
      
    if (!finished) {
      int e2 = err;
      if (e2 > -dx) {
        err -= dy;
        x += sx;
      }
      if (e2 < dy) {
        err += dx;
        y += sy;
      }
    }
    
    EGCanvas.setUpdateRect(minX(), minY(), maxX(), maxY());
    touch();
    return finished;
  }
  
  
  // Også reskalering gjøres ved hjelp av Bresenham's algoritme,
  // se kommentar til move over. I stedet for å finne en rett
  // linje fra ett punkt til et annet, finner vi her en måte
  // å skalere et par (bredde,høyde) til et nytt ved like mange
  // endringer langs begge akser.
  //
  // TODO Forflytning og reskalering gjentar nesten samme kode.
  public void initResize(int width1, int height1) {
    this.width1 = width1;
    this.height1 = height1;
    
    dx = Math.abs(width1 - getWidth());
    dy = Math.abs(height1 - getHeight());
      
    sx = getWidth() < width1 ? STEP : -STEP;
    sy = getHeight() < height1 ? STEP : -STEP;
    err = (dx > dy ? dx : -dy) / 2;
  }
  
    
  public boolean resize() {
    EGCanvas.setUpdateRect(minX(), minY(), maxX(), maxY());
    boolean finished = false;
    
    if (Math.abs(width1 - getWidth()) < DELTA 
     && Math.abs(height1 - getHeight()) < DELTA) {
      setWidth(width1);
      setHeight(height1);
      finished = true;
    }
      
    if (!finished) {
      int e2 = err;
      if (e2 > -dx) {
        err -= dy;
        setWidth(getWidth() + sx);
      }
      if (e2 < dy) {
        err += dx;
        setHeight(getHeight() + sy);
      }
    }
    
    EGCanvas.setUpdateRect(minX(), minY(), maxX(), maxY());
    touch();
    return finished;
  }
  
}





/* ============================================================
 * EGFinishStep.java
 * ============================================================
 */





/*  Klassen brukes for å avslutte en animasjon.
 *  Det innebærer å "vekke" hoved-tråden.
 */
class EGFinishStep extends EGStep {

  public EGFinishStep() {
    super();
  }
  
  
  public boolean continueStep() {
    return false;
  }
  
  
  public boolean execute() {
    EGCommon.latch.animationFinished();
    finished = true;
    return finished;
  }
  
  
  public String toString() {
    return "EGFinishStep";
  }
  
}





/* ============================================================
 * EGGlobalStep.java
 * ============================================================
 */







/*  Klassen brukes for å styre enten fart eller
 *  bakgrunnsfarge, og vil gjelde for alle påfølgende
 *  operasjoner.
 */
class EGGlobalStep extends EGStep {

  private int   speed;
  private int   red;
  private int   green;
  private int   blue;
 
 
  public EGGlobalStep(int speed) {
    super();
    this.speed = speed;
    this.red = -1;
    this.green = -1;
    this.blue = -1;
  }
 

  public EGGlobalStep(int red, int green, int blue) {
    super();
    this.red = red;
    this.green = green;
    this.blue = blue;
    speed = -1;
  }
  
  
  public boolean continueStep() {
    return false;
  }
 
  
  public boolean execute() {
    if (speed >= 1 && speed <= 10)
      EGCanvas.setSpeed(speed);
    if (red >= 0 && red <= 255 && green >= 0 && green <= 255 & blue >= 0 && blue <= 255) {
      Color color = new Color(red, green, blue);
      EGCanvas.setBackgroundColor(color);
    }
     
    finished = true;
    return finished;
  }

  
  public String toString() {
    String str = "EGGlobalStep: speed=" + speed + ", backColor=";
    if (red >= 0 && red <= 255 && green >= 0 && green <= 255 & blue >= 0 && blue <= 255)
      str += "(" + red + ", " + blue + ", " + green + ")";
    else
      str += "illegal";
    return str;
  }
  
}





/* ============================================================
 * EGGui.java
 * ============================================================
 */







/*  Klassen oppretter brukergrensesnittet på event-tråden,
 *  og håndterer inndata fra bruker.
 */
class EGGui implements Runnable, ActionListener {

  private final String STD_TITLE = "EasyGraphics";
  
  private final Font   inputFont = new Font("Arial", Font.PLAIN, 18);
  private final String inputMsg  = "Inndata:";
  
  private EasyGraphics app;      // Hovedvinduet
  
  private JLabel       message;  // Ledetekst for inndatafelt
  private JTextField   line;     // Inndatafelt
  private EGCanvas     canvas;   // Tegneområde
  private String       response; // Respons fra brukeren
  
  
  public EGGui(EasyGraphics app) {
    this.app = app;
  }
  
  
  /* Oppretter brukergrensesnittet og gir beskjed om
   * at setningene i brukerprogrammet kan startes opp.
   */
  public void run() {
    makeGUI();
    EGCommon.latch.startupFinished();
  }
  
  
  public JLabel getMessage() {
    return message;
  }
  
  
  public JTextField getLine() {
    return line;
  }
  
  
  public String getResponse() {
    return response;
  }
  
  
  public void listenModus(boolean modus) {
    if (modus)
      line.addActionListener(this);
    else
      line.removeActionListener(this);
  }
  
  
  public void show(String title, int width, int height) {
    canvas.setCanvasSize(width, height);
    app.setTitle(title);
    app.pack();
    app.setResizable(false);
    app.setVisible(true);
  }
  
  
  public void actionPerformed(ActionEvent e) {
    // Har fanget inndata og avslutter lytting i denne omgangen
    listenModus(false);
    
    response = line.getText().trim();
    message.setText(inputMsg);
    line.setText("");
    line.getCaret().setVisible(false);
    line.setEditable(false);

    EGCommon.latch.userFinished();
    
    // Tråden for hovedprogrammet ble satt "på vent"
    // i påvente av inndata, og kan nå fortsette.
  }
  
  
  private void makeGUI() {
    app.setTitle(STD_TITLE);
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  
    message = new JLabel(inputMsg, SwingConstants.CENTER);
    message.setFont(inputFont);
    
    line = new JTextField(20);
    line.setFont(inputFont);
    line.setBorder(new LineBorder(Color.GRAY));
    line.getCaret().setVisible(false);
    line.setEditable(false);
    
    JPanel topPanel = new JPanel(new GridLayout(2, 1));
    JPanel messagePanel = new JPanel();
    messagePanel.add(message);
    JPanel linePanel = new JPanel();
    linePanel.add(line);
    topPanel.add(messagePanel);
    topPanel.add(linePanel);
    app.add(topPanel, BorderLayout.NORTH);
          
    JPanel centerPanel = new JPanel();
    JPanel canvasPanel = new JPanel(new GridLayout(1, 1, 1, 1));
    canvasPanel.setBackground(Color.GRAY);
    canvasPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
    canvas = new EGCanvas();
    canvasPanel.add(canvas);
    centerPanel.add(canvasPanel);
    app.add(centerPanel, BorderLayout.CENTER);
  }
}





/* ============================================================
 * EGInput.java
 * ============================================================
 */







/*  Klassen brukes for å klargjøre for ny innlesing fra brukeren.
 */
class EGInput implements Runnable {
 
  private EGGui  gui;
  private String msg;
 
 
  public EGInput(EGGui gui, String msg) {
    super();
    this.gui = gui;
    this.msg = msg;
  }
 
  
  public void run() {
    JLabel lbl = gui.getMessage();
    JTextField txt = gui.getLine();
    
    lbl.setText(msg);
    txt.setText("");
    txt.grabFocus();
    txt.getCaret().setVisible(true);
    txt.setEditable(true);
    txt.grabFocus();
    
    // Starter lytting på tekstfeltet
    gui.listenModus(true);
  }
  
  
  public String toString() {
    return "EGInput: msg=" + msg;
  }
  
}





/* ============================================================
 * EGLatch.java
 * ============================================================
 */






/*  Klassen brukes for å synkronisere koden i brukerprogrammet
 *  med event-tråden. EasyGraphics tilbyr brukeren en "sekvensiell"
 *  måte å programmere grafikk på, uten å måtte tenke på hendelser.
 *
 *  For å få til dette må vi sørge for at brukerprogrammet (run) av
 *  og til venter på at animasjoner som kjøres som på event-tråden
 *  blir fullført, eller at innputt fra brukeren (getText i EasyGraphics)
 *  er lest inn. Event-tråden vekker da brukerprogrammet opp igjen.
 *  Denne synkroniseringen gjøres ved hjelp av klassen CountDownLatch.
 */
class EGLatch {

  // Venting mellom hvert forsøk fra event-tråden på
  // å vekke hoved-tråden.
  private static final int PAUSE = 100;
  
  // Vente på animasjon
  private CountDownLatch animationLatch = null;
  
  // Vente på innputt fra brukeren
  private CountDownLatch userLatch = null;
  
  // Vente på initiering av brukergrensesnittet
  private CountDownLatch startupLatch = null;
  
 
 
  /*  Metoden sørger for å sette hoved-tråden "på vent"
   *  til brukergrensesnittet er ferdig opprettet.
   */
  public void awaitStartup() {
    startupLatch = new CountDownLatch(1);
    try {
      startupLatch.await();
    }
    catch (InterruptedException e) {
      EGCommon.logMessage("Interrupt in EGLatch.awaitStartup().");
    }   
  }
  
  
  /*  Metoden sørger for å vekke hoved-tråden når
   *  brukergrensesnittet er ferdig opprettet.
   */
  public void startupFinished() {
    // Hvis startupLatch er null har ikke hoved-tråden
    // rukket å gå inn i "vente-modus".
    while (startupLatch == null)
      pause(PAUSE);
      
    startupLatch.countDown();
    startupLatch = null;
  }

  
  /*  Metoden sørger for å sette hoved-tråden "på vent"
   *  til pågående animasjoner i event-tråden er ferdig
   *  behandlet.
   */
  public void awaitAnimation() {
    animationLatch = new CountDownLatch(1);
    try {
      animationLatch.await();
    }
    catch (InterruptedException e) {
      EGCommon.logMessage("Interrupt in EGLatch.awaitAnimation().");
    }   
  }
  
  
  /*  Metoden sørger for å vekke hoved-tråden når vi
   *  er ferdige med å behandle animasjoner.
   */
  public void animationFinished() {
    // Hvis animationLatch er null har ikke hoved-tråden
    // rukket å gå inn i "vente-modus".
    while (animationLatch == null)
      pause(PAUSE);
      
    animationLatch.countDown();
    animationLatch = null;
  }

  
  /*  Metoden sørger for å sette hoved-tråden "på vent"
   *  til innlesing fra bruker er ferdig.
   */
  public void awaitUser() {
    userLatch = new CountDownLatch(1);
    try {
      userLatch.await();
    }
    catch (InterruptedException e) {
      EGCommon.logMessage("Interrupt in EGLatch.awaitUser().");
    }   
  }
  
  
  /*  Metoden sørger for å vekke hoved-tråden når vi
   *  har lest inndata fra brukeren.
   */
  public void userFinished() {
    // Hvis userLatch er null har ikke hoved-tråden
    // rukket å gå inn i "vente-modus".
    while (userLatch == null)
      pause(PAUSE);
    
    userLatch.countDown();
    userLatch = null;
  }
  
  
  /*  Metoden venter et antall millisekunder.
   */
  private void pause(int ms) {
    try {
      Thread.sleep(ms);
    }
    catch (InterruptedException e) {
      EGCommon.logMessage("EGLatch.pause() interrupted.");
    }
  }

}





/* ============================================================
 * EGLine.java
 * ============================================================
 */







/*  Et objekt av denne klassen representerer et linjestykke.
 */
class EGLine extends EGFigure {

  private int width;
  private int height;
  
  // En  linje fra (30, 50) til (40, 40) blir representert
  // ved x=30, y=50, width=10, height=-10.
  // Absoluttverdiene til width og height representerer
  // hhv. bredde og høyde, og er negative hvis endepunktet
  // er til venstre / over startpunktet.
  //
  // Brukeren kan dermed opprette linjer ved startpunkt og endepunkt,
  // og flytting kan gjøres ved å oppdatere kun startpunkt (som for
  // øvrige geometriobjekt).
  
  
  public EGLine(int x1, int y1, int x2, int y2) {
    super(x1, y1);
    this.width = x2-x1;
    this.height = y2-y1;
  }

  
  public void draw(Graphics g) {
    if (visible) {
      super.draw(g);
      g.drawLine(x, y, x+width, y+height);
    }
  }
  
  
  public int minX() {
    return Math.min(x, x+width);
  }
  
  
  public int minY() {
    return Math.min(y, y+height);
  }
  
  
  public int maxX() {
    return Math.max(x, x+width);
  }
  
  
  public int maxY() {
    return Math.max(y, y+height);
  }
  
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  
  public int getWidth() {
    return width;
  }
  
  
  public void setHeight(int height) {
    this.height = height;
  }
  
  
  public int getHeight() {
    return height;
  }
  
  
  
  public EGFigure copy() {
    EGLine copy = new EGLine(x, y, x+width, y+height);
    copy.copyColor(color);
    copy.setFill(fill);
    copy.setVisible(visible);
    return copy;
  }
  
  
  public String toString() {
    return "[EGLine x=" + x + ", y=" + y
      + ", width=" + width + ", height=" + height + "]";
  }
  
}





/* ============================================================
 * EGMakeStep.java
 * ============================================================
 */







/*  Klassen brukes til å opprette et nytt geometriobjekt,
 *  dvs. tegne det for første gang. Objektet legges til i
 *  listen (se EGCanvas) i konstruktøren, men blir først
 *  synlig når steget blir utført (execute). 
 */
class EGMakeStep extends EGStep {

  private EGFigure fig;
 
 
  public EGMakeStep(EGFigure fig) {
    super();
    this.fig = fig;
  }
  
  
  public boolean continueStep() {
    return false;
  }
  
  
  public boolean execute() {
    // Oppretter friske kopier av alle figurobjekt.
    // Ser ut til å forbedre effektivitet ved at
    // hovedtråden og event-tråden ikke deler slike objekt.
    EGFigure copyFig = fig.copy();
    EGCanvas.addFig(copyFig);
    
    // Tekstobjekt må spesialbehandles. Bruker fonten
    // for å bestemme bredde og høyde.
    if (copyFig instanceof EGText) {
      EGText txt = (EGText) copyFig;
      JLabel label = new JLabel(); // Kun for å få tak i fonten
      FontMetrics metrics = label.getFontMetrics(txt.getFont());
      txt.setWidth(metrics.stringWidth(txt.getStr()));
      txt.setHeight(metrics.getHeight());
    }
    
    // Avmerk omsluttende rektangel ved ny posisjon
    EGCanvas.setUpdateRect(copyFig.minX(), copyFig.minY(), copyFig.maxX(), copyFig.maxY());
    
    copyFig.touch();
    copyFig.setVisible(true);
    
    fig = null;
    
    finished = true;
    return finished;
  }
  
  
  public EGFigure getFig() {
    return fig;
  }
  
  
  public String toString() {
    String str = "EGMakeStep: fig=";
    if (fig != null)
      str += fig.toString();
    else
      str += "null";
    return str;
  }
  
}





/* ============================================================
 * EGMoveStep.java
 * ============================================================
 */





/*  Klassen representerer forflytning av et figur-objekt
 *  langs en rett linje. Endringen blir brutt ned i mange
 *  små steg, slik at man oppnår en animasjonseffekt.
 */
class EGMoveStep extends EGStep {
  
  private EGFigure fig;
  
  private int id;
  private int x;
  private int y;
  private boolean started;
  
  
  public EGMoveStep(int id, int x, int y) {
    super();
    this.id = id;
    this.x = x;
    this.y = y;
    started = false;
  }
  
  
  public boolean continueStep() {
    fig = EGCanvas.getFig(id);
    
    if (fig.touched())
      return false;
    
    return true;
  }
  
    
  public boolean execute() {
    if (!started) {
      if (fig == null)
        fig = EGCanvas.getFig(id);
      fig.initMove(x, y);
      started = true;
    }
    finished = fig.move();
    return finished;
  }
  
  
  public EGFigure getFig() {
    return fig;
  }
  
  
  public String toString() {
    return "EGMoveStep id=" + id + ", x=" + x + ", y= " + y;
  }
  
}





/* ============================================================
 * EGPauseStep.java
 * ============================================================
 */





/*  Klassen brukes for å legge inn en pause i animasjonen.
 */
class EGPauseStep extends EGStep {

  private int ms; // Varighet målt i antall millisekunder
  

  public EGPauseStep(int ms) {
    super();
    this.ms = ms;
  }
  
  
  public boolean continueStep() {
    return false;
  }
    
  
  public boolean execute() {
    try {
      Thread.sleep(ms);
    }
    catch (InterruptedException e) {
      EGCommon.logMessage("EGPauseStep.execute() interrupted.");
    }
    finished = true;
    return finished;
  }
  
  
  public String toString() {
    return "EGPauseStep: ms=" + ms;
  }
  
}





/* ============================================================
 * EGPrintStep.java
 * ============================================================
 */





/*  Klassen brukes for å gjøre utskrift til
 *  konsollet underveis i en animasjon, slik
 *  at effekten kommer på forventet tidspunkt.
 */
class EGPrintStep extends EGStep {

  private String msg;
 
 
  public EGPrintStep(String msg) {
    super();
    this.msg = msg;
  }
  
  
  public boolean continueStep() {
    return false;
  }
  
  public boolean execute() {
    System.out.print(msg);
    
    finished = true;
    return finished;
  }
  
 
  public String toString() {
    return "EGPrintStep: msg=" + msg;
  }
  
}





/* ============================================================
 * EGRectangle.java
 * ============================================================
 */







/*  Et objekt av denne klassen representerer et rektangel.
 */
class EGRectangle extends EGFigure {

  private int width;
  private int height;
  
  
  public EGRectangle(int x, int y, int width, int height) {
    super(x, y);
    this.width = width;
    this.height = height;
  }

  
  public void draw(Graphics g) {
    if (visible) {
      super.draw(g);
      if (fill)
        g.fillRect(x, y, width, height);
      else
        g.drawRect(x, y, width, height);
    }
  }


  public void setWidth(int width) {
    this.width = width;
  }
  
  
  public int getWidth() {
    return width;
  }
  
  
  public void setHeight(int height) {
    this.height = height;
  }
  
  
  public int getHeight() {
    return height;
  }
  
  
  public int minX() {
    return x;
  }
  
  
  public int minY() {
    return y;
  }
  
  
  public int maxX() {
    return x+width;
  }
  
  
  public int maxY() {
    return y+height;
  }
  
  
  public EGFigure copy() {
    EGRectangle copy = new EGRectangle(x, y, width, height);
    copy.copyColor(color);
    copy.setFill(fill);
    copy.setVisible(visible);
    return copy;
  }
  
  
  public String toString() {
    return "[EGRectangle x=" + x + ", y=" + y
      + ", width=" + width + ", height=" + height + "]";
  }
  
}





/* ============================================================
 * EGResizeStep.java
 * ============================================================
 */





/*  Klassen endrer størrelsen på et figur-objekt.
 *  Endringen blir brutt ned i mange små steg, slik
 *  at man oppnår en animasjonseffekt.
 */
class EGResizeStep extends EGStep {

  private EGFigure fig;
  
  private int id;
  private int width;
  private int height;
  private boolean started;
  
  
  public EGResizeStep(int id, int width, int height) {
    super();
    this.id = id;
    this.width = width;
    this.height = height;
    started = false;
  }
  
  
  public boolean continueStep() {
    fig = EGCanvas.getFig(id);
      
    if (fig.touched())
      return false;
      
    return true;
  }
  

  public boolean execute() {
    if (!started) {
      if (fig == null)
        fig = EGCanvas.getFig(id);
      fig.initResize(width, height);
      started = true;
    }
    finished = fig.resize();
    return finished;
  }
  
  
  public EGFigure getFig() {
    return fig;
  }
  
  
  public String toString() {
    return "EGResizeStep id=" + id + ", width=" + width + ", height= " + height;
  }
  
}





/* ============================================================
 * EGRunner.java
 * ============================================================
 */





/*  Klassen kjører setningene i brukerprogrammet på en egen tråd.
 */
class EGRunner implements Runnable {
  
  private EasyGraphics app;  // Applikasjonen
  
   
  public EGRunner(EasyGraphics app) {
    this.app = app;
  }
  
  
  /* Ufører brukerprogrammet og avslutter med å legge
   * de siste, ventende stegene inn på køen.
   */
  public void run() {
    app.run();
    
    // Får utført de siste stegene og avslutter.
    EGCommon.stepBuffer.addStep(new EGFinishStep());
    EGCommon.stepBuffer.addQueue();
    EGCommon.latch.awaitAnimation();
    EGCommon.finish();
  }

}





/* ============================================================
 * EGShowStep.java
 * ============================================================
 */





/*  Klassen brukes for å gjøre et geometriobjekt
 *  synlig/usynlig underveis i en animasjon.
 */
class EGShowStep extends EGStep {

  private int     id;
  private boolean visibility;
 
 
  public EGShowStep(int id, boolean visibility) {
    super();
    this.id = id;
    this.visibility = visibility;
  }
  
  
  public boolean continueStep() {
    return false;
  }
  
  public boolean execute() {
    EGFigure fig = EGCanvas.getFig(id);
    
    // Avmerk omsluttende rektangel ved ny posisjon
    EGCanvas.setUpdateRect(fig.minX(), fig.minY(), fig.maxX(), fig.maxY());
    
    fig.setVisible(visibility);
    
    fig.touch();
    fig = null;
    
    finished = true;
    return finished;
  }
  
  
  public EGFigure getFig() {
    return EGCanvas.getFig(id);
  }
  
  
  public String toString() {
    return "EGShowStep: id=" + id + ", visibility=" + visibility;
  }
  
}





/* ============================================================
 * EGStep.java
 * ============================================================
 */





/*  Abstrakt superklasse for alle oppgaver (steg).
 *  Hovedtråden legger slike steg inn på en kø, som
 *  event-tråden plukker fra, og utfører etter tur.
 *
 */
abstract class EGStep {
          
  // Noen steg (forflytninger/reskaleringer) blir
  // brutt ned i flere "mini-steg".
  protected boolean finished;        // Avsluttet?
    

  public EGStep() {
    finished = false;
  }
  
  
  public boolean finished() {
    return finished;
  }
  
  
  /*  Svært korte/små forflytninger/reskaleringer på
   *  ulike objekt blir utført i parallell. Denne metoden
   *  returnerer true for slike steg.
   */
  public abstract boolean continueStep();
  
 
  /*  Utfører steget og returnerer true hvis ferdig.
   */
  public abstract boolean execute();
  
  
  /*  Subklasser som er knyttet til et bestemt figur-objekt,
   *  f.eks. en flytting, overstyrer denne metoden.
   */
  public EGFigure getFig() {
    return null;
  }

}





/* ============================================================
 * EGText.java
 * ============================================================
 */







/*  Et objekt av denne klassen representerer en (grafisk) tekst.
 */
class EGText extends EGFigure {

  private String str;
  private Font   font;
  private int    width;
  private int    height;
  
  
  public EGText(int x, int y, String str, Font font) {
    super(x, y);
    this.str = str;
    this.font = font;
  }

  
  public void draw(Graphics g) {
    if (visible) {
      super.draw(g);
      g.setFont(font);
      g.drawString(str, x, y);
    }
  }
  
  
  public String getStr() {
    return str;
  }
  
  
  public Font getFont() {
    return font;
  }
  
  
  public int minX() {
    return x;
  }
  
  
  public int minY() {
    return y-height;
  }
  
  
  public int maxX() {
    return x+width;
  }
  
  
  public int maxY() {
    // Noe av teksten er under fotlinjen (y).
    // Tar i litt for å være helt sikker.
    return y+height;
  }
  
  
  
  // EasyGraphics støtter foreløpig ikke "resizeString".
  // Følgende fire brukes dermed ikke.
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  
  public int getWidth() {
    return width;
  }
 
  
  public void setHeight(int height) {
    this.height = height;
  }
  
  
  public int getHeight() {
    return height;
  }
  
  
  
  public EGFigure copy() {
    Font fontCopy = new Font(font.getName(), Font.PLAIN, font.getSize());
    EGText copy = new EGText(x, y, str, fontCopy);
    copy.copyColor(color);
    copy.setFill(fill);
    copy.setVisible(visible);
    return copy;
  }
  
  
  public String toString() {
    return "[EGText x=" + x + ", y=" + y
      + ", str=" + str + "]";
  }
 
}





/* ============================================================
 * EGWindowStep.java
 * ============================================================
 */






/*  Klassen brukes til å opprette opprette det grafiske
 *  vinduet. Dette må alltid være det første steget
 *  (kall på makeWindow må stå først i run).
 */
class EGWindowStep extends EGStep {
 
  private EGGui    gui;
  private String   title;
  private int      width;
  private int      height;
  
  
  public EGWindowStep(EGGui gui, String title, int width, int height) {
    super();
    this.title = title;
    this.width = width;
    this.height = height;
    this.gui = gui;
  }
  
  
  public boolean continueStep() {
    return false;
  }
  
  
  public boolean execute() {
    gui.show(title, width, height);
    finished = true;
    return finished;
  }
  
  
  public String toString() {
    return "EGWindowStep: title=" + title
      + ", width=" + width + ", height=" + height;
  }
  
}
