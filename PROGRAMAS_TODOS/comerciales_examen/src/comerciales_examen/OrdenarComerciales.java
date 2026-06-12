package comerciales_examen;

import java.util.Comparator;
//Se crea una clase y despues se implemente el interfaz
//No se crea una interfaz con {} llaves
public class OrdenarComerciales implements Comparator<Comercial> {

	@Override
	public int compare(Comercial o1, Comercial o2) {
		if (o1.getTotalGanancias() > o2.getTotalGanancias()) {
			return -1; // el mayor va antes
		}
		if (o1.getTotalGanancias() < o2.getTotalGanancias()) {
			return 1; // el mayor después
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
