package TischType;

import java.io.Serializable;

import abstractClasses.Tisch;

public class Einer extends Tisch implements Serializable {

	// ein super Aufruf ist ein Aufruf der Oberklasse/Mutterklasse, womit man die
	// Variable benutzen kann, ohne eine richtige Variable du deklarieren, dazu muss
	// aber die gewünschte Variable + Datentyp im Parameter vom Konstruktor stehen
	public Einer(String type) {
		super(type);
	}

}
