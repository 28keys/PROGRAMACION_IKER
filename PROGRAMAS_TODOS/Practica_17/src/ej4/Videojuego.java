package ej4;

import java.util.Objects;

public class Videojuego implements Prestable {
	private String titulo;
	private int horasEstimadas;
	private boolean prestado;
	private String genero;
	private String compañia;
	private static double totalRecaudado;

	public Videojuego(String titulo, int horasEstimadas, boolean prestado, String genero, String compañia) {
		super();
		this.titulo = titulo;
		this.horasEstimadas = horasEstimadas;
		this.prestado = prestado;
		this.genero = genero;
		this.compañia = compañia;
	}

	public Videojuego(String titulo, boolean prestado, String genero, String compañia) {
		super();
		horasEstimadas = 10;
		this.titulo = titulo;
		this.prestado = prestado;
		this.genero = genero;
		this.compañia = compañia;
	}

	public static double getTotalRecaudado() {
		return totalRecaudado;
	}

	public String getTitulo() {
		return titulo;
	}

	@Override
	public boolean prestar() {
		if (prestado = false) {
			prestado = true;
			totalRecaudado = totalRecaudado + horasEstimadas * 0.5;
			return true;
		} else
			System.out.println("Este producto no se puede prestar");
		return false;
	}

	@Override
	public boolean devolver() {
		if (prestado = true) {
			prestado = false;
			return true;
		} else
			System.out.println("Este producto no se puede devolver");
		return false;
		
	}

	@Override
	public boolean isEntregado() {
		return prestado;
	}

	@Override
	public String toString() {
		return "Videojuego = " + "\n" + "[titulo=" + titulo + ", horasEstimadas=" + horasEstimadas + ", prestado="
				+ prestado + ", genero=" + genero + ", compañia=" + compañia + "]" + "\n";
	}

}