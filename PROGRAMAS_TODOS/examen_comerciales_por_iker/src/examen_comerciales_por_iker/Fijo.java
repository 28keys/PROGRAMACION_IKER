package examen_comerciales_por_iker;

import java.util.HashMap;
import java.util.HashSet;

public class Fijo extends Comercial {
	private double importeFijo;

	public Fijo(String nombre, HashMap<String,Integer> unidades, double importeFijo) {
		super(nombre, unidades);
		this.importeFijo = importeFijo;

	}

}
