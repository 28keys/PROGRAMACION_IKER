package ej5;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce nombre de la carpeta");
		String carpeta = sc.nextLine();
		Path pCarpeta = Paths.get(carpeta);
		if (!Files.isDirectory(pCarpeta)) {
			System.out.println("No es una carpeta");
		} else {
			try {
				boolean borrar = false;
				DirectoryStream<Path> stream = Files.newDirectoryStream(pCarpeta);
				int cont = 0;
				for (Path s : stream) {
					if (Files.isRegularFile(s)) {
						cont++; // Solo hay archivos
					}
				}
				stream.close();
				if (cont > 0) {
					System.out.println("La carpeta " + carpeta + " contiene " + cont
							+ " archivos.\n¿Deseas borrar la carpeta? (S/N)?");
					String decide = sc.nextLine();
					if (!decide.equalsIgnoreCase("S")) {
						System.out.println("Acción Denegada");
					} else {
						borrar = true;
						DirectoryStream<Path> stream2 = Files.newDirectoryStream(pCarpeta);
						for (Path s : stream2) {
							if (Files.isRegularFile(s)) {
								Files.deleteIfExists(s);
							}
						}
						stream2.close();
					}
				} else {
					borrar = true; // si no hay archivos borrar directamente el archivo
				}

				if (borrar) {
					Files.deleteIfExists(pCarpeta);
					System.out.println("Carpeta borrada");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
