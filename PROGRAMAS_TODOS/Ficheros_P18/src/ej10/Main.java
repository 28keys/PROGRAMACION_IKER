package ej10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Charset charset = Charset.forName("UTF-8");
		System.out.println("Introduce nombre del fichero");
		String nombre = sc.nextLine();
		HashSet<Character> vocales = new HashSet<Character>();
		vocales.add('a');
		vocales.add('e');
		vocales.add('i');
		vocales.add('o');
		vocales.add('u');

		Path fichero = Paths.get(nombre);
		Path fichero2 = Paths.get("poema_sin_vocales.txt"); // hay q crearlo aunq no exista, el CREATE ya lo
															// crea

		if (!Files.isRegularFile(fichero)) {
			System.out.println("No se encontró el fichero");
		} else {
			try {
				BufferedReader reader = Files.newBufferedReader(fichero, charset);
				BufferedWriter writer = Files.newBufferedWriter(fichero2, charset, CREATE, TRUNCATE_EXISTING);
				/*TRUNCATE_EXISTING es por si ya estaba creado el archivo, limpia el archivo y ponelo lo nuevo con el
				 * writer*/
				String line = null;
				while ((line = reader.readLine()) != null) {
					for (int i = 0; i < line.length(); i++) {
						char letraOriginal = line.charAt(i);
						if (!vocales.contains(Character.toLowerCase(letraOriginal))) {
							writer.write(letraOriginal);

						}
					}
					writer.newLine();
				}
				reader.close();
				writer.close();
				System.out.println("Archivo " + fichero2 + " creado");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
