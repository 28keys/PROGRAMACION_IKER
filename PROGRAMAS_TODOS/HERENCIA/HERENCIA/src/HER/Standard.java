package HER;

import java.time.LocalDate;
import java.util.HashSet;

public class Standard extends Suscripcion implements Iker {
	private int dispositivos = 1;
	private static double precio = 8;
	private static HashSet<String> peliculas;

	public Standard(String mail, int contraseña, LocalDate fechaSuscrito) {
		super(mail, contraseña, fechaSuscrito);
	}

	public static void añadirPeliculas(HashSet<String> pelis) {
		peliculas = pelis;
	}

	@Override
	public void verPelicula() {
		numeroPeli++;
	}

	@Override
	public double cobrar() {

		double total;
		total = numeroPeli * precio;
		return total;
	}

}