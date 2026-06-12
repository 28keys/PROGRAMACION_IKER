package ordinariaRA7_itv;

import java.util.Comparator;

public class OrdenVehiculos implements Comparator<Vehiculo> {
	public int compare(Vehiculo o1, Vehiculo o2) {
		int comparar = o1.getMatricula().compareTo(o2.getMatricula());
		if (comparar > 0) {
			return 1;
		}
		if (comparar < 0) {
			return -1;
		}

		if (comparar == 0) {
			int fechaComparar = o1.getFechaFallo().compareTo(o2.getFechaFallo());
			if (fechaComparar > 0) {
				return 1;
			}
			if (fechaComparar < 0) {
				return -1;
			}

		}
		return 0;
	}

}
