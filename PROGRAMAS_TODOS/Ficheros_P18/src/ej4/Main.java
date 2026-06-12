package ej4;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Intoduce carpeta");
		String carpeta = sc.nextLine();
		Path pCarpeta = Paths.get(carpeta);

		if (!Files.isDirectory(pCarpeta)) {
			System.out.println("No es una carpeta");
		} else {
			String letra;
			do {
				System.out.println("Introduce letra por la que empieza el fichero");
				letra = sc.nextLine();
			} while (!letra.matches("[a-zA-Z]{1}"));
			try {
				DirectoryStream<Path> stream = Files.newDirectoryStream(pCarpeta);
				for (Path s : stream) {
					String archivo = s.getFileName().toString();
					if (Files.isRegularFile(s) && archivo.toLowerCase().startsWith(letra.toLowerCase())) {
						/*pones to lower case tmb en el archivo por si en la letra pones N pero el archivo es notas.txt, 
						 * si los 2 estan en minusculas te evitas errores*/
						Files.deleteIfExists(s);
					}
				}
				stream.close();
				System.out.println("Archivo borrado con éxito");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sc.close();
	}

}
