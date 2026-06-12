package ej2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
//import static java.nio.file.StandardOpenOption.*;
import static java.nio.file.StandardCopyOption.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.ENGLISH);
		System.out.println("Introduce ruta");
		String ruta = sc.nextLine();
		System.out.println("Introduce nombre del fichero 1");
		String f1 = sc.nextLine();
		System.out.println("Introduce nombre del fichero 2");
		String f2 = sc.nextLine();

		Path p1 = Paths.get(ruta, f1);
		Path p2 = Paths.get(ruta, f2);
		if (!Files.exists(p2)) {
			try {
				Files.copy(p1, p2);
			} catch (IOException e) {
				System.out.println("Error al copiar el archivo");
				e.printStackTrace();
			}
		} else {
			System.out.println("Este archivo ya existe, deseas sobreescribirlo?(S/N)");
			String opc = sc.nextLine();
			if (opc.equalsIgnoreCase("N")) {
				System.out.println("Denegado");
			} else {
				try {
					Files.copy(p1, p2, REPLACE_EXISTING);
					System.out.println("Archivo sobreescrito");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		sc.close();
	}

}
