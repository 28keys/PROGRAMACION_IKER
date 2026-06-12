package ej2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {

		ArrayList<Caducable> caducados = new ArrayList<Caducable>();
		caducados.add(new Medicamento("Ibuprofeno", LocalDate.of(2024, 1, 29)));
		caducados.add(new Medicamento("Frenadol", LocalDate.of(2000, 9, 5)));

		caducados.add(new ClaseCarnet("Iker Marcelo Toaquiza", "2713-JSS", LocalDate.of(2027, 4, 10)));
		caducados.add(new ClaseCarnet("Gio Siradze", "6345-XDP", LocalDate.of(2030, 6, 20)));

		for (Caducable c : caducados) {
			System.out.println(c.toString());
		
			if (c.caducado())
				System.out.println("Ha caducado");
			else
				System.out.println("Te quedan " + c.diasCaducidad() + " dias para que caduque");
		}

	}

}
