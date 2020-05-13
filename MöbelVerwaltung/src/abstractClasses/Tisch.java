package abstractClasses;

public abstract class Tisch {

	// Variablen
	protected String type;

	// Konstruktor
	public Tisch(String type) {
		setType(type);
	}

	// Setter und Getter
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
