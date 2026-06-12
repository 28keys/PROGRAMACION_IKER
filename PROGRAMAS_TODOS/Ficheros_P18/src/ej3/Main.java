package ej3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
/*No usar rutas absolutas, tenemos que crear las carpetas al nivel de src pero no dentro de src*/
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce nombre de una carpeta");
		String carpeta = sc.nextLine();
		Path pCarpeta = Paths.get(carpeta);
		if (!Files.isDirectory(pCarpeta)) {
			System.out.println("No es una carpeta");
		} else {
			try {
				DirectoryStream<Path> stream = Files.newDirectoryStream(pCarpeta);
				for (Path s : stream) {
					if (Files.isRegularFile(s)) {
						Files.delete(s);
						System.out.println("Borrado " + s.getFileName());
					}
				}
				stream.close();
				System.out.println("Los archivos se borraron con éxito");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sc.close();
	}

}
