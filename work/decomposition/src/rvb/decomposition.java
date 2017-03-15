package rvb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class decomposition {

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

			File rouge = new File("rouge" + nom + ".ppm");
			rouge.createNewFile();
			File bleu = new File("bleu" + nom + ".ppm");
			bleu.createNewFile();
			File vert = new File("vert" + nom + ".ppm");
			vert.createNewFile();

			FileWriter fwr = new FileWriter(rouge);
			FileWriter fwb = new FileWriter(bleu);
			FileWriter fwv = new FileWriter(vert);

			fwr.write(tmp);
			fwv.write(tmp);
			fwb.write(tmp);

			// System.out.println(rouge.exists());
			// System.out.println(bleu.exists());
			// System.out.println(vert.exists());

			while ((s = buff.readLine()) != null) {

				switch (loop()) {

				default:
				case 1:
					// r = r + s + " 0 0 \n";
					fwr.write(s + " 0 0 \n");
					break;
				case 2:
					// v = v + " 0 " + s + " 0 \n";
					fwv.write(" 0 " + s + " 0 \n");
					break;
				case 3:
					fwb.write(" 0 0 " + s + "\n");
					// b = b + " 0 0 " + s + "\n";
					break;
				}

			}
			fwr.close();
			fwv.close();
			fwb.close();
			buff.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
