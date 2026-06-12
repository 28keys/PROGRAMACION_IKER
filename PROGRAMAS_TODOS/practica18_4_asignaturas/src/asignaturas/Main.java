package asignaturas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Charset charset = Charset.forName("UTF-8");
		int opc;
		ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
		do {
			String carpeta = "asignaturas_daw1";
			System.out.println(
					"--------ASIGNATURAS--------\n1.Alumnos aprobados\n2.Notas de cada asignatura\n3.Solucionar error");
			opc = sc.nextInt();
			sc.nextLine();

			switch (opc) {
			case 1:
				System.out.println("Introduce nombre de una asignatura(MARCAS,PROGRAMACION,SISTEMAS)");
				String asignatura = sc.nextLine();
				Path pAsignatura = Paths.get(carpeta, asignatura + ".txt"); // recuerda que dentro del fichero no haya
																			// espacios
				if (!Files.isRegularFile(pAsignatura)) {
					System.out.println("No se encontró el fichero");
				} else {
					try {
						BufferedReader reader = Files.newBufferedReader(pAsignatura, charset);
						String line = null;
						while ((line = reader.readLine()) != null) {
							String[] datosAsignatura = line.split(",");
							int nota = Integer.parseInt(datosAsignatura[3]);
							if (nota > 4) {
								asignaturas.add(new Asignatura(datosAsignatura[0], datosAsignatura[1],
										datosAsignatura[2], nota));

							}
						}
						reader.close();
						System.out.println("ALUMNOS");
						for (Asignatura a : asignaturas) {
							System.out.println(a);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				break;
			case 2:
				String carp = "asignaturas_daw1";
				String[] as = new String[3];
				as[0] = "MARCAS.info";
				as[1] = "PROGRAMACION.info";
				as[2] = "SISTEMAS.info";

				System.out.println("Introduce DNI del alumno");
				String dni = sc.nextLine();
				System.out.println("Notas con DNI: " + dni + " :");
				for (String asignaturaDAW : as) {
					Path pCarpeta = Paths.get(carp, asignaturaDAW + ".txt");

					if (!Files.isRegularFile(pCarpeta)) {
						System.out.println("El archivo no existe");
					}

					else {
						boolean mostrarTitulo = false;
						try {
							BufferedReader reader = Files.newBufferedReader(pCarpeta, charset); // ojo no te olvides del
																								// charset q sino da
																								// error
							String line2 = null;
							while ((line2 = reader.readLine()) != null) {
								String[] dato = line2.split(",");
								double nota = Double.parseDouble(dato[3]);
								if (dni.equals(dato[0])) {

									if (!mostrarTitulo) { // osea false
										System.out.println(asignaturaDAW);
										mostrarTitulo = true;
									}
									System.out.println(nota + " ");

								}
							}
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
				break;
			case 3:
				String folder = "asignaturas_daw1";

				String[] asi = new String[3];
				asi[0] = "MARCAS.info";
				asi[1] = "PROGRAMACION.info";
				asi[2] = "SISTEMAS.info";

				for (String s : asi) {
					Path pOriginal = Paths.get(folder, s + ".txt");
					Path pNuevo = Paths.get(folder, s + "_nuevo.txt");
					try {
						if (!Files.isRegularFile(pOriginal)) {
							System.out.println("No se encontró el archivo");
						} else {
							ArrayList<Asignatura> asignaturasL = new ArrayList<Asignatura>();
							BufferedReader reader = Files.newBufferedReader(pOriginal);
							String line3 = null;
							while ((line3 = reader.readLine()) != null) {
								String[] datos = line3.split(",");
								int nota = Integer.parseInt(datos[3]) + 1;

								if (nota > 10) {
									nota = 10; // para caparlo a 10
								}

								asignaturasL.add(new Asignatura(datos[0], datos[1], datos[2], nota));
							}
							reader.close();
							for (int i = 0; i < asignaturasL.size(); i++) {
								for (int j = i + 1; j < asignaturasL.size(); j++) {
									if (asignaturasL.get(i).compareTo(asignaturasL.get(j)) < 0) { // solo entra al if si
																									// ES MENOR QUE 0
																									// por tanto como es
																									// menor va despues,
																									// primero va j que
																									// será mayor que i
										Asignatura temporal = asignaturasL.get(i); // la i es menor
										asignaturasL.set(i, asignaturasL.get(j)); // por tanto, j va primero
										asignaturasL.set(j, temporal); // la i se mueve a j (mayor a menor)
									}
								}
							}

							BufferedWriter writer = Files.newBufferedWriter(pNuevo, CREATE, TRUNCATE_EXISTING);
							for (Asignatura c : asignaturasL) {
								String linea = c.getDni() + "," + c.getNombre() + "," + c.getApellido() + ","
										+ c.getNota();
								writer.write(linea);
								writer.newLine();
							}
							writer.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

			break;

		} while (opc != 4);
	}

}
