package ejercicio_gimnasio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Resto extends Socio {
	private static int numeroSocio;
	private double importeTotal;

	public Resto(String nombre, String apellido, String tfno, LocalDate fechaNacimiento) {
		super(nombre, apellido, tfno, fechaNacimiento);
		numeroSocio++;
		this.codigoSocio = "N" + numeroSocio;
	}

	public long salirGimnasio() {
		long tpo = super.salirGimnasio();
		if (tpo < 30)
			importeTotal += 6;
		else if (tpo < 60)
			importeTotal += 9;
		else
			importeTotal += 12;
		return tpo;
	}

	@Override
	double cobrarMensualidad() {
		double importe = importeTotal;
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
		return "\n" + "Socio Resto : " + "\n" + super.toString();
	}

}