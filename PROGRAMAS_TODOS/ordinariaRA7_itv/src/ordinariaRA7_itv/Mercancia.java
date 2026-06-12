package ordinariaRA7_itv;

import java.util.HashSet;
import java.util.Random;

public class Mercancia extends Vehiculo {
	private int cargaMaxima;

	public Mercancia(String matricula, String dni, String tipoCarburante, boolean tieneCita, int cargaMaxima) {
		super(matricula, dni, tipoCarburante, tieneCita);
		this.cargaMaxima = cargaMaxima;
	}

	@Override
	public double pasarITV() throws ToaquizaRomeroIker {
		double total;
		Random puntuacion = new Random();
		int nota = puntuacion.nextInt(10);
		if (nota < 5) {
			throw new ToaquizaRomeroIker("NO HAS PASADO LA ITV");
		} else {

			double precio = (cargaMaxima / 1000) * 25;
			if (tieneCita == false) {
				total = precio * 1.15;
			} else {
				total = precio;
			}
		}
		total += (intentosFallidos * 20);
		return total;
	}

	@Override
	public String toString() {
		return "Vehículo de Mercancía -->  Carga máxima : " + cargaMaxima + "kg" + "\n" + super.toString();
	}

}
