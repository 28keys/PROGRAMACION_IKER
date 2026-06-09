package HER;

import java.time.LocalDate;
import java.util.HashSet;

public class Standard extends Suscripcion implements Iker {
	protected int dispositivos = 1;
	private static double precio = 8;
	private static HashSet<String> peliculas;
	private static int numPelisStand = 0;
	private static int numPelisExtra = 0;

	public Standard(String mail, int contraseña, LocalDate fechaSuscrito) {
		super(mail, contraseña, fechaSuscrito);
	}

	public static void añadirPeliculas(HashSet<String> pelis) {
		peliculas = pelis;
		costeExtra+=1;
		
	}

	@Override
	public void verPelicula() {
		numPelisStand+=1;
		totalPelis+=1;
		//como sabes que pelicula es ? se lo pasas ?
	}

	@Override
	public double cobrar() {
		double total;
		total= precio * numPelisStand +  (numPelisExtra * 1.5);
		totalRecaudado+=total;
		return total;
	}

}