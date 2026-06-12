package ej9;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce carpeta");
		String nombre = sc.nextLine();
		Path carpeta = Paths.get(nombre);
		BufferedReader reader = null;
		Charset charset = Charset.forName("UTF-8");
		if (!Files.isDirectory(carpeta)) {
			System.out.println("No se encontró la carpeta");
		} else {
			try {
				DirectoryStream<Path> stream = Files.newDirectoryStream(carpeta);
				for (Path s : stream) {
					if (Files.isRegularFile(s)) {
						reader = Files.newBufferedReader(s);
						String line = null;
						while ((line = reader.readLine()) != null) {
							System.out.println(line);
						}
						reader.close(); // !!!!Cerramos el archivo nada más terminar de leerlo,
										// antes de que el for pase al siguiente fichero de la carpeta.
					}
				}
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sc.close();
	}

}
