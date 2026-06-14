package examen_comerciales_por_iker;

import java.util.Collections;
import java.util.HashMap;
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
		LinkedList<Comercial> comerciales = new LinkedList<Comercial>();
		String[] marcas = new String[3];
		marcas[0] = "A";
		marcas[1] = "B";
		marcas[2] = "C";
		int numComerciales;
		System.out.println("Anota numero de comerciales");
		numComerciales = sc.nextInt();
		sc.nextLine();
		if (numComerciales > 4) {
			System.out.println("Como máximo 4 comerciales!!");
		} else if (numComerciales <= 0) {
			System.out.println("Como mínimo un comercial!!");
		} else {
			System.out.println("Anota precio para comerciales Fijos");
			double precioFijo = sc.nextDouble();
			sc.nextLine();
			for (int i = 0; i < numComerciales; i++) {
				System.out.println("Anota nombre");
				String nombre = sc.nextLine();
				HashMap<String, Integer> unidades = new HashMap<String, Integer>();

				System.out.println("Anota unidades de la marca A");
				int uA = sc.nextInt();
				unidades.put("A", uA);
				System.out.println("Anota unidades de la marca B");
				int uB = sc.nextInt();
				unidades.put("B", uB);
				System.out.println("Anota unidades de la marca C");
				int uC = sc.nextInt();
				unidades.put("C", uC);
				sc.nextLine();

				System.out.println("Fijo o comision?");
				String tipoEmpleado = sc.nextLine();
				if (tipoEmpleado.equalsIgnoreCase("Fijo")) {
					comerciales.add(new Fijo(nombre, unidades, precioFijo));
				} else {
					String marcaAcuerdo;
					do {
						System.out.println("Anota marca con la que hay acuerdo");
						marcaAcuerdo = sc.nextLine();
					} while (!(marcaAcuerdo.equals("A") || marcaAcuerdo.equals("B") || marcaAcuerdo.equals("C")));
					comerciales.add(new Comision(nombre, unidades, marcaAcuerdo));
				}

			}
		}

		int opcion = 0;
		while (opcion != -1) {
			// Generamos el numero y marca aleatorios
			Random aleatorio = new Random();
			int numEmpleado = aleatorio.nextInt(4) + 1;
			int marca = aleatorio.nextInt(marcas.length) + 1;
			String marcaComerciales = null;
			// Validamos que existan en el comercial
			if (marca == 1) {
				marcaComerciales = "A";
			}

			if (marca == 2) {
				marcaComerciales = "B";

			}

			if (marca == 3) {
				marcaComerciales = "C";

			}

			boolean esNum = false;
			Comercial encontrado = null;
			for (int i = 0; i < comerciales.size(); i++) {
				if (numEmpleado == comerciales.get(i).getNumEmple()) {
					esNum = true;
					encontrado = comerciales.get(i);
					break;
				}
			}
			if (!esNum) {
				System.out.println("El nº de empleado" + numEmpleado + " no existe");
			} else {
				System.out.println("Empleado nº" + encontrado.getNumEmple() + "\nNombre :" + encontrado.getNombre());
				System.out.println("Anota nº de unidades para la marca " + marcaComerciales);
				int unidades = sc.nextInt();
				encontrado.getUnidades().put(marcaComerciales,
						encontrado.getUnidades().get(marcaComerciales) + unidades);
			}
			System.out.println("Seguir (1) ? o Salir ? (-1)");
			opcion = sc.nextInt();
			sc.nextLine();
		}

		for (Comercial com : comerciales) {
			com.pagar();
		}
		System.out.println("Comerciales cobrados");
		String marca;
		do {
			System.out.println("Anota marca");
			marca = sc.nextLine();
		} while (!marca.matches("[A-C]{1}"));
		int maximo = -1;
		Comercial masAVendido = null;
		Iterator<Comercial> it = comerciales.iterator();
		while (it.hasNext()) {
			Comercial c1 = it.next();
			int unidades = c1.getUnidades().get(marca);
			if (unidades > maximo) {
				maximo = unidades;
				masAVendido = c1;
			}
		}
		System.out.println(
				"Comercial que mas ha vendido: \nNº" + masAVendido.getNumEmple() + "\nNombre:" + masAVendido.getNombre()
						+ "\nUnidades vendidas de la marca -->" + marca + " :" + masAVendido.getUnidades().get(marca));

		System.out.println("Comerciales ordenados:");
		Collections.sort(comerciales, new OrdenarComerciales());
		for (Comercial c : comerciales) {
			System.out.println(c);
		}

	}

}
