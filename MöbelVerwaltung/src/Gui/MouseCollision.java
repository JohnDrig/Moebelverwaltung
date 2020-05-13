package Gui;

public class MouseCollision {

	private int mouseX, mouseY;
	private Button button;
	private int breite, hoehe;

	public boolean inside(int mouseX, int mouseY, Button button) {

		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.button = button;
		this.breite = button.breite;
		this.hoehe = button.hoehe;

		// prüfen, ob die Maus das Objekt berührt
		if (this.mouseX >= this.button.x && this.mouseX < this.button.x + this.breite && this.mouseY >= this.button.y
				&& this.mouseY < this.button.y + this.hoehe) {
			return true;
		} else {
			return false;
		}
	}

}
