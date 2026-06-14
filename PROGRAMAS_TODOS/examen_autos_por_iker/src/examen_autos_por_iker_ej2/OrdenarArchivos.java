package examen_autos_por_iker_ej2;

import java.util.Comparator;

public class OrdenarArchivos implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}

}
