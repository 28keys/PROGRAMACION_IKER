package taller;

import java.util.Comparator;

import modelos.Arreglo;

public class OrdenarArreglos implements Comparator<Arreglo> {

	@Override
	public int compare(Arreglo o, Arreglo o2) {
		if (o.getFechaEntrada().isBefore(o2.getFechaEntrada())) {
			return -1;
		}
		if (o.getFechaEntrada().isAfter(o2.getFechaEntrada())) {
			return 1;
		}
		if (o.getImporte() > o2.getImporte()) {
			return -1;
		}
		if (o.getImporte() < o2.getImporte()) {
			return 1;
		}
		return 0;
	}

}
