package ej_peajes;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		int opc;
		LocalTime tiempo;
		// Primero tenemos que crear la cabina
		Cabina[] peaje = new Cabina[8];
		LinkedList<Vehiculo> colaEspera = new LinkedList<Vehiculo>();
		// Creo 2 distintas para que 100% tengamos de los 2 tipos
		peaje[0] = new CabinaNormal(false,0);
		peaje[1] = new CabinaAbonados(false,1);

		for (int i = 2; i < peaje.length; i++) {
			int resultado = r.nextInt(2); // empiezo desde 2 ya que creé [0] y [1]
			if (resultado < 1) {
				peaje[i] = new CabinaNormal(false,i);
			} else {
				peaje[i] = new CabinaAbonados(false,i);
			}
		}

		do {
			System.out.println("-----PEAJE-----");
			System.out.println("1.Abrir una cabina" + "\n2.LLegar vehículo" + "\n3.Pagar" + "\n4.Mostrar la información"
					+ "\n5.Cerrar una cabina");
			opc = sc.nextInt();
			sc.nextLine();
			switch (opc) {
			case 1:
				System.out.println("Introduce numero de cabina (0-7)");
				int posicion = sc.nextInt();
				if (posicion >= 0 && posicion < peaje.length)
					if (peaje[posicion].abrir()) {
						System.out.println("Cabina abierta");
						LocalTime abierto = LocalTime.now();
						peaje[posicion].setTiempoAbierto(abierto);

					} else
						System.out.println("La cabina ya estaba abierta");
				else
					System.out.println("Número cabina INCORRECTO");
				break;
			case 2:
				String tipo;
				String matricula;
				do {
					System.out.println("Introduce numero de matrícula");
					matricula = sc.nextLine();
				} while (!matricula.matches("^[0-9]{4}[A-Z]{3}$")); // 2713JSS
				do {
					System.out.println("Introduce tipo de vehículo (turismo, moto , otro)");
					tipo = sc.nextLine();
				} while (!tipo.equalsIgnoreCase("turismo") && !tipo.equalsIgnoreCase("moto")
						&& !tipo.equalsIgnoreCase("otro"));
				Vehiculo ve = new Vehiculo(matricula, tipo);
				String cliente;
				do {
					System.out.println("Anota tipo del cliente (abonado o normal)");
					cliente = sc.nextLine();
				} while (!cliente.equalsIgnoreCase("abonado") && !cliente.equalsIgnoreCase("normal"));
				String tipoCabina;
				if (cliente.equals("abonado"))
					tipoCabina = "CabinaAbonados";
				else
					tipoCabina = "CabinaNormal";

				int minimoVehiculo = 999;
				int pos = -1;
				for (int i = 0; i < peaje.length; i++) { // Buscar la cabina con menos vehículos esperando
					if (peaje[i].getClass().getSimpleName().equals(tipoCabina)) { // aqui busco el nombre de la clase
																					// del tipo
						// de cabina asi no hace falta que use
						// instanceof
						if (peaje[i].isEstado() == true) { // la cabina tiene que estar abierta
							if (peaje[i].getCola().size() < minimoVehiculo) { // si valor que tiene dentro la cabina es
																				// menor
																				// que el minvehiculo
								pos = i;
								minimoVehiculo = peaje[i].getCola().size(); // el nuevo minvehiculo es ese valor que
																			// habia
								// dentro del array(cabina)

							}
						}

					}
				}
				// peaje[i].cola.size() cantidad de coches que hay en esa posicion
				// con getSimpleName ignora el paquete y asi me busca exactamente si es
				// CabinaNormal o CabinaAbonados
				try {

					if (pos == -1) {
						throw new NingunaCabinaAbierta("Lo sentimos no tienes cabinas abiertas");
					}
					peaje[pos].addVehiculo(ve);
					System.out.println("El vehículo entró a la cabina");
				} catch (NingunaCabinaAbierta e) {
					colaEspera.add(ve);
					System.out.println("Te hemos asignado a la cola de espera");
				}

				break;
			case 3:
				double total;
				int numero = r.nextInt(8);
				System.out.println("Numero de cabina " + numero);
				if (peaje[numero].isEstado() == false) {
					System.out.println("La cabina está cerrada");
				} else if (!(peaje[numero].getCola().size() > 0)) { // recuerda que .size es la cantidad es la cantidad
																	// de
																	// esa posicion
					System.out.println("La cabina no tiene vehiculos en la cola pero esta abierta :)");
				} else {
					System.out.println("Introduce numero de kilometros");
					double kilometros = sc.nextDouble();
					total = peaje[numero].pagar(kilometros);
					
					System.out.println("Total a pagar de la cabina " + numero + " = " + total + "€");
					System.out.println("Pago realizado con éxito");
				}
				break;

			case 4:
				for (int i = 0; i < peaje.length; i++) {
					System.out.println("Cabina nº " + peaje[i].getNumeroCabina());
					System.out.println("Vehiculos atendidos de la cabina nº " + peaje[i].getNumeroCabina() + " : "
							+ peaje[i].getVehiculosAtendidos() + " vehiculos");
					// mirar como hacer esto que por cabina sepa que va al get de la i de cabina N o
					// Abonados
					if (peaje[i].isEstado() == true) {
						System.out.println(
								"Tiempo abierto en la cabina " + peaje[i].calcularTiempoAbierto() + " minuto/s");
					}
					System.out.println(peaje[i].toString());
				}
				break;

			case 5:
				System.out.println("Introduce nº de cabina");
				int cabina = sc.nextInt();
				peaje[cabina].setEstadoFalse();
				String tipoCab;
				if (peaje[cabina].getClass().getSimpleName().equalsIgnoreCase("CabinaNormal")) {
					tipoCab = "CabinaNormal";
				} else {
					tipoCab = "CabinaAbonados";
				}
				LinkedList<Vehiculo> cabinaCerrada = peaje[cabina].getCola();
				for (int i = 0; i < cabinaCerrada.size(); i++) {
					Vehiculo vehi = cabinaCerrada.get(i); // 1.Identificamos el coche get(espacio de la coleccion, [0],
															// [1] ,[2])
					int minimo = 999; // para saber el contexto de las cabinas
					int posi = -1;
					for (int j = 0; j < peaje.length; j++) { // 2. Le buscamos sitio (cabina) para recolocar
						if (peaje[j].getClass().getSimpleName().equals(tipoCab)) { // busca por el tipo de cabina
																					// cerrada
							if (peaje[j].isEstado() == true) { // tiene que estar abierta
								if (peaje[j].cola.size() < minimo) {
									posi = j;
									minimo = peaje[j].cola.size();
								}
							}
						}
					}
					try {
						if (posi == -1) {
							throw new NingunaCabinaAbierta("Ninguna cabina está abierta");
						}
						// 3. Lo recolocamos
						peaje[posi].addVehiculo(vehi); // encuentro la posicion idoena para meter esos vehiculos, y asi
						// con todos los que haya
						System.out.println("Hemos recolocado el vehiculo");
					} catch (NingunaCabinaAbierta e) {
						colaEspera.add(vehi); // 4.Lo metemos a la cola de espera si no hemos encontrado ninguna cabina
						System.out.println("Te hemos puesta en la cola de espera"); // del mismo tipo que la cerrada, o
																					// q no este abierta o que no tenga
																					// pocos coches
					}
				}
				peaje[cabina].getCola().clear(); // se vacía despues de haber recolocado a TODOS los vehiculos
				System.out.println("Cabina " + cabina + " vacía");
				break;

			}
		} while (opc != 6);
		
		System.out.println("Vehiculos atendidos total  "+ Cabina.getVehiculosAtendidosTotal());
		System.out.println("Vehiculos abonados en total "+ CabinaAbonados.getVehiculosAtendidosTotal());
		
		System.out.println("Cabinas ordenadas por nº de vehiculos atendidos");
		System.out.println();
		
		Arrays.sort(peaje, new CabinaPorNVehiculos());
		for (int k = 0 ; k < peaje.length ; k ++) {
			System.out.println("Cabina nº " + k);
			System.out.println("Nº -->"+peaje[k].getNumeroCabina()+peaje[k].toString());
		}

	}

}
