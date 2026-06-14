package examen_autos_por_iker;

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
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import static java.nio.file.StandardOpenOption.*;

public class Main {
	public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
		HashMap<String, Propietario> propietarios = new HashMap<String, Propietario>();
		LinkedList<Automovil> automoviles = new LinkedList<Automovil>();
		try {
			BufferedReader readerP = Files.newBufferedReader(Paths.get("Propietario.info.txt"), charset);
			String line = null;
			while ((line = readerP.readLine()) != null) {
				String[] datos = line.split(",");
				propietarios.put(datos[0], new Propietario(datos[0], datos[1], datos[2]));
			}
			readerP.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader readerA = Files.newBufferedReader(Paths.get("auto.info.txt"), charset);
			BufferedWriter writerLog = Files.newBufferedWriter(Paths.get("log.txt"), charset);
			String line2 = null;
			while ((line2 = readerA.readLine()) != null) {
				String[] datos = line2.split(",");
				try {
					LocalDate fechaCompra = LocalDate.parse(datos[1]);
					Propietario pr = buscarPropietario(datos[3], propietarios);
					if (pr == null) {
						writerLog.write("El propietario no existe, error en la linea :\n" + line2);
						writerLog.newLine();
					} else {
						automoviles.add(new Automovil(datos[0], fechaCompra, Double.parseDouble(datos[2]), pr.getDni(),
								pr.getNombre(), pr.getApellido()));
					}
				} catch (DateTimeParseException e) {
					writerLog.write("La fecha es incorrecta , error en la linea :\n" + line2);
					writerLog.newLine();
				}
			}
			readerA.close();
			writerLog.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(automoviles, new OrdenarAutos());
		for (Automovil a : automoviles) {
			System.out.println(a);
		}

	}

	public static Propietario buscarPropietario(String dni, HashMap<String, Propietario> propietarios) {
		for (String clave : propietarios.keySet()) {
			if (dni.equals(clave)) {
				return propietarios.get(dni);
			}
		}
		return null;
	}
}
