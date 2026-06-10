package HER;

import java.time.LocalDate;
import java.util.HashSet;

public class Standard extends Suscripcion implements Iker {
	private static int dispositivos = 1;
	private static double precio = 8;
	private static HashSet<String> peliculas;
	private int numPelisExtras;
	private static int numTotalPelisExtras=0;
	public Standard(String mail, int contraseña, LocalDate fechaSuscrito) {
		super(mail, contraseña, fechaSuscrito);
		
	}

	public static void añadirPeliculas(HashSet<String> pelis) {
		peliculas = pelis;
		
	}

	@Override
	public void verPelicula(String peli) {
		if (peliculas.contains(peli)) {
			numPelisExtras++;
			numTotalPelisExtras++;
		}
		totalPelis+=1;
		//como sabes que pelicula es ? se lo pasas ?
	}

	@Override
	public double cobrar() {
		double total;
		total= precio  +  numPelisExtras * 1.5;
		totalRecaudado+=total;
		return total;
	}

	public static int getNumTotalPelisExtras() {
		return numTotalPelisExtras;
	}

	
}