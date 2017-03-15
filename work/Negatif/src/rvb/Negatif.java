package rvb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Negatif {

	static int loop = 0;

	static int loop() {

		loop++;

		if (loop > 3) {
			loop = 1;
		}
		return loop;
	}

	public static void main(String[] a) {

		try {

			String tmp = "";
			File image = new File(a[0]);

			if (!image.exists()) {
				System.out.println("le fichier" + a[0] + " n existe pas");
				System.exit(0);
			}

			if (!image.getName().contains("ppm")) {
				System.out.println("le fichier n est pas un .ppm");
				System.exit(1);
			}

			String nom = image.getName().substring(0,
					image.getName().indexOf("."));

			// System.out.println(nom);

			FileReader fr = new FileReader(image);
			BufferedReader buff = new BufferedReader(fr);

			tmp = tmp + buff.readLine() + "\n" + buff.readLine() + "\n"
					+ buff.readLine() + "\n" + buff.readLine() + "\n";

			String s;

			// System.out.println(tmp);

			File rouge = new File("Negatif" + nom + ".ppm");
			rouge.createNewFile();

			FileWriter fwr = new FileWriter(rouge);

			fwr.write(tmp);

			int z = 0;

			while ((s = buff.readLine()) != null) {

				z = 255 - Integer.valueOf(s);

				fwr.write(z + "\n");

			}
			fwr.close();
			// fwv.close();
			// fwb.close();
			buff.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
