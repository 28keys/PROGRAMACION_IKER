package eXTRA_IKER;

import java.util.HashSet;

public class Transporte extends Solicitud {
	private String abono;
	private static HashSet<String> categorias = new HashSet<String>();

	public Transporte(String tipoAyuda, String id, double importe, String abono) {
		super(tipoAyuda, id, importe);
		this.abono = abono;
	}

	public static void añadirCategorias(HashSet<String> categoriasTipo) {
		categorias = categoriasTipo;

	}

	@Override
	public double calcularAyuda() throws Romero {
		if (!categorias.contains(abono)) {
			throw new Romero();
		}
		ayudasDadas++;
		return 20;
	}

}
