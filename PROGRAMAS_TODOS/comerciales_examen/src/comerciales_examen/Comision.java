package comerciales_examen;

import java.util.ArrayList;

public class Comision extends Comercial {

	public Comision(String nombre, int[] unidades) {
		super(nombre, unidades);

	}

	public double pagar() {
		double totalUnidades = 0;
		double total;
		for (int i = 0; i < unidades.length; i++) {
			totalUnidades += unidades[i];

		}
		total = (totalUnidades * 10) + ((unidades[0] / 5) * 20);
		totalGanancias = total;
		return total;
	}
}
