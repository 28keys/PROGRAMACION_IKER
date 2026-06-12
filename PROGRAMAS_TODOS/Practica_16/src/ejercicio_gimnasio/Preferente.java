package ejercicio_gimnasio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Preferente extends Socio {
	private static int numeroSocio;
	private double importeTotal;

	public Preferente(String nombre, String apellido, String tfno, LocalDate fechaNacimiento) {
		super(nombre, apellido, tfno, fechaNacimiento);
		numeroSocio++;
		this.codigoSocio = "P" + numeroSocio;
	}

	@Override
	public double cobrarMensualidad() {
		double importe = importeTotal;
		importe = 10 + (2 * vecesMes);
		if (getEdad() > 60) {
			importe = importe * 0.8;
		}
		vecesMes = 0;
		importeTotal = 0;
		tiempoTotal = 0;

		return importe;

	}

	@Override
	public String toString() {
		return "\n" + "Socio Preferente  : " + "\n" + super.toString();
	}

}