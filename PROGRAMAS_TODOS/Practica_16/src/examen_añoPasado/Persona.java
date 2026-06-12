package examen_añoPasado;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Persona {
	private int edad;
	double altura;

	public Persona(int edad, double altura) {
		super();
		this.edad = edad;
		this.altura = altura;
	}

	public long calcularEdad(LocalDate fechaNacimiento) {
		LocalDate hoy = LocalDate.now();
		long difftotaldias = ChronoUnit.YEARS.between(hoy, fechaNacimiento);
		return difftotaldias;

	}
}
