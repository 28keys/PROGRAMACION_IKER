package examen_musica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
		LinkedList<Suscripcion> suscripciones = new LinkedList<Suscripcion>();
		HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
		Path Iker = Paths.get("Iker");
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(Iker);
			for (Path s : stream) {
				if (s.getFileName().toString().startsWith("u_")) {
					String correo = s.getFileName().toString();
					String correoSinTxt = correo.substring(2, correo.length() - 4);
					BufferedReader reader = Files.newBufferedReader(s);
					String line = null;
					while ((line = reader.readLine()) != null) {
						String[] datos = line.split(",");
						Usuario u1 = new Usuario(datos[0], datos[1], datos[2]);
						usuarios.put(correoSinTxt, u1);
					}
					reader.close();
				}
			}
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader readerSuscripciones = Files.newBufferedReader(Paths.get("suscripciones.txt"), charset);
			BufferedWriter writerLog = Files.newBufferedWriter(Paths.get("log.txt"), charset, CREATE, APPEND);
			String line2 = null;
			while ((line2 = readerSuscripciones.readLine()) != null) {
				String[] datosSu = line2.split(",");
				String tipo = datosSu[1];
				String tipoOriginal = tipo.substring(5, tipo.length() - 1);

				if (!usuarios.containsKey(datosSu[0])
						|| !(tipoOriginal.equals("basica") || tipoOriginal.equals("premium"))) {
					String lineaError = "Error en la linea :" + line2 + "\n" + LocalDateTime.now();
					writerLog.write(lineaError);
					writerLog.newLine();
				} else {
					LocalDate fechaInicio = LocalDate.parse(datosSu[2]);
					if (tipoOriginal.equals("basica")) {
						Suscripcion b1 = new Basica(datosSu[0], fechaInicio);
						b1.setImporte(b1.calcularPrecio());
						b1.setTipo(tipoOriginal);
						suscripciones.add(b1);
					} else {
						Suscripcion p1 = new Premium(datosSu[0], fechaInicio, Integer.parseInt(datosSu[3]));
						p1.setImporte(p1.calcularPrecio());
						p1.setTipo(tipoOriginal);
						suscripciones.add(p1);
					}
				}
			}
			readerSuscripciones.close();
			writerLog.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(suscripciones, new OrdenarSuscripciones());

		try {
			BufferedWriter writerInforme = Files.newBufferedWriter(Paths.get("informe.txt"), charset, CREATE);
			for (String clave : usuarios.keySet()) {

				for (int j = 0; j < suscripciones.size(); j++) {
					if (suscripciones.get(j).getMail().equals(clave)) {

						double precioFinal = suscripciones.get(j).getImporte() * 1.05;
						precioFinal = Math.round(precioFinal * 100) / 100.0;
						if (suscripciones.get(j) instanceof Premium
								&& (suscripciones.get(j).getDispositivosPremium() >= 4
										&& suscripciones.get(j).getDispositivosPremium() <= 5)) {
							precioFinal = precioFinal - 2;
							String lineaInforme = usuarios.get(clave).getNombre() + ","
									+ usuarios.get(clave).getApellido() + "," + clave + ","
									+ suscripciones.get(j).getTipo() + ","
									+ suscripciones.get(j).getFechaInicio().plusYears(1) + "," + precioFinal;
							writerInforme.write(lineaInforme);
							writerInforme.newLine();
						} else {
							String lineaInforme = usuarios.get(clave).getNombre() + ","
									+ usuarios.get(clave).getApellido() + "," + clave + ","
									+ suscripciones.get(j).getTipo() + ","
									+ suscripciones.get(j).getFechaInicio().plusYears(1) + "," + precioFinal;
							writerInforme.write(lineaInforme);
							writerInforme.newLine();
						}
						suscripciones.get(j).setTotalPrecio(precioFinal);

					}
				}
			}
			writerInforme.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Nº total de suscripciones : " + Suscripcion.getTotalSuscripciones()
				+ "\nImporte total recaudado : " + Suscripcion.getTotalPrecio());
	}

}

/*
 * 
 * for (String s : usuarios.keySet()) { System.out.println();
 * System.out.println("Correo :" + s + "\nNombre :" +
 * usuarios.get(s).getNombre() + "\nApellido :" + usuarios.get(s).getApellido()
 * + "\nTeléfono :" + usuarios.get(s).getTelefono());
 * 
 * }
 */
