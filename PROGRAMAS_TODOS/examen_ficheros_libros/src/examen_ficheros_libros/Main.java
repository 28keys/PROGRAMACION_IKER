package examen_ficheros_libros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
		Path Romero = Paths.get("Romero");

		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(Romero);
			for (Path s : stream) {
				if (s.getFileName().toString().startsWith("ventas")) {
					BufferedReader reader = Files.newBufferedReader(s);
					BufferedWriter writerIker = Files.newBufferedWriter(Paths.get("iker.txt"), charset, CREATE, APPEND);
					String line = null;

					while ((line = reader.readLine()) != null) {
						String[] datos = line.split(",");
						double precio = Double.parseDouble(datos[2]);
						if (precio > 20) {
							String nuevaLinea = "Titulo:'" + datos[0] + "' " + "autor:" + datos[1];
							writerIker.write(nuevaLinea);
							writerIker.newLine();
						}
					}
					writerIker.close();
				}
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
