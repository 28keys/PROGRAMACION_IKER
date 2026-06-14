package examen_autos_por_iker;

import java.util.Comparator;

public class OrdenarAutos implements Comparator<Automovil> {

	@Override
	public int compare(Automovil o1, Automovil o2) {
		if (o1.getFechaCompra().isBefore(o2.getFechaCompra())) {
			return -1;
		}
		if (o1.getFechaCompra().isAfter(o2.getFechaCompra())) {
			return 1;
		}
		if (o1.getPrecio() < o2.getPrecio()) {
			return -1;
		}
		if (o1.getPrecio() > o2.getPrecio()) {
			return 1;
		}
		return 0;
	}

}
