package examen_comerciales_por_iker;

import java.util.HashMap;
import java.util.HashSet;

public class Comision extends Comercial {
	private String acuerdoMarca;

	public Comision(String nombre, HashMap<String, Integer> unidades, String acuerdoMarca) {
		super(nombre, unidades);
		this.acuerdoMarca = acuerdoMarca;
	}

	@Override
	public String toString() {
		return "Comision [acuerdoMarca=" + acuerdoMarca + "\n" + super.toString();
	}

	@Override
	public double pagar() {
		double importe = 0;
		for (String clave : unidades.keySet()) {
			int unid = unidades.get(clave);
			if (clave.equals(acuerdoMarca)) {
				importe += (unid / 5) * 20;
			}
			importe += unid * 10;
		}
		ganado+=importe;
		return importe;
	}

}
