package examen_comerciales_por_iker;

import java.util.HashMap;
import java.util.HashSet;

public class Comision extends Comercial {
	private String acuerdoMarca;

	public Comision(String nombre, HashMap<String, Integer> unidades, String acuerdoMarca) {
		super(nombre, unidades);
		this.acuerdoMarca = acuerdoMarca;
	}

}
