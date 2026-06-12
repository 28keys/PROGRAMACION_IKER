package ej2;

public class Extranjero extends Alumno {
	private String paisOrigen;
	//double tasas; ?
	public Extranjero(String nombre, String apellidos, int curso, double impMatricula, String paisOrigen) {
		super(nombre, apellidos, curso, impMatricula);
		this.impMatricula = impMatricula * 1.15;
		this.paisOrigen = paisOrigen;
	}
	@Override
	public String toString() {
		return super.toString() + " Es extranjero: [paisOrigen=" + paisOrigen + "]";
	}

}
