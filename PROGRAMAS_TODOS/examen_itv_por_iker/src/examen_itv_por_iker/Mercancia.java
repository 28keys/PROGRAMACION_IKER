package examen_itv_por_iker;

import java.util.Random;

public class Mercancia extends Vehiculo {
	private int cargaMaxima;

	public Mercancia(String matricula, String carburante, String dni, boolean cita, int cargaMaxima) {
		super(matricula, carburante, dni, cita);
		this.cargaMaxima = cargaMaxima;
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
				importe = (cargaMaxima / 1000) * 25;
			} else {
				importe = (cargaMaxima / 1000) * (25 * 1.15);
			}
		}
		pasanITV++;
		return importe + (intentosFallidos * 20);
	}

	@Override
	public String toString() {
		return "Mercancia : cargaMaxima=" + cargaMaxima + "\n"+super.toString();
	}

}
