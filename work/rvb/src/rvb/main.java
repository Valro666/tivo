package rvb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {

	public static void main(String[] a) {

		try {

			String r = "", v = "", b = "";
			int ir, iv, ib;

			int larg, lon;

			File image = new File("arbreASCII.ppm");
			FileReader fr = new FileReader(image);
			BufferedReader buff = new BufferedReader(fr);

			System.out.println(buff.readLine());
			System.out.println(buff.readLine());
			System.out.println(buff.readLine());
			System.out.println(buff.readLine());

			while (buff.toString() != null) {

				ir = Integer.valueOf(buff.readLine());
				iv = Integer.valueOf(buff.readLine());
				ib = Integer.valueOf(buff.readLine());

				r = r + "\n" + ir;
				v = v + "\n" + iv;
				b = b + "\n" + ib;

			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
