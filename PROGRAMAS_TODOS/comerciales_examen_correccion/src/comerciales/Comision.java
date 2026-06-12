package comerciales;

import java.util.HashSet;

public class Comision extends Comercial {
	private static final double comision = 10;
	private String marca;

	public Comision(String nombre) {
		super(nombre);
	}

	public Comision(String nombre, String marca) {
		super(nombre);
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Comision [ " + super.toString() + " marca =" + marca + "]";
	}

	@Override
	double pagar() {

		int totalM = nA + nB + nC;

		double total = 0;
		total = totalM * 10;

		if (marca.equalsIgnoreCase("A")) {
			total += (nA / 5) * 20;
		}

		if (marca.equalsIgnoreCase("B")) {
			total += (nB / 5) * 20;
		}

		if (marca.equalsIgnoreCase("C")) {
			total += (nC / 5) * 20;
		}
		ganado += total;
		return total;
	}

}


/*Cuidadom antes tenia compareTo tmb en las clases hijas y retornaban 0 porque
 * era el que me puso por defecto el sistema, al hacer eso nunca ordenaba porque estaba
 * retornando 0 todo el rato*/
