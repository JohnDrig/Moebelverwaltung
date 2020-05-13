package TischType;

import java.io.Serializable;

import abstractClasses.Tisch;

public class Gruppen extends Tisch implements Serializable {

	// ein super Aufruf ist ein Aufruf der Oberklasse/Mutterklasse, womit man die
	// Variable benutzen kann, ohne eine richtige Variable du deklarieren, dazu muss
	// aber die gewünschte Variable + Datentyp im Parameter vom Konstruktor stehen
	public Gruppen(String type) {
		super(type);
	}

}
