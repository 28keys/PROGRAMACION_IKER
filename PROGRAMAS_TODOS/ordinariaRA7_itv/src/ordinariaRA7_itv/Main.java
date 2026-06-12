package ordinariaRA7_itv;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.ENGLISH);
		int opc;
		LinkedList<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
		HashSet<String> carburante = new HashSet<String>();
		Vehiculo[] vRechazados = new Vehiculo[10];
		carburante.add("diesel");
		carburante.add("gasolina");
		carburante.add("electrico");
		carburante.add("hibrido");
		carburante.add("gas");

		do {
			System.out.println(
					"----------ITV----------\n1.Llegada vehículo\n2.Revisión ITV\n3.Reparar vehículo\n4.Mostrar información");
			opc = sc.nextInt();
			switch (opc) {
			case 1:
				String matricula;
				String dni;
				String tipoCarburante;
				String opcion;
				String vehiculo;
				sc.nextLine();

				do {
					System.out.println("Anota matrícula");
					matricula = sc.nextLine();
				} while (!matricula.matches("^[0-9]{4}[A-Z]{3}$"));
				do {
					System.out.println("Anota DNI");
					dni = sc.nextLine();
				} while (!dni.matches("^[0-9]{6,8}[A-Z]{1}$"));
				do {
					System.out.println("Anota carburante");
					tipoCarburante = sc.nextLine();
				} while (!carburante.contains(tipoCarburante));
				System.out.println("¿El vehículo dipone de cita online?(S/N)");
				opcion = sc.nextLine();
				boolean tieneCita = false;

				if (opcion.equalsIgnoreCase("S")) {
					tieneCita = true;
				}

				System.out.println("Es de Mercancia o para Pasajeros?");
				vehiculo = sc.nextLine();
				Vehiculo vehiculoNuevo = null;
				if (vehiculo.equalsIgnoreCase("Mercancia")) {
					System.out.println("Anota carga máxima");
					int carga = sc.nextInt();
					vehiculoNuevo = (new Mercancia(matricula, dni, tipoCarburante, tieneCita, carga));
				} else {
					System.out.println("Anota numero de plazas");
					int nplazas = sc.nextInt();
					vehiculoNuevo = (new Pasajero(matricula, dni, tipoCarburante, tieneCita, nplazas));
				}

				if (tieneCita) {
					int posInsertar = 0;
					for (Vehiculo v : vehiculos) {
						if (v.isTieneCita()) { /*
												 * si el primero añadido no tiene cita aqui no se suma nada al contador
												 * se recorre el for entero y se sabe en que pos ponerlo, y echa hacia
												 * la derecha los demas para meterse
												 */
							posInsertar++;
						}
					}
					vehiculos.add(posInsertar, vehiculoNuevo);
					System.out.println("Vehiculo añadido");
				} else {
					vehiculos.addLast(vehiculoNuevo);
					System.out.println("Vehiculo añadido");

				}
				break;
			case 2:
				sc.nextLine();
				if (vehiculos.isEmpty()) {
					System.out.println("No hay vehiculos para pasar la ITV");
				} else {
					Vehiculo v = vehiculos.getFirst(); // sacamos el primero
					vehiculos.removeFirst(); // borramos el primero
					try {
						double importe = v.pasarITV();
						System.out.println(
								"ITV FAVORABLE CON ÉXITO en el coche " + v.getMatricula() + "\nImporte : " + importe);
						Vehiculo.incrementarAprobados();
					} catch (ToaquizaRomeroIker e) { // si no la ha pasado la ITV
						System.out.println("ITV RECHAZADA");
						System.out.println("Anota motivo");
						String motivo = sc.nextLine();
						LocalDate fechaFalla = LocalDate.now();
						// tenemos que añadir al array un vehiculo con su motivo y fecha de falla
						// se le suman los fallos para que en la opc 3 al sacarlo y pague, el importe
						// sea mayor
						v.setMotivo(motivo);
						v.setFechaFallo(fechaFalla);
						v.incrementarFallos();

						for (int j = 0; j < vRechazados.length; j++) {
							if (vRechazados[j] == null) {
								vRechazados[j] = v;

								System.out.println("Hemos puesto tu vehiculo en la cola de rechazados");
								break; // importante el break pa que no añada mas, recuerda q puede haber mas indicess
										// null
							}
						}
					}
				}
				break;
			case 3:
				sc.nextLine();
				int posInsertar = 0;
				boolean cita = false;
				boolean encontrado = false;
				System.out.println("Introduce matricula");
				String mat = sc.nextLine();
				for (int h = 0; h < vRechazados.length; h++) {
					/*
					 * primeor hay que ver que no sea nullo porque sino da excepcion porque porque
					 * puede que este en una casilla mas adelante y antes de eso haya null
					 */
					if (vRechazados[h] != null && vRechazados[h].getMatricula().equalsIgnoreCase(mat)) {
						Vehiculo vAñadir = vRechazados[h];
						vRechazados[h] = null;
						encontrado = true;

						if (vAñadir.isTieneCita()) {
							int pos = 0;
							for (Vehiculo v : vehiculos) {
								if (v.isTieneCita()) {
									posInsertar++;
								}
							}
							vehiculos.add(posInsertar, vAñadir);
							System.out.println("Vehiculo añadido");

						} else {
							vehiculos.addLast(vAñadir);
							System.out.println("Vehiculo añadido");

						}

						break; // el break porque ya hemos encontrado al coche

					}

				}
				if (encontrado == false) {
					System.out.println("No se encontró al vehiculo");
				}

				break;
			case 4:
				System.out.println("---------VEHICULOS EN COLA DE ESPERA---------");
				if (vehiculos.isEmpty()) {
					System.out.println("No hay vehiculo	s en la cola");
				} else {
					for (Vehiculo v : vehiculos) {
						System.out.println(v);
					}
				}
				System.out.println(
						"Porcentaje de vehículos que han pasado  la ITV :" + Vehiculo.calcularPorcentajeAprobados());
				System.out.println("---------REVISIONES FALLIDAS---------");
				LinkedList<Vehiculo> listaOrdenada = new LinkedList<Vehiculo>();
				for (int j = 0; j < vRechazados.length; j++) {
					if (vRechazados[j] != null) {
						listaOrdenada.add(vRechazados[j]); // vuelco todos los que estan fallidos en una lista para
															// ordenarlos
					}
				}
				if (listaOrdenada.isEmpty()) {
					System.out.println("No hay revisiones fallidas en ese momento");
				} else {
					Collections.sort(listaOrdenada, new OrdenVehiculos());
					Iterator<Vehiculo> it = listaOrdenada.iterator();
					while (it.hasNext()) { // hay alguien mas en la fila?, si es así...
						Vehiculo vRechazado = it.next(); // que pase el siguiente
						System.out.println(vRechazado);
					}
				}
				break;
			}
		} while (opc != 5);
	}

}
