package Subclasses;

import java.io.Serializable;
import java.util.ArrayList;

import abstractClasses.Schrank;
import abstractClasses.Stuhl;
import abstractClasses.Tisch;

public class Raum implements Serializable {

	private static final long serialVersionUID = -2723816547403601619L;

	private int nummer, stockwerk, anzahlStuhl, anzahlTisch, anzahlSchrank;

	private ArrayList<Stuhl> stuhl = new ArrayList<Stuhl>();
	private ArrayList<Tisch> tisch = new ArrayList<Tisch>();
	private ArrayList<Schrank> schrank = new ArrayList<Schrank>();

	// Konstruktor, der als Parameter int nummer und int stockwerk hat, dh. dass bei
	// einer Objekterstellung von dieser Instanz 2 Werte übergeben werden müssen
	public Raum(int nummer, int stockwerk) {
		setNummer(nummer);
		setStockwerk(stockwerk);
	}

	// add und remove-Methoden
	public void addStuhl(Stuhl stuhl) {
		anzahlStuhl++;
		this.stuhl.add(stuhl);
	}

	public void removeStuhl(Stuhl stuhl) {
		anzahlStuhl--;
		this.stuhl.remove(stuhl);
	}

	public void addTisch(Tisch tisch) {
		anzahlTisch++;
		this.tisch.add(tisch);
	}

	public void removeTisch(Tisch tisch) {
		anzahlTisch--;
		this.tisch.remove(tisch);
	}

	public void addSchrank(Schrank schrank) {
		anzahlSchrank++;
		this.schrank.add(schrank);
	}

	public void removeSchrank(Schrank schrank) {
		anzahlSchrank--;
		this.schrank.remove(schrank);
	}

	// Setter und Getter
	public ArrayList<Stuhl> getStuhl() {
		return stuhl;
	}

	public void setStuhl(ArrayList<Stuhl> stuhl) {
		this.stuhl = stuhl;
	}

	public ArrayList<Tisch> getTisch() {
		return tisch;
	}

	public void setTisch(ArrayList<Tisch> tisch) {
		this.tisch = tisch;
	}

	public ArrayList<Schrank> getSchrank() {
		return schrank;
	}

	public void setSchrank(ArrayList<Schrank> schrank) {
		this.schrank = schrank;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public int getStockwerk() {
		return stockwerk;
	}

	public void setStockwerk(int stockwerk) {
		this.stockwerk = stockwerk;
	}

	public int getAnzahlStuhl() {
		return anzahlStuhl;
	}

	public void setAnzahlStuhl(int anzahlStuhl) {
		this.anzahlStuhl = anzahlStuhl;
	}

	public int getAnzahlTisch() {
		return anzahlTisch;
	}

	public void setAnzahlTisch(int anzahlTisch) {
		this.anzahlTisch = anzahlTisch;
	}

	public int getAnzahlSchrank() {
		return anzahlSchrank;
	}

	public void setAnzahlSchrank(int anzahlSchrank) {
		this.anzahlSchrank = anzahlSchrank;
	}

}
