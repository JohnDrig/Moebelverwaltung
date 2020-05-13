package StuhlType;

import java.io.Serializable;

import abstractClasses.Stuhl;

public class Behindertengerecht extends Stuhl implements Serializable {

	private static final long serialVersionUID = 1624680165066106047L;

	// ein super Aufruf ist ein Aufruf der Oberklasse/Mutterklasse, womit man die
	// Variable benutzen kann, ohne eine richtige Variable du deklarieren, dazu muss
	// aber die gewünschte Variable + Datentyp im Parameter vom Konstruktor stehen
	public Behindertengerecht(String type) {
		super(type);
	}

}
