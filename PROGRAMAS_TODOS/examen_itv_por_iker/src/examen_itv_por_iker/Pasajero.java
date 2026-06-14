package examen_itv_por_iker;

import java.util.Random;

public class Pasajero extends Vehiculo {
	private int numeroPlazas;

	public Pasajero(String matricula, String carburante, String dni, boolean cita, int numeroPlazas) {
		super(matricula, carburante, dni, cita);
		this.numeroPlazas = numeroPlazas;
	}

	@Override
	public double pasarITV() throws Iker {
		totalITV++;
		double importe;
		Random aleatorio = new Random();
		int revision = aleatorio.nextInt(10);
		if (revision < 4) {
			intentosFallidos++;
			throw new Iker("Revisión fallida");
		} else {
			if (cita == true) {
				if (carburante.equals("diesel") || carburante.equals("gasolina")) {
					importe = 50;
				} else {
					importe = 30;
				}
			} else {
				if (carburante.equals("diesel") || carburante.equals("gasolina")) {
					importe = 50 * 1.2;
				} else {
					importe = 30 * 1.2;
				}
			}
		}
		pasanITV++;
		return importe + (intentosFallidos * 20);
	}

	@Override
	public String toString() {
		return "Pasajero : numeroPlazas=" + numeroPlazas + "\n" + super.toString();
	}

}
