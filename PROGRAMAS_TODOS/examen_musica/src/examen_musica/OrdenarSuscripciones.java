package examen_musica;

import java.util.Comparator;

public class OrdenarSuscripciones implements Comparator<Suscripcion> {

	@Override
	public int compare(Suscripcion o1, Suscripcion o2) {
		
		if (o1.getFechaInicio().isBefore(o2.getFechaInicio()) ) {
			return -1;
		}
		
		if (o1.getFechaInicio().isAfter(o2.getFechaInicio()) ) {
			return 1;
		}
		
		if (o1.getImporte() < o2.getImporte()) {
			return 1;
		}
		if (o1.getImporte() > o2.getImporte()) {
			return -1;
		}
		
		return 0;
	}

}
