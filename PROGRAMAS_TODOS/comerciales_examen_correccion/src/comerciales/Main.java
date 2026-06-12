package comerciales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		ArrayList<Comercial> comerciales = new ArrayList<Comercial>();
		Scanner sc = new Scanner(System.in);
		boolean otro = true;
		String nombre, tipo, marca;
		double sueldoFijo;

		HashSet<String> marcas = new HashSet<String>();
		marcas.add("A");
		marcas.add("B");
		marcas.add("C");

		System.out.println("Anota sueldo base de los empleados que trabajan a sueldo fijo");
		sueldoFijo = sc.nextDouble();
		Fijo.setFijo(sueldoFijo); // si es metodo static se le llama por su nombre de clase

		sc.nextLine(); // Limpio buffer

		do {
			System.out.println("Introduce nombre:");
			nombre = sc.nextLine();
			System.out.println("¿Es un comercial fijo o con comisión?(F/C)?");
			tipo = sc.nextLine();
			if (tipo.equalsIgnoreCase("F")) {
				comerciales.add(new Fijo(nombre));
			} else {
				do {
					System.out.println("Marca de la que obtiene beneficios extra:");
					marca = sc.nextLine();
				} while (!marcas.contains(marca));
				comerciales.add(new Comision(nombre, marca));

			}
			if (comerciales.size() == 4) {
				otro = false;
			} else if (comerciales.size() >= 1 && comerciales.size() < 4) {
				System.out.println("Otro comercial (S/N)");
				String seguir = sc.nextLine();
				if (seguir.equalsIgnoreCase("N")) {
					otro = false;
				}
			}

		} while (otro == true); // o (otro)
		// Salgo de lbucle porque size >=1 && size > 4

		int numero;
		String marcaEmple;
		do {
			System.out.println("Introduce numero de empleado");
			numero = sc.nextInt();
			sc.nextLine();
			do {
				System.out.println("Introduce marca");
				marcaEmple = sc.nextLine();

			} while (!marcas.contains(marcaEmple));

			boolean encontrado = false;
			for (int i = 0; i < comerciales.size(); i++) {
				if (numero == comerciales.get(i).getNumero()) {
					System.out.println("Introduce el nº de unidades que vas a vender para la marca " + marcaEmple);
					int unidades = sc.nextInt();
					comerciales.get(i).añadirUnidades(marcaEmple, unidades);
					System.out.println("Cantidades incrementadas");
					encontrado = true;
					break;
				}
			}

			if (encontrado == false)
				System.out.println("No existe");
		} while (numero != -1);

		for (Comercial c : comerciales) {
			System.out.println(c.getNombre() + " a cobrado " + c.pagar());

		}

		System.out.println("Anota marca para mostrar el empleado que mas vendió");
		String marcaAnotada = sc.nextLine();
		Comercial c = buscarMarcaMasVendida(comerciales, marcaAnotada);
		int maxUnidades = c.getUnidadesMarca(marcaAnotada);
		System.out.println("Empleado nº " + c.getNumero() + "\n" + "Nombre : " + c.getNombre()
				+ " |  Unidades máximas vendidas  : " + maxUnidades);
		if (c instanceof Fijo) {
			System.out.println("Empleado de tipo Fijo");
		} else {
			System.out.println("Empleado de tipo Comisión");

		}
		System.out.println("----------------------------");
		System.out.println("Comerciales ordenados");
		Collections.sort(comerciales);
		for (Comercial co : comerciales) {
				System.out.println(co);
			}

	}

	public static Comercial buscarMarcaMasVendida(ArrayList<Comercial> comerciales, String marcaAnotada) {

		int maxUnidades = comerciales.get(0).getUnidadesMarca(marcaAnotada);
		int posicion = 0;
		for (int i = 1; i < comerciales.size(); i++) {
			if (comerciales.get(i).getUnidadesMarca(marcaAnotada) > maxUnidades) {
				maxUnidades = comerciales.get(i).getUnidadesMarca(marcaAnotada);
				posicion = i;
			}

		}
		Comercial elegido = comerciales.get(posicion);
		return elegido;
	}

}

/*
 * if (c instanceof Fijo) { System.out.println("Empleado de tipo Fijo");
 * ((Fijo)c).aumentaExtra hacemos casting porque el metodo es de un hijo y no lo
 * tiene el padre para ello c lo hacemos casting al tipo de comercial que es el
 * que tiene ese metodo suyo de hijo } else {
 * System.out.println("Empleado de tipo Comisión");
 * 
 * }
 */
