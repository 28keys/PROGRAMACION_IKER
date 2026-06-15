package modelos;

public abstract class Usuario {
	private String clave;
	private int pin;
	private String nombre, apellidos;
	private String tarifa;
	protected int numeromimebros;
	protected double descuento;
	

	public Usuario(String clave, int pin, String nombre, String apellidos, String tarifa, int numeromimebros,
			double descuento) {
		super();
		this.clave = clave;
		this.pin = pin;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tarifa = tarifa;
		this.numeromimebros = numeromimebros;
		this.descuento = descuento;
	}

	public abstract double pagar();
	
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTarifa() {
		return tarifa;
	}

	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

	public int getNumeromimebros() {
		return numeromimebros;
	}

	public void setNumeromimebros(int numeromimebros) {
		this.numeromimebros = numeromimebros;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "Usuario [clave=" + clave + ", pin=" + pin + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", tarifa=" + tarifa + ", numeromimebros=" + numeromimebros + ", descuento=" + descuento + "]";
	}

	
	
	
}
