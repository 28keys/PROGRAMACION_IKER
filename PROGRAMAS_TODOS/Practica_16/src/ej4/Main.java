package ej4;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int opc, tipo, horas;
		double cantidad;
		String nombre, dni, direccion, tfno, empProcedencia;
		LinkedList<Empleado> empleados = new LinkedList<Empleado>();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("--------------");
			System.out.println("1. Dar de alta");
			System.out.println("2. Fichar empleado");
			System.out.println("3. Reparto de beneficios");
			System.out.println("4. Pago a todos los empleados");
			System.out.println("5. Informacion actual");
			System.out.println("6. Salir");
			System.out.println("--------------");
			opc = sc.nextInt();

			switch (opc) {
			case 1:
				System.out.println("¿ 1 = Propio o 2 = Externo ?");
				tipo = sc.nextInt();
				sc.nextLine();
				System.out.println("Introduce tu nombre");
				nombre = sc.nextLine();
				System.out.println("Introduce tu dni");
				dni = sc.nextLine();
				System.out.println("Dime tu dirección");
				direccion = sc.nextLine();
				System.out.println("Añade tu numero de teléfono");
				tfno = sc.nextLine();
				System.out.println("Introduce su salario base");
				double salarioBase = sc.nextDouble();
				if (tipo == 1) {
					
					empleados.add(new Propio(nombre, dni, direccion, tfno, salarioBase));

				} else {

					System.out.println("¿Cuál es la empresa de la que procedes?");
					empProcedencia = sc.nextLine();

					empleados.add(new Externo(nombre, dni, direccion, tfno, empProcedencia));
				}
				break;
			case 2:
				System.out.println("Contenido de la lista " + empleados);
				System.out.println();
				/*
				 * 
				 * System.out.println("Introduce el numero del empleado");
				 *  int numeroEmple = sc.nextInt(); 
				 *  int posicion = empleados.indexOf(new Empleado(numeroEmple));
				 *   if(posicion == -1) 
				 *   System.out.println("NO ESTÁ"); 
				 *   else {
				 * System.out.println("Introduce el numero de horas relizadas"); 
				 * horas = sc.nextInt(); empleados.get(posicion).fichar(horas);
				 * }
				 */
				System.out.println("Introduce el numero del empleado");
				int numeroEmple = sc.nextInt();
				for (Empleado e : empleados) {
					if (e.getnEmple() == numeroEmple) {
						System.out.println("Introduce el numero de horas relizadas");
						horas = sc.nextInt();
						e.fichar(horas);
						break;
					}
				}
				break;
			case 3:
				System.out.println("Introduce la cantidad que quieres repartir");
				cantidad = sc.nextDouble();
				double beneficio = cantidad / Propio.getTotalPropios();
				for (Empleado e : empleados) {
					if (e instanceof Propio) {
						((Propio) e).dineroRepatido(beneficio);
					}
				}
				break;
			case 4:
				for (Empleado e : empleados)
					System.out.println(e.toString() + " Total a pagar " + e.pagar());
				break;
			case 5:
				System.out.println(empleados.toString());
				break;
			}
		} while (opc != 6);

		System.out.println(empleados.toString());

	}

}