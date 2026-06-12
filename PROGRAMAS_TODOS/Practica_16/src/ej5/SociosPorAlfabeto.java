package ej5;

import java.util.Comparator;

import ejercicio_gimnasio.Socio;

public class SociosPorAlfabeto implements Comparator<Socio> {

	@Override
	public int compare(Socio o1, Socio o2) {
		return o1.getNombre().compareToIgnoreCase(o2.getNombre());
	}

}