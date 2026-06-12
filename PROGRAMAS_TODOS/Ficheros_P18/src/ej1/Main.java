package ej1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.ENGLISH);
		String ruta;
		System.out.println("Introduce la ruta del fichero");
		ruta = sc.nextLine();
		Path pruta = Paths.get(ruta);

		if (!Files.exists(pruta)) {
			System.out.println("El fichero no existe");

		} else {
			try {
				DirectoryStream<Path> stream = Files.newDirectoryStream(pruta);
				for (Path p : stream) {
					System.out.println(p.getFileName());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}