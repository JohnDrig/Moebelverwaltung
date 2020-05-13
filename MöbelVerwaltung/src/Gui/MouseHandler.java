package Gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import MainPackage.Window;

public class MouseHandler implements MouseListener, MouseMotionListener {

	// Variablen
	public static int temp;

	// Instanzen
	private MouseCollision mc = new MouseCollision();
	private Window window;

	public MouseHandler(Window window) {
		this.window = window;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// falls man auf die Button klickt
		if (mc.inside(e.getX(), e.getY(), window.stuhlTab)) {
			window.stuhlTab.geklickt = true;
			window.tischTab.geklickt = false;
			window.schrankTab.geklickt = false;
		} else if (mc.inside(e.getX(), e.getY(), window.tischTab)) {
			window.tischTab.geklickt = true;
			window.stuhlTab.geklickt = false;
			window.schrankTab.geklickt = false;
		} else if (mc.inside(e.getX(), e.getY(), window.schrankTab)) {
			window.schrankTab.geklickt = true;
			window.stuhlTab.geklickt = false;
			window.tischTab.geklickt = false;
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		// wenn man über den mainTab-Button rüber geht mit der Maus
		if (mc.inside(e.getX(), e.getY(), window.mainTab)) {
			window.mainTab.hover = true;
		} else {
			window.mainTab.hover = false;
		}

		// wenn man über den stuhlTab-Button rüber geht mit der Maus
		if (mc.inside(e.getX(), e.getY(), window.stuhlTab)) {
			window.stuhlTab.hover = true;
		} else {
			window.stuhlTab.hover = false;
		}

		// wenn man über den tischTab-Button rüber geht mit der Maus
		if (mc.inside(e.getX(), e.getY(), window.tischTab)) {
			window.tischTab.hover = true;
		} else {
			window.tischTab.hover = false;
		}

		// wenn man über den schrankTab-Button rüber geht mit der Maus
		if (mc.inside(e.getX(), e.getY(), window.schrankTab)) {
			window.schrankTab.hover = true;
		} else {
			window.schrankTab.hover = false;
		}
	}
}
