package examen_comerciales_por_iker;

import java.util.HashMap;
import java.util.HashSet;

public class Comercial {
	private String nombre;
	private static int numEmple = 1;
	private HashMap<String, Integer> unidades;

	public Comercial(String nombre, HashMap<String, Integer> unidades) {
		super();
		this.nombre = nombre;
		this.numEmple = numEmple; // le asignas el 1, cuando de la vuelta el 2 suma, da la vuelta 3, suma
		this.unidades = unidades; // le pones las unidades que le pasas del main
		numEmple++;
	}
	
	

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public static int getNumEmple() {
		return numEmple;
	}



	public static void setNumEmple(int numEmple) {
		Comercial.numEmple = numEmple;
	}



	public HashMap<String, Integer> getUnidades() {
		return unidades;
	}



	public void setUnidades(HashMap<String, Integer> unidades) {
		this.unidades = unidades;
	}



	@Override
	public String toString() {
		return "Comercial [nombre=" + nombre + ", numEmple=" + numEmple + ", unidades=" + unidades + "]";
	}

}
