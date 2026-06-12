package ej2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ClaseCarnet implements Caducable {
	private String nombre;
	private String carnet;
	private LocalDate fechaCaducidad;

	public ClaseCarnet(String nombre, String carnet, LocalDate fechaCaducidad) {
		super();
		this.nombre = nombre;
		this.carnet = carnet;
		this.fechaCaducidad = fechaCaducidad;
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
		return "ClaseCarnet [nombre=" + nombre + ", carnet=" + carnet + ", fechaCaducidad=" + fechaCaducidad + "]";
	}

	
}
