package Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	/*
	 * mit diesem Code verbinde ich eine Datenbank mit mySQL mit der mySQL
	 * Workbench, um Daten in eine Datenbank abzuspeichern. Mir ist klar, dass diese
	 * Anwendung mit der Datenbank nur bei meinem Computer funktioniert, da es nicht
	 * mit einem Network läuft, aber ich wollte mal versuchen die Daten mit einer
	 * externen Datenbank zu verknüpfen.
	 */

	public void ConnectDatabase(int nummer, int stockwerk, int anzahlStuhl, int anzahlTisch, int anzahlSchrank) {

		int rs;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/möbelverwaltung?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
					"root", "password");
			// mein Passwort gebe ich nicht bekannt :D, deswegen steht dort nur "password"

			String query = "insert into raum(nummer, stockwerk, anzahlStuhl, anzahlTisch, anzahlSchrank)values("
					+ nummer + ", " + stockwerk + ",0,0,0);";

			stmt = con.createStatement();
			rs = stmt.executeUpdate(query);
			System.out.println("Raum wurde erfolgreich in die Datenbank hinzugefügt");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void DeleteDatensatz(int id) {
		int rs;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/möbelverwaltung?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
					"root", "password");
			// mein Passwort gebe ich nicht bekannt :D, deswegen steht dort nur "password"

			String query = "delete from raum where id = " + id + ";";

			stmt = con.createStatement();
			rs = stmt.executeUpdate(query);
			System.out.println("Der Raum/Datensatz wurde erfolgreich aus der Datenbank entfernt");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void UpdateDatabase(int id, int anzahl, int type) {

		/*
		 * type 0 = Stuhl type 1 = Tisch type 2 = Schrank
		 */

		int rs;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/möbelverwaltung?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
					"root", "password");
			// mein Passwort gebe ich nicht bekannt :D, deswegen steht dort nur "password"

			if (type == 0) {
				String query = "update raum set anzahlStuhl = " + anzahl + " where id = " + id + ";";

				stmt = con.createStatement();
				rs = stmt.executeUpdate(query);
				System.out.println("Die Anzahl wurde in der Datenbank erfolgreich bearbeitet...");

			} else if (type == 1) {

				String query = "update raum set anzahlTisch = " + anzahl + " where id = " + id + ";";

				stmt = con.createStatement();
				rs = stmt.executeUpdate(query);
				System.out.println("Die Anzahl wurde in der Datenbank erfolgreich bearbeitet...");

			} else if (type == 2) {

				String query = "update raum set anzahlSchrank = " + anzahl + " where id = " + id + ";";

				stmt = con.createStatement();
				rs = stmt.executeUpdate(query);
				System.out.println("Die Anzahl wurde in der Datenbank erfolgreich bearbeitet...");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Es konnte keine Connection zu der Datenbank hergestellt werden! :(");
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
