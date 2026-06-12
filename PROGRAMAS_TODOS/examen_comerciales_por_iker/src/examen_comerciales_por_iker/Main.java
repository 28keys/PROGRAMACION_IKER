package examen_comerciales_por_iker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.ENGLISH);
		LinkedList<Comercial> comerciales = new LinkedList<Comercial>();
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

		for (Comercial c : comerciales) {
			System.out.println("Empleado nº " + c.getNumEmple() + " Nombre : " + c.getNombre());
			for (String clave : c.getUnidades().keySet()) {
				System.out.println("Marca " + clave + "\n" + c.getUnidades().get(clave));
			}
		}
	}

}
