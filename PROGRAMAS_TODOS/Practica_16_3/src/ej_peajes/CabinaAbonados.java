package ej_peajes;

import java.util.ArrayList;
import java.util.LinkedList;

public class CabinaAbonados extends Cabina {
	private static int vehiculosAbonados;
	
	public CabinaAbonados(boolean estado,int numero) {
		super(estado,numero);
	}

	public double pagar(double km) {
		double total = 0;
		String tipo = cola.getFirst().getTipo();
		if (tipo.equalsIgnoreCase("turismo")) {
			if (km <= 10) {
				total = 2;
			} else if (km > 10) {
				total = 5;
			}
		}
		if (tipo.equalsIgnoreCase("moto")) {
			if (km <= 10) {
				total = 2 * 0.8;
			} else if (km > 10) {
				total = 5 * 0.8;
			}
		}
		if (tipo.equalsIgnoreCase("otro")) {
			if (km <= 10) {
				total = 2 * 1.2;
			} else if (km > 10) {
				total = 5 * 1.2;
			}
		}
		cola.removeFirst(); // borro el primero al que le he cobrado
		super.atender();
		vehiculosAbonados++;
		return total;

	}
	



	@Override
	public String toString() {
		return "-TIPO DE CABINA: CabinaAbonados\n" + super.toString();
	}

}
