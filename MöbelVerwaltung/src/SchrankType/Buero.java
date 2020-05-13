package SchrankType;

import java.io.Serializable;

import abstractClasses.Schrank;

public class Buero extends Schrank implements Serializable {

	// Serializable ist ein Interface, was implementiert wird für die Speicherung
	// von Objekten in Dateien

	private static final long serialVersionUID = -229311368024254154L;

	// ein super Aufruf ist ein Aufruf der Oberklasse/Mutterklasse, womit man die
	// Variable benutzen kann, ohne eine richtige Variable du deklarieren, dazu muss
	// aber die gewünschte Variable + Datentyp im Parameter vom Konstruktor stehen
	public Buero(String type) {
		super(type);
	}

}
