package ejercicio_gimnasio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public abstract class Socio {
	private int edad;
	private String nombre;
	private String apellido;
	private String tfno;
	private LocalDate fechaNacimiento;
	protected String codigoSocio;
	private LocalTime horaEntrada;
	private LocalTime horaSalida;
	protected int vecesGym, vecesMes, tiempoTotal;

	// vecesMes es el total de visitas de todos los socios
	/*
	 * Porque mientras se cuentan por separado las veces que va al gym un preferente
	 * o uno normal, se cuenta por detras todas las visitas para el calculo de la
	 * mensualidad dejarlo en 0
	 */
	public Socio(String nombre, String apellido, String tfno, LocalDate fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tfno = tfno;
		this.fechaNacimiento = fechaNacimiento;

	}

	public int getEdad() {
		return (int) ChronoUnit.YEARS.between(this.getFechaNacimiento(), LocalDate.now());
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCodigoSocio() {
		return codigoSocio;
	}

//	public LocalTime tiempoGimnasio ();

	public boolean entradaGimnasio() {
		if (horaEntrada != null)
			return false;
		horaEntrada = LocalTime.now();
		vecesGym++;
		vecesMes++;
		return true;
	};

	public long salirGimnasio() {
		long segundos;
		if (horaEntrada == null)
			return 0;
		segundos = ChronoUnit.SECONDS.between(horaEntrada, LocalTime.now());
		tiempoTotal += segundos;
		horaEntrada = null;
		return segundos;
	};

	abstract double cobrarMensualidad();

	@Override
	public String toString() {
		return "Mi codigo de socio: " + codigoSocio + "\n" + "Tu nombre = " + nombre + ", Apellido = " + apellido
				+ ", Telefono = " + tfno + ", Fecha de nacimiento = " + fechaNacimiento + "\n"
				+ "Ha acudido un total de " + vecesGym + " dia/s . Y el tiempo total que ha estado es de "
				+ tiempoTotal;
	}

}