package MainPackage;

public class Main {

	/*
	 * ein externer jar-File wurde zum Java-Build-Path hinzugefügt, damit es möglich
	 * ist eine Connection zu der Datenbank zu haben, und zwar der
	 * mysql-connector-java-8.0.19
	 */

	// Variablen
	public static final int breite = 1500, hoehe = 800;

	// Instanzen
	private Window window;

	public Main() {

		// neues Objekt der Klasse Window erstellen und die createFrame() Methode
		// aufrufen, um ein Fenster zu erstellen. Dazu muss die Breite, Hoehe und der
		// Titel des Fenster übergeben werden
		window = new Window();
		window.createFrame(breite, hoehe, "Möbelverwaltung");

	}

	public static void main(String args[]) {
		new Main();
	}

}
