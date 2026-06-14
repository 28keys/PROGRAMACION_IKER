package examen_autos_por_iker;

import java.time.LocalDate;

public class Automovil {
	private String matricula;
	private LocalDate fechaCompra;
	private double precio;
	private String dni, nombre, apellido;

	public Automovil(String matricula, LocalDate fechaCompra, double precio, String dni, String nombre,
			String apellido) {
		super();
		this.matricula = matricula;
		this.fechaCompra = fechaCompra;
		this.precio = precio;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Automovil [matricula=" + matricula + ", fechaCompra=" + fechaCompra + ", precio=" + precio + ", dni="
				+ dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

}
