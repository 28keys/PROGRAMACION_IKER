package ej12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Intoduce nombre del fichero");
		String nombre = sc.nextLine();
		System.out.println("Introduce palabra");
		String palabra = sc.nextLine();
		Path fichero = Paths.get(nombre);
		Path otroFichero = Paths.get("fichero_nuevo.txt"); // recuerda poner la etiqueta, en este caso .txt

		if (!Files.isRegularFile(fichero)) {
			System.out.println("No es un fichero");
		} else {
			escribirEnOtro(fichero, otroFichero, palabra);

		}

	}

	public static void escribirEnOtro(Path fichero, Path otroFichero, String palabra) {
		Charset charset = Charset.forName("UTF-8");
		try {
			BufferedReader reader = Files.newBufferedReader(fichero, charset);
			BufferedWriter writer = Files.newBufferedWriter(otroFichero, charset, CREATE. TRUNCATE_EXISTING);
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.contains(palabra)) {
					writer.write(line);
					writer.newLine();
					System.out.println("Se escribió la linea en el fichero"); 	
					
					/*"En cada vuelta que dé el bucle while... 
					 * ¿voy a escribir algo en el archivo nuevo sí o sí,
					o puede que haya vueltas en las que no escriba absolutamente nada?"*/
				}
			}
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
