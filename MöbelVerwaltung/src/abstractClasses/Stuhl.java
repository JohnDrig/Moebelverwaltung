package abstractClasses;

public abstract class Stuhl {

	// Variablen
	protected String type;

	// Konstruktor
	public Stuhl(String type) {
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
