package ej2;

public class Alumno {

	private String nombre, apellidos;
	private int curso;
	protected double impMatricula;

	public Alumno(String nombre, String apellidos, int curso, double impMatricula) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.curso = curso;
		this.impMatricula = impMatricula;
	}

	@Override
	public String toString() {
		return "Alumno [nombre= " + nombre + ", apellidos= " + apellidos + ", curso= " + curso + ", impMatricula= "
				+ impMatricula + "]";
	}

}
