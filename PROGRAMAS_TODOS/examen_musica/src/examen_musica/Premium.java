package examen_musica;

import java.time.LocalDate;

public class Premium extends Suscripcion {
	private int limite = 5;
	private int numDispositivos;

	public Premium(String mail, LocalDate fechaInicio, int numDispositivos) {
		super(mail, fechaInicio);
		this.numDispositivos = numDispositivos;
		dispositivosPremium = numDispositivos;
		totalSuscripciones+=1;
	}

	@Override
	public double calcularPrecio() {
		double total = 0;
		if (numDispositivos >= 1 && numDispositivos <= 2) {
			total = 9.99;
		}
		if (numDispositivos >= 3 && numDispositivos <= limite) {
			total = 14.99;
		}
		return total;
	}

	@Override
	public String toString() {
		return "\nPremium :  nº dispositivos=" + numDispositivos + "\n" + super.toString();
	}

}
