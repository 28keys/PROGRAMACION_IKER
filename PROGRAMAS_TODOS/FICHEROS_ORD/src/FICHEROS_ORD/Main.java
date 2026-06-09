package FICHEROS_ORD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
		Path Iker = Paths.get("Iker");
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(Iker);
			for (Path s : stream) {
				if (Files.isRegularFile(s)) {
					BufferedReader reader = Files.newBufferedReader(s, charset);
					String line = null;
					// s es tipo path hay q pasarlo a string para que
					// funcione el startswith
					if (s.getFileName().toString().startsWith("n_")) {
						while ((line = reader.readLine()) != null) {
							String datos[] = line.split(",");
							String nombreArchivo = s.getFileName().toString();
							String nif = nombreArchivo.substring(2, nombreArchivo.length() - 4);
							usuarios.add(new Usuario(nif, datos[0], datos[1], datos[2]));

						}
					}
					reader.close();
				}
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Path Romero = Paths.get("Romero");
		Path Log = Paths.get("log");
		try {
			
					BufferedReader readerSuscripciones = Files.newBufferedReader(Paths.get("Iker/suscripciones.txt"), charset);
					BufferedWriter writerRomero = Files.newBufferedWriter(Romero, charset, CREATE);
					BufferedWriter writerLog = Files.newBufferedWriter(Log, charset, CREATE, APPEND);

					String line2 = null;
					while ((line2 = readerSuscripciones.readLine()) != null) {
						String[] datos2 = line2.split(",");
						DateTimeFormatter patron = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate fechaInicio = LocalDate.parse(datos2[1], patron);
						fechaInicio=fechaInicio.plusYears(1);
						Usuario usuEncontrado = buscarDni(usuarios, datos2[0]);
						if (usuEncontrado != null) {
							String linea = usuEncontrado.getNif() + "," + usuEncontrado.getNombre() + ","
									+ usuEncontrado.getApellido() + "," + usuEncontrado.getEmail() + "," + fechaInicio
									+ "," + Double.parseDouble(datos2[2]) * 1.20;
							writerRomero.write(linea);
							writerRomero.newLine();
						} else {
							String lineaLog = "No se encontró el dni " + datos2[0] + " con fecha de inicio"
									+ fechaInicio + " e importe " + datos2[2];

							writerLog.write(lineaLog);
							writerLog.newLine();
						}

					}
					writerRomero.close();
					writerLog.close();
					readerSuscripciones.close();
		}catch(IOException e) {
			System.out.println("Error");
		}
	}

	
	public static Usuario buscarDni(LinkedList<Usuario> usuarios, String nif) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNif().equals(nif)) {
				return usuarios.get(i);
			}
		}
		return null;

	}

}

/*
 * En el examen queria escribir los datos de usuencontrado pero era null, por
 * eso me explotaba el error, en todo caso lo que puedo escribir es lo de la
 * liena informando del error
 */