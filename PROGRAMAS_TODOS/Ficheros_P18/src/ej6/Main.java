package ej6;

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
				int contA = 0;
				int contB = 0;
				for (Path s : stream) {

					if (Files.isRegularFile(s)) {
						contA++;
					}
					if (Files.isDirectory(s)) {
						contB++;
					}

				}
				stream.close();
				if (contA > 0 || contB > 0) {
					System.out.println("La carpeta " + carpeta + " contiene " + contA + " archivos y " + contB
							+ " subdirectorios.\n¿Deseas borrar la carpeta? (S/N)?");
					String decide = sc.nextLine();
					if (!decide.equalsIgnoreCase("S")) {
						System.out.println("Acción Denegada");
					} else {
						vaciarCarpetaRecursiva(pCarpeta);
					}
				} else {
					Files.deleteIfExists(pCarpeta);
					System.out.println("Carpeta borrada"); // esta seria la carpeta principal
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static void vaciarCarpetaRecursiva(Path ruta) {
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(ruta);
			for (Path s : stream) {
				if (Files.isRegularFile(s)) {
					Files.deleteIfExists(s);

				} else if (Files.isDirectory(s)) {
					vaciarCarpetaRecursiva(s);
				}

			}
			stream.close();
			Files.deleteIfExists(ruta);
			System.out.println("Directorio vaciado y borrado " + ruta.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
