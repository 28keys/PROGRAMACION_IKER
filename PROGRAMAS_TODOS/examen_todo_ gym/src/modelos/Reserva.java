package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva {
	private String codigoclase, usuario;
	private LocalDate fechaReserva;
	private int preferencia;

	public Reserva(String codigoclase, String usuario) {
		super();
		this.codigoclase = codigoclase;
		this.usuario = usuario;
		this.fechaReserva = null;
		this.preferencia = 0;
	}

	
	
	
	public int getPreferencia() {
		return preferencia;
	}

	public LocalDate getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public void setPreferencia(int preferencia) {
		this.preferencia = preferencia;
	}

	public String getCodigoclase() {
		return codigoclase;
	}

	public void setCodigoclase(String codigoclase) {
		this.codigoclase = codigoclase;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Reserva [codigoclase=" + codigoclase + ", usuario=" + usuario + ", fechaReserva=" + fechaReserva + "]";
	}

}
