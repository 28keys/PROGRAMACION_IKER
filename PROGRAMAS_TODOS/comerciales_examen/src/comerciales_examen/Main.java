package comerciales_examen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.ENGLISH);
		Random r = new Random();
		ArrayList<Comercial> comerciales = new ArrayList<Comercial>();
		String[] marca = new String[3];
		marca[0] = "A";
		marca[1] = "B";
		marca[2] = "C";

		System.out.println("Cuantos comerciales quieres ?");
		int comercial = sc.nextInt();

		if (comercial > 4) {
			System.out.println("Limite máximo de comerciales de 4");
			System.exit(0);
		} else {
			System.out.println("Introduce importe fijo");
			double importe = sc.nextDouble();
			sc.nextLine();
			for (int j = 0; j < comercial; j++) {
				int[] unidades = new int[3]; // cada vez que se repite el bucle crea un nuevo espacio en la ram por el
												// new
				System.out.println("Anota nombre");
				String nombre = sc.nextLine();
				for (int i = 0; i < unidades.length; i++) {
					System.out.println("Anota unidades vendidas de cada marca (A,B,C)");
					unidades[i] = sc.nextInt();
				}
				sc.nextLine(); // Limpio buffer, no me pillaba el tipo de comercial
				String tipo;
				System.out.println("fijo o comision?");
				tipo = sc.nextLine();

				if (tipo.equalsIgnoreCase("fijo")) {
					comerciales.add(new Fijo(nombre, unidades, importe));

				} else {
					comerciales.add(new Comision(nombre, unidades));
				}
			}
		}
		int salir = 0;
		do {
			int nEmpleado = r.nextInt(4) + 1; // +1 por si me sale numero 0 y no tengo empleados desde 0 , solo a partir
												// de 1
			int rmarca = r.nextInt(marca.length); // marca.lenght son las posiciones del array, ej 1
			System.out.println("Nº de empleado " + nEmpleado);
			System.out.println("Marca " + marca[rmarca]); // si sale 1 llamamos a la pos del array saliendo B
			Comercial comercialElegido = null;
			for (int k = 0; k < comerciales.size(); k++) {
				if (nEmpleado == comerciales.get(k).getNumero()) {
					System.out.println("Numero encontrado");
					comercialElegido = comerciales.get(k);
					break;
				}
			}
			if (comercialElegido == null) {
				System.out.println("El comercial no existe");
			} else {
				System.out.println("Introduce las unidades para la marca " + marca[rmarca]);
				int unidades = sc.nextInt();
				sc.nextLine(); // limpia buffer, no me pillaba int opcion
				comercialElegido.getUnidades()[rmarca] += unidades;
				System.out.println("Desas salir ? (-1)");
				salir = sc.nextInt();
			}
		} while (salir != -1);
		System.out.println("------PAGO DE EMPLEADOS------");
		for (Comercial c : comerciales) {
			c.pagar();
		}
		System.out.println("Empleados pagados con éxito");
		sc.nextLine();
		String marc;
		System.out.println("Anota marca");
		marc = sc.nextLine();

		// guardamos la pos de la marca
		int posMarca = -1;
		if (marc.equalsIgnoreCase("A")) {
			posMarca = 0;
		}

		if (marc.equalsIgnoreCase("B")) {
			posMarca = 1;
		}

		if (marc.equalsIgnoreCase("C")) {
			posMarca = 2;
		}
		int max = -1;
		Comercial empleadoVendidoMas = null;
		for (int i = 0; i < comerciales.size(); i++) {
			int unidadesEmple = comerciales.get(i).getUnidades()[posMarca]; // filtramos solo por las unidades de la pos
																			// de la marca anotada ej B
			if (unidadesEmple > max) { // si las unidades de B son mayores que -1....
				max = comerciales.get(i).getUnidades()[posMarca]; // ej 47, pues max = 47
				empleadoVendidoMas = comerciales.get(i); // apuntamos al indice 0 ej: Pepe empleadoVendidoMas = Pepe
				// y volvemos a repetir. Si por ej en el indice 1 pos de marca B Ana vende 56,
				// pos 56 > 47
				// empleadoVendidoMas = Ana
				// y asi hasta recorrer el for y ver quien tiene mas unidades en cada marca ej:B
			}

		}
		System.out.println("El empleado que vendió mas de la marca " + marc + " es :\n" + empleadoVendidoMas.getNombre()
				+ " | Nº de empleado " + empleadoVendidoMas.getNumero() + " | Unidades vendidas "
				+ empleadoVendidoMas.getUnidades()[posMarca]);
		if (empleadoVendidoMas instanceof Fijo) {
			System.out.println("Empleado de tipo Fijo");
		} else {
			System.out.println("Empleado de tipo Comisión");
		}

		Collections.sort(comerciales, new OrdenarComerciales());
		System.out.println("Empleados");
		for (Comercial c : comerciales) {
			System.out.println(c.getNombre() + " | Ganancias " + c.getTotalGanancias());
		}
	}

}
