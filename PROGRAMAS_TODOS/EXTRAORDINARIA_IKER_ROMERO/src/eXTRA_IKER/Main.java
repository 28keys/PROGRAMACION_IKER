package eXTRA_IKER;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
		LinkedList<Solicitud> solicitudes = new LinkedList<Solicitud>();
		HashSet<String> conceptos = new HashSet<String>();
		conceptos.add("dentista");
		conceptos.add("gafas");
		conceptos.add("fisio");
		conceptos.add("otros");
		GastoMedico.añadirConceptos(conceptos);
		HashSet<String> categorias = new HashSet<String>();
		categorias.add("B3");
		categorias.add("C1");
		categorias.add("C2");
		categorias.add("E1");
		Transporte.añadirCategorias(categorias);

		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get("solicitudes.txt"), charset);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] datos = line.split(",");
				if (datos[1].matches("[A-Z]{1}[0-9]{2,3}")) {
					if (datos[0].equals("T")) {
						solicitudes.add(new Transporte(datos[0], datos[1], Double.parseDouble(datos[2]), datos[3]));
					} else if (datos[0].equals("M")) {
						solicitudes.add(new GastoMedico(datos[0], datos[1], Double.parseDouble(datos[2]), datos[3]));
					} else {
						solicitudes.add(new Estudio(datos[0], datos[1], Double.parseDouble(datos[2]),
								Double.parseDouble(datos[3])));
					}
				} else {
					System.out.println("ERROR al leer la linea");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Solicitud s1 = null;
		for (Solicitud s : solicitudes) {
			try {
				s1 = s;
				s1.calcularAyuda();
			} catch (Romero e) {
				System.out.println("Solicitud rechazada | Empleado : " + s.getId());
				// borrarSolicitud(s.getId(), solicitudes);
				/*Me salía un error rarisimo al intentar borrar, probé con remove directamente
				 * poniendome el metodo equals pero nada*/
			}
		}

		Collections.sort(solicitudes, new OrdenarSolicitudes());
		for (Solicitud s : solicitudes) {
			System.out.println(s);
		}
		System.out.println("Se dieron un total del " + Solicitud.porcentajeAyudas() + "% de ayudas por gastos médicos");

		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("."));
			boolean estudios = false;
			boolean medicos = false;
			for (Path s : stream) {
				if (s.getFileName().toString().startsWith("s_estudios")) {
					estudios = true;
					break;
				}
			}
			DirectoryStream<Path> stream2 = Files.newDirectoryStream(Paths.get("."));

			for (Path st : stream2) {
				if (st.getFileName().toString().startsWith("s_medico")) {
					medicos = true;
					break;
				}
			}
			stream.close();
			if (estudios == true) {
				String nombreEstudio = "s_estudios";
				borrarArchivos(nombreEstudio, solicitudes);
			}

			if (medicos == true) {
				String nombreMedico = "s_medico";
				borrarArchivos(nombreMedico, solicitudes);
			}
			if (estudios == false) {
				String nombreEstudio = "s_estudios";
				borrarArchivos(nombreEstudio, solicitudes);

			}
			if (medicos = false) {
				String nombreMedico = "s_medico";
				borrarArchivos(nombreMedico, solicitudes);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void borrarSolicitud(String id, LinkedList<Solicitud> solicitudes) {
		Solicitud s = null;

		for (int i = 0; i < solicitudes.size(); i++) {
			if (solicitudes.get(i).getId().equals(id)) {

				s = solicitudes.remove(i);
				break;
			}
		}

	}

	public static void borrarArchivos(String nombre, LinkedList<Solicitud> solicitudes) {
		Charset charset = Charset.forName("UTF-8");

		boolean encontrar = false;
		try {
			DirectoryStream<Path> stream1 = Files.newDirectoryStream(Paths.get("."));
			for (Path s : stream1) {
				if (s.getFileName().toString().startsWith(nombre)) {
					encontrar = true;
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (encontrar == true) {
			try {
				DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(nombre));
				for (Path s : stream) {
					String archivo = s.getFileName().toString();
					archivo = archivo.substring(archivo.length() - 5);
					if (archivo.startsWith(".info")) {
						Files.deleteIfExists(s);
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			if (nombre.equals("s_estudios")) {
				try {
					BufferedWriter writer = Files.newBufferedWriter(Paths.get(nombre + "/solicitud.info"), CREATE,
							APPEND);
					for (Solicitud s : solicitudes) {
						if (s instanceof Estudio) {
							writer.write(s.toString());
							writer.newLine();
						}
					}
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (nombre.equals("s_medico")) {
				try {
					BufferedWriter writer = Files.newBufferedWriter(Paths.get(nombre + "/solicitud.info"), CREATE,
							APPEND);
					for (Solicitud s : solicitudes) {
						if (s instanceof GastoMedico) {
							writer.write(s.toString());
							writer.newLine();
						}
					}
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} else {

			if (nombre.equals("s_estudios")) {
				Path estudio = Paths.get(nombre);
				try {
					BufferedWriter writer = Files.newBufferedWriter(Paths.get(nombre + "/solicitud.info"), CREATE,
							APPEND);
					for (Solicitud s : solicitudes) {
						if (s instanceof Estudio) {
							writer.write(s.toString());
							writer.newLine();
						}
					}
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (nombre.equals("s_medico")) {
				try {
					BufferedWriter writer = Files.newBufferedWriter(Paths.get(nombre + "/solicitud.info"), CREATE,
							APPEND);
					for (Solicitud s : solicitudes) {
						if (s instanceof GastoMedico) {
							writer.write(s.toString());
							writer.newLine();
						}
					}
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}
}
