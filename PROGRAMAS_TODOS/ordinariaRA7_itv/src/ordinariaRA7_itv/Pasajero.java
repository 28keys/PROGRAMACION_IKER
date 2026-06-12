package ordinariaRA7_itv;

import java.util.HashSet;
import java.util.Random;

public class Pasajero extends Vehiculo {
	private int nplazas;

	public Pasajero(String matricula, String dni, String tipoCarburante, boolean tieneCita, int nplazas) {
		super(matricula, dni, tipoCarburante, tieneCita);
		this.nplazas = nplazas;
	}

	@Override
	public double pasarITV() throws ToaquizaRomeroIker {
		double total = 0;
		Random puntuacion = new Random();
		int nota = puntuacion.nextInt(10);
		if (nota < 5) {
			throw new ToaquizaRomeroIker("NO HAS PASADO LA ITV");
		} else {
			if (tieneCita == true) {
				if (tipoCarburante.equalsIgnoreCase("diesel") || tipoCarburante.equalsIgnoreCase("gasolina")) {
					total = 50;
				} else {
					total = 30;
				}
			} else { // si no tiene cita aplicamos el 20% a cada tipo
				if (tipoCarburante.equalsIgnoreCase("diesel") || tipoCarburante.equalsIgnoreCase("gasolina")) {
					total = 50 * 1.2;
				} else {
					total = 30 * 1.2;
				}

			}
		}
		total += (intentosFallidos * 20);
		return total;
	}

	@Override
	public String toString() {
		return " Vehiculo de Pasajeros --> nº plazas=" + nplazas + "\n" + super.toString();
	}

}
