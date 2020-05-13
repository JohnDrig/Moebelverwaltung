package abstractClasses;

public abstract class Schrank {

	// Variablen
	protected String type;

	// Konstruktor
	public Schrank(String type) {
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
