package taller;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.*;
import bbdd.*;
import static java.nio.file.StandardOpenOption.*;
import modelos.Arreglo;
import modelos.Cliente;

public class Principal {
	/* Cuando te conectes a clase es el 3306, con contraseña root */
	/* Aqui en casa es el 3096 y sin contraseña */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);
		int opc = 0;
		BD_Taller bd = new BD_Taller("mysql-properties.xml");

		LinkedList<Arreglo> arreglos = new LinkedList<Arreglo>();
		try {
			arreglos = bd.buscarArreglosPendientes();
			Collections.sort(arreglos, new OrdenarArreglos());

		} catch (ErrorBaseDatos e) {
			escribirLog(e.getMessage());
			e.printStackTrace();
		}

		String matricula;
		Arreglo encontrado = null;
		do {
			System.out.println("Anota matricula");
			matricula = sc.nextLine();
			encontrado = buscarArreglo(matricula, arreglos);
		} while (!matricula.matches("[0-9]{4}[A-Z]{3}") || encontrado == null);

		try {
			Cliente clienteArreglo = bd.buscarCliente(encontrado);
			clienteArreglo.pagar(encontrado);
			/*
			 * Arreglo encontrado se ha actualizado por los set, pq no son copias estan
			 * dentro de la memoria del programa solo que apunta a su direccion del
			 * linkedlist el objeto
			 */
			int filas = bd.actualizarArreglos(encontrado);

			if (filas == 0) {
				System.out.println("0 filas actualizadas");
			} else {
				System.out.println(filas + " filas actualizadas");
				arreglos.remove(encontrado);
				System.out.println("Se eliminó el arreglo de la colección");
				int filas2 = bd.actualizarVisitas(clienteArreglo);
				if (filas2 == 0) {
					System.out.println("0 filas actualizadas");
				} else {
					System.out.println(filas2 + "filas actualizadas");
				}
			}

			System.out.println("ARREGLO MAS CARO "); //no lo hice pq es bbdd pero se haria con max
			System.out.println("Nº de mecanicos que mas arreglos ha realizado"); // se haria con count

		} catch (ErrorBaseDatos e) {
			escribirLog(e.getMessage());
			e.printStackTrace();
		}

	}

	public static Arreglo buscarArreglo(String matricula, LinkedList<Arreglo> arreglos) {
		for (int i = 0; i < arreglos.size(); i++) {
			if (matricula.equals(arreglos.get(i).getMatricula())) {
				return arreglos.get(i);
			}
		}
		return null;
	}

	public static void escribirLog(String descripcion) {
		Charset charset = Charset.forName("UTF-8");
		try {
			BufferedWriter writerLog = Files.newBufferedWriter(Paths.get("log.txt"), charset, CREATE, APPEND);
			String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

			writerLog.write(fechaHora + "-" + descripcion);
			writerLog.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
