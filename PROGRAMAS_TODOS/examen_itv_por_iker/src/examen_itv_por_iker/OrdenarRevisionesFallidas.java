package examen_itv_por_iker;

import java.util.Comparator;

public class OrdenarRevisionesFallidas implements Comparator<Vehiculo> {

	@Override
	public int compare(Vehiculo o1, Vehiculo o2) {
		int comparacion = o1.getMatricula().compareTo(o2.getMatricula());
		if (comparacion != 0) {
			return comparacion; 
		}
		return o1.getFechaHoy().compareTo(o2.getFechaHoy()); //las fechas tienen compareTo
	}

}

/*si pones o1 ira primero ese asiq retorna -1*/
/*
 * 
 * o2.getMatricula().compareTo(o1.getMatricula())  // Z a la A
o2.getFechaHoy().compareTo(o1.getFechaHoy())    // más reciente primero
 * */
