package MainPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Datenbank.Database;
import Gui.Button;
import Gui.MouseHandler;
import Label.Draw;
import SchrankType.Akten;
import SchrankType.Buero;
import SchrankType.Rollschrank;
import SchrankType.Unterlagen;
import Speicherung.Speichern;
import StuhlType.Behindertengerecht;
import StuhlType.Hoehenverstellbar;
import StuhlType.Normal;
import Subclasses.Raum;
import TischType.Eck;
import TischType.Einer;
import TischType.Gruppen;
import TischType.Rund;
import TischType.Vierer;
import TischType.Zweier;

public class Window implements Runnable {

	private static final long serialVersionUID = -7045453969366999210L;

	// Variablen
	private JFrame frame;
	private boolean isRunning = false;
	private Thread thread;
	public static int temp = 0;
	public static int tempTisch = 0;
	public static int tempSchrank = 0;

	// Variablen für den Stuhl-Tab
	private JCheckBox behindertengerecht = new JCheckBox();
	private JCheckBox hoehenverstellbar = new JCheckBox();
	private JCheckBox normal = new JCheckBox();
	private JCheckBox behindertengerecht2 = new JCheckBox();
	private JCheckBox hoehenverstellbar2 = new JCheckBox();
	private JCheckBox normal2 = new JCheckBox();
	private JButton deleteStuhlButton = new JButton("Ok");
	private JButton deleteStuhl = new JButton("entfernen");
	private JComboBox<String> box = new JComboBox<String>();
	private JComboBox<String> box2 = new JComboBox<String>();
	private JButton addStuhlButton = new JButton();
	private JComboBox<String> box3 = new JComboBox<String>();

	// Variablen für den Tisch-Tab
	private JCheckBox eck = new JCheckBox();
	private JCheckBox einer = new JCheckBox();
	private JCheckBox gruppen = new JCheckBox();
	private JCheckBox rund = new JCheckBox();
	private JCheckBox vierer = new JCheckBox();
	private JCheckBox zweier = new JCheckBox();
	private JComboBox<String> boxTisch = new JComboBox<String>();
	private JButton addTischButton = new JButton();
	private JComboBox<String> boxTisch2 = new JComboBox<String>();
	private JButton deleteTisch = new JButton();
	private JComboBox<String> boxTisch3 = new JComboBox<String>();
	private JButton deleteTischButton = new JButton();

	// Variablen für den Schrank-Tab
	private JCheckBox akten = new JCheckBox();
	private JCheckBox buero = new JCheckBox();
	private JCheckBox rollschrank = new JCheckBox();
	private JCheckBox unterlagen = new JCheckBox();
	private JComboBox<String> boxSchrank = new JComboBox<String>();
	private JButton addSchrankButton = new JButton();
	private JComboBox<String> boxSchrank2 = new JComboBox<String>();
	private JButton deleteSchrank = new JButton();
	private JComboBox<String> boxSchrank3 = new JComboBox<String>();
	private JButton deleteSchrank2 = new JButton();

	// Variablen für die Raum-Tabelle
	private Object[] descriptions;
	private Object[][] data;
	private JTable tableRaum;
	private JScrollPane spRaum;
	private DefaultTableModel modelRaum;

	// Buttons / Instanzen, die mithilfe der paintComponent vom JLabel auf dem frame
	// gemalt werden können
	public Button mainTab, stuhlTab, schrankTab, tischTab;

	// Instanz
	private Draw draw;
	public ArrayList<Raum> raum = new ArrayList<Raum>();
	private Speichern speichern = new Speichern();
	private Database database = new Database();

	// Konstruktor
	public Window() {
		tableRaum = new JTable();
		spRaum = new JScrollPane(tableRaum);

		// die start() Methode wird aufgerufen, indem dann der Thread startet
		start();
	}

	public void initButtons() {
		// Button-Tabs
		mainTab = new Button(0, 0, Main.breite / 2 - 50, 120, false, false);
		stuhlTab = new Button(Main.breite / 2 - 50, 0, 266, 120, false, false);
		tischTab = new Button(Main.breite / 2 - 50 + 266, 0, 266, 120, false, false);
		schrankTab = new Button(Main.breite / 2 - 50 + 532, 0, 266, 120, false, false);
	}

	public void initArrayList() {

		File file = new File("D:\\Downloads\\Save Data\\save.txt");

		// hier würden die gespeicherten Objekte der ArrayList wieder geladen werden
		/*
		 * if (file.exists()) { for (Raum raum : speichern.loadData()) {
		 * this.raum.add(new Raum(raum.getNummer(), raum.getStockwerk())); } }
		 */

	}

