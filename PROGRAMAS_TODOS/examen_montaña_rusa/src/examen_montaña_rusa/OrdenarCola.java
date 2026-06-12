package examen_montaña_rusa;

import java.util.Comparator;

public class OrdenarCola implements Comparator<Persona> {

	@Override
	public int compare(Persona o1, Persona o2) {
		if (o1.getEdad() < o2.getEdad()) {
			return -1;
		}
		if (o1.getEdad() > o2.getEdad()) {
			return 1;
		}

		if (o1.getAltura() < o2.getAltura()) {
			return -1;
		}
		if (o1.getAltura() > o2.getAltura()) {
			return 1;
		}
		return 0;
	}

}
