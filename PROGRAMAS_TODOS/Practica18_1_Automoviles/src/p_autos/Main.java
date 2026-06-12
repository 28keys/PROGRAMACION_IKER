package p_autos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Charset charset = Charset.forName("UTF-8");
		// Propietario.info.txt" en windows me lo pone en txt pero en el examen no
		// pasara eso, si pongo .info
		Path pPropietario = Paths.get("Propietario.info.txt");
		HashMap<String, Propietario> propietarios = new HashMap<String, Propietario>();
		LinkedList<Auto> autos = new LinkedList<Auto>();
		if (!Files.isRegularFile(pPropietario)) {
			System.out.println("No se encontró el fichero");
		} else {
			try {
				BufferedReader reader = Files.newBufferedReader(pPropietario);
				String line = null;
				while ((line = reader.readLine()) != null) {
					String[] datosPropietario = line.split(",");
					Propietario nuevo = new Propietario(datosPropietario[0], datosPropietario[1], datosPropietario[2]);
					propietarios.put(datosPropietario[0], nuevo);
				}
				System.out.println("Datos del fichero propietario importados");
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		Path pAuto = Paths.get("auto.info.txt");
		Path pLog = Paths.get("errores.log.txt");

		if (!Files.isRegularFile(pAuto)) {
			System.out.println("El fichero no se encontró");
		} else {
			try {
				BufferedReader readerAuto = Files.newBufferedReader(pAuto, charset);
				BufferedWriter writerLog = Files.newBufferedWriter(pLog, charset, CREATE, TRUNCATE_EXISTING);
				String line2 = null;
				while ((line2 = readerAuto.readLine()) != null) {
					String[] datosAuto = line2.split(",");
					try {
						/*
						 * Auto nuevoAuto = new Auto(datosAuto[0], LocalDate.parse(datosAuto[1]),
						 * Double.parseDouble(datosAuto[2]), datosAuto[3]); nopuedo crearlo sin antes
						 * haberlo validado
						 */
						LocalDate fechaCompra = LocalDate.parse(datosAuto[1]); // EL FORMATO NORMAL ES AÑO MES DIA
						Double precio = Double.parseDouble(datosAuto[2]);
						String dniAutomovil = datosAuto[3];

						if (!propietarios.containsKey(dniAutomovil)) {
							writerLog.write("No se ha encontrado al propietario con DNI : " + dniAutomovil
									+ " en la linea " + line2);
							writerLog.newLine();
						} else {
							Auto nuevoAuto = new Auto(datosAuto[0], fechaCompra, precio, dniAutomovil);

							boolean insertado = false;
							for (int i = 0; i < autos.size(); i++) {
								if (nuevoAuto.compareTo(autos.get(i)) < 0) {
									// si el nuevo auto es -1 va antes que el otro
									autos.add(i, nuevoAuto); // se desplaza a la derecha solo el otro
									insertado = true;
									break;

								}

							}
							if (insertado == false) { // si el coche no va antes se pone al final
								autos.add(nuevoAuto); // el if va fuera del for
							}
							System.out.println("Automoviles extraidos e insertados en orden");
						}

					} catch (DateTimeParseException d) {
						d.getMessage();
						writerLog.write("Error al leer la fecha en la linea " + line2);
						writerLog.newLine();
					}
				}
				writerLog.close();
				readerAuto.close();
			} catch (IOException e) {
				e.printStackTrace();

			}

		}
		System.out
				.println("Auto ordenados de menor a mayor fecha (o de menor a mayor precio si la fecha es la misma) ");
		for (Auto a : autos) {
			System.out.println(a);
		}

		Path baratos = Paths.get("baratos.txt");
		try {
			BufferedWriter writer = Files.newBufferedWriter(baratos, charset,CREATE, TRUNCATE_EXISTING);
			for (int i = 0; i < autos.size(); i++) {
				if (autos.get(i).getPrecio() < 2000) {

					Propietario p = propietarios.get(autos.get(i).getDni());
					String nuevaLinea = autos.get(i).getMatricula() + "," + autos.get(i).getFechaCompra() + ","
							+ autos.get(i).getPrecio() + "," + autos.get(i).getDni() + "," + p.getNombre() + ","
							+ p.getApellido();
					writer.write(nuevaLinea);
					writer.newLine();
				}
			}
			writer.close();
			System.out.println("Automoviles importados :)");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
/*
 * EL hashamap no admite tipso primitivos como int, double boolean,char. hay que
 * usar sus versiones en clase, Integer Double, Boolean, Character
 * 
 * mapa.put(clave, objeto) : Sirve para guardar algo dentro del mapa.
 * 
 * mapa.get(clave) : Sirve para buscar/recuperar un objeto usando su clave. Si
 * no existe esa clave, te devuelve null.
 * 
 * mapa.containsKey(clave): Te devuelve un boolean (true/false) para saber si
 * esa clave ya está registrada.
 * 
 * EL HASHAMAP no va por posiciones, va por claves Cuando tú llamas a
 * propietarios.keySet(), Java te devuelve una lista (un conjunto) que contiene
 * únicamente todos los DNI que has guardado en el mapa. Si en tu fichero tenías
 * tres propietarios, keySet() es exactamente esto: ["1111A", "2222B", "3333C"]
 */
