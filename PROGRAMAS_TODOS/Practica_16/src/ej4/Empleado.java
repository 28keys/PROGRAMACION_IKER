package ej4;

import java.util.Objects;

public abstract class Empleado {
	private String nombre, dni, direccion, tfno;
	private int nEmple;
	protected int horas;
	private static int totalnEmple = 1000;

	public Empleado(String nombre, String dni, String direccion, String tfno) {
		this.nEmple = totalnEmple++;
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
		this.tfno = tfno;
	}
	

	public Empleado(int nEmple) {
		this.nEmple = nEmple;
	}
	/*
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (!(obj instanceof Empleado)) return false;
	 * Empleado other = (Empleado) obj; return nEmple == other.nEmple; }
	 */
	
	/*Para usar el metodo equals hace falta del hashcode si no es int o string*/

	public int getnEmple() {
		return nEmple;
	}

	public void fichar(int horas) {
		this.horas = this.horas + horas;
	}

	@Override
	public String toString() {
		return "\n" + "--> Horas relizadas: " + horas + ", Nombre = " + nombre + ", DNI = " + dni + ", Dirección = "
				+ direccion + " Telefono = " + tfno + ", Numero de Empleado = " + nEmple + "]";
	}

	abstract double pagar();

	

}