	public void createRaumTabelle() {
		// Raum-Tabelle erstellen

		// Header
		descriptions = new Object[] { "Nummer", "Stockwerk", "Anzahl Stühle", "Anzahl Tische", "Anzahl Schränke" };

		data = new Object[raum.size()][];

		// Body
		for (int i = 0; i < raum.size(); i++) {
			data[i] = new Object[] { i + 1 + ". " + raum.get(i).getNummer(), raum.get(i).getStockwerk(),
					raum.get(i).getAnzahlStuhl(), raum.get(i).getAnzahlTisch(), raum.get(i).getAnzahlSchrank() };
		}

		tableRaum.getTableHeader().setBackground(new Color(6, 28, 54));
		tableRaum.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tableRaum.getTableHeader().setForeground(new Color(162, 203, 252));
		tableRaum.setRowHeight(25);
		tableRaum.setRowSelectionAllowed(true);
		tableRaum.setFont(new Font("Arial", Font.PLAIN, 15));

		modelRaum = new DefaultTableModel(data, descriptions);
		tableRaum.setModel(modelRaum);
		modelRaum.fireTableDataChanged();
		spRaum.setBounds(10, 150, 680, 300);

	}

	public void createStuhlTab() {
		// diese Methode wird beim Thread aufgerufen, um zu prüfen, welcher Tab
		// angeklickt wurde um dann bestimmte Objekte sichtbar oder unsichtbar zu
		// schalten
		if (stuhlTab.geklickt) {
			behindertengerecht.setVisible(true);
			hoehenverstellbar.setVisible(true);
			normal.setVisible(true);
			box.setVisible(true);
			addStuhlButton.setVisible(true);
			box2.setVisible(true);
			behindertengerecht2.setVisible(true);
			hoehenverstellbar2.setVisible(true);
			normal2.setVisible(true);
			deleteStuhlButton.setVisible(true);

			if (temp == 1) {
				box3.setVisible(true);
				deleteStuhl.setVisible(true);
			} else {
				deleteStuhl.setVisible(false);
			}

		} else {
			behindertengerecht.setVisible(false);
			hoehenverstellbar.setVisible(false);
			normal.setVisible(false);
			box.setVisible(false);
			addStuhlButton.setVisible(false);
			box2.setVisible(false);
			behindertengerecht2.setVisible(false);
			hoehenverstellbar2.setVisible(false);
			normal2.setVisible(false);
			deleteStuhlButton.setVisible(false);
			box3.setVisible(false);
			deleteStuhl.setVisible(false);
		}

	}

	public void createTischTab() {
		// diese Methode wird beim Thread aufgerufen, um zu prüfen, welcher Tab
		// angeklickt wurde um dann bestimmte Objekte sichtbar oder unsichtbar zu
		// schalten
		if (tischTab.geklickt) {
			eck.setVisible(true);
			einer.setVisible(true);
			gruppen.setVisible(true);
			rund.setVisible(true);
			vierer.setVisible(true);
			zweier.setVisible(true);
			boxTisch.setVisible(true);
			addTischButton.setVisible(true);
			boxTisch2.setVisible(true);
			deleteTisch.setVisible(true);
			if (tempTisch == 1) {
				boxTisch3.setVisible(true);
				deleteTischButton.setVisible(true);
			} else {
				deleteTischButton.setVisible(false);
			}

		} else {
			eck.setVisible(false);
			einer.setVisible(false);
			gruppen.setVisible(false);
			rund.setVisible(false);
			vierer.setVisible(false);
			zweier.setVisible(false);
			boxTisch.setVisible(false);
			addTischButton.setVisible(false);
			boxTisch2.setVisible(false);
			deleteTisch.setVisible(false);
			boxTisch3.setVisible(false);
			deleteTischButton.setVisible(false);
		}
	}

	public void createSchrankTab() {
		// diese Methode wird beim Thread aufgerufen, um zu prüfen, welcher Tab
		// angeklickt wurde um dann bestimmte Objekte sichtbar oder unsichtbar zu
		// schalten
		if (schrankTab.geklickt) {
			akten.setVisible(true);
			buero.setVisible(true);
			rollschrank.setVisible(true);
			unterlagen.setVisible(true);
			boxSchrank.setVisible(true);
			addSchrankButton.setVisible(true);
			boxSchrank2.setVisible(true);
			deleteSchrank.setVisible(true);

			if (tempSchrank == 1) {
				boxSchrank3.setVisible(true);
				deleteSchrank2.setVisible(true);
			} else {
				deleteSchrank2.setVisible(false);
			}
		} else {
			akten.setVisible(false);
			buero.setVisible(false);
			rollschrank.setVisible(false);
			unterlagen.setVisible(false);
			boxSchrank.setVisible(false);
			addSchrankButton.setVisible(false);
			boxSchrank2.setVisible(false);
			deleteSchrank.setVisible(false);
			boxSchrank3.setVisible(false);
			deleteSchrank2.setVisible(false);
		}
	}

