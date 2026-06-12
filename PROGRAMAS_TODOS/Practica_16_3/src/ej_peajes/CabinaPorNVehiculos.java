package ej_peajes;

import java.util.Comparator;

public class CabinaPorNVehiculos implements Comparator<Cabina> {
	@Override
	public int compare(Cabina arg0, Cabina arg1) {
		if (arg0.getVehiculosAtendidos() < arg1.getVehiculosAtendidos()) {
			return -1;
		}
		if (arg0.getVehiculosAtendidos()> arg1.getVehiculosAtendidos()) {
			return 1;
		}
		
		return 0;
	}

}
