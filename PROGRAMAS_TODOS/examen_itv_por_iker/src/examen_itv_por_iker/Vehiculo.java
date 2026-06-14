package examen_itv_por_iker;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Vehiculo {
	private String matricula, dni;
	protected String carburante;
	protected boolean cita = false;
	protected static double totalITV;
	protected static double pasanITV;
	protected int intentosFallidos = 0;
	private LocalDate fechaHoy;
	private String motivo;

	public Vehiculo(String matricula, String carburante, String dni, boolean cita) {
		super();
		this.cita = cita;
		this.matricula = matricula;
		this.carburante = carburante;
		this.dni = dni;
		this.intentosFallidos = intentosFallidos;
		this.fechaHoy = null;
		this.motivo = null;
	}

	public  static double calcularPorcentaje() {
		if (totalITV == 0) {
			return 0;
		}
		return (pasanITV / totalITV) * 100;
	}

	public LocalDate getFechaHoy() {
		return fechaHoy;
	}

	public void setFechaHoy(LocalDate fechaHoy) {
		this.fechaHoy = fechaHoy;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isCita() {
		return cita;
	}

	public void setCita(boolean cita) {
		this.cita = cita;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCarburante() {
		return carburante;
	}

	public void setCarburante(String carburante) {
		this.carburante = carburante;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}

	public abstract double pasarITV() throws Iker;

	@Override
	public String toString() {
		return "Matricula=" + matricula + ", carburante=" + carburante + ", dni=" + dni;
	}

}
