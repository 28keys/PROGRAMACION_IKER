package ej11;

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
		System.out.println("Anota fichero que vas a enviar");
		String nombre = sc.nextLine();
		Path fichero = Paths.get(nombre);
		Path archivo1 = Paths.get("archivo1.txt");
		Path archivo2 = Paths.get("archivo2.txt");

		if (!Files.isRegularFile(fichero)) {
			System.out.println("El fichero no existe");
		} else {
			procesoDividir(fichero, archivo1, archivo2);
			procesoUnir(archivo1, archivo2, fichero);
			// f1 tiene un patron de cortar los numeros pares
			// f2 tiene un patron de cortar los numeros impares
			System.out.println("Archivos enviados con éxito");
		}

	}

	public static void procesoDividir(Path fichero, Path archivo1, Path archivo2) {
		Charset charset = Charset.forName("UTF-8");
		try {
			BufferedReader reader = Files.newBufferedReader(fichero, charset);
			BufferedWriter writer = Files.newBufferedWriter(archivo1, charset, CREATE);
			BufferedWriter writer2 = Files.newBufferedWriter(archivo2, charset, CREATE);
			String line = null;
			while ((line = reader.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					char letra = line.charAt(i);
					if (i % 2 == 0) {
						writer.write(letra);
					} else {
						writer2.write(letra);
					}

				}
				writer.newLine();
				writer2.newLine();
			}
			reader.close();
			writer.close();
			writer2.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void procesoUnir(Path archivo1, Path archivo2, Path destino) {
		Charset charset = Charset.forName("UTF-8");
		try {
			BufferedReader reader = Files.newBufferedReader(archivo1, charset);
			BufferedReader reader2 = Files.newBufferedReader(archivo2, charset);
			BufferedWriter writer = Files.newBufferedWriter(destino, charset, CREATE, TRUNCATE_EXISTING);
			String line = null;
			String line2 = null;
			while ((line = reader.readLine()) != null && (line2 = reader2.readLine()) != null) {
				int maximoTam = Math.max(line.length(), line2.length());
				for (int i = 0; i < maximoTam; i++) {
					
					// 1º: Escribimos el carácter de la línea PAR (archivo1)
				    if (i < line.length()) {
				        writer.write(line.charAt(i));
				    }

				    // 2º: Escribimos el carácter de la línea IMPAR (archivo2)
				    if (i < line2.length()) { 
				        writer.write(line2.charAt(i));
				    }
				}
				writer.newLine();
			}
			writer.close();
			reader.close();
			reader2.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
