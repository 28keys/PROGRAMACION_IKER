package ej_peajes;

import java.util.ArrayList;
import java.util.LinkedList;

public class CabinaNormal extends Cabina {

	public CabinaNormal(boolean estado, int numero) {
		super(estado,numero);
	}

	public double pagar(double km) {
		double total = 0;
		String tipo = cola.getFirst().getTipo();
		if (tipo.equalsIgnoreCase("turismo")) {
			if (km <= 10) {
				total = 3;
			} else if (km > 10) {
				total = km * 0.3;
			}

		}
		if (tipo.equalsIgnoreCase("moto")) {
			total = 5;
		}

		if (tipo.equalsIgnoreCase("otro")) {
			total = 5 * 1.25;
		}
		cola.removeFirst();
		super.atender();
		return total;

	}

	@Override
	public String toString() {
		return "-TIPO DE CABINA: CabinaNormal\n" + super.toString();
	}

}
/*
 * 1 es el 100% si hago 1.25 es 100 + 25 extras si es 20% menos no es 0,2 porque
 * estoy haciendo un descuento del 80% yo quiero la diferencia osea 100-20% =
 * 80% eso si es pagar un 20% menos (pagas un 20% del 100 no)
 */