	public void createRaumOnFrame() {
		JTextField nummerfeld = new JTextField();
		nummerfeld.setBounds(25, 490, 300, 30);
		nummerfeld.setText("Nummer vom Raum");
		nummerfeld.setFont(new Font("Arial", Font.PLAIN, 14));
		nummerfeld.setVisible(true);
		frame.add(nummerfeld);

		JTextField stockwerkfeld = new JTextField();
		stockwerkfeld.setBounds(25, 570, 300, 30);
		stockwerkfeld.setText("Stockwerk vom Raum");
		stockwerkfeld.setFont(new Font("Arial", Font.PLAIN, 14));
		stockwerkfeld.setVisible(true);
		frame.add(stockwerkfeld);

		JButton addRaum = new JButton("add Raum");
		addRaum.setFont(new Font("Arial", Font.BOLD, 15));
		addRaum.setBackground(new Color(35, 99, 107));
		addRaum.setForeground(Color.WHITE);
		addRaum.setBounds((Main.breite / 8) - 40 - 50, 660, 150, 50);
		addRaum.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// prüfen, ob die gewünschten Angaben stimmen, bzw. ob Zahlen eingegeben wurden
				String string1 = nummerfeld.getText();
				String string2 = stockwerkfeld.getText();

				if (string1 != null && string2 != null) {
					try {
						int int1 = Integer.parseInt(string1);
						int int2 = Integer.parseInt(string2);

						// wenn das gelingt, dann ändere die Farbe auf grün
						addRaum.setBackground(Color.GREEN);
					} catch (Exception e1) {
						addRaum.setBackground(Color.RED);
					}
				} else {
					addRaum.setBackground(Color.RED);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		addRaum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nummerString = nummerfeld.getText();
				String stockwerkString = stockwerkfeld.getText();

				nummerfeld.setText("Nummer vom Raum");
				stockwerkfeld.setText("Stockwerk vom Raum");

				try {
					int nummerInt = Integer.parseInt(nummerString);
					int stockwerkInt = Integer.parseInt(stockwerkString);

					Raum raum2 = new Raum(nummerInt, stockwerkInt);

					raum.add(raum2);

					// die Nummer und das Stockwerk in die Datenbank hinzufügen
					database.ConnectDatabase(nummerInt, stockwerkInt, 0, 0, 0);

					createRaumTabelle();

					int indexNeu = raum.size();
					try {
						String stringIndex = String.valueOf("Index:" + indexNeu);

						// den neuen Index vom neuen Raum in die ganzen JComboBoxen eintragen
						box.addItem(stringIndex);
						box2.addItem(stringIndex);
						boxTisch.addItem(stringIndex);
						boxTisch2.addItem(stringIndex);
						boxSchrank.addItem(stringIndex);
						boxSchrank2.addItem(stringIndex);

						box.repaint();

						frame.repaint();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					frame.repaint();

				} catch (Exception e1) {
					System.out.println("Falsche Eingabe");
				}
			}
		});
		frame.add(addRaum);

	}

	public void removeRaumOnFrame() {
		JTextField indexfeld = new JTextField();
		// die -15 kommen dadurch zustande, da man die mitte von den beiden anderen
		// Textfeldern nehmen muss von der y-Achse. Dann vom ersten die y-Achse + die
		// hoehe und dann die Differenz von dem anderem Textfeld, somit man die Hoehe
		// abzieht
		indexfeld.setBounds(375, 545 - 15, 300, 30);
		indexfeld.setText("Index vom Raum");
		indexfeld.setFont(new Font("Arial", Font.PLAIN, 14));
		indexfeld.setVisible(true);
		frame.add(indexfeld);

		JButton removeRaum = new JButton("remove Raum");
		removeRaum.setFont(new Font("Arial", Font.BOLD, 15));
		removeRaum.setBackground(new Color(35, 99, 107));
		removeRaum.setForeground(Color.WHITE);
		removeRaum.setBounds((Main.breite / 3) - 55, 660, 150, 50);
		removeRaum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String indexString = indexfeld.getText();

				indexfeld.setText("Index vom Raum");

				try {
					int index = Integer.parseInt(indexString);
					int indexRichtig = index - 1;

					// prüfen, ob der Index vorhanden ist
					if (indexRichtig >= 0 && indexRichtig < raum.size()) {
						raum.remove(indexRichtig);

						// Raum bzw. den Datensatz in der Datenbank entfernen
						database.DeleteDatensatz(indexRichtig + 1);

						// Ein Item von den JCheckBoxen entfernen
						box.removeItemAt(indexRichtig);
						box2.removeItemAt(indexRichtig);
						boxTisch.removeItemAt(indexRichtig);
						boxTisch2.removeItemAt(indexRichtig);
						boxSchrank.removeItemAt(indexRichtig);
						boxSchrank2.removeItemAt(indexRichtig);

					} else {
						System.out.println(
								"Diesen Index gibt es nicht. Tipp: Der Index steht vor dem Punkt bei der Kategorie Nummer. Der Index fängt in dem Fall bei 1 an!");
					}

					// Raum-Tabelle wird geupdated
					createRaumTabelle();

					frame.repaint();

				} catch (Exception e1) {
					System.out.println("Falsche Eingabe");
				}
			}
		});
		frame.add(removeRaum);

	}

	public void DeleteStuhlNichtBeimThread() {
		for (int i = 0; i < raum.size(); i++) {
			box2.addItem("Index:" + (i + 1));
		}

		box2.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170, 200, 170, 40);
		box2.setFont(new Font("Arial", Font.BOLD, 16));
		frame.add(box2);

		deleteStuhlButton.setFont(new Font("Arial", Font.BOLD, 16));
		deleteStuhlButton.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170, 300, 170, 40);
		deleteStuhlButton.setBackground(new Color(16, 87, 64));
		deleteStuhlButton.setForeground(Color.WHITE);

		deleteStuhlButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Index wird von der JComboBox entnommen und in eine Variable verpackt

				String indexRaumString = (String) box2.getSelectedItem();
				String indexRaum = indexRaumString.split(":")[1];

				int indexFast = Integer.parseInt(indexRaum);
				int index = indexFast - 1;

				if (raum.get(index).getAnzahlStuhl() > 0) {
					temp = 1;
					box3.setFont(new Font("Arial", Font.BOLD, 17));
					box3.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170, 420, 170, 40);

					// die Items müssen erstmal wieder gelöscht werden, damit nicht die doppelte
					// Anzahl von Items in der JComboBox sind
					box3.removeAllItems();

					for (int i = 0; i < raum.get(index).getAnzahlStuhl(); i++) {
						box3.addItem(i + ": " + raum.get(index).getStuhl().get(i).getType());
					}

					deleteStuhl.setFont(new Font("Arial", Font.BOLD, 20));
					deleteStuhl.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170, Main.hoehe - 200,
							170, 40);
					deleteStuhl.setForeground(Color.WHITE);
					deleteStuhl.setBackground(new Color(16, 87, 64));

					// es sollen nicht mehrere ActionListener fähig sein, weshalb der letztere
					// removed werden muss
					for (ActionListener a : deleteStuhl.getActionListeners()) {
						deleteStuhl.removeActionListener(a);
					}

					deleteStuhl.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String indexTypeString = (String) box3.getSelectedItem();
							String indexType = indexTypeString.split(":")[0];

							int indexTypeInt = Integer.parseInt(indexType);

							// bestimmten Stuhl-Type löschen
							raum.get(index).getStuhl().remove(indexTypeInt);

							// die Anzahl der Stühle um 1 verringern
							raum.get(index).setAnzahlStuhl(raum.get(index).getAnzahlStuhl() - 1);

							// Datensatz in der Datenbank geupdated
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlStuhl(), 0);

							box3.repaint();

							// die Methode aufrufen, damit die Raum-Tabelle geupdated wird
							createRaumTabelle();

							box3.setVisible(false);
							temp = 0;
						}
					});
				}

			}
		});
		frame.add(deleteStuhlButton);

	}

	public void DeleteSchrankNichtBeimThread() {
		for (int i = 0; i < raum.size(); i++) {
			boxSchrank2.addItem("Index:" + (i + 1));
		}
		boxSchrank2.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170, 200, 170, 40);
		boxSchrank2.setFont(new Font("Arial", Font.BOLD, 16));
		frame.add(boxSchrank2);

		deleteSchrank.setFont(new Font("Arial", Font.BOLD, 16));
		deleteSchrank.setText("Ok");
		deleteSchrank.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170, 300, 170, 40);
		deleteSchrank.setBackground(new Color(16, 87, 64));
		deleteSchrank.setForeground(Color.WHITE);
		deleteSchrank.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String indexRaumString = (String) boxSchrank2.getSelectedItem();
				String indexRaum = indexRaumString.split(":")[1];

				int indexFast = Integer.parseInt(indexRaum);
				int index = indexFast - 1;

				if (raum.get(index).getAnzahlSchrank() > 0) {
					tempSchrank = 1;

					boxSchrank3.setFont(new Font("Arial", Font.BOLD, 17));
					boxSchrank3.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170, 420, 170, 40);

					// die Items müssen erstmal wieder gelöscht werden, damit nicht die doppelte
					// Anzahl von Items in der JComboBox sind
					boxSchrank3.removeAllItems();

					for (int i = 0; i < raum.get(index).getAnzahlSchrank(); i++) {
						boxSchrank3.addItem(i + ": " + raum.get(index).getSchrank().get(i).getType());
					}

					deleteSchrank2.setFont(new Font("Arial", Font.BOLD, 20));
					deleteSchrank2.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170,
							Main.hoehe - 160, 170, 40);
					deleteSchrank2.setForeground(Color.WHITE);
					deleteSchrank2.setText("entfernen");
					deleteSchrank2.setBackground(new Color(16, 87, 64));

					for (ActionListener a : deleteSchrank2.getActionListeners()) {
						deleteSchrank2.removeActionListener(a);
					}

					deleteSchrank2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String indexTypeString = (String) boxSchrank3.getSelectedItem();
							String indexType = indexTypeString.split(":")[0];

							int indexTypeInt = Integer.parseInt(indexType);

							// bestimmten Schrank-Type löschen
							raum.get(index).getSchrank().remove(indexTypeInt);

							// die Anzahl der Schränke um 1 verringern
							raum.get(index).setAnzahlSchrank(raum.get(index).getAnzahlSchrank() - 1);

							// Datensatz in der Datenbank geupdated
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlSchrank(), 2);

							boxSchrank3.repaint();

							// die Methode aufrufen, damit die Raum-Tabelle geupdated wird
							createRaumTabelle();

							boxSchrank3.setVisible(false);
							tempSchrank = 0;
						}
					});

				}
			}
		});
		frame.add(deleteSchrank);
	}

	public void DeleteTischNichtBeimThread() {
		for (int i = 0; i < raum.size(); i++) {
			boxTisch2.addItem("Index:" + (i + 1));
		}

		boxTisch2.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170, 200, 170, 40);
		boxTisch2.setFont(new Font("Arial", Font.BOLD, 16));
		frame.add(boxTisch2);

		deleteTisch.setFont(new Font("Arial", Font.BOLD, 16));
		deleteTisch.setText("Ok");
		deleteTisch.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170, 300, 170, 40);
		deleteTisch.setBackground(new Color(16, 87, 64));
		deleteTisch.setForeground(Color.WHITE);

		deleteTisch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String indexRaumString = (String) boxTisch2.getSelectedItem();
				String indexRaum = indexRaumString.split(":")[1];

				int indexFast = Integer.parseInt(indexRaum);
				int index = indexFast - 1;

				if (raum.get(index).getAnzahlTisch() > 0) {
					tempTisch = 1;

					boxTisch3.setFont(new Font("Arial", Font.BOLD, 17));
					boxTisch3.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170, 420, 170, 40);

					// die Items müssen erstmal wieder gelöscht werden, damit nicht die doppelte
					// Anzahl von Items in der JComboBox sind
					boxTisch3.removeAllItems();

					for (int i = 0; i < raum.get(index).getAnzahlTisch(); i++) {
						boxTisch3.addItem(i + ": " + raum.get(index).getTisch().get(i).getType());
					}

					deleteTischButton.setFont(new Font("Arial", Font.BOLD, 20));
					deleteTischButton.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 + 170,
							Main.hoehe - 160, 170, 40);
					deleteTischButton.setForeground(Color.WHITE);
					deleteTischButton.setBackground(new Color(16, 87, 64));
					deleteTischButton.setText("entfernen");

					for (ActionListener a : deleteTischButton.getActionListeners()) {
						deleteTischButton.removeActionListener(a);
					}

					deleteTischButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String indexTypeString = (String) boxTisch3.getSelectedItem();
							String indexType = indexTypeString.split(":")[0];

							int indexTypeInt = Integer.parseInt(indexType);

							// bestimmten Tisch-Type löschen
							raum.get(index).getTisch().remove(indexTypeInt);

							// die Anzahl der Tische um 1 verringern
							raum.get(index).setAnzahlTisch(raum.get(index).getAnzahlTisch() - 1);

							// Datensatz in der Datenbank geupdated
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlTisch(), 1);

							boxTisch3.repaint();

							// die Methode aufrufen, damit die Raum-Tabelle geupdated wird
							createRaumTabelle();

							boxTisch3.setVisible(false);
							tempTisch = 0;
						}
					});
				}
			}
		});
		frame.add(deleteTisch);
	}

	public void SchrankTabNichtBeimThread() {
		// diese Methode wird nicht permanent aufgerufen, da sie nicht in der run
		// methode vom Interface vom Thread drin steht

		JCheckBoxVorlage(akten, "Akten", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 200, frame);
		JCheckBoxVorlage(buero, "Büro", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 241, frame);
		JCheckBoxVorlage(rollschrank, "Rollschrank", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 282,
				frame);
		JCheckBoxVorlage(unterlagen, "Unterlagen", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 323,
				frame);

		// wenn eine JComboBox selected ist, sollen die anderen nicht mehr selected sein
		akten.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (akten.isSelected()) {
					buero.setSelected(false);
					rollschrank.setSelected(false);
					unterlagen.setSelected(false);
				}
			}
		});

		buero.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (buero.isSelected()) {
					akten.setSelected(false);
					rollschrank.setSelected(false);
					unterlagen.setSelected(false);
				}
			}
		});

		rollschrank.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rollschrank.isSelected()) {
					akten.setSelected(false);
					buero.setSelected(false);
					unterlagen.setSelected(false);
				}
			}
		});

		unterlagen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (unterlagen.isSelected()) {
					akten.setSelected(false);
					buero.setSelected(false);
					rollschrank.setSelected(false);
				}
			}
		});

		// JCheckBox adden
		for (int i = 0; i < raum.size(); i++) {
			boxSchrank.addItem("Index:" + (i + 1));
		}
		boxSchrank.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 460, 170, 40);
		boxSchrank.setFont(new Font("Arial", Font.BOLD, 16));
		frame.add(boxSchrank);

		addSchrankButton.setText("hinzufügen");
		addSchrankButton.setForeground(Color.WHITE);
		addSchrankButton.setFont(new Font("Arial", Font.BOLD, 20));
		addSchrankButton.setBackground(new Color(16, 87, 64));
		addSchrankButton.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, Main.hoehe - 160, 170,
				40);
		addSchrankButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String type = null;

				if (akten.isSelected()) {
					type = "Akten";
				} else if (buero.isSelected()) {
					type = "Büro";
				} else if (rollschrank.isSelected()) {
					type = "Rollschrank";
				} else if (unterlagen.isSelected()) {
					type = "Unterlagen";
				} else {
					System.out.println("Geben Sie ein Type an");
				}

				String indexRaumString = (String) boxSchrank.getSelectedItem();
				String indexRaum = indexRaumString.split(":")[1];

				int indexFast = Integer.parseInt(indexRaum);
				int index = indexFast - 1;

				if (type != null) {
					if (index >= 0 && index < raum.size()) {
						if (type.equals("Akten")) {
							raum.get(index).addSchrank(new Akten("Akten"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlSchrank(), 2);
						} else if (type.equals("Büro")) {
							raum.get(index).addSchrank(new Buero("Büro"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlSchrank(), 2);
						} else if (type.equals("Rollschrank")) {
							raum.get(index).addSchrank(new Rollschrank("Rollschrank"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlSchrank(), 2);
						} else if (type.equals("Unterlagen")) {
							raum.get(index).addSchrank(new Unterlagen("Unterlagen"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlSchrank(), 2);
						}

						// Raum-Tabelle muss geupdated werden
						createRaumTabelle();

						// wieder deselektieren
						akten.setSelected(false);
						buero.setSelected(false);
						rollschrank.setSelected(false);
						unterlagen.setSelected(false);

					}
				}
			}
		});
		frame.add(addSchrankButton);
	}

	public void TischTabNichtBeimThread() {
		JCheckBoxVorlage(eck, "Eck", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 200, frame);
		JCheckBoxVorlage(einer, "Einer", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 241, frame);
		JCheckBoxVorlage(gruppen, "Gruppen", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 282, frame);
		JCheckBoxVorlage(rund, "Rund", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 323, frame);
		JCheckBoxVorlage(vierer, "Vierer", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 364, frame);
		JCheckBoxVorlage(zweier, "Zweier", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 405, frame);

		// wenn eine JComboBox selected ist, sollen die anderen nicht mehr selected sein
		eck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (eck.isSelected()) {
					einer.setSelected(false);
					gruppen.setSelected(false);
					rund.setSelected(false);
					vierer.setSelected(false);
					zweier.setSelected(false);
				}
			}
		});

		einer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (einer.isSelected()) {
					eck.setSelected(false);
					gruppen.setSelected(false);
					rund.setSelected(false);
					vierer.setSelected(false);
					zweier.setSelected(false);
				}
			}
		});

		gruppen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gruppen.isSelected()) {
					eck.setSelected(false);
					einer.setSelected(false);
					rund.setSelected(false);
					vierer.setSelected(false);
					zweier.setSelected(false);
				}
			}
		});

		rund.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rund.isSelected()) {
					eck.setSelected(false);
					einer.setSelected(false);
					gruppen.setSelected(false);
					vierer.setSelected(false);
					zweier.setSelected(false);
				}
			}
		});

		vierer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vierer.isSelected()) {
					eck.setSelected(false);
					einer.setSelected(false);
					gruppen.setSelected(false);
					rund.setSelected(false);
					zweier.setSelected(false);
				}
			}
		});

		zweier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (zweier.isSelected()) {
					eck.setSelected(false);
					einer.setSelected(false);
					gruppen.setSelected(false);
					rund.setSelected(false);
					vierer.setSelected(false);
				}
			}
		});

		// JCheckBox adden
		for (int i = 0; i < raum.size(); i++) {
			boxTisch.addItem("Index:" + (i + 1));
		}
		boxTisch.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 540, 170, 40);
		boxTisch.setFont(new Font("Arial", Font.BOLD, 16));
		frame.add(boxTisch);

		addTischButton.setText("hinzufügen");
		addTischButton.setForeground(Color.WHITE);
		addTischButton.setFont(new Font("Arial", Font.BOLD, 20));
		addTischButton.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, Main.hoehe - 160, 170, 40);
		addTischButton.setBackground(new Color(16, 87, 64));
		addTischButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String type = null;

				// in eine Variable vom Typ String wird eingetragen, welchen Type man angegeben
				// hat

				if (eck.isSelected()) {
					type = "Eck";
				} else if (einer.isSelected()) {
					type = "Einer";
				} else if (gruppen.isSelected()) {
					type = "Gruppen";
				} else if (rund.isSelected()) {
					type = "Rund";
				} else if (vierer.isSelected()) {
					type = "Vierer";
				} else if (zweier.isSelected()) {
					type = "Zweier";
				} else {
					System.out.println("Geben Sie ein Type an");
				}

				String indexRaumString = (String) boxTisch.getSelectedItem();
				String indexRaum = indexRaumString.split(":")[1];

				int indexFast = Integer.parseInt(indexRaum);
				int index = indexFast - 1;

				if (type != null) {
					// somit wird dann der Type in die ArrayList eingetragen von dem bestimmten
					// Raum, der individuell angegeben werden kann
					if (index >= 0 && index < raum.size()) {
						if (type.equals("Eck")) {
							raum.get(index).addTisch(new Eck("Eck"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlTisch(), 1);
						} else if (type.equals("Einer")) {
							raum.get(index).addTisch(new Einer("Einer"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlTisch(), 1);
						} else if (type.equals("Gruppen")) {
							raum.get(index).addTisch(new Gruppen("Gruppen"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlTisch(), 1);
						} else if (type.equals("Rund")) {
							raum.get(index).addTisch(new Rund("Rund"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlTisch(), 1);
						} else if (type.equals("Vierer")) {
							raum.get(index).addTisch(new Vierer("Vierer"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlTisch(), 1);
						} else if (type.equals("Zweier")) {
							raum.get(index).addTisch(new Zweier("Zweier"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlTisch(), 1);
						}

						createRaumTabelle();
						eck.setSelected(false);
						einer.setSelected(false);
						gruppen.setSelected(false);
						rund.setSelected(false);
						vierer.setSelected(false);
						zweier.setSelected(false);
					}
				}
			}
		});
		frame.add(addTischButton);
	}

	public void StuhlTabNichtBeimThread() {
		JCheckBoxVorlage(behindertengerecht, "Behindertengerecht",
				(Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 200, frame);
		JCheckBoxVorlage(hoehenverstellbar, "Hoehenverstellbar",
				(Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 241, frame);
		JCheckBoxVorlage(normal, "Normal", (Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 282, frame);

		// ActionListener (falls eine Box selected ist, soll es nicht möglich sein die
		// anderen Boxen zu selektieren)
		behindertengerecht.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (behindertengerecht.isSelected()) {
					hoehenverstellbar.setSelected(false);
					normal.setSelected(false);
				}
			}
		});

		hoehenverstellbar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (hoehenverstellbar.isSelected()) {
					behindertengerecht.setSelected(false);
					normal.setSelected(false);
				}
			}
		});

		normal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (normal.isSelected()) {
					behindertengerecht.setSelected(false);
					hoehenverstellbar.setSelected(false);
				}
			}
		});

		for (int i = 0; i < raum.size(); i++) {
			box.addItem("Index:" + (i + 1));
		}

		box.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, 420, 170, 40);
		box.setFont(new Font("Arial", Font.BOLD, 16));
		frame.add(box);

		addStuhlButton.setText("hinzufügen");
		addStuhlButton.setForeground(Color.WHITE);
		addStuhlButton.setFont(new Font("Arial", Font.BOLD, 20));
		addStuhlButton.setBounds((Main.breite / 2 - 50) + (Main.breite / 2 - 50) / 2 - 250, Main.hoehe - 200, 170, 40);
		addStuhlButton.setBackground(new Color(16, 87, 64));
		addStuhlButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String type = null;

				if (behindertengerecht.isSelected()) {
					type = "Behindertengerecht";
				} else if (hoehenverstellbar.isSelected()) {
					type = "Hoehenverstellbar";
				} else if (normal.isSelected()) {
					type = "Normal";
				} else {
					System.out.println("Geben Sie ein Type an");
				}

				String indexRaumString = (String) box.getSelectedItem();
				String indexRaum = indexRaumString.split(":")[1];

				int indexFast = Integer.parseInt(indexRaum);
				int index = indexFast - 1;

				if (type != null) {
					if (index >= 0 && index < raum.size()) {
						if (type.equals("Behindertengerecht")) {
							raum.get(index).addStuhl(new Behindertengerecht("Behindertengerecht"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlStuhl(), 0);
						} else if (type.equals("Hoehenverstellbar")) {
							raum.get(index).addStuhl(new Hoehenverstellbar("Hoehenverstellbar"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlStuhl(), 0);
						} else if (type.equals("Normal")) {
							raum.get(index).addStuhl(new Normal("Normal"));
							database.UpdateDatabase(index + 1, raum.get(index).getAnzahlStuhl(), 0);
						}

						createRaumTabelle();
						behindertengerecht.setSelected(false);
						hoehenverstellbar.setSelected(false);
						normal.setSelected(false);
					}
				}
			}
		});
		frame.add(addStuhlButton);
	}

	public void JCheckBoxVorlage(JCheckBox box, String name, int x, int y, JFrame frame) {
		box.setBounds(x, y, 170, 40);
		box.setText(name);
		box.setSelected(false);
		frame.add(box);
	}

	public void createFrame(int width, int height, String title) {

		initArrayList();
		initButtons();

		frame = new JFrame(title);

		frame.setSize(width, height);
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.requestFocus();
		frame.addMouseListener(new MouseHandler(this));
		frame.addMouseMotionListener(new MouseHandler(this));
		frame.getContentPane().setBackground(new Color(24, 67, 94));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				speichern.saveData(raum);
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});

		createRaumTabelle();
		StuhlTabNichtBeimThread();
		TischTabNichtBeimThread();
		SchrankTabNichtBeimThread();
		DeleteStuhlNichtBeimThread();
		DeleteTischNichtBeimThread();
		DeleteSchrankNichtBeimThread();
		removeRaumOnFrame();
		createRaumOnFrame();

		frame.add(spRaum);
		frame.add(box3);
		frame.add(deleteStuhl);
		frame.add(boxTisch3);
		frame.add(deleteTischButton);
		frame.add(deleteSchrank2);
		frame.add(boxSchrank3);

		// paint Komponente zum Fenster hinzufügen
		draw = new Draw(this);
		draw.setBounds(0, 0, width, height);
		draw.setVisible(true);
		draw.addMouseListener(new MouseHandler(this));
		draw.addMouseMotionListener(new MouseHandler(this));
		frame.add(draw);

		// frame sichtbar machen
		frame.setVisible(true);

	}

	public void start() {
		// Thread starten
		if (isRunning) {
			return;
		}

		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	public void stop() {
		if (!isRunning) {
			return;
		}

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		// thread-loop
		// das wird benötigt, damit Methoden durchgehend aufgerufen werden können
		long lastTime = System.nanoTime();
		double fps = 30.0;
		double ns = 1000000000 / fps;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				createStuhlTab();
				createTischTab();
				createSchrankTab();
				delta--;
			}

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

}
