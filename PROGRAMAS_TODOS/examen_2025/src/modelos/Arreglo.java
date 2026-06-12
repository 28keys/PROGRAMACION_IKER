package modelos;

import java.time.LocalDate;
import java.util.Objects;

public class Arreglo {
	private int idFactura, idMecanico;
	private String matricula;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private double importe;
	
	
	public Arreglo(int idFactura, int idMecanico, String matricula, LocalDate fechaEntrada, LocalDate fechaSalida,
			double importe) {
		super();
		this.idFactura = idFactura;
		this.idMecanico = idMecanico;
		this.matricula = matricula;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.importe = importe;
	}
	
	
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getIdMecanico() {
		return idMecanico;
	}
	public void setIdMecanico(int idMecanico) {
		this.idMecanico = idMecanico;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public LocalDate getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arreglo other = (Arreglo) obj;
		return Objects.equals(matricula, other.matricula);
	}


	@Override
	public String toString() {
		return "Arreglo [idFactura=" + idFactura + ", idMecanico=" + idMecanico + ", matricula=" + matricula
				+ ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", importe=" + importe + "\n";
	}
	
	
}
