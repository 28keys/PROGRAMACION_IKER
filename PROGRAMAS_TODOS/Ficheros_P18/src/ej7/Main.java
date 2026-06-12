package ej7;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce nombre del archivo");
		String nombre = sc.nextLine(); //recuerda poner siempre la extension, archivo no, archivo.txt sí
		Path fichero = Paths.get(nombre);

		if (!Files.exists(fichero)) {
			System.out.println("No se encuentra el fichero en el directorio raíz");
		} else {

			Charset charset = Charset.forName("UTF-8");
			BufferedReader reader = null;
			int totalC = 0;

			try {

				reader = Files.newBufferedReader(fichero, charset);
				String line = null;

				while ((line = reader.readLine()) != null) {
					totalC += line.length();
					
				}
				reader.close();
				System.out.println("El arhivo " + fichero + " tiene " + totalC + " caracteres");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
