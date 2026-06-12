package ej13;

import java.time.LocalDate;

public class Alumno {
	private String nombre, apellido;
	private double nota;
	private LocalDate fechaNac;

	public Alumno(String nombre, String apellido, double nota, LocalDate fechaNac) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nota = nota;
		this.fechaNac = fechaNac;
	}

	public double getNota() {
		return nota;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", apellido=" + apellido + ", nota=" + nota + ", fechaNac=" + fechaNac
				+ "]";
	}

}
