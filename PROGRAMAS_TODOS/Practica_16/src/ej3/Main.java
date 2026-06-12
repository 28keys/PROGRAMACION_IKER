package ej3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int cantidad, opc, tipo, dias;
		String titular;
		double saldoCuenta;
		HashSet<String> titulares = null; // aqui yo guardo los titulares dentro del Hashet
		LinkedList<Cuenta> cuentas = new LinkedList<Cuenta>(); // Aqui guardo cada cuenta
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("--------------");
			System.out.println("1. Dar de alta");
			System.out.println("2. Borrar cuenta");
			System.out.println("3. Días");
			System.out.println("4. Sorteo de un viaje entre Cuentones");
			System.out.println("5. Salir");
			System.out.println("--------------");
			opc = sc.nextInt();
			switch (opc) {
			case 1:
				System.out.println("1 = Cuenta o 2 = Cuenton ?");
				tipo = sc.nextInt(); // Primero el tipo cuenta o cuenton
				System.out.println("¿Numero de titulares?(1,2 o 3)");
				cantidad = sc.nextInt();// Los titulares que queremos que hayan en una misma cuenta
										// como maximo 3
				titulares = new HashSet<String>();
				sc.nextLine(); // limpio buffer
				for (int i = 0; i < cantidad; i++) { // recorre hasta el numero de titulares q le puse
					System.out.println("Introduce nombre del titular");
					titular = sc.nextLine();
					titulares.add(titular);
				}
				System.out.println("Introduce saldo");
				saldoCuenta = sc.nextDouble();
				if (tipo == 1)
					cuentas.add(new Cuenta(titulares, saldoCuenta));
				else {
					if (saldoCuenta >= Cuenton.getMinimo()) // porque el cuenton tiene que tener un saldo
															// de al menos 600 euros como minimo, sino no se puede abrir
						cuentas.add(new Cuenton(titulares, saldoCuenta));
					else
						System.out.println("La cuenta debe tener al menos 600€");
				}
				break;
			case 2:
				System.out.println("Introduce nombre del titular");
				sc.nextLine(); // limpio buffer
				titular = sc.nextLine();
				for (int i = 0; i < cuentas.size(); i++) {
					if (cuentas.get(i).validarTitular(titular) == true) {
						cuentas.remove(i);
						System.out.println("Cuenta eliminada con éxito");
						// i recorre cada espacio del linkedlist
						// si la funcion devuelve true es pq sí hay titular
						// y elimina la cuenta
					}
				}
				break;
			case 3:
				System.out.println("Introduce el nº de días trabajados");
				dias = sc.nextInt();
				for (Cuenta c : cuentas) {
					System.out.println(c.toString() + " ha producido :" + c.dineroProducido(dias) + "€");
				}
				break;
			case 4:
				// crear sorteador
				// recorrer todas las cuentas
				// si la cuenta es un cuenton, añadirla
				// si no hay participantes, avisar
				// si hay participantes, sacar ganador
				
				Sorteador <Cuenta> sorteo = new Sorteador <Cuenta>();
				for (Cuenta c : cuentas) {
					if (c instanceof Cuenton) {/* con instanceof filtro el elemento cuenton*/
						/*Cuando encuentro un cuenton lo meto en el sorteo
						  por eso hago add */
						sorteo.add(c);
					}
				}
				if (sorteo.cuentonVacio()) {
					System.out.println("No hay cuentones para hacer el sorteo");
				} else {
					Cuenta ganador = sorteo.get();
					System.out.println("El ganador del viaje es: "+ganador);
				}
				break;				
			} // cierro el switch

		} while (opc != 5);

		System.out.println(cuentas.toString());
	}
}
