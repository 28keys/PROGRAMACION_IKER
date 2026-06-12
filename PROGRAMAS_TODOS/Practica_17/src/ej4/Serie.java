package ej4;

import java.util.Objects;

public class Serie implements Prestable {

	private String titulo;
	private int nTemporadas;
	private boolean prestado;
	private String genero;
	private static double totalRecaudado;

	public Serie(String titulo, int nTemporadas, boolean prestado, String genero) {
		super();
		this.titulo = titulo;
		this.nTemporadas = nTemporadas;
		this.prestado = prestado;
		this.genero = genero;
	}

	public Serie(String titulo, boolean prestado, String genero) {
		super();
		nTemporadas = 3;
		this.titulo = titulo;
		this.prestado = prestado;
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public static double getTotalRecaudado() {
		return totalRecaudado;
	}

	@Override
	public boolean prestar() {
		if (prestado = false) {
			prestado = true;
			totalRecaudado = totalRecaudado + nTemporadas * 2;
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
		return prestado = true;
	}

	@Override
	public String toString() {
		return "Serie = " + "\n" + "[titulo=" + titulo + ", nTemporadas=" + nTemporadas + ", prestado=" + prestado
				+ ", genero=" + genero + "]" + "\n";
	}

}