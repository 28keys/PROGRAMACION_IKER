package examen_musica;

import java.time.LocalDate;

public class Basica extends Suscripcion {
	private final double precio = 4.99;

	public Basica(String mail, LocalDate fechaInicio) {
		super(mail, fechaInicio);
		totalSuscripciones+=1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularPrecio() {
		double total;
		total = precio;
		return total;

	}

	@Override
	public String toString() {
		return "\nBasica :" + super.toString();
	}

}
