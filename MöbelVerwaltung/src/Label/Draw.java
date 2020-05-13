package Label;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

import Gui.MouseHandler;
import MainPackage.Main;
import MainPackage.Window;

public class Draw extends JLabel {

	// Instanz
	private Window window;

	public Draw(Window window) {
		this.window = window;

	}

	private static final long serialVersionUID = -3790047058275101501L;

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D gd = (Graphics2D) g;

		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/*
		 * paint
		 */

		// mainTab
		g.setColor(new Color(2, 101, 163));
		g.fillRect(window.mainTab.x, window.mainTab.y, window.mainTab.breite, window.mainTab.hoehe);

		g.setFont(new Font("Arial", Font.BOLD, 80));
		g.setColor(new Color(71, 161, 111));

		g.drawString("Räume", (Main.breite / 2 - 50) / 2 - 130, window.mainTab.y + 90);

		if (window.mainTab.hover) {
			g.setColor(new Color(69, 125, 161, 150));
			g.fillRect(window.mainTab.x, window.mainTab.y, window.mainTab.breite, window.mainTab.hoehe);

		}

		// stuhlTab
		g.setColor(new Color(39, 87, 60));
		g.fillRect(window.stuhlTab.x, window.stuhlTab.y, window.stuhlTab.breite, window.stuhlTab.hoehe);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Stühle", window.stuhlTab.x + 55, window.stuhlTab.y + 80);

		if (window.stuhlTab.hover) {
			// der vierte Wert im Parameter ist der Transparentwert, damit der background
			// bisschen durchsichtig wird ;)
			g.setColor(new Color(58, 133, 91, 150));
			g.fillRect(window.stuhlTab.x, window.stuhlTab.y, window.stuhlTab.breite, window.stuhlTab.hoehe);
		}

		// tischtab
		g.setColor(new Color(39, 87, 60));
		g.fillRect(window.tischTab.x, window.tischTab.y, window.tischTab.breite, window.tischTab.hoehe);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Tische", window.tischTab.x + 50, window.tischTab.y + 80);

		if (window.tischTab.hover) {
			g.setColor(new Color(58, 133, 91, 150));
			g.fillRect(window.tischTab.x, window.tischTab.y, window.tischTab.breite, window.tischTab.hoehe);
		}

		// schrankTab
		g.setColor(new Color(39, 87, 60));
		g.fillRect(window.schrankTab.x, window.schrankTab.y, window.schrankTab.breite, window.schrankTab.hoehe);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("Schränke", window.schrankTab.x + 20, window.schrankTab.y + 80);

		if (window.schrankTab.hover) {
			g.setColor(new Color(58, 133, 91, 150));
			g.fillRect(window.schrankTab.x, window.schrankTab.y, window.schrankTab.breite, window.schrankTab.hoehe);
		}

		/*
		 * MouseHandler-Draw mal das, wenn man auf einen Button klickt
		 */

		// falls man den StuhlTab-Button klickt
		if (window.stuhlTab.geklickt) {

			g.setColor(Color.WHITE);
			g.drawLine((Main.breite / 2 - 50) + (Main.breite / 2 + 50) / 2, 120,
					(Main.breite / 2 - 50) + (Main.breite / 2 + 50) / 2, Main.hoehe);

			// Schrift Types
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Types", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 200, 170);

			// Striche durch die JCheckBoxen
			g.setColor(Color.BLACK);
			g.drawLine((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 240,
					((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 70) - 10, 240);
			g.drawLine((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 281,
					((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 70) - 10, 281);

			g.setColor(Color.WHITE);
			g.drawString("in Raum", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250 + 45, 380);

			g.drawString("von Raum", (Main.breite / 2 - 50) + 540, 170);

			if (Window.temp == 1) {
				g.drawString("Type:", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 220, 390);
			} else {

			}

		}

		// falls man den TischTab-Button klickt
		if (window.tischTab.geklickt) {
			g.setColor(Color.WHITE);
			g.drawLine((Main.breite / 2 - 50) + (Main.breite / 2 + 50) / 2, 120,
					(Main.breite / 2 - 50) + (Main.breite / 2 + 50) / 2, Main.hoehe);

			// Schrift Types
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Types", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 200, 170);

			g.setColor(Color.WHITE);
			g.drawString("in Raum", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250 + 45, 500);

			g.drawString("von Raum", (Main.breite / 2 - 50) + 540, 170);

			if (Window.tempTisch == 1) {
				g.drawString("Type:", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 220, 390);
			} else {

			}
		}

		// falls man den SchrankTab-Button klickt
		if (window.schrankTab.geklickt) {
			g.setColor(Color.WHITE);
			g.drawLine((Main.breite / 2 - 50) + (Main.breite / 2 + 50) / 2, 120,
					(Main.breite / 2 - 50) + (Main.breite / 2 + 50) / 2, Main.hoehe);

			g.setColor(Color.WHITE);
			g.drawLine((Main.breite / 2 - 50) + (Main.breite / 2 + 50) / 2, 120,
					(Main.breite / 2 - 50) + (Main.breite / 2 + 50) / 2, Main.hoehe);

			// Schrift Types
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Types", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 200, 170);

			g.setColor(Color.WHITE);
			g.drawString("in Raum", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250 + 45, 420);

			g.drawString("von Raum", (Main.breite / 2 - 50) + 540, 170);

			if (Window.tempSchrank == 1) {
				g.drawString("Type:", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 220, 390);
			} else {

			}
		}

		// Striche
		g.setColor(Color.WHITE);
		g.drawLine(0, 120, Main.breite, 120);

		// -50 kommt dadurch zustande, da der mittlere Strich auf der y-Achse durch 2
		// geteilt ist, aber dann ich -50 Pixel nach links alles verschoben habe, damit
		// man meniger auf dem Main-Tab hat und mehr Platz für die anderen Tabs
		g.drawLine(Main.breite / 2 - 50, 0, Main.breite / 2 - 50, Main.hoehe);

		g.drawLine(Main.breite / 2 - 50 + 266, 0, Main.breite / 2 - 50 + 266, 120);

		g.drawLine(Main.breite / 2 - 50 + 532, 0, Main.breite / 2 - 50 + 532, 120);

		g.drawLine(350, 450, 350, Main.hoehe);

		repaint();
	}

}
