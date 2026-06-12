package ordinariaRA7_itv;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class Vehiculo {
	private String matricula;
	protected String tipoCarburante;
	private String dni;
	protected boolean tieneCita;
	private LocalDate fechaFallo;
	private String motivo;
	protected int intentosFallidos;
	private static int totalVehIntentan = 0;
	private static int totalVehAprueban = 0;

	public Vehiculo(String matricula, String dni, String tipoCarburante, boolean tieneCita) {
		super();
		this.tieneCita = tieneCita;
		this.matricula = matricula;
		this.tipoCarburante = tipoCarburante;
		this.dni = dni;
		this.fechaFallo = fechaFallo;
		this.motivo = motivo;
		totalVehIntentan++;
	}

	public static void incrementarAprobados() {
		totalVehAprueban++;
	}

	public static double calcularPorcentajeAprobados() {
		if (totalVehIntentan == 0) { //se evita dividir por 0 si no ha venido ningun vehiculo
			return 0;
		}
		return (totalVehAprueban * 100) / totalVehIntentan;
	}

	public void incrementarFallos() {
		intentosFallidos++;
	}

	public int getIntentosFallidos() {
		return intentosFallidos;
	}

	public boolean isTieneCita() {
		return tieneCita;
	}

	public String getMatricula() {
		return matricula;
	}

	public LocalDate getFechaFallo() {
		return fechaFallo;
	}

	public void setFechaFallo(LocalDate fechaFallo) {
		this.fechaFallo = fechaFallo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public abstract double pasarITV() throws ToaquizaRomeroIker;

	@Override
	public String toString() {
		return "Matricula: " + matricula + ", Carburante:" + tipoCarburante + ", DNI: " + dni + ", Cita: " + tieneCita;
	}

}
