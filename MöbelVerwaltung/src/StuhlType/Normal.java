package StuhlType;

import java.io.Serializable;

import abstractClasses.Stuhl;

public class Normal extends Stuhl implements Serializable {

	// ein super Aufruf ist ein Aufruf der Oberklasse/Mutterklasse, womit man die
	// Variable benutzen kann, ohne eine richtige Variable du deklarieren, dazu muss
	// aber die gew�nschte Variable + Datentyp im Parameter vom Konstruktor stehen
	public Normal(String type) {
		super(type);
	}

}
