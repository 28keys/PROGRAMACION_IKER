package examen_comerciales_por_iker;

import java.util.HashMap;
import java.util.HashSet;

public class Fijo extends Comercial {
	private double importeFijo;

	public Fijo(String nombre, HashMap<String, Integer> unidades, double importeFijo) {
		super(nombre, unidades);
		this.importeFijo = importeFijo;

	}

	@Override
	public double pagar() {
		double importe;
		importe = importeFijo;
		for (String clave : unidades.keySet()) {
			int u = unidades.get(clave);
			u = u * 5;
			importe += u;
		}
		ganado+=importe;
		return importe;
	}

}
