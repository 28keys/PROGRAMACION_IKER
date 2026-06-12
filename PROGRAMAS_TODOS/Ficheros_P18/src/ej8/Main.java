package ej8;

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
		System.out.println("Introduce nombre de un archivo");
		String nombre = sc.nextLine();
		System.out.println("Introduce letra");
		String letra = sc.nextLine();
		char letraMinus = letra.toLowerCase().charAt(0);
		Path archivo = Paths.get(nombre);
		BufferedReader reader = null;
		Charset charset = Charset.forName("UTF-8");

		if (!Files.exists(archivo)) {
			System.out.println("No se encontró el archivo en el directorio raíz");
		} else {
			try {
				reader = Files.newBufferedReader(archivo);
				String line = null;
				int contRepetida = 0;
				while ((line = reader.readLine()) != null) {
					String lineaMinus = line.toLowerCase();
					for (int i = 0; i < lineaMinus.length(); i++) {
						if (lineaMinus.charAt(i) == letraMinus) {
							contRepetida++;
						}
					}
				}
				reader.close();
				System.out.println("La letra " + letra + " se repite " + contRepetida + " veces");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sc.close();
	}

}
