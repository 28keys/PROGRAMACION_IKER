package examen_autos_ej_4;

import java.util.Comparator;

public class OrdenarNotas implements Comparator<Alumno> {

	@Override
	public int compare(Alumno o1, Alumno o2) {
		if (o1.getNota() > o2.getNota()) {
			return -1;
		}
		if (o1.getNota() < o2.getNota()) {
			return 1;
		}
		return 0;
	}

}
