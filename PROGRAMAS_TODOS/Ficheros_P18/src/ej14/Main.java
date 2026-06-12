package ej14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Charset charset = Charset.forName("UTF-8");
		System.out.println("Introduce el nombre del fichero");
		String nombre = sc.nextLine();
		Path fichero1 = Paths.get(nombre);
		Path fichero2 = Paths.get("do2.txt");
		if (nombre.equals(fichero2.getFileName().toString())) { // o .equals ("fichero7.txt")
			System.out.println("Los nombres de los ficheros no pueden coincidir");
		} else if (!Files.isRegularFile(fichero1)) {
			System.out.println("No se encontró el fichero");
		} else {
			System.out.println("Introduce la palabra que quieres borrar");
			String palabra = sc.nextLine();
			try {
				BufferedReader reader = Files.newBufferedReader(fichero1, charset);
				BufferedWriter writer = Files.newBufferedWriter(fichero2, charset, CREATE, TRUNCATE_EXISTING);

				String line = null;
				while ((line = reader.readLine()) != null) {
					if (line.contains(palabra)) {
						line = line.replace(palabra, "");
					}
					writer.write(line);
					writer.newLine();
				}
				reader.close();
				writer.close();
				System.out.println("Se borró la palabra y se creó el nuevo archivo");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		sc.close();
	}

}
