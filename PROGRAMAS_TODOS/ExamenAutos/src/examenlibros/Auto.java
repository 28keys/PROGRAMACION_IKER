package examenlibros;

import java.time.LocalDate;

public class Auto{
	private String matricula,propietario;
	private LocalDate fechaCompra;
	private double precio;
	
	
	public Auto(String matricula, String propietario, LocalDate fechaCompra, double precio) {
		super();
		this.matricula = matricula;
		this.propietario = propietario;
		this.fechaCompra = fechaCompra;
		this.precio = precio;
	}


	public LocalDate getFechaCompra() {
		return fechaCompra;
	}


	public double getPrecio() {
		return precio;
	}


	public String getPropietario() {
		return propietario;
	}


	@Override
	public String toString() {
		return  matricula + ","+ propietario + "," + fechaCompra
				+ "," + precio;
	}
	
	
	
}