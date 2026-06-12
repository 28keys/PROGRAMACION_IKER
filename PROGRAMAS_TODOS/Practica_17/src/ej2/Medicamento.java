package ej2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Medicamento implements Caducable {
	private String medicina;
	private LocalDate fechaCaducidad;

	public Medicamento(String medicina, LocalDate fechaCaducidad) {
		super();
		this.fechaCaducidad = fechaCaducidad;
		this.medicina = medicina;
	}

	@Override
	public boolean caducado() {
		if (LocalDate.now().isAfter(fechaCaducidad))
			return true;
		return false;
	}

	@Override
	public int diasCaducidad() {
		long dias;
		dias = ChronoUnit.DAYS.between(LocalDate.now(), fechaCaducidad);
		return (int) dias;
	}

	@Override
	public String toString() {
		return "Medicamento [medicina=" + medicina + ", fechaCaducidad=" + fechaCaducidad + "]";
	}

}
