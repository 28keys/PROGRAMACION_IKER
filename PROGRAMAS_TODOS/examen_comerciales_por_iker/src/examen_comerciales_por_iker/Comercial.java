package examen_comerciales_por_iker;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Comercial {
	private String nombre;
	private int numEmple;
	protected HashMap<String, Integer> unidades;
	private static int contador = 0;
	protected double ganado;

	public Comercial(String nombre, HashMap<String, Integer> unidades) {
		super();
		contador++;
		this.nombre = nombre;
		this.numEmple = contador; // le asignas el 1, cuando de la vuelta el 2 suma, da la vuelta 3, suma
		this.unidades = unidades; // le pones las unidades que le pasas del main

	}

	public abstract double pagar();

	public double getGanado() {
		return ganado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumEmple() {
		return numEmple;
	}

	public void setNumEmple(int numEmple) {
		this.numEmple = numEmple;
	}

	public HashMap<String, Integer> getUnidades() {
		return unidades;
	}

	public void setUnidades(HashMap<String, Integer> unidades) {
		this.unidades = unidades;
	}

	@Override
	public String toString() {
		return "Comercial [nombre=" + nombre + ", numEmple=" + numEmple + ", unidades=" + unidades + "importeGanado : "
				+ ganado;
	}

}
