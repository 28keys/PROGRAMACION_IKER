package comerciales_examen;

import java.util.ArrayList;

public class Fijo extends Comercial {
	double importe;

	public Fijo(String nombre, int[] unidades, double importe) {
		super(nombre, unidades);
		this.importe = importe;
		this.unidades = unidades;
	}

	public double pagar() {
		double totalUnidades = 0;
		double total;
		for (int i = 0; i < unidades.length; i++) {
			totalUnidades += unidades[i];
		}

		total = totalUnidades * 5;
		total += importe;
		totalGanancias = total;
		return total;

	}
}
