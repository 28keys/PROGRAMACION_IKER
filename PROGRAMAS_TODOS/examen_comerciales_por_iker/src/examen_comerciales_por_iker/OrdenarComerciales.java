package examen_comerciales_por_iker;

import java.util.Comparator;

public class OrdenarComerciales implements Comparator<Comercial> {

	@Override
	public int compare(Comercial o1, Comercial o2) {
		if (o1.getGanado() > o2.getGanado()) {
			return -1;
		}
		if (o1.getGanado() < o2.getGanado()) {
			return 1;
		}
		if (o1 instanceof Fijo && o2 instanceof Comision) {
			return -1;
		}
		if (o1 instanceof Comision && o2 instanceof Fijo) {
			return 1;
		}
		return 0;
	}

}
