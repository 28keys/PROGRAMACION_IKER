package ej1;

class Libro implements Comparable <Libro>{

	private String titulo, autor, editorial;
	private double precio;
	public Libro(String titulo, String autor, String editorial, double precio) {

		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "titulo= " + titulo + ", autor= " + autor + ", editorial= " + editorial + ", precio= " + precio;
	}

	public double getPrecio() {
		return precio;
	}

	@Override
	public int compareTo(Libro o) {
		return titulo.compareTo(o.titulo);
	}

	
	
	
}
