package Speicherung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Subclasses.Raum;

public class Speichern {

	private static final long serialVersionUID = 8800137869883795826L;

	/*
	 * hier werden die Daten in externe Dateien gespeichert
	 */

	private String path = "D:\\Downloads\\Save Data";
	private String path2 = "D:\\Downloads\\Save Data\\save.txt";

	// Daten in eine externe Datei reinschreiben, um es somit abzuspeichern
	public void saveData(ArrayList<Raum> raum) {
		File file, ordner;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			// neuen Ordner erstellen, falls es den noch nicht gibt xD
			ordner = new File(path);

			if (!ordner.exists()) {
				ordner.mkdirs();
			}

			file = new File(path2);

			if (!file.exists()) {
				file.createNewFile();
			}

			fos = new FileOutputStream(path2);
			oos = new ObjectOutputStream(fos);

			// ganze ArrayList in die Textdatei reinschreiben, um das somit abzuspeichern
			oos.writeObject(raum);

		} catch (Exception e) {
			System.out.println("das geht leider nicht");
			e.printStackTrace();
		} finally {
			if (fos != null && oos != null) {
				try {
					fos.close();
					oos.close();
				} catch (Exception e) {
					System.out.println("Geht immernoch nicht");
					e.printStackTrace();
				}
			}
		}
	}

	// Daten laden, indem man eine Datei liest und die Infos der ArrayList
	// wiedergibt
	@SuppressWarnings("unchecked")
	public ArrayList<Raum> loadData() {

		File file = new File(path2);

		if (file.exists()) {
			ArrayList<Raum> raum = new ArrayList<Raum>();

			FileInputStream fis = null;
			ObjectInputStream ois = null;

			try {
				fis = new FileInputStream(file.getPath());
				ois = new ObjectInputStream(fis);

				raum = (ArrayList<Raum>) ois.readObject();

			} catch (Exception e) {
				System.out.println("geht nicht");
				e.printStackTrace();
			} finally {
				if (fis != null && ois != null) {
					try {
						fis.close();
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

			return raum;
		} else {
			return null;
		}
	}

}
