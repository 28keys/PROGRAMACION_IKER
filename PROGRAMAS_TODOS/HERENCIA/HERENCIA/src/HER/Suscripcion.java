package HER;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Suscripcion implements Iker {
	private String mail;
	private int contraseña;
	private LocalDate fechaFinal;
	private boolean conectado;
	protected static int totalPelis;
	protected int numPantallas;
	protected static double totalRecaudado;
	public Suscripcion(String mail, int contraseña, LocalDate fechaSuscrito) {
		super();
		this.mail = mail;
		this.contraseña = contraseña;
		this.fechaFinal = fechaSuscrito;
		conectado = false;
	}

	public static int getTotalPelis() {
		return totalPelis;
	}

	

	public static double getTotalRecaudado() {
		return totalRecaudado;
	}

	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getContraseña() {
		return contraseña;
	}

	public void setContraseña(int contraseña) {
		this.contraseña = contraseña;
	}

	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDate fechaSuscrito) {
		this.fechaFinal = fechaSuscrito;
	}

	@Override
	public String toString() {
		return "Suscripcion  mail=" + mail + ", contraseña=" + contraseña + ", fechaSuscrito=" + fechaFinal
				+ ", conectado=" + conectado+"\n";
	}

	public abstract void verPelicula(String peli);

	public abstract double cobrar();
	
	public abstract boolean limitePantallas();

	@Override
	public boolean conectar() {
		conectado = true;
		numPantallas++;
		return conectado;
	}

	@Override
	public boolean desconectar() {
		conectado = false;
		numPantallas--;
		return conectado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Suscripcion other = (Suscripcion) obj;
		return Objects.equals(mail, other.mail);
	}

}