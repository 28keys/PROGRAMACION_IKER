package comerciales_examen;

import java.util.ArrayList;
import java.util.Arrays;

public class Comercial {

	private String nombre;
	private int numero;
	private static int numeroSecuencial = 1;
	protected int[] unidades = new int[3];
	protected double totalGanancias; // no pongo static porque guardaria el dinero de todos yo quiero de cada uno

	public Comercial(String nombre, int[] unidades) {
		super();
		this.nombre = nombre;
		this.unidades = unidades;
		this.numero = numeroSecuencial;
		numeroSecuencial++;

	}

	public String getNombre() {
		return nombre;
	}

	public int getNumero() {
		return numero;
	}

	public int[] getUnidades() {
		return unidades;
	}

	public double pagar() {
		return 0;
	}

	public double getTotalGanancias() {
		return totalGanancias;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + ", Nº = " + numero + ", Unidades vendidas = " + Arrays.toString(unidades);
	}

}
