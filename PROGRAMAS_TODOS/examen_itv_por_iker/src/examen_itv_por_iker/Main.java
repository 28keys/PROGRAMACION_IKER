package examen_itv_por_iker;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.ENGLISH);
		LinkedList<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
		Vehiculo[] vehiculosRechazados = new Vehiculo[10];
		HashSet<String> carburantes = new HashSet<String>();
		carburantes.add("diesel");
		carburantes.add("gasolina");
		carburantes.add("electrico");
		carburantes.add("hibrido");
		carburantes.add("gas");

		int opc;
		do {
			System.out.println(
					"----------ITV---------\n1.Llegada de vehículo\n2.Revión de los veículos\n3.Reparar vehículo\n4.Información");
			opc = sc.nextInt();
			sc.nextLine();
			switch (opc) {
			case 1:
				String matricula;
				do {
					System.out.println("Introduce matrícula");
					matricula = sc.nextLine();
				} while (!matricula.matches("[0-9]{4}[A-Z]{3}"));

				String dni;
				do {
					System.out.println("Introduce dni");
					dni = sc.nextLine();
				} while (!dni.matches("[0-9]{6,8}[A-Z]{1}"));
				String carburante;
				do {
					System.out.println("Anota carburante");
					carburante = sc.nextLine();
				} while (!carburantes.contains(carburante));

				System.out.println("Tiene cita online?(S/N)");
				String cita = sc.nextLine();

				if (cita.equalsIgnoreCase("N")) {
					System.out.println("Mercancia o pasajeros?");
					String tipo = sc.nextLine();
					if (tipo.equalsIgnoreCase("mercancia")) {
						System.out.println("Anota carga maxima");
						int cargaMaxima = sc.nextInt();
						vehiculos.addLast(new Mercancia(matricula, carburante, dni, false, cargaMaxima));
					} else {
						System.out.println("Anota numero de plazas");
						int nPlazas = sc.nextInt();
						vehiculos.addLast(new Pasajero(matricula, carburante, dni, false, nPlazas));
					}
				} else {
					int cont = 0;
					for (int i = 0; i < vehiculos.size(); i++) {
						if (vehiculos.get(i).isCita() == true) {
							cont++;
						}
					}
					System.out.println("Mercancia o pasajeros?");
					String tipo = sc.nextLine();
					if (tipo.equalsIgnoreCase("mercancia")) {
						System.out.println("Anota carga maxima");
						int cargaMaxima = sc.nextInt();
						vehiculos.add(cont, new Mercancia(matricula, carburante, dni, true, cargaMaxima));
					} else {
						System.out.println("Anota numero de plazas");
						int nPlazas = sc.nextInt();
						vehiculos.add(cont, new Pasajero(matricula, carburante, dni, true, nPlazas));

					}
				}

				break;
			case 2:
				Vehiculo v = null;
				for (int i = 0; i < vehiculos.size(); i++) {
					v = vehiculos.get(i);
					vehiculos.remove(vehiculos.get(i));
					break;
				}

				try {
					v.pasarITV();
				} catch (Iker e) {
					e.getMessage();
					v.setFechaHoy(LocalDate.now());
					System.out.println("Anota motivo");
					String motivo = sc.nextLine();
					v.setMotivo(motivo);
					for (int i = 0; i < vehiculosRechazados.length; i++) {
						if (vehiculosRechazados[i] == null) {
							vehiculosRechazados[i] = v;
							break;
						}
					}
					System.out.println("ITV desfavorable, te hemos añadido a la cola de rechazados");
				}
				break;
			case 3:
				System.out.println("Anota matricula");
				String mat = sc.nextLine();
				Vehiculo ve = null;
				for (int i = 0; i < vehiculosRechazados.length; i++) {
					if (vehiculosRechazados[i].getMatricula().equals(mat)) {
						ve = vehiculosRechazados[i];
						vehiculosRechazados[i] = null;
						break;
					}
				}
				if (ve.isCita() == false) {
					vehiculos.addLast(ve);
				} else {
					int cont = 0;
					for (int i = 0; i < vehiculos.size(); i++) {
						if (vehiculos.get(i).isCita() == true) {
							cont++;
						}
					}
					vehiculos.add(cont, ve);
				}
				break;
			case 4:
				System.out.println("----------VEHICULOS EN LA COLA----------");
				for (Vehiculo veh : vehiculos) {
					System.out.println(veh);
				}
				System.out.println("-----------------------------------------");
				System.out.println("----------REVISIONES FALLIDAS-----------");
				LinkedList<Vehiculo> vehRechazados = new LinkedList<Vehiculo>();
				for (int i = 0; i < vehiculosRechazados.length; i++) {
					if (vehiculosRechazados[i] != null) {
						vehRechazados.add(vehiculosRechazados[i]);
					}
				}
				Collections.sort(vehRechazados, new OrdenarRevisionesFallidas());
				Iterator<Vehiculo> it = vehRechazados.iterator();
				while (it.hasNext()) {
					Vehiculo rechazado = it.next();
					System.out.println(rechazado);
				}
				System.out.println("-----------------------------------------");
				System.out.println("---------VEHICULOS QUE PASAN LA ITV: " + Vehiculo.calcularPorcentaje() + "%");
				break;
			}
		} while (opc != 5);

	}

}
