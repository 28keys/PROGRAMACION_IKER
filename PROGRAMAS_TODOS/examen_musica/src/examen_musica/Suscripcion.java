package examen_musica;

import java.time.LocalDate;

public abstract class Suscripcion implements Facturable {
	private String mail;
	private LocalDate fechaInicio;
	private double importe;
	private String tipo;
	protected static double totalPrecio;
	protected int dispositivosPremium;
	protected static int totalSuscripciones;

	public Suscripcion(String mail, LocalDate fechaInicio) {
		super();
		this.mail = mail;
		this.fechaInicio = fechaInicio;
	}

	public static int getTotalSuscripciones() {
		return totalSuscripciones;
	}

	public static double getTotalPrecio() {
		return totalPrecio;
	}

	public static void setTotalPrecio(double totalPrecio) {
		Suscripcion.totalPrecio += totalPrecio;
	}

	public int getDispositivosPremium() {
		return dispositivosPremium;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fecha_Inicio) {
		this.fechaInicio = fecha_Inicio;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "Mail=" + mail + ", fecha_Inicio=" + fechaInicio + " IMPORTE : " + importe;
	}

	@Override
	public double calcularPrecio() {
		return importe;

	}

}
