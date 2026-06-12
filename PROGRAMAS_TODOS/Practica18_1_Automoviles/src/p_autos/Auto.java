package p_autos;

import java.time.LocalDate;

public class Auto implements Comparable<Auto> {
	private String matricula;
	private LocalDate fechaCompra;
	private double precio;
	private String dni;

	public Auto(String matricula, LocalDate fechaCompra, double precio, String dni) {
		super();
		this.matricula = matricula;
		this.fechaCompra = fechaCompra;
		this.precio = precio;
		this.dni = dni;
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

	@Override
	public String toString() {
		return "Auto [matricula=" + matricula + ", fechaCompra=" + fechaCompra + ", precio=" + precio + ", dni=" + dni
				+ "]";
	}

	@Override
	public int compareTo(Auto o) {
		if (this.fechaCompra.isBefore(o.getFechaCompra())) {
			return -1;
		}
		if (this.fechaCompra.isAfter(o.getFechaCompra())) {
			return 1;
		}

		if (this.precio < o.getPrecio()) {
			return -1;
		}
		if (this.precio > o.getPrecio()) {
			return 1;
		}

		return 0; // si tienen misma fecha y mismo precio

	}

}
