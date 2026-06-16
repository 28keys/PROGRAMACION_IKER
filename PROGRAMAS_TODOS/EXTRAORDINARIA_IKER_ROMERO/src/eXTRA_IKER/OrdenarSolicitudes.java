package eXTRA_IKER;

import java.util.Comparator;

public class OrdenarSolicitudes implements Comparator<Solicitud> {

	@Override
	public int compare(Solicitud o1, Solicitud o2) {
		if (o1.getImporte() > o2.getImporte()) {
			return -1;
		}
		if (o1.getImporte() < o2.getImporte()) {
			return 1;
		}
		int comparar = o1.getId().compareTo(o2.getId());
		if (comparar != 0) {
			return comparar;
		}
		return 0;
	}

}
