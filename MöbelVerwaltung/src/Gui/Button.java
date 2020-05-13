package Gui;

public class Button {

	// Variablen
	public int x, y, breite, hoehe;
	public boolean hover;
	public boolean geklickt;

	// Konstruktor
	public Button(int x, int y, int breite, int hoehe, boolean hover, boolean geklickt) {
		this.x = x;
		this.y = y;
		this.breite = breite;
		this.hoehe = hoehe;
		this.geklickt = geklickt;
	}
}
