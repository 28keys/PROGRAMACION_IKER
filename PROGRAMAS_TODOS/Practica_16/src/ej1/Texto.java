package ej1;

public class Texto extends Libro {
	private String curso;
	private double descuento;
	public Texto(String titulo, String autor, String editorial, double precio, String curso, double descuento) {
		super(titulo, autor, editorial, precio);
		this.curso = curso;
		this.descuento = descuento;
	}
	@Override
	public String toString() {
		return "Texto curso = " + curso + ", descuento = " + descuento + super.toString(); 
	}
	public String getCurso() {
		return curso;
	}
	
	
}
