package examen_autos_ej_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
		Scanner sc = new Scanner(System.in);
		HashMap<String, ArrayList<Alumno>> alumnos = new HashMap<String, ArrayList<Alumno>>();
		HashSet<String> asignaturas = new HashSet<String>();
		asignaturas.add("MARCAS");
		asignaturas.add("PROGRAMACION");
		asignaturas.add("SISTEMAS");
		int opc;
		do {
			System.out.println("1.Leer asignatura\n2.Mostrar notas de alumno\n3.Reordenar notas");
			opc = sc.nextInt();
			sc.nextLine();
			switch (opc) {
			case 1:
				String nombre;
				do {
					System.out.println("Anota nombre de asignatura");
					nombre = sc.nextLine();
					nombre = nombre.toUpperCase();
				} while (!asignaturas.contains(nombre));
				try {
					DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("asignaturas"));
					for (Path s : stream) {
						if (s.getFileName().toString().startsWith(nombre)) {
							BufferedReader reader = Files.newBufferedReader(s, charset);
							String line = null;
							while ((line = reader.readLine()) != null) {
								String[] datos = line.split(",");
								int nota = Integer.parseInt(datos[3]);
								if (nota >= 5) {
									Alumno alu = new Alumno(datos[0], datos[1], datos[2], nota);
									if (!alumnos.containsKey(nombre)) { // si no contiene la clave...
										alumnos.put(nombre, new ArrayList<Alumno>()); // lo creo y creo vacio el alumno
									}
									alumnos.get(nombre).add(alu); // lo añado
									/*
									 * si ya existiese directamente llama a la clave y añade ese alumno sin
									 * necesidad de tener un else
									 */
								}
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				String dni;
				do {
					System.out.println("Anota dni");
					dni = sc.nextLine();
				} while (!dni.matches("[0-9]{4}[A-Z]{1}"));
				for (String clave : alumnos.keySet()) {
					ArrayList<Alumno> listaAl = alumnos.get(clave);
					for (Alumno a : listaAl) {
						if (a.getDni().equals(dni)) {
							System.out.println("Asignatura " + clave + "\nNombre : " + a.getNombre() + " | Apellido : "
									+ a.getApellido() + " | Dni : " + a.getDni() + "\nNota : " + a.getNota());
						}
					}
				}

				break;
			case 3:
				for (String clave : alumnos.keySet()) {
					ArrayList<Alumno> lista = alumnos.get(clave);
					Collections.sort(lista, new OrdenarNotas());
					try {
						BufferedWriter writer = Files.newBufferedWriter(Paths.get(clave + ".info.txt"), CREATE);
						for (Alumno a : lista) {
							
							String linea = (a.getDni() + "," + a.getNombre() + "," + a.getApellido() + "," + (a.getNota()
									+ 1));
							writer.write(linea);
							writer.newLine();
						}
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				break;
			}
		} while (opc != 4);
	}

}
