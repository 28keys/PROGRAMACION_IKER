package HER;

import java.time.LocalDate;

public abstract class Suscripcion implements Iker {
	private String mail;
	private int contraseña;
	private LocalDate fechaSuscrito;
	private boolean conectado;
	protected int numeroPeli;

	public Suscripcion(String mail, int contraseña, LocalDate fechaSuscrito) {
		super();
		this.mail = mail;
		this.contraseña = contraseña;
		this.fechaSuscrito = fechaSuscrito;
		conectado = false;
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

	public LocalDate getFechaSuscrito() {
		return fechaSuscrito;
	}

	public void setFechaSuscrito(LocalDate fechaSuscrito) {
		this.fechaSuscrito = fechaSuscrito;
	}

	@Override
	public String toString() {
		return "Suscripcion [mail=" + mail + ", contraseña=" + contraseña + ", fechaSuscrito=" + fechaSuscrito
				+ ", conectado=" + conectado + "]";
	}

	public abstract void verPelicula();

	public abstract double cobrar();

	@Override
	public boolean conectar() {
		conectado = true;
		return conectado;
	}

	@Override
	public boolean desconectar() {
		conectado = false;
		return conectado;
	}

}